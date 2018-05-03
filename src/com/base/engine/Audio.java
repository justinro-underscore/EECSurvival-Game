package com.base.engine;

import org.lwjgl.openal.*;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.*;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.stb.STBVorbis.stb_vorbis_decode_filename;
import static org.lwjgl.system.MemoryStack.stackMallocInt;
import static org.lwjgl.system.MemoryStack.stackPop;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.libc.LibCStdlib.free;

/**
 * Audio class to manage all audio buffers
 * For each audio file we add a buffer to the master array
 * Audio can set gain, pitch, loop, pause, direction of audio
 */
public class Audio {
    private static List<Integer> buffers = new ArrayList<>();
    private static Map<Integer, Integer> sources = new HashMap<>();

    private static long device;
    private static long context;

    private static boolean isMuted;

    /**
     * Initializes OpenAl capabilities
     */
    public static void init() {
        String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
        device = alcOpenDevice(defaultDeviceName);

        int[] attributes = {0};
        context = alcCreateContext(device, attributes);
        alcMakeContextCurrent(context);

        ALCCapabilities alcCapabilities = ALC.createCapabilities(device);
        ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);

        isMuted = false;
    }

    /**
     * Loads a sound into the buffer array
     * @param filePath File path to audio file (audio sample must be *.ogg)
     * @return the integer representing the buffer
     */
    public static int loadSound(String filePath) {
        //Allocate space to store return information from the function
        stackPush();
        IntBuffer channelsBuffer = stackMallocInt(1);

        stackPush();
        IntBuffer sampleRateBuffer = stackMallocInt(1);

        ShortBuffer rawAudioBuffer = stb_vorbis_decode_filename(filePath, channelsBuffer, sampleRateBuffer);

        //Retreive the extra information that was stored in the buffers by the function
        int channels = channelsBuffer.get();
        int sampleRate = sampleRateBuffer.get();

        //Free the space we allocated earlier
        stackPop();
        stackPop();

        //Find the correct OpenAL format
        int format = -1;
        if(channels == 1) {
            format = AL_FORMAT_MONO16;
        } else if(channels == 2) {
            format = AL_FORMAT_STEREO16;
        }

        //Request space for the buffer
        int bufferPointer = alGenBuffers();

        //Send the data to OpenAL
        alBufferData(bufferPointer, format, rawAudioBuffer, sampleRate);

        //Free the memory allocated by STB
        free(rawAudioBuffer);

        buffers.add(bufferPointer);

        return bufferPointer;
    }

    /**
     * Plays the buffer by creating a source for the audio sample
     * @param bufferPointer buffer representing audio sample
     */
    public static void playBuffer(int bufferPointer) {
        int sourcePointer = alGenSources();

        //Assign our buffer to the source
        alSourcei(sourcePointer, AL_BUFFER, bufferPointer);
        alSourcePlay(sourcePointer);

        sources.put(bufferPointer, sourcePointer);
    }

    /**
     * Pauses a specific buffer
     * @param bufferPointer buffer representing audio sample
     */
    public static void pauseBuffer(int bufferPointer) {
        alSourcePause(sources.get(bufferPointer));
    }

    /**
     * Resumes a specific buffer
     * @param bufferPointer buffer representing audio sample
     */
    public static void resumeBuffer(int bufferPointer) {
        alSourcePlay(sources.get(bufferPointer));
    }

    /**
     * Pauses all current audio samples
     */
    public static void pauseAll() {
        alSourcePausev(objectArrayToIntArray(sources.values().toArray()));
    }

    /**
     * Resumes all audio samples
     */
    public static void resumeAll() {
        alSourcePlayv(objectArrayToIntArray(sources.values().toArray()));
    }

    /**
     * Stops a specific buffer
     * @param bufferPointer buffer representing audio sample
     */
    public static void stopBuffer(int bufferPointer) {
        alSourceStop(sources.get(bufferPointer));
    }

    /**
     * Stops all current audio samples
     */
    public static void stopAll() {
        alSourceStopv(objectArrayToIntArray(sources.values().toArray()));
    }

    /**
     * Loops a specific buffer
     * @param bufferPointer buffer representing audio sample
     */
    public static void loopBuffer(int bufferPointer) {
        alSourcei(sources.get(bufferPointer), AL_LOOPING, AL_TRUE);
    }

    /**
     * Sets a specific audio sample's gain
     * @param bufferPointer buffer representing audio sample
     * @param bufferGain amt to set gain to (gain must not be negative)
     */
    public static void setBufferGain(int bufferPointer, float bufferGain) {
        alSourcef(sources.get(bufferPointer), AL_GAIN, Math.abs(bufferGain));
    }

    /**
     * Sets the gain for all audio samples
     * @param bufferGain amt to set gain to (gain must not be negative)
     */
    public static void setMasterGain(float bufferGain) {
        int[] _sources = objectArrayToIntArray(sources.values().toArray());
        for (int source : _sources) {
            alSourcef(source, AL_GAIN, Math.abs(bufferGain));
        }

        isMuted = (bufferGain == 0);
    }

    /**
     * Sets a specific audio sample's pitch
     * @param bufferPointer buffer representing audio sample
     * @param bufferPitch amt to set pitch to
     */
    public static void setBufferPitch(int bufferPointer, float bufferPitch) {
        alSourcef(sources.get(bufferPointer), AL_PITCH, bufferPitch);
    }

    /**
     * Sets an audio sample's velocity
     * @param bufferPointer buffer representing audio sample
     * @param velocity Velocity of audio
     */
    public static void setBufferVelocity(int bufferPointer, Vector2f velocity) {
        alSource3f(sources.get(bufferPointer), AL_VELOCITY, velocity.x, velocity.y, 0);
    }

    /**
     * Sets the audio sample's position in the game world
     * @param bufferPointer buffer representing audio sample
     * @param pos position of audio sample
     */
    public static void setBufferPos(int bufferPointer, Vector2f pos) {
        alSource3f(sources.get(bufferPointer), AL_POSITION, pos.x, pos.y, 0);
    }

    /**
     * Sets the direction of the audio sample in the game world
     * @param bufferPointer buffer representing audio sample
     * @param dir direction of audio sample
     */
    public static void setBufferDirection(int bufferPointer, Vector2f dir) {
        alSource3f(sources.get(bufferPointer), AL_DIRECTION, dir.x, dir.y, 0);
    }

    /**
     * Destroys all audio sources and buffers
     * Also destroys the OpenAl context and device
     */
    public static void cleanUp() {
        alDeleteBuffers(objectArrayToIntArray(buffers.toArray()));
        alDeleteSources(objectArrayToIntArray(sources.values().toArray()));

        alcDestroyContext(context);
        alcCloseDevice(device);
    }

    /**
     * Adapted from https://stackoverflow.com/questions/960431/how-to-convert-listinteger-to-int-in-java
     * by Pshemo
     * @param sources Object list
     * @return int[] of buffers
     */
    private static int[] objectArrayToIntArray(Object[] sources) {
        Integer[] src = Arrays.copyOf(sources, sources.length, Integer[].class);
        return Arrays.stream(src).mapToInt(i -> i).toArray();
    }

    /**
     * Returns if master audio is muted
     * @return isMuted status
     */
    public static boolean isMuted() {
        return isMuted;
    }
}

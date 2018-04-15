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

public class Audio {

    private static List<Integer> buffers = new ArrayList<>();
    private static Map<Integer, Integer> sources = new HashMap<>();

    private static long device;
    private static long context;

    public static void init() {
        String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
        device = alcOpenDevice(defaultDeviceName);

        int[] attributes = {0};
        context = alcCreateContext(device, attributes);
        alcMakeContextCurrent(context);

        ALCCapabilities alcCapabilities = ALC.createCapabilities(device);
        ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);

    }

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

    public static void playBuffer(int bufferPointer) {
        int sourcePointer = alGenSources();

        //Assign our buffer to the source
        alSourcei(sourcePointer, AL_BUFFER, bufferPointer);
        alSourcePlay(sourcePointer);

        sources.put(bufferPointer, sourcePointer);
    }

    public static void pauseBuffer(int bufferPointer) {
        alSourcePause(sources.get(bufferPointer));
    }

    public static void resumeBuffer(int bufferPointer) {
        alSourcePlay(sources.get(bufferPointer));
    }

    public static void pauseAll() {
        alSourcePausev(objectArrayToIntArray(sources.values().toArray()));
    }

    public static void resumeAll() {
        alSourcePlayv(objectArrayToIntArray(sources.values().toArray()));
    }

    public static void stopBuffer(int bufferPointer) {
        alSourceStop(sources.get(bufferPointer));
    }

    public static void stopAll() {
        alSourceStopv(objectArrayToIntArray(sources.values().toArray()));
    }

    public static void loopBuffer(int bufferPointer) {
        alSourcei(sources.get(bufferPointer), AL_LOOPING, AL_TRUE);
    }

    public static void setBufferGain(int bufferPointer, float bufferGain) {
        alSourcef(sources.get(bufferPointer), AL_GAIN, Math.abs(bufferGain));
    }

    public static void setBufferPitch(int bufferPointer, float bufferPitch) {
        alSourcef(sources.get(bufferPointer), AL_PITCH, bufferPitch);
    }

    public static void setBufferVelocity(int bufferPointer, Vector2f velocity) {
        alSource3f(sources.get(bufferPointer), AL_VELOCITY, velocity.x, velocity.y, 0);
    }

    public static void setBufferPos(int bufferPointer, Vector2f pos) {
        alSource3f(sources.get(bufferPointer), AL_POSITION, pos.x, pos.y, 0);
    }

    public static void setBufferDirection(int bufferPointer, Vector2f dir) {
        alSource3f(sources.get(bufferPointer), AL_DIRECTION, dir.x, dir.y, 0);
    }

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
}

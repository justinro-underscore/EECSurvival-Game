package com.base.engine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Excerpt from http://www.java-gaming.org/index.php?PHPSESSID=h31a7ct4qqlgrl1fij7857dcv5 topic=25516.0
 * By theagentd
 * Binds a buffered image to a texture id in openGl
 */

//takes a buffered image and returns an integer that is a textureID
public class TextureLoader {
    /**
     * Generate a texture ID that corresponds to the image taken in
     * @param image the buffered image to be bound to a texture ID
     * @return texture ID that openGL has binded to that image
     * @throws IOException throws IOEexception
     */
    public static int loadTexture(BufferedImage image) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();

        int[] pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);

        for(int i = 0; i < width * height; i++){
                int pixel = pixels[i];
                buffer.put((byte) ((pixel >> 16) & 0xFF));   // Red component
                buffer.put((byte) ((pixel >> 8 ) & 0xFF));   // Green component
                buffer.put((byte) ((pixel      ) & 0xFF));   // Blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF));   // Alpha component
        }

        buffer.flip();

        int textureID = glGenTextures(); //Generate texture ID
        glBindTexture(GL_TEXTURE_2D, textureID); //Bind texture ID

        //Setup wrap mode
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        //Setup texture scaling filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        //Send texel data to OpenGL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        //Return the texture ID so we can bind it later again
        return textureID;
    }
}

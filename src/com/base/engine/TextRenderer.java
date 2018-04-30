package com.base.engine;

import com.base.game.utilities.Delay;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;

public class TextRenderer
{
    private Sprite[][] stringsArr; // The sprites to be rendered, showing text
    private char[] strings; // The chars of the strings array
    private int numCharsInRow; // Number of characters that will fit per row
    private int numRows; // Number of rows that will fit in the text box
    private int stringsLength; // Length of the string being printed

    private boolean typewriterMode; // Writes the text in a typewriter fashion - one character at a time
    private int currIndex; // How much of the text should be rendered (typewriter mode only)
    private Delay indexUpdate; // The delay to update the index (typewriter mode only)

    private float x; // X coord of text box
    private float y; // Y coord of text box

    /**
     * Creates a new text box to render the text
     * @param words The words to be printed
     * @param width The width of the text box
     * @param height The height of the text box
     * @param typewriter Whether or not we are in typewriter mode
     * @param x X coord of box
     * @param y Y coord of box
     */
    public TextRenderer(String words, int width, int height, boolean typewriter, float x, float y)
    {
        numCharsInRow = width / 50; // 50 is the width/height of the alphabet sprites
        numRows = height / 50;
        stringsArr = new Sprite[numRows][numCharsInRow];
        strings = new char[numRows * numCharsInRow];
        stringsLength = words.length();
        String temp = words.toUpperCase(); // All characters must be uppercase
        if(temp.isEmpty()) // We don't want an empty string
            temp = " ";
        int tempIndex = 0; // tempIndex will follow the characters being added to the array

        // Populate the array
        for(int row = 0; row < numRows; row++)
        {
            // Wrapping text
            int endIndex = -1; // What index the current row should stop at (either a space or the end of the string)
            if(tempIndex + numCharsInRow < stringsLength) // Make sure we won't throw an out of bounds error
                endIndex = temp.substring(tempIndex, tempIndex + numCharsInRow).lastIndexOf(' '); // The string on current row

            if(endIndex < 0) // If the space wasn't found or we are at the end of the string
                endIndex = numCharsInRow;

            for(int index = 0; index < numCharsInRow; index++)
            {
                if(index <= endIndex && tempIndex < stringsLength) // Make sure we can still add to the array
                {
                    char currChar = temp.charAt(tempIndex);
                    strings[index + (row * numCharsInRow)] = currChar; // Get the characters being added

                    // Add the characters to the array
                    if ((currChar >= 'A' && currChar <= 'Z') || currChar == '!' || currChar == '?')
                        stringsArr[row][index] = new Sprite(50, 50, "res/alphabet/" + currChar + ".png");
                    else if (currChar == ' ')
                        stringsArr[row][index] = new Sprite(50, 50, "res/alphabet/SPACE.png");
                    else
                        stringsArr[row][index] = new Sprite(50, 50, "res/alphabet/ERROR.png");
                    tempIndex++;
                }
                else // Pad the end of the rows with spaces
                {
                    stringsArr[row][index] = new Sprite(50, 50, "res/alphabet/SPACE.png");
                    strings[index + (row * numCharsInRow)] = ' ';
                }
            }
        }

        typewriterMode = typewriter;
        if(typewriterMode)
        {
            currIndex = -1; // If in typewriter mode, start index out of bounds
            indexUpdate = new Delay((int)Math.pow(2, 2) * 50);
        }
        else
            currIndex = stringsLength; // If not, start index at the end so it immediately renders everything

        this.x = x;
        this.y = y;
    }

    /**
     * Sets the text speed
     * @param speed The speed at which the text should be written (1 = fast, 3 = slow)
     * @return true if in typewriter mode & speed is within [1, 3], false if not
     */
    public boolean setSpeed(int speed)
    {
        if(typewriterMode && speed >= 1 && speed <= 3) // Make sure speed is in correct range
        {
            indexUpdate.restart((int)Math.pow(speed, 3) * 20); // Restart the speed
            indexUpdate.stop(); // Make sure we don't automatically start the text
            return true;
        }
        return false;
    }

    /**
     * Starts the typewriter delay
     * @return true if in typewriter mode, false if not
     */
    public boolean startTypewriterDelay()
    {
        if(typewriterMode)
        {
            indexUpdate.start();
            return true;
        }
        return false;
    }

    /**
     * Stops the typewriter delay
     * @return true if in typewriter mode, false if not
     */
    public boolean pauseTypewriterDelay()
    {
        if(typewriterMode)
        {
            indexUpdate.stop();
            return true;
        }
        return false;
    }

    /**
     * Updates the text renderer
     */
    public void update()
    {
        if(currIndex < stringsLength) // If we are not showing the whole string
        {
            if(InputHandler.isKeyDown(GLFW_KEY_ENTER)) // If the player presses enter, speed up the text
                indexUpdate.restart(5);

            if(indexUpdate.isOver()) // Add a new character to the string
            {
                currIndex++;
                indexUpdate.start();
            }
        }
    }

    /**
     * Renders all of the characters
     */
    public void render()
    {
        int tempIndex = 0; // Will follow what is printed
        for(int row = 0; row < numRows; row++)
        {
            for(int index = 0; index < numCharsInRow; index++)
            {
                if(tempIndex <= currIndex) // Prints whatever should be written
                {
                    stringsArr[row][index].render(x + ((index - (numCharsInRow / 2)) * 50), y + (((numRows / 2) - row) * 50));
                    if(strings[index + (row * numCharsInRow)] != ' ') // If we are not at the end, increase tempIndex
                        tempIndex++;
                }
            }
        }
    }
}

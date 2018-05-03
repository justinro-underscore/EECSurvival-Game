package com.base.engine;

import com.base.game.utilities.Delay;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;

/**
 * Text renderer renders a string into a renderable object
 */
public class TextRenderer
{
    private final Sprite[] ALPHABET = SpriteRetriever.loadSprite("res/alphabet/alphabet.png", 0, 0, 46, 50, 50);
    private final int SPACE = 26;
    private final int PERIOD = 27;
    private final int EXCLAMATION = 28;
    private final int QUESTION = 29;
    private final int ERROR = 30;
    private final int COMMA = 31;
    private final int APOSTRAPHE = 32;
    private final int COLON = 33;
    private final int PLUS = 34;
    private final int MINUS = 35;
    private final int DIGIT_START = 36;

    private Sprite[][] stringsArr; // The sprites to be rendered, showing text
    private char[] strings; // The chars of the strings array
    private int fontSize; // The size of the letter sprites
    private boolean centered; // Whether or not the text should be centered NOTE: Text can only be centered if there is only one line
    private int width; // Width of the text box (used with centered)
    private int height; // Height of the text box (used with centered)
    private int numCharsInRow; // Number of characters that will fit per row
    private int numRows; // Number of rows that will fit in the text box
    private int stringsLength; // Length of the string being printed

    private boolean typewriterMode; // Writes the text in a typewriter fashion - one character at a time
    private int currIndex; // How much of the text should be rendered (typewriter mode only)
    private Delay indexUpdate; // The delay to update the index (typewriter mode only)

    private boolean isOver; // If the text is fully displayed
    private float x; // X coord of text box
    private float y; // Y coord of text box

    /**
     * Creates a new text box to render the text
     * @param words The words to be printed
     * @param width The width of the text box
     * @param height The height of the text box
     * @param center boolean if center text
     * @param font The size of the font (1 = small, 2 = medium, 3 = large) default to medium
     * @param typewriter Whether or not we are in typewriter mode
     * @param x X coord of box
     * @param y Y coord of box
     */
    public TextRenderer(String words, int width, int height, boolean center, int font, boolean typewriter, float x, float y)
    {
        switch(font)
        {
            case 1: fontSize = 10; break;
            case 3: fontSize = 50; break;
            case 2:
            default: fontSize = 25; break;
        }
        this.width = width;
        this.height = height;
        numCharsInRow = width / fontSize; // Get the number of letters in a row
        numRows = height / fontSize;
      
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
                    if (currChar >= 'A' && currChar <= 'Z')
                        stringsArr[row][index] = ALPHABET[currChar - 'A'];
                    else if (currChar == ' ')
                        stringsArr[row][index] = ALPHABET[SPACE];
                    else if(currChar == '.')
                        stringsArr[row][index] = ALPHABET[PERIOD];
                    else if(currChar == '!')
                        stringsArr[row][index] = ALPHABET[EXCLAMATION];
                    else if(currChar == '?')
                        stringsArr[row][index] = ALPHABET[QUESTION];
                    else if(currChar == ',')
                        stringsArr[row][index] = ALPHABET[COMMA];
                    else if(currChar == '\'')
                        stringsArr[row][index] = ALPHABET[APOSTRAPHE];
                    else if(currChar == ':')
                        stringsArr[row][index] = ALPHABET[COLON];
                    else if(currChar == '+')
                        stringsArr[row][index] = ALPHABET[PLUS];
                    else if(currChar == '-')
                        stringsArr[row][index] = ALPHABET[MINUS];
                    else if(currChar >= '0' && currChar <= '9')
                        stringsArr[row][index] = ALPHABET[DIGIT_START + Integer.parseInt("" + currChar)];
                    else
                        stringsArr[row][index] = ALPHABET[ERROR];

                    tempIndex++;
                }
                else // Pad the end of the rows with spaces
                {
                    stringsArr[row][index] = ALPHABET[SPACE];
                    strings[index + (row * numCharsInRow)] = ' ';
                }
            }
        }

        centered = (numRows == 1 && center);

        typewriterMode = typewriter;
        if(typewriterMode)
        {
            currIndex = -1; // If in typewriter mode, start index out of bounds
            indexUpdate = new Delay((int)Math.pow(2, 2) * 50);
          
            isOver = false;
        }
        else
        {
            currIndex = stringsLength; // If not, start index at the end so it immediately renders everything
            isOver = true;
        }

        this.x = x;
        this.y = y;
    }

    /**
     * Sets the text speed
     * @param speed The speed at which the text should be written (1 = fast, 3 = slow)
     * @return true if in typewriter mode and speed is within [1, 3], false if not
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

                if(currIndex == stringsLength)
                    isOver = true;
                else
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
                    if(centered)
                    {
                        stringsArr[row][index].render(x + ((width / 2.0f) - ((stringsLength / 2) - index + (stringsLength % 2 == 0 ? 0 : 0.5f)) * fontSize),
                                y + (height - fontSize) / 2.0f, fontSize, fontSize);
                    }
                    else
                        stringsArr[row][index].render(x + (index * fontSize), y + ((numRows - (row + 1)) * fontSize), fontSize, fontSize);

                    if(strings[index + (row * numCharsInRow)] != ' ') // If we are not at the end, increase tempIndex
                        tempIndex++;
                }
            }
        }
    }

    /**
     * Returns whether or not the text is fully displayed
     * @return whether or not the text is fully displayed
     */
    public boolean getIsOver()
    {
        return isOver;
    }
}

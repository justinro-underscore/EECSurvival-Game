package com.base.game.gameobject.entity;

import com.base.engine.Audio;
import com.base.engine.Physics;
import com.base.engine.GameObject;
import com.base.game.gameobject.item.ConsumableItem;
import com.base.game.gameobject.projectile.Projectile;
import com.base.game.Game;
import com.base.game.scenes.Dialog;
import com.base.game.scenes.Event;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public abstract class Character extends GameObject
{
    private ArrayList<Dialog> dialogs;
    private boolean startDialog;

    private int hitSfx;
    
    protected Stats stats;

    /**
     * Abstract constructor for Character
     * @param xPos x-coordinate of the render
     * @param yPos y-coordinate of the render
     * @param numFrames number of frames in the animation
     * @param width width
     * @param height height
     * @param speed the speed of the character
     * @param health starting health of the character
     * @param attackDamage how much damage the character deals
     * @param isBoss boolean if obj is boss
     * @param image image for character
     */
    protected Character(float xPos, float yPos, int numFrames, int width, int height, float speed, int health, int attackDamage, boolean isBoss,String image) {
        init(xPos, yPos, 0, 0, numFrames,isBoss,image,width,height,width,height); // Call super initialize method

        dialogs = new ArrayList<>();
        startDialog = false;

        hitSfx = Audio.loadSound("res/audio/hit_sfx.ogg");

        stats = new Stats(speed, health, attackDamage);
    }

    @Override
    /**
     * Renders the character
     */
    public void render() {
        super.render();

        if (!dialogs.isEmpty() && startDialog)
            getCurrDialog().render();
    }

    /**
     * Updates the dialog box when text is appearing
     */
    public void updateDialog() {
        if (!startDialog)
            return;

        if (getCurrDialog().isOver()) {
            dialogs.remove(0);
        }

        getCurrDialog().update();
    }

    /**
     * sets the start dialog flag to true
     */
    public void startDialog() {
        startDialog = true;
    }

    /**
     * Sets the start dialog flag to false
     */
    public void stopDialog() {
        startDialog = false;
    }

    /**
     * Allows us to add a new dialog to a character
     * @param dialog The script of what is going to be said
     */
    public void addDialog(Dialog dialog) {
        dialogs.add(dialog);
    }

    /**
     * Gets the dialog we are currently on
     * @return the current dialog
     */
    public Dialog getCurrDialog() {
        return dialogs.get(0);
    }

    /**
     * Creates a new dialog event for a character
     * @param content The content of the dialog
     * @param fontSize The size of the font
     * @return A dialog event
     */
    public Event createDialogEvent(String content, int fontSize) {
        Callable<Boolean> callable;
        Dialog dialog = new Dialog(content, fontSize);

        addDialog(dialog);
        callable = () -> {
            startDialog();
            updateDialog();

            if (getCurrDialog().isOver()) {
                stopDialog();
                return true;
            }

            return false;
        };

        return new Event("dialog", callable);
    }

    /**
     * Checks to see if the character has collided with close objects
     */
    protected void checkCharacterCollision()
    {
        ArrayList<GameObject> closeObjects = Game.game.getCurrLevel().getCloseObjects(this, 5); // Get any objects close to the character (cuts down on load time)

        for(GameObject obj : closeObjects)
        {
            if(Physics.checkCollision(this, obj)) // If the character is touching a GameObject
            {
                if(obj.getBoss()==true && this.getBoss()==true)
                {}
                else if(obj instanceof ConsumableItem) // If the object is a consumable item...
                {
                    if(stats.getHealth() + ((ConsumableItem) obj).getAddedHealth() <= stats.getMaxHealth()){
                        gainHealth(((ConsumableItem) obj).getAddedHealth()); // Gain specified amount of health from consumable
                    }
                    else{
                        stats.setHealth(stats.getMaxHealth());
                    }
                    obj.remove(); // Delete the consumable
                }
                else if(obj.getBoss()==false && this.getBoss()==false){

                }
                else if(obj instanceof Projectile) // If the object is a projectile...
                {
                    Audio.playBuffer(hitSfx);

                    loseHealth(((Projectile) obj).getDamage()); // Lose specified amount of health
                    obj.remove(); // Delete the projectile
                }
                checkCharacterCollisionSpecific(obj); // Go to subclass specific collisions
            }
        }
    }

    /**
     * Take a specified amount of health off of character's health bar
     * @param hit amount of damage to take
     */
    protected void loseHealth(int hit)
    {
        stats.setHealth(stats.getHealth() - hit);
        if(stats.getHealth() <= 0) // If health drops below 0
        {
            stats.setHealth(0);
            stats.setIsDead(true); // You dead, son
        }
    }

    /**
     * Add a specified amount to the character's heath
     * @param healthGain amount of health to gain
     */
    protected void gainHealth(int healthGain)
    {
        stats.setHealth(stats.getHealth() + healthGain);
        //TODO: add checking for max health
    }

    /**
     * Set the character's health to the max health
     */
    public void setMaxHealth() { stats.setMaxHealth(); }

    /**
     * Returns the character's health
     * @return health of character
     */
    public int getHealth()
    {
        return stats.getHealth();
    }

    /**
     * Returns the character's max health
     * @return max health of character
     */
    public int getMaxHealth()
    {
        return stats.getMaxHealth();
    }

    /**
     * Subclasses should specify what specific things they collide with
     * @param obj the object being collided with
     */
    abstract protected void checkCharacterCollisionSpecific(GameObject obj);

    /**
     * Characters have different things happen when they die
     */
    abstract protected void checkDeath();

    /**
     * Characters should implement their own attack methods
     */
    abstract protected void attack();
}

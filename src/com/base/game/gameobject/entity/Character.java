package com.base.game.gameobject.entity;

import com.base.engine.Physics;
import com.base.engine.Sprite;
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
    
    protected Stats stats;

    /**
     * Abstract constructor for Character
     * @param xPos x-coordinate of the sprite
     * @param yPos y-coordinate of the sprite
     * @param width width
     * @param height height
     * @param imgPath file path to the image representing the sprite
     * @param speed the speed of the character
     * @param health starting health of the character
     * @param attackDamage how much damage the character deals
     */
    protected Character(float xPos, float yPos, int width, int height, String imgPath, float speed, int health, int attackDamage, boolean isBoss) {
        init(xPos, yPos, width, height, imgPath,isBoss); // Call super initialize method

        dialogs = new ArrayList<>();
        startDialog = false;
        
        stats = new Stats(speed, health, attackDamage);
    }

    @Override
    public void render() {
        super.render();

        if (!dialogs.isEmpty() && startDialog)
            getCurrDialog().render();
    }

    public void updateDialog() {
        if (!startDialog)
            return;

        if (getCurrDialog().isOver()) {
            dialogs.remove(0);
        }

        getCurrDialog().update();
    }

    public void startDialog() {
        startDialog = true;
    }

    public void stopDialog() {
        startDialog = false;
    }

    public void addDialog(Dialog dialog) {
        dialogs.add(dialog);
    }

    public Dialog getCurrDialog() {
        return dialogs.get(0);
    }

    public Event createDialogEvent(String content) {
        Callable<Boolean> callable;
        Dialog dialog = new Dialog(content);

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
                    obj.remove(); // Delete the consumable
                }
                else if(obj.getBoss()==false && this.getBoss()==false){

                }
                else if(obj instanceof Projectile) // If the object is a projectile...
                {
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
     * Returns the character's health
     * @return health of character
     */
    public int getHealth()
    {
        return stats.getHealth();
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

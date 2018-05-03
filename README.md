# EECSurvival 

### Downloading
We have ported this over to be easily runnable from a jar. You can download it at: 
https://www.dropbox.com/s/lfuz3lu59brwecv/EECSurvival_Alpha.zip?dl=0

### Documentation
Test documentation goes over how the test suite is implemented.
Game documentation discusses the implementation of the game in terms of trasition states and game objects.
Engine documentation discusses the behind the scenes rendering and display management.

## Overview

EECSurvival is a bullet hell style game that follows an lowly EECS student within the University of Kansas. Battle through the thought provoking and adrenaline pumping story as the student overcomes heart break, immense pain, and the risk of a failed life path, to finally end as a self fulfilled successful student, no, a true programmer. 

Coded and Designed by: Varun Chadha, Justin Roderman, Harrison Luo, Alex Kunz, and Noah Brabec

![Alt text][MainMenu]

## Controls
The controls for this game are very straightforward. To move up press W, to move left press A, to move right press D, and to move down press S. To shoot you can either tap or hold down the space bar. There is also a sprint feature. To sprint you need to hold down shift and then a movement and the player will move drastically faster, though you will be unable to shoot. To transition between levels, a door will appear at the top of the screen. Simply move into it and you will be taken to the next level. 

## Levels
EECSurvival takes place over 5 levels of heated combative gameplay. The student starts in 168, and then makes their way from 268, 368, 388, and ends at 448. Each level (or class) has a boss that is a model of the professor. EECS 168 and 268 are commanded by Professor John Gibbons. EECS 368 is defended by Professor Paul Kline. EECS 388 features Professor Gary Minden. And finally, EECS 448 is led by Professor Alex Bardas.

#### Level 1 (EECS 168)
Programming 1. Where every great Computer Science major at KU began. When the player enters the level there is a small dialogue sequence that happens. Dr. Gibbons says "insert dialogue here" and then says "insert more dialogue here". From there the player returns to the starting position in the bottom middle of the screen and combat ensues. Professor Gibbons has three attacks. A wall attack, and burst attack, and a targeted attack. The wall attack is a straight line of projectiles that all falls down at the same rate. The burst attack is a half circle of projectiles that spreads throughout the screen. The targeted attack is a singular projectile that shoots towards the position that the player was standing when the projectile was shot. After defeating Dr. Gibbons in 168, you have to face him again in 268.

![Alt text][LevelOne]

#### Level 2 (EECS 268)
Programming 2. Where every great Computer Science major at KU begins to question their major decision. The level is very similar to level 1, and Professor Gibbons even has the same attacks, but the attacks are stronger and shoot more frequently. The projectiles also travel at a higher speed. 

![Alt text][LevelTwo]

#### Level 3 (EECS 388)
Embedded Systems. Where students get their first taste of the lower level C language, and even get to delve into the registry filled maze that is assembly. Professor Minden is the boss of this level and he has 2 NEW attacks. The four corners attack and the heat seeking attack. The four corners attack shoots projectiles in the shape of an X across the screen. The heat seeking attack is similar to the targeted attack but on top of the other functionality it will continue to follow the player around the screen. 

![Alt text][LevelThree]

#### Level 4 (EECS 368)
Paradigms of Programming Languages. "Its just a fun class where you learn and have fun" -Varun Chadha. The beauty of Haskell paired with the elgency of JavaScript makes this class a step above the rest. The boss for this level is Paul Kline. He also has three attacks. There is the left to right wall attack, the right to left wall attack, and a burst attack. The burst attack is the same from levels 1 and 2. The left to right wall attack is the same as the normal wall attack, but it comes from the left of the screen, the right to left wall attack is the same as well but it comes from the right side of the screen.   

![Alt text][LevelFour]

#### Level 5 (EECS 448)
Software Engineering 1. Prepare to get to know 3-4 people better than you wanted to because thats what this class is all about, well, I guess there is some coding in there too. Professor Bardas is the boss of this level. He has every attack previously mentioned except for the basic wall attack. After defeating Bardas he presents the student with a diploma and you are transported to the game over screen. 

![Alt text][LevelFive]

## Testing 

From the main menu you will be able to see 3 buttons that are outlined with a box that says testing. Test Suite runs the integration and unit test and prints the results of those tests to a log file. Test Manual is a form of acceptance testing where the user has full control of the players actions.
Test Automated will do the same thing as Manual,excpet it just feeds the character random inputs and if the game ever crashes in this state the key strokes will be saved into a log file. 

## Konami Code
This game has a cheat code! To instantly kill the boss (you dirty cheater you) just enter: up up down down.

[LevelOne]: Documentation/UGPics/LevelOne.png
[LevelTwo]: Documentation/UGPics/LevelTwo.png
[LevelThree]: Documentation/UGPics/LevelThree.png
[LevelFour]: Documentation/UGPics/LevelFour.png
[LevelFive]: Documentation/UGPics/LevelFive.png
[TestManual]: Documentation/UGPics/TestManual.png
[TestAutomated]: Documentation/UGPics/TestAutomated.png
[MainMenu]: Documentation/UGPics/MainMenu.png

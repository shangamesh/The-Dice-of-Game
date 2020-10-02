# The-Dice-of-Game

The "Game of Dice" is a multiplayer game where N players roll a 6 faced dice in a round-robin
fashion. Each time a player rolls the dice their points increase by the number (1 to 6) achieved
by the roll.

As soon as a player accumulates M points they complete the game and are assigned a rank.
Remaining players continue to play the game till they accumulate at least M points. The game
ends when all players have accumulated at least M points.

#### Rules of the game
- The order in which the users roll the dice is decided randomly at the start of the game.
- If a player rolls the value "6" then they immediately get another chance to roll again and move
ahead in the game.
- If a player rolls the value "1" two consecutive times then they are forced to skip their next turn
as a penalty.


#### Implementation Details
- Implement a standalone program in your favorite programming language which takes the
values N (number of players) and M (points of accumulate) as command line arguments.
- Name the players as Player-1 to Player-N and randomly assign the order in which they
will roll the dice.
- When it's the turn for Player-X to roll the dice prompt a message like “Player-3 its your
turn (press ‘r’ to roll the dice)
- Randomly simulate a dice roll, display the points achieved and add the points to the
user’s score.
- Print the current rank table which displays the points of all users and their rank after
each roll.
- If the user gets another chance because they rolled a ‘6’ or they are penalised because
they rolled ‘1’ twice consecutively then print appropriate message on standard output to
inform the user.
- If a user completes the game, print an appropriate message on the output displaying
their rank.


#### Prerequisite :
- Java 1.8 needs to be installed in machine


#### Steps to execute :

1. Download the runnbale jar file named as "TheGameOfDice.jar" from folder "Executables"
2. Run the follow command terminal/console/powershell

   - Syntax :
   java -jar TheGameOfDice.jar NoOfPlayers MaximumPointToWin

   - Example :
   java -jar TheGameOfDice.jar 3 10
   
3. Game starts now!


#### Sample Output :
```
Players List  - [Player-1, Player-2, Player-3]
Welcome to Game of Dice!!!
-----------------------------------------------------------------------------
Points to  Win the game  - 10
Order of players to Play the game - [Player-2, Player-3, Player-1]
Game starts now....3...2...1...Go
-----------------------------------------------------------------------------
Player-2 its your turn,  (Press ‘r’ to roll the dice) 
r
Points achieved by - Player-2 is 6
Lucky roll...!
-----------------------------------------------------------------------------
Players      Score     Rank       Completed Game
-----------------------------------------------------------------------------
Player-2     6          1           false
-----------------------------------------------------------------------------
Player-2 its your turn,  (Press ‘r’ to roll the dice) 
r
Points achieved by - Player-2 is 4
Congrats Player-2 Completes the Game!!!
-----------------------------------------------------------------------------
Players      Score     Rank       Completed Game
-----------------------------------------------------------------------------
Player-2    10          1            true
-----------------------------------------------------------------------------
Player-3 its your turn,  (Press ‘r’ to roll the dice) 
r
Points achieved by - Player-3 is 1
-----------------------------------------------------------------------------
Players      Score     Rank       Completed Game
-----------------------------------------------------------------------------
Player-3     1          2           false
Player-2    10          1            true
-----------------------------------------------------------------------------
Player-1 its your turn,  (Press ‘r’ to roll the dice) 
r
Points achieved by - Player-1 is 6
Lucky roll...!
-----------------------------------------------------------------------------
Players      Score     Rank       Completed Game
-----------------------------------------------------------------------------
Player-1     6          2           false
Player-3     1          3           false
Player-2    10          1            true
-----------------------------------------------------------------------------
Player-1 its your turn,  (Press ‘r’ to roll the dice) 
r
Points achieved by - Player-1 is 4
Congrats Player-1 Completes the Game!!!
-----------------------------------------------------------------------------
Players      Score     Rank       Completed Game
-----------------------------------------------------------------------------
Player-1    10          2            true
Player-3     1          3           false
Player-2    10          1            true
-----------------------------------------------------------------------------
Player-3 its your turn,  (Press ‘r’ to roll the dice) 
r
Points achieved by - Player-3 is 4
-----------------------------------------------------------------------------
Players      Score     Rank       Completed Game
-----------------------------------------------------------------------------
Player-1    10          2            true
Player-3     5          3           false
Player-2    10          1            true
-----------------------------------------------------------------------------
Player-3 its your turn,  (Press ‘r’ to roll the dice) 
r
Points achieved by - Player-3 is 3
-----------------------------------------------------------------------------
Players      Score     Rank       Completed Game
-----------------------------------------------------------------------------
Player-1    10          2            true
Player-3     8          3           false
Player-2    10          1            true
-----------------------------------------------------------------------------
Player-3 its your turn,  (Press ‘r’ to roll the dice) 
r
Points achieved by - Player-3 is 6
Congrats Player-3 Completes the Game!!!
Game Over
-----------------------------------------------------------------------------
Players      Score     Rank       Completed Game
-----------------------------------------------------------------------------
Player-1    10          2            true
Player-3    14          3            true
Player-2    10          1            true
-----------------------------------------------------------------------------
```

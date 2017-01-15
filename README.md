# FinalProject
This is our bomb final project..its mad finesse

Brief Description:
HELLO this is the final project of RahmanNoodLees!!
Our game is terminal based Scrabble, and this is now our least favorite board game.
There are 2 modes in Scrabble, a 2 player mode and an AI mode where the computer will complete words and it is much smarter than you think. In the 2 PLayer mode there is also a timed section where a player gets 1 minute 30 seconds per turn and if they fail to input a word in the alotted time, then they lose their turn and it moves on to the next player.

List of Working features / Things I should test:
-only one word can be added at a time
-only real words can be added
-the first letter placed must be in the center of the grid, if not the user is told to do so
-players can only build off existing letters on the grid, the same goes for the ai mode
-if the computer cannot generate any words based off their rack and the existing letters, the screen will say the computer has given up
-once the score reaches 50 of either player (including the computer), the screen will display who won and will exit the program
-in the timed mode, once the player's turn reaches 1.5 min it will forfeit and move on to the next player's turn

List of unresolved bugs:
-sometimes the computer takes a long time to add a word or may crash

Directions on how to compile and run your code:
1. compile the Scrabble.java file (javac Scrabble.java)
2. run the Scrabble file (java Scrabble)

Directions on how to use your program:
1. The beginning message will show the grid and ask which mode to play. Type exactly which mode you want to play, it is case sensitive. If you wish to play 2Player mode, type in exactly "2Player" and if you wish to play Computer mode, type in exactly "Computer".
2. If 2 Player mode is chosen, the next message will ask for which type of game to play: "Normal" or "Timed". the next message will appear ask to input letters in a format of "A 1 1". The game starts off with player 1 playing.
   a. In "Normal" mode, after Player 1 submits their word it will be reviewed and adds the scores and moves on to Player 2's turn. It will ask for player 2's input and after player 2 submits it will add it to player 2's score and move on to player 1 and so on and so forth.
   b. In "Timed" mode, the same rules and playing process applies however if either player fails to submit a valid word within 1.5 minutes it goes on to the next Player's turn. 
3.If Computer mode is chosen, the next message will appear ask to input letters in a format of "A 1 1". The game starts off with player 1 playing. After player 1 submits a word, it will input the computer's turn and will automatically fill the word in the grid and display it to the viewer or will show the error message that the computer forfeits its turn and will immediately go to Player 1's turn and ask for their input.
4. In all modes, once the score hits 50 of any player whether it be Player 1, Player 2, or the computer, the game will say which player won and exit the program.
5. The END! If you wish to play more, just run the game again (java Scrabble)




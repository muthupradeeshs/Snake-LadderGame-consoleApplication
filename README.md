# Snake-LadderGame-consoleApplication
The Ladder Game is a console-based application that simulates a board game where players navigate through a board with snakes and ladders. The primary goal is for players to reach the end of the board while facing challenges and opportunities presented by snakes and ladders.

Code Structure
Data Transfer Objects (DTO)
Ladder:

Represents a ladder with a bottom and top position.
Player:

Represents a player with a name and position on the board.
Snake:

Represents a snake with a head and tail position.
LadderGame Class
Attributes:

boardSize: Size of the game board.
snakes: List of Snake objects.
ladders: List of Ladder objects.
players: List of Player objects.
Methods:

initializeGame():

Initializes the game with user-defined parameters.
rollDice(Player player):

Simulates rolling a dice and returns the result.
movePlayer(Player player, int steps):

Moves the player on the board considering snakes and ladders.
isGameWon(Player player):

Checks if a player has won the game.
playGame():

Main game loop where players take turns rolling the dice.
findMinimumSteps(int target, List<Snake> snakes, List<Ladder> ladders):

Finds the minimum steps required to reach a target position.
main(String[] args):

Entry point; allows the user to play the game or find minimum steps.
Usage
Run the application.
Enter the board size, number of snakes, ladders, and players.
Choose to play the game or find the minimum steps.
If playing, roll the dice and navigate the board.
If finding minimum steps, the program calculates and displays the result.

package laddergame;

import java.util.*;
import laddergame.dto.*;

public class LadderGame {
	private static int boardSize;
	private static List<Snake> snakes;
	private static List<Ladder> ladders;
	private List<Player> players;
	static Scanner scanner = new Scanner(System.in);

	public LadderGame() {
		initializeGame();
	}

	private void initializeGame() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the Size of the Board: ");
		boardSize = scanner.nextInt();

		snakes = new ArrayList<>();
		ladders = new ArrayList<>();
		players = new ArrayList<>();
		System.out.println("Enter the number of Snakes: ");
		int numSnakes = scanner.nextInt();
		for (int i = 0; i < numSnakes; i++) {
			System.out.println("Enter Snake " + (i + 1) + " head and tail : ");
			int head = scanner.nextInt();
			int tail = scanner.nextInt();
			snakes.add(new Snake(head, tail));
		}
		System.out.println("Enter the number of Ladders: ");
		int numLadders = scanner.nextInt();
		for (int i = 0; i < numLadders; i++) {
			System.out.println("Enter Ladder " + (i + 1) + " bottom and top : ");
			int bottom = scanner.nextInt();
			int top = scanner.nextInt();
			ladders.add(new Ladder(bottom, top));
		}
		System.out.println("Enter the number of Players: ");
		int numPlayers = scanner.nextInt();
		for (int i = 0; i < numPlayers; i++) {
			System.out.println("Enter Player " + (i + 1) + " name: ");
			String playerName = scanner.next();
			players.add(new Player(playerName));
		}
	}

	private int rollDice(Player player) {
//		System.out.println(player.getName() + "press 1 for roll dice : ");
//		int choice = scanner.nextInt();
//		if (choice == 1)
			return new Random().nextInt(6) + 1;
//		else
//			rollDice(player);
//		return 0;
	}

	private void movePlayer(Player player, int steps) {
		int currentPosition = player.getPosition();
		int newPosition = currentPosition + steps;
		for (Snake snake : snakes) {
			if (snake.getHead() == newPosition) {
				newPosition = snake.getTail();
				break;
			}
		}
		for (Ladder ladder : ladders) {
			if (ladder.getBottom() == newPosition) {
				newPosition = ladder.getTop();
				break;
			}
		}

		if (newPosition <= boardSize * boardSize) {
			player.setPosition(newPosition);
		}
	}

	private boolean isGameWon(Player player) {
		return player.getPosition() == boardSize * boardSize;
	}

	public void playGame() {
		while (true) {
			for (Player player : players) {
				int diceRoll = rollDice(player);
				movePlayer(player, diceRoll);
				System.out.println(player.getName() + " rolled a " + diceRoll + " and moved from "
						+ (player.getPosition() - diceRoll) + " to " + player.getPosition() + ".");
				if (isGameWon(player)) {
					System.out.println(player.getName() + " win");
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		LadderGame game = new LadderGame();
		System.out.println("1-->Play");
		System.out.println("2-->FindMinimumsteps");
		int ch=scanner.nextInt();
		if(ch==1)
		game.playGame();
		if(ch==2)
		{
			int n=findMinimumSteps(boardSize*boardSize,snakes,ladders);
			System.out.println("Minimum Steps : "+n);
		}
	}
	private static int findMinimumSteps(int target, List<Snake> snakes, List<Ladder> ladders) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(0); 
        visited.add(0);

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int currentPosition = queue.poll();

                if (currentPosition == target) {
                    return steps;
                }

                for (int diceRoll = 1; diceRoll <= 6; diceRoll++) {
                    int newPosition = currentPosition + diceRoll;

                    for (Snake snake : snakes) {
                        if (snake.getHead() == newPosition) {
                            newPosition = snake.getTail();
                            break;
                        }
                    }

                    for (Ladder ladder : ladders) {
                        if (ladder.getBottom() == newPosition) {
                            newPosition = ladder.getTop();
                            break;
                        }
                    }

                    if (newPosition <= boardSize * boardSize && !visited.contains(newPosition)) {
                        queue.offer(newPosition);
                        visited.add(newPosition);
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}

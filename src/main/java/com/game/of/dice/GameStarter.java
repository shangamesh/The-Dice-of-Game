package com.game.of.dice;

import com.game.of.dice.service.GameService;
import com.game.of.dice.utility.GameServiceHelper;

/**
 * “The Game of Dice".The "Game of Dice" is a multi-player game where N players
 * roll a 6 faced dice in a round-robin fashion. Each time a player rolls the
 * dice their points increase by the number (1 to 6) achieved by the roll.As
 * soon as a player accumulates M points they complete the game and are assigned
 * a rank. Remaining players continue to play the game till they accumulate at
 * least M points. The game ends when all players have accumulated at least M
 * points.
 * 
 * @author shangamesh.t
 *
 */
public class GameStarter {
    public static void main(String[] arguments) {
	// Getting the inputs via Command line arguments
	int noOfPlayers = Integer.parseInt(arguments[0]);
	int pointToWinTheGame = Integer.parseInt(arguments[1]);
	// Creating an object for GameService
	GameService gameService = new GameService(noOfPlayers, pointToWinTheGame, new GameServiceHelper());
	// Starting the game
	gameService.startGame();
    }
}

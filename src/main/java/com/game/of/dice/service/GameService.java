package com.game.of.dice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.game.of.dice.constants.GameConstants;
import com.game.of.dice.model.PlayerScoreModel;
import com.game.of.dice.utility.GameServiceHelper;

/**
 * This is service class where all the functionalities of game is written. This
 * class is having dependency with classes GameServiceHelper & GameConstants
 * 
 * @author shangamesh.t
 *
 */
public class GameService {

    private final int noOfPlayers;
    private final int pointToWinTheGame;
    private final GameServiceHelper gameServiceHelper;

    /**
     * Parameterized constructor
     * 
     * @param noOfPlayers
     * @param pointToWinTheGame
     * @param gameServiceHelper
     */
    public GameService(int noOfPlayers, int pointToWinTheGame, GameServiceHelper gameServiceHelper) {
	this.noOfPlayers = noOfPlayers;
	this.pointToWinTheGame = pointToWinTheGame;
	this.gameServiceHelper = gameServiceHelper;
    }

    /*
     * Method used to start the game 
     * Steps to understand the Logic: 
     * 1. Get the Player list in shuffled order
     * 2. Roll the dice for current player if user press key 'r', else show message to re-type the correct key
     * 3. Get the point achieved by current player & add the point to score for player & display score table
     * 4. If point is 6, then repeat Step 2 to 3 for same player
     * 5. If point is 1 & previously scored point is also 1, then mark the current player to skip next turn
     * 6. Print score table
     * 7. If player reaches maximum point to win, remove the player from player list & mark the player as winner with rank 
     * 8. Validate the playerIndex reached playersOrderListSize, if so re-start the iteration by making playerIndex as 0
     * 9. Repeat the steps until all players scored maximum point to win
     * 10. Stop the game if Step-9 is achieved
     */
    public void startGame() {
	// Getting player list in shuffled order
	List<String> playersOrderList = gameServiceHelper.getPlayersListInWithShuffle(noOfPlayers);
	// Printing the welcome note about game
	gameServiceHelper.printGameStartingMessage(pointToWinTheGame, playersOrderList);
	// Variable declaration to track winner rank, player index & score table
	int playerIndex = 0;
	int winnerRank = 0;
	Map<String, PlayerScoreModel> scoreTable = new HashMap<String, PlayerScoreModel>();
	// Scanner to get user input from console
	Scanner scanner = new Scanner(System.in);
	// Game iteration starts here
	while (true) {
	    // Getting the name & score details of the current player
	    String currentPlayer = playersOrderList.get(playerIndex);
	    PlayerScoreModel playerScoreModel = scoreTable.get(currentPlayer);
	    // Checking if current player got any penalty, if so skip the turn of the player
	    if (playerScoreModel == null || (playerScoreModel != null && !playerScoreModel.isPenalty())) {
		// Printing message to player to roll the dice
		gameServiceHelper.printMessage(currentPlayer + GameConstants.ITS_YOUR_TURN_PRESS_R_TO_ROLL_THE_DICE);
		// Check if user enter key 'r' else ask user to enter correct key
		if (scanner.nextLine().equalsIgnoreCase(GameConstants.KEY_R)) {
		    int point = gameServiceHelper.rollDice(playerIndex, scoreTable, currentPlayer);
		    // Condition to check player reaches maximum point, if so mark player as completed game
		    if (scoreTable.get(currentPlayer).getScore() >= pointToWinTheGame) {
			gameServiceHelper.playerCompletedGame(playersOrderList, ++winnerRank, scoreTable,
				currentPlayer);
			playerIndex--;
		    }
		    // Condition to check if player got point 6, if so given one more roll for same player
		    if (point == 6 && playersOrderList.contains(currentPlayer)) {
			gameServiceHelper.luckyRoll(winnerRank, scoreTable);
			continue;
		    }
		    // Condition to check if player got point as 1 for two consecutive time, if so mark player to skip next turn
		    if (point == 1 && scoreTable.get(currentPlayer).getPreviousPoint() == 1
			    && playersOrderList.contains(currentPlayer)) {
			gameServiceHelper.unluckyRoll(scoreTable, currentPlayer);
		    }
		    // Add the point of the player to score table
		    scoreTable.get(currentPlayer).setPreviousPoint(point);
		} else {
		    gameServiceHelper.printMessage(GameConstants.OOPS_ENTERED_KEY_IS_NOT_VALID_PLEASE_TRY_AGAIN);
		    continue;
		}
	    } else {
		gameServiceHelper.skipPlayerTurn(scoreTable, currentPlayer);
	    }
	    // Increment the playerIndex to keep track of players
	    playerIndex++;
	    // Once playerIndex playersOrderList size, all players have played. Now start the game from first player
	    if (playerIndex >= playersOrderList.size()) {
		playerIndex = 0;
	    }
	    // If playersOrderList Size is 0, all players completed the game, so stop the game
	    if (playersOrderList.size() == 0) {
		gameServiceHelper.stopGame(winnerRank, scoreTable, scanner);
		break;
	    }
	    // Print the score table
	    gameServiceHelper.printScoreTable(scoreTable, winnerRank);
	}
    }
}

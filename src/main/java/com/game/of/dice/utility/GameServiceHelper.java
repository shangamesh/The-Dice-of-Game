package com.game.of.dice.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.game.of.dice.constants.GameConstants;
import com.game.of.dice.model.PlayerScoreModel;

/**
 * This is a helper class used to execute the logic called for each step of the
 * game
 * 
 * @author shangamesh.t
 *
 */
public class GameServiceHelper {

    // Method used to creates players & shuffle the player to play in random orders
    public List<String> getPlayersListInWithShuffle(int noOfPlayers) {
	List<String> playersList = new ArrayList<String>();
	for (int index = 1; index <= noOfPlayers; index++) {
	    playersList.add(GameConstants.PLAYER + index);
	}
	printMessage(GameConstants.PLAYERS_LIST + playersList);
	Collections.shuffle(playersList);
	return playersList;
    }

    // Method used to print the Welcome note of the game
    public void printGameStartingMessage(int pointToWinTheGame, List<String> playersOrderList) {
	printMessage(GameConstants.WELCOME_TO_GAME_OF_DICE);
	printMessage(GameConstants.LINE);
	printMessage(GameConstants.POINT_TO_WIN_THE_GAME + pointToWinTheGame);
	printMessage(GameConstants.ORDER_OF_PLAYERS_TO_PLAY_THE_GAME + playersOrderList);
	printMessage(GameConstants.GAME_STARTS_NOW_3_2_1_GO);
	printMessage(GameConstants.LINE);
    }

    // Method used to roll the dice & to add the point to player score board
    public int rollDice(int playerIndex, Map<String, PlayerScoreModel> scoreTable, String currentPlayer) {
	// Getting random number from 1 to 6
	int point = getRandom();
	printMessage(GameConstants.POINT_ACHIEVED_BY + currentPlayer + GameConstants.IS + point);
	// Adding the point to player's score
	addScoreForPlayer(scoreTable, currentPlayer, point);
	return point;
    }

    // Method used to get random number between 1 to 6
    private int getRandom() {
	int randomNumber = (int) (Math.random() * (6)) + 1;
	return randomNumber;
    }

    // Method used to add score for player
    private void addScoreForPlayer(Map<String, PlayerScoreModel> scoreTable, String currentPlayer, int point) {
	PlayerScoreModel playerScoreModel;
	// If the player is playing first time, then create playerScoreModel object &
	// add values, else get the playerScoreModel for player & update values
	if (scoreTable.get(currentPlayer) == null) {
	    playerScoreModel = new PlayerScoreModel();
	    playerScoreModel.setScore(point);
	} else {
	    playerScoreModel = scoreTable.get(currentPlayer);
	    playerScoreModel.setScore(playerScoreModel.getScore() + point);
	}
	// Add the values to score table
	scoreTable.put(currentPlayer, playerScoreModel);
    }

    // Method used to print the score board in tabular format
    public void printScoreTable(Map<String, PlayerScoreModel> scoreTable, int winnerRank) {
	printMessage(GameConstants.LINE);
	System.out.printf(GameConstants.TABLE_HEADER_ALIGNMENT_FORMAT, GameConstants.PLAYERS, GameConstants.SCORE,
		GameConstants.RANK, GameConstants.COMPLETED_GAME);
	printMessage(GameConstants.LINE);
	calculateRankAndDisplayScoreTable(scoreTable, winnerRank);
	printMessage(GameConstants.LINE);
    }

    // Method used to calculate the rank after each player roll the dice
    private void calculateRankAndDisplayScoreTable(Map<String, PlayerScoreModel> scoreTable, int winnerRank) {
	// Get all players score who is still not completed game
	List<Integer> playerScoreModels = scoreTable.values().parallelStream()
		.sorted(Comparator.comparing(PlayerScoreModel::getScore).reversed())
		.filter(currentPlayer -> currentPlayer.isCompleteGame() == false)
		.map(currentPlayer -> currentPlayer.getScore()).collect(Collectors.toList());
	// Calculate the rank based on sorting the scores & assign rank to each player
	for (Entry<String, PlayerScoreModel> entry : scoreTable.entrySet()) {
	    // Adding the rank to player who is still playing the game
	    if (!entry.getValue().isCompleteGame()) {
		int rank = playerScoreModels.indexOf(entry.getValue().getScore()) + winnerRank + 1;
		entry.getValue().setRank(rank);
	    }
	    // Print the current player score details
	    System.out.printf(GameConstants.TABLE_ROW_ALIGNMENT_FORMAT, entry.getKey(), entry.getValue().getScore(),
		    entry.getValue().getRank(), entry.getValue().isCompleteGame());
	}
    }

    // Method used to mark the player has completed game & remove the player from
    // game to restrict playing
    public void playerCompletedGame(List<String> playersOrderList, int winnerRank,
	    Map<String, PlayerScoreModel> scoreTable, String currentPlayer) {
	printMessage(GameConstants.CONGRATS + currentPlayer + GameConstants.COMPLETES_THE_GAME);
	playersOrderList.remove(currentPlayer);
	PlayerScoreModel playerScoreModel = scoreTable.get(currentPlayer);
	playerScoreModel.setCompleteGame(true);
	playerScoreModel.setRank(winnerRank);
    }

    // Method used to print the message player got lucky roll & add score
    public void luckyRoll(int winnerRank, Map<String, PlayerScoreModel> scoreTable) {
	printMessage(GameConstants.LUCKY_ROLL);
	printScoreTable(scoreTable, winnerRank);
    }

    // Method used to print the message player got unlucky roll & add penalty to
    // player which will skip next turn
    public void unluckyRoll(Map<String, PlayerScoreModel> scoreTable, String currentPlayer) {
	printMessage(GameConstants.UN_LUCKY_ROLL_YOU_CANNOT_PLAY_FOR_NEXT_TURN);
	scoreTable.get(currentPlayer).setPenalty(true);
    }

    // Method used to skip the player for current turn & enable for next turn
    public void skipPlayerTurn(Map<String, PlayerScoreModel> scoreTable, String currentPlayer) {
	printMessage(currentPlayer + GameConstants.TURN_IS_SKIPPED);
	scoreTable.get(currentPlayer).setPenalty(false);
    }

    // Method used to stop the game
    public void stopGame(int winnerRank, Map<String, PlayerScoreModel> scoreTable, Scanner scanner) {
	printMessage(GameConstants.GAME_OVER);
	printScoreTable(scoreTable, winnerRank);
	scanner.close();
    }

    // Method used to print message in each step of the game
    public void printMessage(String message) {
	System.out.println(message);
    }

}

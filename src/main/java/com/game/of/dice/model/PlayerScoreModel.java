package com.game.of.dice.model;

/**
 * This is POJO/Model class for maintaining Player scores & rank
 * 
 * @author shangamesh.t
 *
 */
public class PlayerScoreModel {
    // Variables
    private int score;
    private boolean penalty;
    private int rank;
    private int previousPoint;
    private boolean completeGame;

    // Getters & Setters
    public int getScore() {
	return score;
    }

    public void setScore(int score) {
	this.score = score;
    }

    public boolean isPenalty() {
	return penalty;
    }

    public void setPenalty(boolean penalty) {
	this.penalty = penalty;
    }

    public int getRank() {
	return rank;
    }

    public void setRank(int rank) {
	this.rank = rank;
    }

    public int getPreviousPoint() {
	return previousPoint;
    }

    public void setPreviousPoint(int previousPoint) {
	this.previousPoint = previousPoint;
    }

    public boolean isCompleteGame() {
	return completeGame;
    }

    public void setCompleteGame(boolean completeGame) {
	this.completeGame = completeGame;
    }

}

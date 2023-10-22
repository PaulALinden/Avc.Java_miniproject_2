package model;

import java.util.ArrayList;

public abstract class TwoPlayerBoard implements BoardOperations {
    protected String[][] board;
    protected String player;
    protected String opponent;
    final protected String empty = " ";
    protected GameStates gameStates;
    protected ArrayList<TwoPlayerBoard> possibleBoards;

    public TwoPlayerBoard(String[][] board, String player, String opponent) {
        this.board = board;
        this.player = player;
        this.opponent = opponent;
        this.possibleBoards = new ArrayList<>();
    }
    public String getEmpty() {
        return empty;
    }
    public String getPlayer() {
        return player;
    }
    public String getOpponent() {
        return opponent;
    }
    public GameStates getGameStates() {
        return gameStates;
    }
    public void setGameStates(GameStates gameStates) {
        this.gameStates = gameStates;
    }

    public abstract ArrayList<TwoPlayerBoard> getPossibleBoards();
}
package com.blackbeardstreasure.game;

import com.blackbeardstreasure.enums.Difficulty;
import com.blackbeardstreasure.gamemaster.GameMaster;
import com.blackbeardstreasure.gamemaster.GameMasterImpl;
import com.blackbeardstreasure.gamemaster.GameMasterState;
import com.blackbeardstreasure.player.Player;

/**
 * Created by lawtonel on 20/10/2017.
 */
public class GameImpl implements Game {
    private Player[] players;
    private Player nextTurn;
    private GameMaster blackbeard;
    private GameBoard board;
    private Player leftDuelist;
    private Player rightDuelist;
    private boolean isDuelOver;
    private GameMasterState blackbeardState;
    private Difficulty difficultyLevel;

    public GameImpl(Player[] players) {
        //setup players
        this.players = players;
        nextTurn = players[0];

        //setup board
        board = new GameBoardImpl();

        // setup gamemaster
        blackbeard = new GameMasterImpl("The Ghost of Blackbeard", new Coordinates(0, 0), players, this);
        blackbeardState = GameMasterState.NO_ACTION;

        // setup duel
        isDuelOver = true;
        leftDuelist = null;
        rightDuelist = null;

        // set difficulty
        difficultyLevel = Difficulty.EASY;
    }

    @Override
    public Player getLeftDuelist() {
        return leftDuelist;
    }

    @Override
    public Player getRightDuelist() {
        return rightDuelist;
    }

    @Override
    public void setLeftDuelist(Player player) {
        leftDuelist = player;
    }

    @Override
    public void setRightDuelist(Player player) {
        rightDuelist = player;
    }

    @Override
    public boolean isDuelOver() {
        return isDuelOver;
    }

    @Override
    public void setDuelOver(boolean duelOver) {
        isDuelOver = duelOver;
    }

    //TODO next iteration
    @Override
    public boolean canDuel(Player opponent) {
        return false;
    }

    //TODO next iteration
    @Override
    public boolean duel(Player opponent, int attackRoll) {
        return false;
    }

    @Override
    public boolean isGameOver() {
        for (Player player : players) {
            if (player.hasTreasure()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Player whoseTurn() {
        return nextTurn;
    }

    @Override
    public String gameMasterAction() {
        return blackbeard.randomAction(nextTurn);
    }

    @Override
    public void play() {
        if (!isGameOver()) {
            setNextTurn();
        }
    }

    @Override
    public void setNextTurn() {
        if (nextTurn == players[0]) {
            nextTurn = players[1];
        } else if (nextTurn == players[1]) {
            nextTurn = players[2];
        } else if (nextTurn == players[2]) {
            nextTurn = players[3];
        } else {
            nextTurn = players[0];
        }
    }

    public Player[] getAllPlayers() {
        return players;
    }

    @Override
    public void setBlackBeardState(GameMasterState blackbeardState) {
        this.blackbeardState = blackbeardState;
    }

    @Override
    public GameMasterState getBlackbeardState() {
        return blackbeardState;
    }

    public GameBoard getBoard() {
        return board;
    }

    public Difficulty getDifficultyLevel() {
        return difficultyLevel;
    }

    @Override
    public void setDifficulty(Difficulty difficulty) {
        difficultyLevel = difficulty;
    }

}


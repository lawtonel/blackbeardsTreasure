package blackbeardtreasure.lib;

import java.util.Set;

public interface Game {
    public boolean canDuel(Player opponent);
    public boolean duel(Player opponent, int attackRoll);
    public boolean isGameOver();
    public Player whoseTurn();
    public String gameMasterAction();
    public void play();
    public void setNextTurn();
    public Player[] getAllPlayers();
    public void setBlackBeardState(GameMasterState blackbeardState);
    public GameMasterState getBlackbeardState();
    // public boolean guessTreasure(Coordinates coordinates);
    public boolean isDuelOver();
    public void setDuelOver(boolean duelOver);
    public Player getLeftDuelist();
    public Player getRightDuelist();
    public void setLeftDuelist(Player player);
    public void setRightDuelist(Player player);
    public GameBoard getBoard();
    public Difficulty getDifficultyLevel();
    public void setDifficulty(Difficulty difficulty);
}

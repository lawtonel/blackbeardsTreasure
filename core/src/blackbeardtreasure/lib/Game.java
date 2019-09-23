package blackbeardtreasure.lib;

public interface Game {
    boolean canDuel(Player opponent);
    boolean duel(Player opponent, int attackRoll);
    boolean isGameOver();
    Player whoseTurn();
    String gameMasterAction();
    void play();
    void setNextTurn();
    Player[] getAllPlayers();
    void setBlackBeardState(GameMasterState blackbeardState);
    GameMasterState getBlackbeardState();
    boolean isDuelOver();
    void setDuelOver(boolean duelOver);
    Player getLeftDuelist();
    Player getRightDuelist();
    void setLeftDuelist(Player player);
    void setRightDuelist(Player player);
    GameBoard getBoard();
    Difficulty getDifficultyLevel();
    void setDifficulty(Difficulty difficulty);
}

package model;

public interface BoardOperations {

    void displayBoard();
    String[][] getBoard();
    void setBoard(String[][] newBoard);
    void createPossibleBoards(String marker);
    void addMarkerToBoard(String mark, int row, int col);
    boolean hasNoEmptyCells();
    int evaluateBoard();
    void checkBoardState();
    boolean isWinner();

}

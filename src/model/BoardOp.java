package model;

import java.util.ArrayList;

public interface BoardOp {

    void printBoard();
    boolean hasEmptyCells(Board board);
    void createChildBoards(String [][] board,String marker);
    ArrayList<Board> getChildBoards();
    void placeMarker(String mark, int row, int col);
    int evalMove(int depth);
}

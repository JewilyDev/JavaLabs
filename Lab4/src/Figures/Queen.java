package Figures;

public class Queen extends Figure {
    public Queen(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if (!super.canMove(row, col, row1, col1)) {
            return false;
        }

        int rowDiff = Math.abs(row1 - row);
        int colDiff = Math.abs(col1 - col);

        return (row == row1 || col == col1 || rowDiff == colDiff);
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        return canMove(row, col, row1, col1);
    }
}

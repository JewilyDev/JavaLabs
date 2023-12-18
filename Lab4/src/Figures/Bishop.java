package Figures;

public class Bishop extends Figure {
    public Bishop(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if (!super.canMove(row, col, row1, col1)) {
            return false;
        }

        int rowDiff = Math.abs(row1 - row);
        int colDiff = Math.abs(col1 - col);

        return rowDiff == colDiff; // Слон двигается только по диагонали, где разница между строками и столбцами одинакова.
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        return canMove(row, col, row1, col1);
    }
}

package Figures;

public class Pawn extends Figure {

    private boolean isFirstStep = true;

    public Pawn(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        //   if (!super.canMove(row, col, row1, col1)){
        //       return false;
        //   }

        int rowDiff = Math.abs(row - row1);
        int colDiff = Math.abs(col - col1);

        if (this.isFirstStep) {
            if (this.getColor() == 'w' && (rowDiff == 1 || rowDiff == 2) && colDiff == 0) {
                // Проверка на наличие препятствий
                if (rowDiff == 2) {
                    this.isFirstStep = false;
                    return true;
                } else if (rowDiff == 1) {
                    return true;
                }
            } else if (this.getColor() == 'b' && (rowDiff == 1 || rowDiff == 2) && colDiff == 0) {
                if (rowDiff == 2) {
                    this.isFirstStep = false;
                    return true;
                } else if (rowDiff == 1) {
                    return true;
                }
            }
        } else {
            if (this.getColor() == 'w' && rowDiff == 1 && colDiff == 0) {
                return true;
            } else if (this.getColor() == 'b' && rowDiff == 1 && colDiff == 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        int rowDiff = Math.abs(row - row1);
        int colDiff = Math.abs(col - col1);

        switch (this.getColor()) {
            case 'w':
                if (rowDiff == 1 && colDiff == 1) {
                    return true;
                }
                break;
            case 'b':
                if (rowDiff == 1 && colDiff == 1) {
                    return true;
                }
                break;
        }

        return false;
    }
}

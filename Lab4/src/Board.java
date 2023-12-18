import Figures.Bishop;
import Figures.Figure;
import Figures.King;
import Figures.Knight;
import Figures.Pawn;
import Figures.Queen;
import Figures.Rook;

import java.util.ArrayList;

public class Board {
    //TODO: Список фигур и начальное положение всех фигур
    private Figure fields[][] = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);

    public char getColorGaming() {
        return colorGaming;
    }

    public void setColorGaming(char colorGaming) {
        this.colorGaming = colorGaming;
    }

    private char colorGaming;

    public void init() {
        this.fields[0] = new Figure[]{
                new Rook("R", 'w'), new Knight("N", 'w'),
                new Bishop("B", 'w'), new Queen("Q", 'w'),
                new King("K", 'w'), new Bishop("B", 'w'),
                new Knight("N", 'w'), new Rook("R", 'w')
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'),
        };

        this.fields[7] = new Figure[]{
                new Rook("R", 'b'), new Knight("N", 'b'),
                new Bishop("B", 'b'), new Queen("Q", 'b'),
                new King("K", 'b'), new Bishop("B", 'b'),
                new Knight("N", 'b'), new Rook("R", 'b')
        };
        this.fields[6] = new Figure[]{
                new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'),
        };
    }

    public String getCell(int row, int col) {
        Figure figure = this.fields[row][col];
        if (figure == null) {
            return "    ";
        }
        return " " + figure.getColor() + figure.getName() + " ";
    }

    public ArrayList<String> getTakeWhite() {
        return takeWhite;
    }

    public ArrayList<String> getTakeBlack() {
        return takeBlack;
    }

    public boolean move_figure(int row1, int col1, int row2, int col2) {

        Figure figure = this.fields[row1][col1];

        if(!this.isPathClear(row1, col1, row2, col2)) return false; //проверка препятствий на пути

        if (figure.canMove(row1, col1, row2, col2) && this.fields[row2][col2] == null) {
            System.out.println("move");
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        } else if (figure.canAttack(row1, col1, row2, col2) && this.fields[row2][col2] != null) {
            System.out.println("attack");

            if(this.fields[row2][col2] instanceof Knight) { //нельзя атаковать короля
                return false;
            }

            switch (this.fields[row2][col2].getColor()) {
                case 'w':
                    this.takeWhite.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
                    break;
                case 'b':
                    this.takeBlack.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
                    break;
            }

            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        }
        return false;


    }

    public void print_board() {
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for (int row = 7; row > -1; row--) {
            System.out.print(row);
            for (int col = 0; col < 8; col++) {
                System.out.print("|" + getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for (int col = 0; col < 8; col++) {
            System.out.print("    " + col);
        }


    }

    private boolean isPathClear(int row, int col, int row1, int col1) {
        int startRow = Math.min(row, row1);
        int endRow = Math.max(row, row1);
        int startCol = col;

        for (int r = startRow + 1; r < endRow; r++) {
            if (fields[r][startCol] != null) {
                return false;
            }
        }

        return true;
    }

    public boolean isCheckmate() {
        char currentColor = this.getColorGaming();

        //ищем короля нужного цвета
        int kingRow = -1, kingCol = -1;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = this.fields[row][col];
                if (figure instanceof King && figure.getColor() == currentColor) {
                    kingRow = row;
                    kingCol = col;
                    break;
                }
            }
            if (kingRow != -1) break;
        }

        //проверка на возможные ходы для короля
        for (int row = kingRow - 1; row <= kingRow + 1; row++) {
            for (int col = kingCol - 1; col <= kingCol + 1; col++) {
                if (row >= 0 && row < 8 && col >= 0 && col < 8) {
                    if (this.move_figure(kingRow, kingCol, row, col)) {
                        //если король может сделать этот ход, то это не мат
                        this.move_figure(row, col, kingRow, kingCol);  //возвращаем фигуру на место после проверки хода
                        return false;
                    }
                }
            }
        }

        //если король не может сделать ход, то проверим, есть ли у других фигур возможность блокировать шах
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Figure figure = this.fields[row][col];
                if (figure != null && figure.getColor() == currentColor) {
                    for (int targetRow = 0; targetRow < 8; targetRow++) {
                        for (int targetCol = 0; targetCol < 8; targetCol++) {
                            if (this.move_figure(row, col, targetRow, targetCol)) {
                                //если какая-то фигура может блокировать шах, то это не мат
                                this.move_figure(targetRow, targetCol, row, col); //возвращаем фигуру на место после проверки хода
                                return false;
                            }
                        }
                    }
                }
            }
        }

        //это мат
        return true;
    }
}

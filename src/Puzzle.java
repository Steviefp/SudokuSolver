import java.util.Arrays;
import java.util.Random;

public class Puzzle {
    int GIVEN_NUMBERS;


    private int[][] board = {
            {0, 0, 0,   0, 0, 0,    0, 0, 0},
            {0, 0, 0,   0, 0, 0,    0, 0, 0},
            {0, 0, 0,   0, 0, 0,    0, 0, 0},

            {0, 0, 0,   0, 0, 0,    0, 0, 0},
            {0, 0, 0,   0, 0, 0,    0, 0, 0},
            {0, 0, 0,   0, 0, 0,    0, 0, 0},

            {0, 0, 0,   0, 0, 0,    0, 0, 0},
            {0, 0, 0,   0, 0, 0,    0, 0, 0},
            {0, 0, 0,   0, 0, 0,    0, 0, 0},
    };

    public Puzzle(int numbers) {
        this.GIVEN_NUMBERS = numbers;
        generate();
        printBoard(board);
        System.out.println("Unplaced Tiles = " + countZeros(board));
        System.out.println("Preplaced tiles = " + (81-countZeros(board)));
    }

    public Puzzle(){
        //printBoard(board);
    }

    public int[][] getBoard(){
        return board;
    }

    // prints the board formatted
    public void printBoard(int[][] b){
        int counter = 0;
        int counterCol = 0;
        for (int[] rows : b) {
            for (int numbers : rows) {
                if(counter == 3){
                    counter = 0;
                    System.out.print("   ");
                }
                counter++;
                System.out.print(numbers + " ");
            }
            counter = 0;
            counterCol++;
            if(counterCol == 3){
                System.out.println();
                counterCol = 0;
            }

            System.out.println();

        }
    }

    // returns true if the number is allowed to be placed
    // returns false if number is not allowed to be placed
    protected boolean checkBoundary(int x, int y, int number, int[][] b, boolean solver){
        int[] row = getRow(x, b);
        int[] col = getCol(y, b);
        int[] section = getSection(x,y, b);

        if(b[x][y] != 0 && !solver){
            return false;
        }
        else if(Arrays.stream(row).anyMatch(q->q == number)){
            return false;
        }
        else if(Arrays.stream(col).anyMatch(q->q == number)){
            return false;
        }
        else if(Arrays.stream(section).anyMatch(q->q == number)){
            return false;
        }
        return true;
    }

    protected int[] getSection(int x, int y, int[][] b){
        int[] section = new int[9];
        int counter = 0;
        int setX = -1,setY = -1;

        setX = switch (x) {
            case 0, 1, 2 -> 0;
            case 3, 4, 5 -> 3;
            case 6, 7, 8 -> 6;
            default -> setX;
        };
        setY = switch (y) {
            case 0, 1, 2 -> 0;
            case 3, 4, 5 -> 3;
            case 6, 7, 8 -> 6;
            default -> setY;
        };

        for (int i = setX; i < setX+3; i++) {
            for (int j = setY; j < setY+3; j++) {
                section[counter] = b[i][j];
                counter++;
            }
        }

        return section;
    }

    protected int countZeros(int[][] b){
        int counter = 0;
        for(int[] i : b){
            for(int j: i){
                if(j == 0){
                    counter++;
                }
            }
        }
        return counter;
    }

    protected int[] getRow(int x, int[][] b){
        return b[x];
    }

    protected int[] getCol(int y, int[][] b){
        int[] col = new int[9];

        for (int i = 0; i < b.length; i++){
            col[i] = b[i][y];
        }

        return col;
    }

    private void generate() {
        Random rand = new Random();
        int randNumber;
        int numbersLeft = GIVEN_NUMBERS;
        int randBoardX , randBoardY;
        boolean check;

        while (numbersLeft> 0) {
            randNumber = rand.nextInt(1, 9);
            randBoardX = rand.nextInt(8);
            randBoardY = rand.nextInt(8);
            check = checkBoundary(randBoardX, randBoardY, randNumber, board, false);

            if(check){
                board[randBoardX][randBoardY] = randNumber;
                numbersLeft--;
            }
        }
    }

}

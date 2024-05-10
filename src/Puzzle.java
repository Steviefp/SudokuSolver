import java.util.Arrays;
import java.util.Random;

public class Puzzle {
    final int GIVEN_NUMBERS;


    int[][] board = {
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
        printBoard();
        System.out.println("countZeros() = " + countZeros());
        System.out.println("Preplaced tiles = " + (81-countZeros()));
        int[] test = getSection(0,0);
        for(int i : test){
            System.out.print("i = " + i + " ");
        }

    }

    // prints the board formatted
    public void printBoard(){
        int counter = 0;
        int counterCol = 0;
        for (int[] rows : board) {
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
    private boolean checkBoundary(int x, int y, int number){
        int[] row = getRow(x);
        int[] col = getCol(y);
        int[] section = getSection(x,y);

        if(board[x][y] != 0){
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

    private int[] getSection(int x, int y){
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
                section[counter] = board[i][j];
                counter++;
            }
        }

        return section;
    }

    private int countZeros(){
        int counter = 0;
        for(int[] i : board){
            for(int j: i){
                if(j == 0){
                    counter++;
                }
            }
        }
        return counter;
    }

    private int[] getRow(int x){
        return board[x];
    }

    private int[] getCol(int y){
        int[] col = new int[9];

        for (int i = 0; i < board.length; i++){
            col[i] = board[i][y];
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
            check = checkBoundary(randBoardX, randBoardY, randNumber);

            if(check){
                board[randBoardX][randBoardY] = randNumber;
                numbersLeft--;
            }
        }
    }

}

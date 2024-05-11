public class Solver extends Puzzle{

    int[][] solveBoard;


    public Solver(int[][] b){
        this.solveBoard = b;
        solve();

        printBoard(solveBoard);
    }


    public boolean solve(){

        int[] blankPos = findBlankPos();
        if(blankPos == null){
            System.out.println("Puzzle Complete");
            return true;
        }

        int x = blankPos[0], y = blankPos[1];

        for (int i = 0; i < solveBoard.length; i++) {
            for (int j = 0; j < solveBoard.length; j++) {
                for (int k = 1; k < 10; k++) {
                    if (addToBoard(x, y, k)) {
                        if (solve()) {
                            return true;
                        } else {
                            solveBoard[x][y] = 0;
                        }
                    }
                }
                return false;

            }
        }
        return true;
    }

    public boolean addToBoard(int x, int y, int number){
        boolean boundary;
        boundary = checkBoundary(x,y,number, solveBoard, true);
        if(boundary){
            solveBoard[x][y]=number;
            return true;
        }

        return false;

    }

    public int[] findBlankPos(){
        int[] pos = new int[2];
        for (int i = 0; i < solveBoard.length; i++) {
            for (int j = 0; j < solveBoard.length; j++) {
                if(solveBoard[i][j] == 0){
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }

        }
        return null;
    }

}

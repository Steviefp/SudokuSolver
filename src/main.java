import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        final int GIVEN_NUMBERS = 20;
        Puzzle puzzle = new Puzzle(GIVEN_NUMBERS);

        Solver solve = new Solver(puzzle.getBoard());

    }
}

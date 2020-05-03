package leet.sudoku;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class Sudoku {

	@Benchmark
	//@BenchmarkMode(Mode.AverageTime)
	@BenchmarkMode(Mode.All)
	@Fork(value = 1, warmups = 2)
	@Warmup(iterations = 5)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	public void benchMarkTest(ExecutionPlan executionPlan) {
		char[][] test = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
				{'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
				{'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
				{'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
				{'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};


		for (int i = executionPlan.iterations; i > 0; i--) {
			isValidSudoku(test);
		}
	}

	@State(Scope.Thread)
	public static class ExecutionPlan {

		@Param({"10", "20", "30", "50", "100"})
		public int iterations;

		@Setup(Level.Invocation)
		public void setUp() {

		}
	}

	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
		char[][] test = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
				{'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
				{'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
				{'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
				{'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
		System.out.println(sudoku.isValidSudoku(test));

		char [][] test2 = new char[][] {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
				{'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
				{'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
				{'8', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
				{'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
		System.out.println(sudoku.isValidSudoku(test2));
}
	public boolean isValidSudoku(char[][] board) {
		if (doBasicValidation(board) || validateGrid(board))
			return false;
		return true;
	}

	private boolean doBasicValidation(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9)
			return true;
		return false;
	}

	private boolean validateGrid( char[][] board){

		for (int j = 0; j < 9; j++) {
			boolean[] rows = new boolean[9];
			boolean[] columns = new boolean[9];
			boolean[] boxes = new boolean[9];

			for (int i = 0; i < 9; i++) {
				if (board[i][j] != '.') {
					if (rows[board[i][j] - '1']) {
						//duplicate value
						return true;
					}
					rows[(board[i][j] - '1')] = true;
				}

				if (board[j][i] != '.') {
					if (columns[board[j][i] - '1']) {
						//duplicate value
						return true;
					}
					columns[board[j][i] - '1'] = true;
				}
			}

			for (int i = j / 3 * 3; i < j / 3 * 3 + 3; i++) {
				for (int k = j % 3 * 3; k < j % 3 * 3 + 3; k++) {
					if (board[i][k] != '.') {
						if (boxes[(int) (board[i][k] - '1')]) {
							//duplicate value
							return true;
						}
						boxes[(int) (board[i][k] - '1')] = true;
					}
				}
			}
		}
		return false;
	}

}

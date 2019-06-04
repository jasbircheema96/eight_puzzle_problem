package model;

import java.util.ArrayList;
import java.util.List;

import constant.Constants;

public class State {
	private int[][] grid;

	public int[][] getGrid() {
		return grid;
	}

	public State() {
		this.grid = new int[Constants.GRID_SIZE][Constants.GRID_SIZE];
	}

	State(State state) {
		this.grid = new int[Constants.GRID_SIZE][Constants.GRID_SIZE];
		for (int row = 0; row < Constants.GRID_SIZE; row++) {
			for (int col = 0; col < Constants.GRID_SIZE; col++) {
				this.grid[row][col] = state.grid[row][col];
			}
		}
	}

	public void print() {
		for (int row = 0; row < Constants.GRID_SIZE; row++) {
			for (int col = 0; col < Constants.GRID_SIZE; col++) {
				System.out.print(this.getGrid()[row][col] + " ");
			}
			System.out.println();
		}
	}

	public List<State> nextPossibleStates() {
		List<State> nextPossibleStates = new ArrayList<State>();
		Cell emptyCell = this.getEmptyCell();
		Move leftMove = new Move(emptyCell, Direction.LEFT);
		Move rightMove = new Move(emptyCell, Direction.RIGHT);
		Move upMove = new Move(emptyCell, Direction.UP);
		Move downMove = new Move(emptyCell, Direction.DOWN);
		if (this.isValidMove(leftMove))
			nextPossibleStates.add(this.makeMove(leftMove));
		if (this.isValidMove(rightMove))
			nextPossibleStates.add(this.makeMove(rightMove));
		if (this.isValidMove(upMove))
			nextPossibleStates.add(this.makeMove(upMove));
		if (this.isValidMove(downMove))
			nextPossibleStates.add(this.makeMove(downMove));
		return nextPossibleStates;
	}

	public int distanceFromGoal() {

		// heuristic function -- sum of Manhattan distance
		int distance = 0;
		for (int row = 0; row < Constants.GRID_SIZE; row++) {
			for (int col = 0; col < Constants.GRID_SIZE; col++) {
				for (int gridRow = 0; gridRow < Constants.GRID_SIZE; gridRow++) {
					for (int gridCol = 0; gridCol < Constants.GRID_SIZE; gridCol++) {
						if (Constants.GOAL_STATE[row][col] == this.grid[gridRow][gridCol]) {
							distance += (Math.abs(row - gridRow) + Math.abs(col - gridCol));
						}
					}
				}
			}
		}
		return distance;
	}

	public Cell getEmptyCell() {
		for (int row = 0; row < Constants.GRID_SIZE; row++) {
			for (int col = 0; col < Constants.GRID_SIZE; col++) {
				if (this.grid[row][col] == Constants.EMPTY_CELL)
					return new Cell(row, col);
			}
		}
		return null;
	}

	public State makeMove(Move move) {
		State state = new State(this);
		int row = move.getCell().getRow();
		int col = move.getCell().getCol();
		Direction direction = move.getDirection();
		if (direction == Direction.LEFT) {
			state.grid[row][col] = state.grid[row][col - 1];
			state.grid[row][col - 1] = Constants.EMPTY_CELL;
		} else if (direction == Direction.RIGHT) {
			state.grid[row][col] = state.grid[row][col + 1];
			state.grid[row][col + 1] = Constants.EMPTY_CELL;
		} else if (direction == Direction.UP) {
			state.grid[row][col] = state.grid[row - 1][col];
			state.grid[row - 1][col] = Constants.EMPTY_CELL;
		} else if (direction == Direction.DOWN) {
			state.grid[row][col] = state.grid[row + 1][col];
			state.grid[row + 1][col] = Constants.EMPTY_CELL;
		}
		return state;
	}

	public boolean isValidMove(Move move) {
		int row = move.getCell().getRow();
		int col = move.getCell().getCol();
		Direction direction = move.getDirection();
		if (direction == Direction.LEFT) {
			if (col <= 0)
				return false;
		} else if (direction == Direction.RIGHT) {
			if (col >= Constants.GRID_SIZE - 1)
				return false;
		} else if (direction == Direction.UP) {
			if (row <= 0)
				return false;
		} else if (direction == Direction.DOWN) {
			if (row >= Constants.GRID_SIZE - 1)
				return false;
		}
		return true;
	}

	public boolean isGoalState() {
		for (int row = 0; row < Constants.GRID_SIZE; row++) {
			for (int col = 0; col < Constants.GRID_SIZE; col++) {
				if (this.grid[row][col] != Constants.GOAL_STATE[row][col])
					return false;
			}
		}
		return true;
	}

	public void randomlyInitialize() {
		List<Integer> orderedList = new ArrayList<Integer>();
		for (int i = 0; i < Constants.GRID_SIZE * Constants.GRID_SIZE; i++) {
			orderedList.add(i);
		}
		for (int row = 0; row < Constants.GRID_SIZE; row++) {
			for (int col = 0; col < Constants.GRID_SIZE; col++) {
				int randomindex = (int) (Math.random() * orderedList.size());
				this.grid[row][col] = orderedList.get(randomindex);
				orderedList.remove(randomindex);
			}
		}
	}
}
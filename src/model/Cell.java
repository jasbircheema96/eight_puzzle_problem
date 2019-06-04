package model;

public class Cell {
	private int row;
	private int col;

	Cell(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}
}

package model;

public class Move {
	private Cell cell;
	private Direction direction;

	Move(Cell cell, Direction direction) {
		this.cell = cell;
		this.direction = direction;
	}

	public Cell getCell() {
		return cell;
	}

	public Direction getDirection() {
		return direction;
	}
}

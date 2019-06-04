package search_algorithm;

import java.util.List;

import constant.Constants;
import model.State;

public class HillClimbing {
	// returns the cost to reach from the passed state to goal state
	// returns infinity if not possible to reach goal state
	public static int run(State state) {
		boolean upwardStepTaken = true;
		int cost = 0;
		while (upwardStepTaken) {
			List<State> nextPossibleStates = state.nextPossibleStates();
			upwardStepTaken = false;
			for (State nextState : nextPossibleStates) {
				if (nextState.distanceFromGoal() < state.distanceFromGoal()) {
					cost++;
					upwardStepTaken = true;
					state = nextState;
					continue;
				}
			}
		}
		if (state.isGoalState())
			return cost;
		return Constants.INFINITY;
	}
}

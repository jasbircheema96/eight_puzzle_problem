package search_algorithm;

import java.util.List;

import constant.Constants;
import model.State;

public class HillClimbingSteepestAscent {

	// returns the cost to reach from the passed state to goal state
	// returns infinity if not possible to reach goal state

	public static int run(State nextBestState) {
		int cost = 0;
		State state = nextBestState;
		while (nextBestState != null) {
			state = nextBestState;
			List<State> nextPossibleStates = state.nextPossibleStates();
			nextBestState = null;
			for (State nextState : nextPossibleStates) {
				if (nextState.distanceFromGoal() < state.distanceFromGoal()) {
					if (nextBestState == null)
						nextBestState = nextState;
					else if (nextState.distanceFromGoal() < nextBestState.distanceFromGoal())
						nextBestState = nextState;
				}
			}
			cost++;
		}

		if (state.isGoalState()) {
			return cost;
		}
		return Constants.INFINITY;

	}
}

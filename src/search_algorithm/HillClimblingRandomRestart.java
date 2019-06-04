package search_algorithm;

import java.util.List;

import constant.Constants;
import model.State;

public class HillClimblingRandomRestart {

	// returns the cost to reach from the passed state to goal state
	// returns infinity if not possible to reach goal state
	
	public static int run() {
		int cost = 0;
		State state = new State();
		state.randomlyInitialize();
		boolean upwardStepTaken;

		while (!state.isGoalState()) {
			state.randomlyInitialize();
			upwardStepTaken = true;
			while (upwardStepTaken) {
				List<State> nextPossibleStates = state.nextPossibleStates();
				upwardStepTaken = false;
				for (State nextState : nextPossibleStates) {
					if (nextState.distanceFromGoal() < state.distanceFromGoal()) {
						upwardStepTaken = true;
						state = nextState;
						continue;
					}
				}
				cost++;
				if(cost==Constants.INFINITY)
					return Constants.INFINITY;
			}
		}
		return cost;
	}
}

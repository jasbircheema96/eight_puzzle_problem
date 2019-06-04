package search_algorithm;

import java.util.List;

import constant.Constants;
import model.State;

public class SimulatedAnnealing {
	
	private static double acceptanceProbability(State state, State nextState, double temperature) {
		if (nextState.distanceFromGoal() < state.distanceFromGoal()) {
			return 1.0;
		}
		return Math.exp((state.distanceFromGoal() - nextState.distanceFromGoal()) / temperature);
	}

	// returns cost if goal state is reached; otherwise returns infinity
	public static int run(State state,double temp,double coolingRate) {
		int cost=0;
		while(temp>1) {
			List<State> nextPossibleStates = state.nextPossibleStates();
			State nextState=nextPossibleStates.get((int)(Math.random()*nextPossibleStates.size()));
			if (acceptanceProbability(state, nextState, temp) > Math.random()) {
				state = nextState;
			}
			if(state.isGoalState())
				break;
			temp*=(1-coolingRate);
			cost++;
		}
		if(state.isGoalState())
			return cost;
		return Constants.INFINITY;
	}
}

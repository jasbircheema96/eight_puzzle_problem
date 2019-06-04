package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import constant.Constants;
import model.State;
import search_algorithm.HillClimbing;
import search_algorithm.HillClimbingSteepestAscent;
import search_algorithm.HillClimblingRandomRestart;
import search_algorithm.SimulatedAnnealing;

public class App {
	public static void main(String[] args) {
	
		try {
			File file=new File(Constants.OUTPUT_FILE_NAME);
			FileWriter fileWriter=new FileWriter(file);
			
			double initialTemperature=Constants.INITIAL_TEMPERATURE;
			double coolingRate=Constants.COOLING_RATE;
			
			int n=1000;
			long totalCost1,totalCost2,totalCost3,totalCost4;
			int solved1,solved2,solved3,solved4;
			int cost1,cost2,cost3,cost4;
			totalCost1=totalCost2=totalCost3=totalCost4=0;
			solved1=solved2=solved3=solved4=0;
			
			
			for(int i=1;i<=n;i++) {
				State state=new State();
				state.randomlyInitialize();
				System.out.println(state.distanceFromGoal());
				
				cost1=HillClimbing.run(state);
				cost2=HillClimbingSteepestAscent.run(state);
				cost3=HillClimblingRandomRestart.run();
				cost4=SimulatedAnnealing.run(state, initialTemperature, coolingRate);
				
				if(cost1!=Constants.INFINITY) {
					fileWriter.write(cost1+"\t");
					totalCost1+=cost1;
					solved1++;
				}
				else
					fileWriter.write("NA\t");
				
				if(cost2!=Constants.INFINITY) {
					fileWriter.write(cost2+"\t");
					totalCost2+=cost2;
					solved2++;
				}
					
				else 
					fileWriter.write("NA\t");
				
				if(cost3!=Constants.INFINITY) {
					fileWriter.write(cost3+"\t");
					totalCost3+=cost3;
					solved3++;
				}
					
				else 
					fileWriter.write("NA\t");
				
				if(cost4!=Constants.INFINITY) {
					fileWriter.write(cost4+"\t");
					totalCost4+=cost4;
					solved4++;
				}
				else 
					fileWriter.write("NA\t");
				
				fileWriter.write(state.distanceFromGoal()+"\t");
				fileWriter.write("\n");
			}
			
			fileWriter.write("Percentage of age problems solved\n");
			fileWriter.write(((double)solved1/n)+"\t"+((double)solved2/n)+"\t"+((double)solved3/n)+"\t"+((double)solved4/n)+"\n");
			fileWriter.write("Average cost to solve problem\n");
			fileWriter.write(((double)totalCost1/solved1+"\t"+((double)totalCost2/solved2)+"\t"+((double)totalCost3/solved3)+"\t"+((double)totalCost4/solved4)+"\n"));
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
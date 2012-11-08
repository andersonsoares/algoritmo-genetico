package info.andersonsoares.algorithm.selection.survivor;

import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.interfaces.FunctionInterface;
import info.andersonsoares.model.Chromosome;
import info.andersonsoares.util.PopulationAnalyzer;
import info.andersonsoares.util.PrintArray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RoletaSurvivorSelection implements SurvivorSelection {
	
	private Decoder d;
    private FunctionInterface f;
    
    public RoletaSurvivorSelection() {};

    
    
	public RoletaSurvivorSelection(Decoder d, FunctionInterface f) {
		
		this.d = d;
		this.f = f;
	}



	public List<Chromosome> select(List<Chromosome> population,
			int targetPopulationSize) {
		
		List<Chromosome> survivors = new LinkedList<Chromosome>();
//		System.out.println("Populacao Inicial");
//		PopulationAnalyzer.print(population, d, f);
		
		
		List<Double> absoluteFitnesses = new LinkedList<Double>();
		List<Double> proportionalFitnesses = new LinkedList<Double>();
		
		 double totalFitness = 0;

	        for (Chromosome i : population)
	        {
	            double fitness = Math.abs(f.calculate(d.decode(i)));
	            totalFitness += fitness;
	            absoluteFitnesses.add(fitness);
	        }

	        Collections.reverse(absoluteFitnesses);
	        
	        if (totalFitness == 0)
	            throw new ArithmeticException(""
	                + "Sum fitness is zero in RouletteParentSelection, "
	                + "so cannot determine proportional fitness.");

	        for (Double i : absoluteFitnesses)
	            proportionalFitnesses.add(i / totalFitness);

//	        Collections.sort(proportionalFitnesses);

	        while (survivors.size() < targetPopulationSize)
	        {
	            double p = Math.random()/10;//gambiarraa

	            for (int i = 0; i < population.size(); i += 1) {
	                if (p > proportionalFitnesses.get(i) && survivors.size() < targetPopulationSize) {
//	                	System.out.println("Random: "+p);
//	                	PrintArray.print(proportionalFitnesses)	;
//	                	System.out.println();
//	                	findCromosome(proportionalFitnesses.get(i),population);
//	                	survivors.add(population.get(i));
	                	survivors.add(findCromosome(absoluteFitnesses.get(i),population));
//	                	double soma=0;
//	                	for(int j=0;j<proportionalFitnesses.size();j++) {
//	                		soma+=proportionalFitnesses.get(j);
//	                	}
//	                	System.out.println("Soma: "+soma);
	                	break;
	                }
	                
	            }
	        }
		
//		System.out.println("Populacao sobrevivente");
//		PopulationAnalyzer.print(survivors, d, f);
		
		return survivors;
	}



	private Chromosome findCromosome(Double double1, List<Chromosome> population) {
		
		for(Chromosome c : population) {
			if(Math.abs(f.calculate(d.decode(c))) == double1) { 
				return c;
			}
		}
		return null;
	}



	

}

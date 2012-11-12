package info.andersonsoares.algorithm.selection.survivor;

import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.interfaces.FunctionInterface;
import info.andersonsoares.model.Chromosome;
import info.andersonsoares.util.PrintArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Ranking implements SurvivorSelection {

	
	private Decoder d;
    private FunctionInterface f;

    private Ranking() {}

    public Ranking(Decoder d, FunctionInterface f)
    {
        this.d = d;
        this.f = f;
    }
	
	
	public List<Chromosome> select(List<Chromosome> population,
			int targetPopulationSize) {
		
		List<Chromosome> survivors = new LinkedList<Chromosome>();
		
		
		List<Double> absoluteFitness = new ArrayList<Double>();
		
		for(Chromosome c : population) {
			absoluteFitness.add(Math.abs(f.calculate(d.decode(c))));
		}
		
		Collections.sort(absoluteFitness);
	
		for(int i=0;i<targetPopulationSize;i++) {
			survivors.add(findCromosome(absoluteFitness.get(i), population));
		}
		
		
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

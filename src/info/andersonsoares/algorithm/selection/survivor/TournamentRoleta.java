package info.andersonsoares.algorithm.selection.survivor;

import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.interfaces.FunctionInterface;
import info.andersonsoares.model.Chromosome;
import info.andersonsoares.util.PrintArray;

import java.util.LinkedList;
import java.util.List;

public class TournamentRoleta implements SurvivorSelection { 
	
	private Decoder d;
    private FunctionInterface f;

    private TournamentRoleta() {}
		

    public TournamentRoleta(Decoder d, FunctionInterface f)
    {
        this.d = d;
        this.f = f;
    }


	public List<Chromosome> select(List<Chromosome> population,	int targetPopulationSize) {
		
 		List<Chromosome> survivors = new LinkedList<Chromosome>();
		
		TournamentSurvivorSelection ts = new TournamentSurvivorSelection(d, f);
		Roleta roleta = new Roleta(d, f);
		
//		System.out.println(population.size());
//		System.out.println(population.size()/2);
//		System.out.println(population.size()/4);
		
		if((population.size()/2) % 2 == 0) {
			//populacao par
			survivors.addAll(roleta.select(population, population.size()/4));
//			System.out.println(survivors.size());
			survivors.addAll(ts.select(population, population.size()/4));
//			System.out.println(survivors.size());
		} else {
			//populacao impar
			List<Chromosome> roletaSurvivors = roleta.select(population, population.size()/4); 
//			System.out.println(roletaSurvivors.size());
			survivors.addAll(roletaSurvivors);
//			System.out.println(survivors.size());
			survivors.addAll(ts.select(population, (population.size()/4) + 1));
//			System.out.println(survivors.size());
			
		}
		
//		System.out.println(survivors.size());
		
		return survivors;
	}
    
    
	

}

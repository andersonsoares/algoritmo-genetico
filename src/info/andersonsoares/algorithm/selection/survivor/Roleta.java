package info.andersonsoares.algorithm.selection.survivor;

import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.interfaces.FunctionInterface;
import info.andersonsoares.model.Chromosome;
import info.andersonsoares.util.PrintArray;

import java.util.LinkedList;
import java.util.List;
 
public class Roleta implements SurvivorSelection {

	
	private Decoder d;
    private FunctionInterface f;

    private Roleta() {}

    public Roleta(Decoder d, FunctionInterface f)
    {
        this.d = d;
        this.f = f;
    }
	
	
	public List<Chromosome> select(List<Chromosome> population,
			int targetPopulationSize) {

		
		List<Chromosome> survivors = new LinkedList<Chromosome>();
		
		List<Double> absoluteFitnesess = new LinkedList<Double>();
		double totalFitness=0;
		for(Chromosome c : population) {
			double valor = Math.abs(f.calculate(d.decode(c)));
			absoluteFitnesess.add(valor);
			totalFitness+=valor;
		}
		
		List<Double> proportionalFitnesses = new LinkedList<Double>();
		
		for(int i=0;i<absoluteFitnesess.size();i++) {
			//Como eh minimizacao, pega o valor total e divide por valor,
			//para obter a proporcao
			double valor = absoluteFitnesess.get(i);
			double calc = totalFitness / valor;
			if(!proportionalFitnesses.isEmpty()) {
				// se nao estiver vazia, soma com o anterior
				proportionalFitnesses.add(calc+proportionalFitnesses.get(i-1));
			} else {
				// se estiver vazia
				proportionalFitnesses.add(calc);
			}
		}
		
		
		while(survivors.size() < targetPopulationSize) {
			
//			PrintArray.print(proportionalFitnesses);
			double max = proportionalFitnesses.get(proportionalFitnesses.size()-1);
			double random = Math.random() * max;
//			System.out.println();
//			System.out.println(random);
//			System.out.println();
			for(int i=0;i<proportionalFitnesses.size();i++) {
				double proporcao = proportionalFitnesses.get(i);
				if(random <= proporcao) {
					survivors.add(findCromosome(absoluteFitnesess.get(i), population));
					break;
				}
			}
			
			
			
			
			
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

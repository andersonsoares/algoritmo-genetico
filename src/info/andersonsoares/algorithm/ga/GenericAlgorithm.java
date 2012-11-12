
package info.andersonsoares.algorithm.ga;

import info.andersonsoares.algorithm.ga.interfaces.AbstractGA;
import info.andersonsoares.algorithm.mutation.chromosome.RandomBitFlipMutation;
import info.andersonsoares.algorithm.recombination.SinglePointCrossover;
import info.andersonsoares.algorithm.selection.parent.RouletteParentSelection;
import info.andersonsoares.algorithm.selection.survivor.Ranking;
import info.andersonsoares.algorithm.selection.survivor.SurvivorSelection;
import info.andersonsoares.algorithm.selection.survivor.TournamentRoleta;
import info.andersonsoares.functions.ColvilleFunction;
import info.andersonsoares.functions.DeJongF1Function;
import info.andersonsoares.functions.DeJongF2Function;
import info.andersonsoares.model.Chromosome;
import info.andersonsoares.util.GAResultSet;
import info.andersonsoares.util.PopulationAnalyzer;
import info.andersonsoares.util.Run;

import java.util.LinkedList;
import java.util.List;

public class GenericAlgorithm extends AbstractGA
{
    @Override
    public void init()
    {
        super.init();

        algPS = new RouletteParentSelection(d, f);
//        algPS = new RandomParentSelection();
//        algSS = new TournamentSurvivorSelection(d, f);
//        algSS = new RoletaSurvivorSelection(d, f);
//        algSS = new Roleta(d, f);
//        algSS = new RankingSurvivorSelection(d, f);
//        algSS = new Ranking(d, f);
//        algSS = new TournamentRoleta(d, f);
        algRec = new SinglePointCrossover();
        algMutC = new RandomBitFlipMutation();
    }

    /**
     * @param populationSize Population size.
     * @param chromosomeSize Chromosome size (genes per chromosome).
     * @param pC Probability of recombination.
     * @param pM Probability of mutation.
     * @param termGeneration Number of generations to run,
     *        or null for fitness-based termination.
     * @param termFitness Fitness at which to halt,
     *        or null for generation-based termination.
     * @param verbose Whether to print periodic statistics.
     */
    public GAResultSet run(int populationSize, int chromosomeSize,
            Double pC, Double pM,
            Integer termGeneration, Double termFitness,
            boolean verbose) {
    	
        GAResultSet results = new GAResultSet();

        /* --- CRIANDO POPULACAO ------ */
        Run.log("Criando populacao inicial");
//        System.out.println("Criando populucao...");
        List<Chromosome> population = new LinkedList<Chromosome>();
        
        int j=0;
        Chromosome c = null;
        List<Double> decodedValues;
        double inferiorLimit=0;
        double superiorLimit=0;
        
        if(f instanceof ColvilleFunction) {
    		inferiorLimit = -10;
    		superiorLimit = 10;
    	} else if(f instanceof DeJongF1Function) {
    		inferiorLimit = -5.12;
    		superiorLimit = 5.12;
    	} else if(f instanceof DeJongF2Function) {
    		inferiorLimit = -2.048;
    		superiorLimit = 2.048;
    	}
        
        boolean flag;
        while(j < populationSize) {
        	flag = false;
        	
        		
        	c = new Chromosome(chromosomeSize);
        	decodedValues = d.decode(c);
//        	System.out.println("Iteracao: "+j);
        	for (Double xi : decodedValues) {
				if(xi < inferiorLimit || xi > superiorLimit) {
//					System.out.println("Negado: "+xi+" of: "+decodedValues);
					flag = true;
				}
			}
        	if(flag == false) {
        		//O cromossomo passou no teste, e ele eh adicionado a populacao
        		population.add(c);
//        		System.out.println("Adicionado: "+j);
            	j++;
        	}
        }
        Run.log("Populacao criada!");
//        System.out.println("Populacao criada!");
//        System.out.println("---Populacao Inicial---");
        /* ---  FINALIZANDO CRIACAO DA POPULACAO ------ */
        PopulationAnalyzer.print(population, d, f);
        Run.log("==================");
        Double gBest  = null;
        Double gWorst = null;
        double bestValue = 1000000.0;
        int totalIterations=0;
        for (int i = 0; i <= termGeneration; i += 1)  {
        	totalIterations++;
        	if (gBest == null)
                gBest = PopulationAnalyzer.bestFitness(population, d, f);
            else {
            	if(Math.abs(gBest) <= termFitness)
            		break;
                if (gBest < PopulationAnalyzer.bestFitness(population, d, f))
                    gBest = PopulationAnalyzer.bestFitness(population, d, f);
            }
            if (gWorst == null)
                gWorst = PopulationAnalyzer.worstFitness(population, d, f);
            else
                if (gWorst > PopulationAnalyzer.worstFitness(population, d, f))
                    gWorst = PopulationAnalyzer.worstFitness(population, d, f);

            
            if(Math.abs(bestValue) > Math.abs(gBest)) {
            	bestValue = gBest;
//            	System.out.println("[Iter. #"+i+"] Best result from now: "+bestValue);
            	PopulationAnalyzer.print(population, d, f);
            	Run.log("[Iter. #"+i+"] Melhor aptidao encontrada ate agora: "+bestValue);
            }
            results.bestFitnesses.add(gBest);
            results.avgFitnesses.add(PopulationAnalyzer
                    .avgFitness(population, d, f));
            results.worstFitnesses.add(gWorst);
            List<Chromosome> newGeneration = new LinkedList<Chromosome>();
            while (newGeneration.size() < population.size())
            {
                List<Chromosome> parents = getParents(population);
//                System.out.println("Pais");
//                PrintArray.print(parents);
                Chromosome dad = parents.get(0);
                Chromosome mom = parents.get(1);
                Chromosome childA = mutate(recombinate(dad, mom, pC), pM);
                Chromosome childB = mutate(recombinate(dad, mom, pC), pM);
                newGeneration.add(childA);
                // in case of odd-numbered population sizes:
                if (newGeneration.size() < population.size())
                    newGeneration.add(childB);
            }

            newGeneration.addAll(population);
//            System.out.println("Pegando sobreviventes");
            newGeneration = getSurvivors(newGeneration, population.size());
            population = newGeneration;
//            System.out.println("---------Nova populacao---------- ");
            Run.log("Nova geracao");
            PopulationAnalyzer.print(population, d, f);
//            System.out.println("Nr. Iteracoes: "+i);
        }

//        System.out.println("---Populacao Final---");
        /* ---  FINALIZANDO CRIACAO DA POPULACAO ------ */
//        PopulationAnalyzer.print(population, d, f);
        
//        System.out.println("Best Value last Population: "+gBest);
        Run.log("Melhor aptidao encontrada da ultima geracao: "+gBest);
        Run.log("Total iteracoes: "+totalIterations);
        Run.log("--------------------------------------------");
        
//		System.out.println("Total iteracoes: "+totalIterations);
//        System.out.println("Fim");
        return results;
    }

	public void setSurvivorSelector(SurvivorSelection selector) {
		algSS = selector; 
	}
}

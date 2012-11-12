package info.andersonsoares.algorithm.ga.interfaces;

import info.andersonsoares.algorithm.selection.survivor.SurvivorSelection;
import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.interfaces.FunctionInterface;
import info.andersonsoares.util.GAResultSet;

public interface GA {
	/**
    * @param populationSize Population size.
    * @param chromosomeSize Chromosome size (genes per chromosome).
    * @param pC Crossover probability.
    * @param pM Mutation probability.
    * @param termGeneration Number of generations to run,
    *        or null for fitness-based termination.
    * @param termFitness Fitness at which to halt,
    *        or null for generation-based termination.
    * @param verbose Whether to print periodic statistics.
    */
   public abstract GAResultSet run(int populationSize, int chromosomeSize,
           Double pC, Double pM,
           Integer termGeneration, Double termFitness,
           boolean verbose);

   public abstract void setDecoder(Decoder d);
   public abstract void setFunction(FunctionInterface f);
   public abstract void init();

public abstract void setSurvivorSelector(SurvivorSelection selector);


}

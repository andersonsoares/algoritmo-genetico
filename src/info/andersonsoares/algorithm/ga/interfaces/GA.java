package info.andersonsoares.algorithm.ga.interfaces;

import info.andersonsoares.algorithm.selection.survivor.SurvivorSelection;
import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.interfaces.FunctionInterface;
import info.andersonsoares.util.GAResultSet;

public interface GA {
	
   public abstract GAResultSet run(int populationSize, int chromosomeSize,
           Double pC, Double pM,
           Integer termGeneration, Double termFitness,
           boolean verbose);

   public abstract void setDecoder(Decoder d);
   public abstract void setFunction(FunctionInterface f);
   public abstract void init();

public abstract void setSurvivorSelector(SurvivorSelection selector);


}

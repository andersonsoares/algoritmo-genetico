
package info.andersonsoares.algorithm.selection.survivor;

import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.interfaces.FunctionInterface;
import info.andersonsoares.model.Chromosome;
import info.andersonsoares.util.PopulationAnalyzer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 *  Rank chromosomes from best to worst, then keep the top N chromosomes.
 */
public class RankingSurvivorSelection implements SurvivorSelection
{
    private Decoder d;
    private FunctionInterface f;

    private RankingSurvivorSelection() {}

    public RankingSurvivorSelection(Decoder d, FunctionInterface f)
    {
        this.d = d;
        this.f = f;
    }

    public List<Chromosome> select(List<Chromosome> population,
            int targetPopulationSize)
    {
    	
    	List<Chromosome> survivors = new LinkedList<Chromosome>();
		System.out.println("Populacao Inicial");
		PopulationAnalyzer.print(population, d, f);
    	
        if (targetPopulationSize > population.size())
            throw new IllegalArgumentException(
                    "targetPopulationSize > populationSize");

        Map<Double, List<Chromosome>> fitnessMap
            = new HashMap<Double, List<Chromosome>>();

        for (Chromosome i : population)
        {
            double fitness = f.calculate(d.decode(i));

            if (fitnessMap.get(fitness) == null)
                fitnessMap.put(fitness, new LinkedList<Chromosome>());

            fitnessMap.get(fitness).add(i);
        }

        Double[] fitnesses = fitnessMap
            .keySet().toArray(new Double[fitnessMap.size()]);

        Arrays.sort(fitnesses);

        /*
         * Sorted to ascending order, so iterate backward.
         */

        for (int i = fitnesses.length - 1; i >= 0; i -= 1)
        {
            if (survivors.size() + fitnessMap.get(fitnesses[i]).size()
                    <= targetPopulationSize)
                survivors.addAll(fitnessMap.get(fitnesses[i]));
            else
            {
                int chromosomesToSave
                    = targetPopulationSize - survivors.size();

                for (int j = 0; j < chromosomesToSave; j += 1)
                    survivors.add(fitnessMap.get(fitnesses[i]).get(j));
            }
        }

        System.out.println("Populacao sobrevivente");
		PopulationAnalyzer.print(survivors, d, f);
        return survivors;
    }
}

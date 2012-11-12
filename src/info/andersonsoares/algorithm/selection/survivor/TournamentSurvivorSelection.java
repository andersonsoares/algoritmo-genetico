
package info.andersonsoares.algorithm.selection.survivor;

import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.interfaces.FunctionInterface;
import info.andersonsoares.model.Chromosome;
import info.andersonsoares.util.PrintArray;

import java.util.LinkedList;
import java.util.List;

public class TournamentSurvivorSelection implements SurvivorSelection
{
    private Decoder d;
    private FunctionInterface f;

    private TournamentSurvivorSelection() {}

    public TournamentSurvivorSelection(Decoder d, FunctionInterface f)
    {
        this.d = d;
        this.f = f;
    }

    public List<Chromosome> select(List<Chromosome> population,
            int targetPopulationSize)
    {
        if (targetPopulationSize > population.size())
            throw new IllegalArgumentException(
                    "targetPopulationSize > populationSize");

        List<Chromosome> survivorSet = new LinkedList<Chromosome>();

//        System.out.println("POPULACAO ANTES DO TORNEIO");
//        PrintArray.print(population);
//        System.out.println("--------------------------------------");
        while(population.size() > targetPopulationSize)
        {
            int indexA = (int) Math.random() * (population.size() + 1);
            int indexB = (int) Math.random() * (population.size() + 1);

            Chromosome a = population.get(indexA);
            Chromosome b = population.get(indexB);

            population.remove(f.calculate(d.decode(a)) < f.calculate(d.decode(b)) ? a : b);
        }
//        System.out.println("POPULACAO DEPOIS DO TORNEIO");
//        PrintArray.print(population);
//        System.out.println("--------------------------------------");
        return population;
    }
}

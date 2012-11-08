
package info.andersonsoares.algorithm.selection.parent;

import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.interfaces.FunctionInterface;
import info.andersonsoares.model.Chromosome;

import java.util.LinkedList;
import java.util.List;

/*
 * Roulette (fitness-proportionate) selection.
 */
public class RouletteParentSelection implements ParentSelection
{
    private Decoder d;
    private FunctionInterface f;

    private RouletteParentSelection() {}

    public RouletteParentSelection(Decoder d, FunctionInterface f)
    {
        this.d = d;
        this.f = f;
    }

    public List<Chromosome> select(List<Chromosome> population)
    {
        List<Double> absoluteFitnesses = new LinkedList<Double>();
        List<Double> proportionalFitnesses = new LinkedList<Double>();

        double totalFitness = 0;

        for (Chromosome i : population)
        {
            double fitness = Math.abs(f.calculate(d.decode(i)));
            totalFitness += fitness;
            absoluteFitnesses.add(fitness);
        }

        if (totalFitness == 0)
            throw new ArithmeticException(""
                + "Sum fitness is zero in RouletteParentSelection, "
                + "so cannot determine proportional fitness.");

        for (Double i : absoluteFitnesses)
            proportionalFitnesses.add(i / totalFitness);

        List<Chromosome> parents = new LinkedList<Chromosome>();

        while (parents.size() < 2)
        {
            double p = Math.random();

            for (int i = 0; i < population.size(); i += 1)
                if (p < proportionalFitnesses.get(i) && parents.size() < 2)
                    parents.add(population.get(i));
        }

        
        return parents;
    }
}

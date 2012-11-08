
package info.andersonsoares.util;

import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.interfaces.FunctionInterface;
import info.andersonsoares.model.Chromosome;

import java.util.ArrayList;
import java.util.List;

public class PopulationAnalyzer
{
    public static void print(List<Chromosome> population,
            Decoder d, FunctionInterface f)
    {
        System.out.println("CROMOSSOMO\t\tVALORES DECODIFICADOS\t\tF(X)");

        for (Chromosome i : population)
        {
            List<Double> vals = d.decode(i);

            StringBuffer sb = new StringBuffer();
            sb.append("{ ");
            for (Double j : vals)
                sb.append(String.format("%f ", j));
            sb.append("}");

            System.out.println(String.format("%s\t%s\t%f",
                        i.toString(), sb.toString(), f.calculate(vals)));
        }
    }

    public static double avgFitness(List<Chromosome> population,
            Decoder d, FunctionInterface f)
    {
        double acc = 0;

        for (Chromosome chromosome : population)
            acc += f.calculate(d.decode(chromosome));

        return acc / population.size();
    }

    public static double bestFitness(List<Chromosome> population,
            Decoder d, FunctionInterface f)
    {
        /*
         * Don't initialize this to zero as it will cause incorrect
         * results if the fitnesses of all chromosomes are negative.
         */
        Double best = null;

        for (Chromosome chromosome : population)
        {
            if (best == null)
                best = f.calculate(d.decode(chromosome));
            else
            {
                double current = f.calculate(d.decode(chromosome));
                best = current > best ? current : best;
            }
        }

        return best;
    }

    
    public static double worstFitness(List<Chromosome> population,
            Decoder d, FunctionInterface f)
    {
        /*
         * Don't initialize this to zero as it will cause incorrect
         * results if the fitnesses of all genes are negative.
         */
        Double worst = null;

        for (Chromosome chromosome : population)
        {
            if (worst == null)
                worst = f.calculate(d.decode(chromosome));
            else
            {
                double current = f.calculate(d.decode(chromosome));
                worst = current < worst ? current : worst;
            }
        }

        return worst;
    }
    
}

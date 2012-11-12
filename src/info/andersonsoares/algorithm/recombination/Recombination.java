
package info.andersonsoares.algorithm.recombination;

import info.andersonsoares.model.Chromosome;

public interface Recombination
{
    public Chromosome recombinate(Chromosome dad, Chromosome mom, Double pC);
}


package info.andersonsoares.algorithm.mutation.chromosome;

import info.andersonsoares.model.Chromosome;

public interface ChromosomeMutation
{
    public Chromosome mutate(Chromosome chromosome, double pM);
}

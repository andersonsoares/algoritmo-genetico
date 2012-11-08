
package info.andersonsoares.algorithm.mutation.chromosome;

import info.andersonsoares.model.Chromosome;

public class RandomBitFlipMutation implements ChromosomeMutation
{
    public RandomBitFlipMutation() {}

    public Chromosome mutate(Chromosome chromosome, double pM)
    {
        if (Math.random() < pM) {
//        	System.out.println("Sofreu mutacao");
    	   chromosome.flip(chromosome.getRandIndex());
        }

        return chromosome;
    }
}

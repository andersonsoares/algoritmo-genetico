
package info.andersonsoares.algorithm.selection.parent;

import info.andersonsoares.model.Chromosome;

import java.util.List;

public interface ParentSelection
{
    public List<Chromosome> select(List<Chromosome> population);
}

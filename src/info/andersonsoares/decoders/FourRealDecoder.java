package info.andersonsoares.decoders;

import java.util.LinkedList;
import java.util.List;

import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.model.Chromosome;

public class FourRealDecoder extends Decoder {

	public FourRealDecoder(Integer decimalPlaces, Integer baseNumber) {
		super(decimalPlaces, baseNumber);
	}

	public List<Double> decode(Chromosome chromosome) {

		// TODO: not totally correct
		if (chromosome.size() % 4 != 0)
            throw new IllegalArgumentException(
                "Chromosomze length must be divisible by four.");

        if (decimalPlaces < 0)
            throw new IllegalArgumentException("decimalPlaces must be >= 0");

        List<Double> list = new LinkedList<Double>();
        int splitIndex = chromosome.size() / 4;
        double valueA = 0;
        double valueB = 0;
        double valueC = 0;
        double valueD = 0;
        int base;

        /*
         * Note we stop before the first bit of each number's chromosome.
         *
         * This is intentional, as this bit is not part of the number itself
         * but instead represents whether the number is positive (false) or
         * negative (true).
         */
        base = 1;
        for (int i = splitIndex - 1; i >= 1; base *= baseNumber)
            valueA += chromosome.get(i--) ? base : 0;

        base = 1;
        for (int i = (splitIndex * 2) - 1; i >= splitIndex + 1; base *= baseNumber)
            valueB += chromosome.get(i--) ? base : 0;

        base = 1;
        for (int i = chromosome.size() - 1; i >= (splitIndex * 2) + 1; base *= baseNumber)
            valueC += chromosome.get(i--) ? base : 0;
        
        base = 1;
        for (int i = chromosome.size() - 1; i >= (splitIndex * 3) + 1; base *= baseNumber)
            valueD += chromosome.get(i--) ? base : 0;

        /*
         * Convert to a floating-point numbers per 'decimalPlaces' argument.
         */
        for (int i = 0; i < decimalPlaces; i += 1)
        {
            valueA *= 0.1;
            valueB *= 0.1;
            valueC *= 0.1;
            valueD *= 0.1;
        }

        if (chromosome.get(0))
            valueA *= (-1);

        if (chromosome.get(splitIndex))
            valueB *= (-1);

        if (chromosome.get(splitIndex * 2))
            valueC *= (-1);

        if (chromosome.get(splitIndex * 3))
            valueD *= (-1);

        list.add(valueA);
        list.add(valueB);
        list.add(valueC);
        list.add(valueD);

        return list;
    }

}

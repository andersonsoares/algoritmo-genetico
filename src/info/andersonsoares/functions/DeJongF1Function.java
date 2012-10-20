package info.andersonsoares.functions;

import java.util.List;

import info.andersonsoares.functions.interfaces.FunctionInterface;


public class DeJongF1Function implements FunctionInterface {

	/*
	 * Recebe 3(tres) variaveis como parametro
	 * (non-Javadoc)
	 * @see info.andersonsoares.functions.base.FunctionInterface#calculate(java.util.List)
	 */
	public double calculate(List<Double> variables) {
		if (variables.size() < 1)
            throw new IllegalArgumentException("Expecting at least one input variable.");

        double result = 0;

        for (Double i : variables)
            result += (i * i);

        result *= (-1);
        return result;
    }

}

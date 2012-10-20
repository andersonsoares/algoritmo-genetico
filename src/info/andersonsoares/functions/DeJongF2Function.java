package info.andersonsoares.functions;

import java.util.List;

import info.andersonsoares.functions.interfaces.FunctionInterface;

public class DeJongF2Function implements FunctionInterface {

	
	/*
	 * Recebe 2(duas) variaveis como parametro
	 * (non-Javadoc)
	 * @see info.andersonsoares.functions.base.FunctionInterface#calculate(java.util.List)
	 */
	public double calculate(List<Double> variables) {
		
		if (variables.size() != 2)
            throw new IllegalArgumentException("Expecting 2 input variables.");

        double result = 0;

        double x = variables.get(0);
        double y = variables.get(1);

        /*
         * 100(x^2 - y)^2 + (1 - x)^2
         */
        result = (100 * ((x * x) - y) * ((x * x) - y)) + ((1 - x) * (1 - x));

        result *= (-1);
        return result;
	}

}

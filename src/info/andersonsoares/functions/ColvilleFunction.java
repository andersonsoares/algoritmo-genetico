package info.andersonsoares.functions;

import java.util.List;

import info.andersonsoares.functions.interfaces.FunctionInterface;

public class ColvilleFunction implements FunctionInterface {

	
	/*
	 * Recebe 4(quatro) parametros
	 * x1, x2, x3, x4
	 * -10 <= xi <= 10
	 */
	public double calculate(List<Double> variables) {
		if (variables.size() != 4)
            throw new IllegalArgumentException("Expecting 4 input variables.");

        double result = 0;

        double x1 = variables.get(0);
        double x2 = variables.get(1);
        double x3 = variables.get(2);
        double x4 = variables.get(3);


        /*
         * 100(x2 - (x1^2))^2 + (1 - (x1^2))^2 + 90(x4 - (x3^2))^2 + 10,1((x2-1)^2 + (x4-1)^2) + 19.8(x2 - 1)(x4 - 1)
         */
        
        result = 100 * (x2 - (x1*x1)) * (x2 - (x1*x1))  
        		+ 
        		( (1 - x1) * (1 - x1) )  
        		+
        		90 * (x4 - (x3*x3)) * (x4 - (x3*x3) )
        		+
        		(1 - x3) * (1 - x3) 
        		+
        		10.1*( (x2 - 1)*(x2 - 1) + (x4-1)*(x4-1) ) 
        		+
        		19.8*(x2-1)*(x4-1)
        		;


        result *= (-1);
        return result;
	}

	
	
}

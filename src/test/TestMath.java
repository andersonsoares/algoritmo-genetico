package test;

public class TestMath {

	
	public static void main(String[] args) {
		double zero = 0;
		double precisao = 2; // duas casas decimais
		
		/*
         * Convert to a floating-point numbers per 'decimalPlaces' argument.
         */
		zero += 1;
        for (int i = 0; i < precisao; i += 1)
        {
            zero *= 0.1;
        }
        
		System.out.println(-0.0 < 0.0);
	}
	
}

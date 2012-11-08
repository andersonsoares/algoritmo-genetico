package info.andersonsoares.util;

import info.andersonsoares.algorithm.ga.GenericAlgorithm;
import info.andersonsoares.algorithm.ga.interfaces.GA;
import info.andersonsoares.decoders.FourRealDecoder;
import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.ColvilleFunction;
import info.andersonsoares.functions.interfaces.FunctionInterface;

import java.util.List;

public class GARunner {

	
	public static void main(String[] args) {
	
		int i = 0;
		double mediaMelhorResultado = 0;
		long mediaTempoDecorrido = 0;
		
		int nrExecucoes = 10;
		
		while(i < nrExecucoes) {
			int populationSize = 50;
			double pC = 0.7; // probability Crossover
			double pM = 0.01; // probability Mutation
			int termGeneration = 10000;
			double termFitness = 0;
			
			//Run DeJongF1
	//		FunctionInterface function = new DeJongF1Function();
	//		ThreeRealDecoder decoder = new ThreeRealDecoder(2);
	//		int chromosomeSize = 30;
	
			//Run DeJongF2
//			FunctionInterface function = new DeJongF2Function();
//			TwoRealDecoder decoder = new TwoRealDecoder(3);
//			int chromosomeSize = 24;
			
	//		//Run Colvillie Function test
			FunctionInterface function = new ColvilleFunction();
			Decoder decoder = new FourRealDecoder(2);
			int chromosomeSize = 44;
			
	
			
			
			
			
			GA ga = new GenericAlgorithm();
			ga.setDecoder(decoder);
			ga.setFunction(function);
			ga.init();
			
			long inicio = System.currentTimeMillis();
			
			GAResultSet results = ga.run(populationSize, chromosomeSize, pC, pM, termGeneration, termFitness, true);
			
			long fim = System.currentTimeMillis();
			long tempoDecorrido = fim - inicio;
			
			mediaTempoDecorrido+=tempoDecorrido;
			System.out.println(ImprimirTempo.print(tempoDecorrido));
			
			double melhorResultado = getBestResult(results.bestFitnesses);
			System.out.println(melhorResultado);
			mediaMelhorResultado+=melhorResultado;
			i++;
		}//fim while
		
		mediaMelhorResultado /= nrExecucoes;
		mediaTempoDecorrido  /= nrExecucoes;
		
		System.out.println("Media de tempo: "+ImprimirTempo.print(mediaTempoDecorrido));
		System.out.println("Media de resultado: "+mediaMelhorResultado);
	}//fim main	

	private static double getBestResult(List<Double> bestFitnesses) {
		
		Double best = null;
		
		for (Double result : bestFitnesses) {
			if (best != null) {
				best = Math.abs(result) < Math.abs(best) ? result : best;
			} else {
        	   best = result;
			}
		}
		return best;
	}
}
package info.andersonsoares.util;

import info.andersonsoares.algorithm.ga.GenericAlgorithm;
import info.andersonsoares.algorithm.ga.interfaces.GA;
import info.andersonsoares.algorithm.selection.survivor.Ranking;
import info.andersonsoares.algorithm.selection.survivor.Roleta;
import info.andersonsoares.algorithm.selection.survivor.SurvivorSelection;
import info.andersonsoares.algorithm.selection.survivor.TournamentRoleta;
import info.andersonsoares.algorithm.selection.survivor.TournamentSurvivorSelection;
import info.andersonsoares.decoders.FourRealDecoder;
import info.andersonsoares.decoders.ThreeRealDecoder;
import info.andersonsoares.decoders.TwoRealDecoder;
import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.ColvilleFunction;
import info.andersonsoares.functions.DeJongF1Function;
import info.andersonsoares.functions.DeJongF2Function;
import info.andersonsoares.functions.interfaces.FunctionInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class Run {

	public static File file = new File("results"+System.currentTimeMillis()+".txt");
	
	
	public static void main(String[] args) {
	
		
		System.out.println("Log completo em: "+file.getName());
		
		
		//Run DeJongF1
		FunctionInterface function = new DeJongF1Function();
		Decoder decoder = new ThreeRealDecoder(2);
		int chromosomeSize = 30;
		
		executarAlgoritmo(function, decoder, chromosomeSize, new Roleta(decoder,function));
		executarAlgoritmo(function, decoder, chromosomeSize, new Ranking(decoder,function));
		executarAlgoritmo(function, decoder, chromosomeSize, new TournamentSurvivorSelection(decoder,function));
		executarAlgoritmo(function, decoder, chromosomeSize, new TournamentRoleta(decoder,function));
		
		
		//RUn DeJongF2
		function = new DeJongF2Function();
		decoder = new TwoRealDecoder(3);
		chromosomeSize = 24;
		
		executarAlgoritmo(function, decoder, chromosomeSize, new Roleta(decoder,function));
		executarAlgoritmo(function, decoder, chromosomeSize, new Ranking(decoder,function));
		executarAlgoritmo(function, decoder, chromosomeSize, new TournamentSurvivorSelection(decoder,function));
		executarAlgoritmo(function, decoder, chromosomeSize, new TournamentRoleta(decoder,function));
		
		
		//Run Colvillie Function
		function = new ColvilleFunction();
		decoder = new FourRealDecoder(2);
		chromosomeSize = 44;
		executarAlgoritmo(function, decoder, chromosomeSize, new Roleta(decoder,function));
		executarAlgoritmo(function, decoder, chromosomeSize, new Ranking(decoder,function));
		executarAlgoritmo(function, decoder, chromosomeSize, new TournamentSurvivorSelection(decoder,function));
		executarAlgoritmo(function, decoder, chromosomeSize, new TournamentRoleta(decoder,function));
		
		
	}	

	

	public static void executarAlgoritmo(FunctionInterface f, Decoder d, int chromosomeSize, SurvivorSelection selector) {
		int i = 0;
		double mediaMelhorResultado = 0;
		long mediaTempoDecorrido = 0;
		
		int nrExecucoes = 10;
		
		String funcaoTeste = null;
		String metodoSelecao = null;
		
		if(f instanceof DeJongF1Function)
			funcaoTeste = "De Jong F1 Function";
		else if (f instanceof DeJongF2Function)
			funcaoTeste = "De Jong F2 Function";
		else if (f instanceof ColvilleFunction)
			funcaoTeste = "Colvillie Function";
		
		if(selector instanceof Roleta)
			metodoSelecao = "Roleta";
		else if(selector instanceof TournamentSurvivorSelection)
			metodoSelecao = "Torneio";
		else if(selector instanceof Ranking)
			metodoSelecao = "Ranking";
		else if(selector instanceof TournamentRoleta)
			metodoSelecao = "Ranking+Torneio";
		
		log("INICIANDO ALGORITMO PARA: "+funcaoTeste);
		log("Metodo de selecao: "+metodoSelecao);
		
		System.out.println("\n\nINICIANDO ALGORITMO PARA: "+funcaoTeste);
		System.out.println("Metodo de selecao: "+metodoSelecao);
		
		while(i < nrExecucoes) {
			
			int j=i+1;
			System.out.println("Rodando algoritmo ["+j+"] de ["+nrExecucoes+"] vezes");
			log("Rodando algoritmo ["+j+"] de ["+nrExecucoes+"] vezes");
			
			int populationSize = 50;
			double pC = 0.7; // probability Crossover
			double pM = 0.01; // probability Mutation
			int termGeneration = 10000;
			double termFitness = 0.01;
			
			//Run DeJongF1
//			FunctionInterface function = new DeJongF1Function();
//			ThreeRealDecoder decoder = new ThreeRealDecoder(2);
//			int chromosomeSize = 30;
	
			//Run DeJongF2
//			FunctionInterface function = new DeJongF2Function();
//			TwoRealDecoder decoder = new TwoRealDecoder(3);
//			int chromosomeSize = 24;
			
	//		//Run Colvillie Function test
//			FunctionInterface function = new ColvilleFunction();
//			Decoder decoder = new FourRealDecoder(2);
//			int chromosomeSize = 44;
			
	
			
			
			
			
			GA ga = new GenericAlgorithm();
			ga.setDecoder(d);
			ga.setFunction(f);
			ga.setSurvivorSelector(selector);
			ga.init();
			
			long inicio = System.currentTimeMillis();
			
			GAResultSet results = ga.run(populationSize, chromosomeSize, pC, pM, termGeneration, termFitness, true);
			
			long fim = System.currentTimeMillis();
			long tempoDecorrido = fim - inicio;
			
			mediaTempoDecorrido+=tempoDecorrido;
//			System.out.println(ImprimirTempo.print(tempoDecorrido));
			log("Tempo decorrido: "+ImprimirTempo.print(tempoDecorrido)+"( "+tempoDecorrido+" ms)");
			System.out.println("\tTempo decorrido: "+ImprimirTempo.print(tempoDecorrido)+" ( "+tempoDecorrido+" ms )");
			
			double melhorResultado = getBestResult(results.bestFitnesses);
//			System.out.println(melhorResultado);
			log("Melhor resultado: "+Math.abs(melhorResultado));
			System.out.println("\tMelhor resultado: "+Math.abs(melhorResultado));
			
			
			mediaMelhorResultado+=melhorResultado;
			i++;
//			System.out.println(i);
			
		}//fim while
		
		mediaMelhorResultado /= nrExecucoes;
		mediaTempoDecorrido  /= nrExecucoes;
		
		System.out.println("Media de tempo: "+ImprimirTempo.print(mediaTempoDecorrido)+" ( "+mediaTempoDecorrido+" ms )");
		System.out.println("Media de resultado: "+Math.abs(mediaMelhorResultado));
		log("Media de tempo: "+ImprimirTempo.print(mediaTempoDecorrido)+" ( "+mediaTempoDecorrido+" ms )");
		log("Media de resultaod: "+Math.abs(mediaMelhorResultado));
	}
	
	
	
	
	public static void log(String info) {
		try {
			FileOutputStream fos = new FileOutputStream(file,true);
			OutputStreamWriter out = new OutputStreamWriter(fos,"UTF-8");
			
			
			out.append("\n\n"+info);
			
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
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
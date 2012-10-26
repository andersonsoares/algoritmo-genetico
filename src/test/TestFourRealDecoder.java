package test;

import java.util.Arrays;

import info.andersonsoares.decoders.FourRealDecoder;
import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.ColvilleFunction;
import info.andersonsoares.functions.interfaces.FunctionInterface;
import info.andersonsoares.model.Chromosome;

public class TestFourRealDecoder {

	public static void main(String[] args) {
	
		FunctionInterface function = new ColvilleFunction();
		Decoder decoder = new FourRealDecoder(2);
		int chromosomeSize = 32;
		
		System.out.println(decoder.decode(new Chromosome(Arrays.asList(
				true,true,true,true,
				true,true,true,true,
				true,true,true,true,
				true,true,true,true,
				true,true,true,true,
				true,true,true,true,
				true,true,true,true,
				true,true,true,true
				))));
		
	}
	
}

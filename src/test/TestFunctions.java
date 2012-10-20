package test;

import info.andersonsoares.decoders.FourRealDecoder;
import info.andersonsoares.functions.ColvilleFunction;
import info.andersonsoares.model.Chromosome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class TestFunctions {

	
	public static void main(String[] args) {
		Chromosome c = null;
		FourRealDecoder four = new FourRealDecoder(5,2);
		
		ColvilleFunction cf = new ColvilleFunction();
		
		ArrayList<Double> array = new ArrayList<Double>();
		int i=0;
		while(i<10) {
			c = new Chromosome(36);
//			System.out.println(four.decode(c));
//			array.add(cf.calculate(four.decode(c)));
			array.add(cf.calculate(Arrays.asList(9d,9d,9d,9d)));
			i++;
		}
		
		Collections.sort(array);
		System.out.println(array.get(0));
		System.out.println(array.get(array.size()-1));
		System.out.println("----------------------");
		Iterator<Double> it = array.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	
}

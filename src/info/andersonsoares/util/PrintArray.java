package info.andersonsoares.util;

import info.andersonsoares.model.Chromosome;

import java.util.Iterator;
import java.util.List;



public class PrintArray {


	public static void print(List<Chromosome> list) {
		Iterator<Chromosome> it = list.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	
}

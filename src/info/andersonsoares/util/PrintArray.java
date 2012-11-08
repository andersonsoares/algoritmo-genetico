package info.andersonsoares.util;

import java.util.Iterator;
import java.util.List;




public class PrintArray {


	public static void print(List<? extends Object> list) {
		Iterator<Object> it = (Iterator<Object>) list.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	
	
	
}

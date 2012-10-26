package test;

import info.andersonsoares.decoders.FourRealDecoder;
import info.andersonsoares.decoders.ThreeRealDecoder;
import info.andersonsoares.decoders.TwoRealDecoder;
import info.andersonsoares.model.Chromosome;

public class TestDecoders {

	
	public static void main(String[] args) {
	
		Chromosome c = null;
		
		
		
		// -2.048 <= x <= 2.048
		TwoRealDecoder two = new TwoRealDecoder(3);
		
		// -10 <= xi <= 10
		FourRealDecoder four = new FourRealDecoder(5);

		// -5.12 <= xi <= 5.12
		ThreeRealDecoder three = new ThreeRealDecoder(2);
		
		
		while(true) {
			//F2
//			-2.048 <= x <= 2.048 
//			c = new Chromosome(24);
//			System.out.println(two.decode(c));
			
			
//			-10 <= xi <= 10
//			c = new Chromosome(36);
//			System.out.println(four.decode(c));
			
			//F1
//			-5.12 <= xi <= 5.12
//			c = new Chromosome(30);
//			System.out.println(three.decode(c));
		}
		
		
	}
	
	
}

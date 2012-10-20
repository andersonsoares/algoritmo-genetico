package info.andersonsoares.decoders.interfaces;

import info.andersonsoares.model.Chromosome;

import java.util.List;

public abstract class Decoder implements DecoderInterface {
	
	protected Integer decimalPlaces;
	protected Integer baseNumber;
	
	/**
	 * @param decimalPlaces igual quantas casas decimais
	 * @param baseNumber numero a ser elevado 2^1 ou 3^1 (2 ou 3) no caso
	 */
	public Decoder(Integer decimalPlaces, Integer baseNumber) {
		this.decimalPlaces = decimalPlaces;
		this.baseNumber = baseNumber;
	}
    
    
}

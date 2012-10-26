package info.andersonsoares.decoders.interfaces;


public abstract class Decoder implements DecoderInterface {
	
	public Integer decimalPlaces;
	
	/**
	 * @param decimalPlaces igual quantas casas decimais
	 * @param baseNumber numero a ser elevado 2^1 ou 3^1 (2 ou 3) no caso
	 */
	public Decoder(Integer decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}
    
    
}

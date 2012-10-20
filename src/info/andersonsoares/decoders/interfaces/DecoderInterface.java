package info.andersonsoares.decoders.interfaces;

import info.andersonsoares.model.Chromosome;

import java.util.List;

public interface DecoderInterface {

	public List<Double> decode(Chromosome chromosome);
	
}

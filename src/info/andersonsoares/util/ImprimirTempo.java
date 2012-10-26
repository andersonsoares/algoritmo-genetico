package info.andersonsoares.util;

import java.util.concurrent.TimeUnit;

public class ImprimirTempo {

	
	public static String print(long tempoDecorrido) {
		
		return  String.format("%d min, %d sec", 
			    TimeUnit.MILLISECONDS.toMinutes(tempoDecorrido),
			    TimeUnit.MILLISECONDS.toSeconds(tempoDecorrido) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tempoDecorrido))
			);
	}
	
}

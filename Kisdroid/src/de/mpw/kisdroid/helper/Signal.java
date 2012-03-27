/**
 * 
 */
package de.mpw.kisdroid.helper;

/**
 * @author Markus Wersig
 * @Description
 * Classe f�r Signalst�rken
 *
 */
public class Signal {

	private Integer mSignal;
	private Integer mNoise;
	/**
	 * 
	 */
	public Signal(String signal, String noise) {
		this.mSignal = Integer.decode(signal);
		this.mNoise = Integer.decode(noise);
	}
	public String getSignal(){
		return mSignal.toString();
	}
	public String getNoise(){
		return mNoise.toString();
	}

}

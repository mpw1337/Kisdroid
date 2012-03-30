/**
 * 
 */
package de.mpw.kisdroid.helper;

/**
 * @author Markus Wersig
 * @Description Classe für Signalstärken
 * 
 */
public class Signal {

	private Integer mSignal;
	private Integer mNoise;

	/**
	 * 
	 * @param signal
	 *            String mit der Signalstärke.
	 * @param noise
	 *            String mit der Noisestärke.
	 */
	public Signal(String signal, String noise) {
		this.mSignal = Integer.decode(signal);
		this.mNoise = Integer.decode(noise);
	}

	/**
	 * @return Liefert das Signal als String zurück.
	 */
	public String getSignal() {
		return mSignal.toString();
	}

	/**
	 * @return Liefert die Noise als String zurück.
	 */
	public String getNoise() {
		return mNoise.toString();
	}

}

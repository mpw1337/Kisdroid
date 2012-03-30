/**
 * 
 */
package de.mpw.kisdroid.helper;

/**
 * @author Markus Wersig
 * @Description Classe f�r Signalst�rken
 * 
 */
public class Signal {

	private Integer mSignal;
	private Integer mNoise;

	/**
	 * 
	 * @param signal
	 *            String mit der Signalst�rke.
	 * @param noise
	 *            String mit der Noisest�rke.
	 */
	public Signal(String signal, String noise) {
		this.mSignal = Integer.decode(signal);
		this.mNoise = Integer.decode(noise);
	}

	/**
	 * @return Liefert das Signal als String zur�ck.
	 */
	public String getSignal() {
		return mSignal.toString();
	}

	/**
	 * @return Liefert die Noise als String zur�ck.
	 */
	public String getNoise() {
		return mNoise.toString();
	}

}

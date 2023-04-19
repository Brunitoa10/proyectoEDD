/**
 * InvalidPositionException 
 * 
 * Excepcion producida cuando la posicion es invalida
 * 
 * @author Massiris - Parisi
 */
package Auxiliar;

@SuppressWarnings("serial")
public class InvalidPositionException extends Exception {
	public InvalidPositionException(String msg) {
		super(msg);
	}
}

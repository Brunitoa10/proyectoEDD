/**
 * InvalidKeyException 
 * 
 * Excepcion producida cuando la clave es invalida
 * 
 * @author Massiris - Parisi
 */
package Auxiliar;

@SuppressWarnings("serial")
public class InvalidKeyException extends Exception {
	public InvalidKeyException(String msg) {
		super(msg);
	}
}

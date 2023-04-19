/**
 * InvalidOperationException 
 * 
 * Excepcion producida cuando la operacion es invalida
 * 
 * @author Massiris - Parisi
 */
package Auxiliar;

@SuppressWarnings("serial")

public class InvalidOperationException extends Exception {
	public InvalidOperationException(String msg) {
		super(msg);
	}
}

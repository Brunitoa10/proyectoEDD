/**
 * InvalidEntryException 
 * 
 * Excepcion producida cuando la entrada es invalida
 * 
 * @author Massiris - Parisi
 */

package Auxiliar;

@SuppressWarnings("serial")

public class InvalidEntryException extends Exception {
	public InvalidEntryException(String msg) {

		super(msg);
	}
}

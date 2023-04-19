/**
 * EmptyListException
 * 
 * Excepcion producida cuando la lista esta vacia
 * 
 * @author Massiris - Parisi
 */
package Auxiliar;

@SuppressWarnings("serial")
public class EmptyListException extends Exception {
	public EmptyListException(String msg) {
		super(msg);
	}
}

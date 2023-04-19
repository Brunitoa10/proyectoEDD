/**
 * EmptyStackException
 * 
 * Excepcion producida cuando la pila esta vacia
 * 
 * @author Massiris - Parisi
 */
package Auxiliar;

@SuppressWarnings("serial")
public class EmptyStackException extends Exception {

	public EmptyStackException(String msg) {
		super(msg);
	}

}

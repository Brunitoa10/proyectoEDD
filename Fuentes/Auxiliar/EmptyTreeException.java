/**
 * EmptyTreeException
 * 
 * Excepcion producida cuando el arbol esta vacio
 * 
 * @author Massiris - Parisi
 */
package Auxiliar;

@SuppressWarnings("serial")

public class EmptyTreeException extends Exception {

	public EmptyTreeException(String msg) {
		super(msg);
	}
}

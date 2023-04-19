/**
 * EmptyQueueException
 * 
 * Excepcion producida cuando la cola esta vacia
 * 
 * @author Massiris - Parisi
 */
package Auxiliar;

@SuppressWarnings("serial")
public class EmptyQueueException extends Exception {
	public EmptyQueueException(String msg) {
		super(msg);
	}
}

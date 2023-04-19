package TDAPila;

import Auxiliar.EmptyStackException;

public class PilaEnlazada<E> implements Stack<E> {

	// Atributos de instancia

	protected Nodo<E> head;
	protected int tamanio;

	/**
	 * Constructor
	 * 
	 * @returns Inicializada la cabeza en null
	 * @returns Inicializada en tamanio de la pila en cero
	 */

	public PilaEnlazada() {
		head = null;
		tamanio = 0;
	}

	// Comandos
	@Override
	public void push(E elem) {
		Nodo<E> aux = new Nodo<E>(elem, head);
		head = aux;
		tamanio++;
	} // T(n) --> O(1)

	// Consultas
	@Override
	public boolean isEmpty() {
		return head == null;
	}// T(n) --> O(1)

	@Override
	public E pop() throws EmptyStackException {
		E aux = null;
		E salida = null;
		if (isEmpty()) {
			throw new EmptyStackException("PilaEnlazada Vacia.");
		} else {
			aux = head.getElem();
			head = head.getSig();
			tamanio = tamanio - 1;
			salida = aux;
		}
		return salida;
	} // T(n) --> O(1)

	@Override
	public int size() {
		return tamanio;
	}

	@Override
	public E top() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("PilaEnlazada Vacia.");
		}
		return head.getElem();
	}
}

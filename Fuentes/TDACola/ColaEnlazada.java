package TDACola;

import Auxiliar.EmptyQueueException;

/**
 * ColaEnlazada
 * @author Massiris Daniel - Bruno Parisi
 * @version 1.0
 * */
public class ColaEnlazada<E> implements Queue<E>{
	
	//Atributos de instancia
	protected int cantElem;
	protected Nodo<E> head,tail;
	
	
	/**
	 * Constructor
	 * @returns Inicializa la cabeza y la cola en null 
	 * @returns Inicializada la cantidad de elementos de la cola en 0
	 * */
	public ColaEnlazada() {
		head = tail = null;
		cantElem = 0;
	}
	
	@Override
	public void enqueue(E elem) {
		Nodo<E> nodo = new Nodo<E>(elem);
		
		nodo.setElem(elem);
		nodo.setSig(null);
		
		if (cantElem == 0) {
			head = nodo;
		}else {
			tail.setSig(nodo);
		}
		tail = nodo;
		cantElem++;
	}

	@Override
	public int size() {
		return cantElem;
	}

	@Override
	public boolean isEmpty() {
		return cantElem == 0;
	}

	@Override
	public E front() throws EmptyQueueException {
		E salida;
		if (isEmpty()) {
			throw new EmptyQueueException("Cola vacia"); 
		}else {
			salida = head.getElem();
		}
		return salida;
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		E salida,temp;
		
		if (isEmpty()) {
			throw new EmptyQueueException("Cola vacia"); 
		}else {
			temp = head.getElem();
			head = head.getSig();
			cantElem--;
			if (isEmpty()) {
				tail = null;
			}
			salida = temp;
		}
		return salida;
	}
}

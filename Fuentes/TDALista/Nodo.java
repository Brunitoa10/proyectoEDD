package TDALista;

public class Nodo<E> implements Position<E> {
	// Atributos de instancia
	private E element;
	private Nodo<E> next;
	
	/**
	 * Constructor A
	 * @param E e
	 * @param Nodo s
	 * Inicializa al element con el valor del elemento e
	 * Inicializa a next con el valor del nodo s
	 * */
	public Nodo(E e, Nodo<E> s) {
		element = e;
		next = s;
	}
	
	/**
	 * Constructor B
	 * @param E elemento
	 * Inicializada a element con el valor de elemento
	 * Inicializada a next en null
	 * */
	public Nodo(E elemento) {this(elemento,null);}

	/**
	 * setNext
	 * @param Nodo n
	 * setael valor a next con el valor de n
	 */
	public void setNext(Nodo<E> n ) {next = n;}
	/**
	 * setElement
	 * @param E e
	 * setea el valor de element con el valor de e
	 */
	public void setElement(E e ) {element = e;}
	
	//Consultas
	/**
	 * element
	 * @return element
	 */
	public E element() {return element;}

	/**
	 * getNext
	 * @return next
	 */
	public Nodo<E> getNext(){return next;}	
}

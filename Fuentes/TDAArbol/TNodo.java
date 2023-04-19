package TDAArbol;

import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;

public class TNodo<E> implements Position<E> {

	protected E elemento;
	protected TNodo<E> padre;
	protected PositionList<TNodo<E>> hijos;

	/**
	 * Constructor A
	 * 
	 * @param E     Elemento
	 * @param TNodo padre
	 * @returns Inicializa un nodo de árbol con rótulo, referencia al padre y sin
	 *          hijos.
	 */
	public TNodo(E elemento, TNodo<E> padre) {
		this.elemento = elemento;
		this.padre = padre;
		hijos = new ListaDE<TNodo<E>>();
	}

	/**
	 * Constructor B
	 * 
	 * @param E elemento
	 * @returns Inicializada this.elemento con el valor de elemento
	 * @returns Inicialziada a padre en null
	 * @returns Crea una lista de hijos a vacia
	 */
	public TNodo(E elemento) {
		this(elemento, null);
	}

	@Override
	public E element() {
		return this.elemento;
	}

	/**
	 * Establece el elemento del parámetro en el elemento del nodo de árbol.
	 * 
	 * @param elemento
	 * @returns Elemento a establecer en el elemento del nodo de árbol.
	 */

	public void setElement(E elemento) {
		this.elemento = elemento;
	}

	/**
	 * getPadre
	 * 
	 * @returns padre
	 */

	public TNodo<E> getPadre() {
		return padre;
	}

	/**
	 * setPadre
	 * 
	 * @param TNodo padre
	 * @returns Establece el nodo del parámetro como el padre del nodo de árbol
	 *          actual.
	 */

	public void setPadre(TNodo<E> padre) {
		this.padre = padre;
	}

	/**
	 * getHijos
	 * 
	 * @returns hijos
	 */
	public PositionList<TNodo<E>> getHijos() {
		return hijos;
	}

}

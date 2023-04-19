
package TDALista;

public class Dnode<E> implements Position<E> {
	private E elemento;
	private Dnode<E> anterior;
	private Dnode<E> siguiente;

	/**
	 * Constructor A Inicializada a elemento = anterior = siguiente = null;
	 */
	public Dnode() {
		this(null, null, null);
	}

	/**
	 * Constructor B
	 * 
	 * @param E     elemento
	 * @param Dnode anterior
	 * @param Dnode siguiente
	 * 
	 * @returns setea el valor de this.elemento con el valor del elemento pasado por
	 *          paramentro
	 * @returns setea el valor de this.anterior con el valor de anterior pasado por
	 *          parametro
	 * @returns setea el valor de this.siguiente con el valor de siguiente pasado
	 *          por parametro
	 */
	public Dnode(final E elemento, final Dnode<E> anterior, final Dnode<E> siguiente) {
		this.elemento = elemento;
		this.anterior = anterior;
		this.siguiente = siguiente;
	}

	/**
	 * getAnterior
	 * 
	 * @return anterior
	 */
	public Dnode<E> getAnterior() {
		return this.anterior;
	}

	/**
	 * setAnterior
	 * 
	 * @param Dnode anterior
	 * @return setea a this.anterior con el valor de anterior
	 */
	public void setAnterior(final Dnode<E> anterior) {
		this.anterior = anterior;
	}

	/**
	 * getSiguiente
	 * 
	 * @return siguiente
	 */
	public Dnode<E> getSiguiente() {
		return this.siguiente;
	}

	/**
	 * setSiguiente
	 * 
	 * @param siguiente
	 * @return setea a this.siguiente con el valor de siguiente
	 */
	public void setSiguiente(final Dnode<E> siguiente) {
		this.siguiente = siguiente;
	}

	/**
	 * setElemento
	 * 
	 * @param elemento
	 * @return setea a this.elemento con el valor de elemento
	 */
	public void setElemento(final E elemento) {
		this.elemento = elemento;
	}

	@Override
	public E element() {
		return this.elemento;
	}
}
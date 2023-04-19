package TDAArbol;

import java.util.Iterator;

import Auxiliar.BoundaryViolationException;
import Auxiliar.EmptyListException;
import Auxiliar.EmptyQueueException;
import Auxiliar.EmptyTreeException;
import Auxiliar.InvalidOperationException;
import Auxiliar.InvalidPositionException;
import TDACola.ColaEnlazada;
import TDACola.Queue;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;

/**
 * Arbol Generico
 * 
 * @version 1.1
 * @author Massiris Daniel - Parisi Bruno
 */

public class ArbolGenerico<E> implements Tree<E> {

	protected TNodo<E> root;
	protected int size;

	/**
	 * Constructor
	 * 
	 * @returns Inicialiada a la raiz en null Inicializa la cantidad de nodos del
	 * @returns arbol en 0
	 */
	public ArbolGenerico() {
		root = null;
		size = 0;
	}

	@Override
	/**
	 * Size
	 * 
	 * @return la cantidad de nodos del arbol
	 */
	public int size() {
		return size;
	}

	@Override
	/**
	 * IsEmpty()
	 * 
	 * @return true si la raiz es null, false de caso contrario
	 */
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	/**
	 * iterator
	 * 
	 * @return Retorna un iterador con los elementos ubicados en los nodos del arbol
	 */
	public Iterator<E> iterator() {
		PositionList<E> list = new ListaDE<E>();
		for (Position<E> a : positions()) {
			list.addLast(a.element());
		}
		return list.iterator();
	}

	@Override
	/**
	 * Positions
	 * 
	 * @return Retorno una coleccion iterable de los nodos del arbol
	 */
	public Iterable<Position<E>> positions() {
		return preOrden();
	}

	@Override
	/**
	 * replace
	 * 
	 * @param Position v
	 * @param E        e
	 * @return Reemplaza con e y retorna el elemento ubicado en v
	 */
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		E toReturn = nodo.element();
		nodo.setElement(e);

		return toReturn;
	}

	@Override
	/**
	 * root
	 * 
	 * @return Retorna la raiz del arbol, error si el arbol esta vacio O(1)
	 */
	public Position<E> root() throws EmptyTreeException {
		if (isEmpty()) {
			throw new EmptyTreeException("Arbol vacio");
		}
		return root;
	}

	@Override
	/**
	 * Parent
	 * 
	 * @param Position v
	 * @return Retorna el padre de v, error si v esta vacio
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {

		TNodo<E> nodo = checkPosition(v);

		if (nodo == root) {
			throw new BoundaryViolationException("p corresponde a la raiz del árbol");
		}

		return nodo.getPadre();
	}

	/**
	 * Children
	 * 
	 * @param Position v
	 * @return Retorna una coleccion iterable conteniendo los hijos del nodo v
	 */

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> p = checkPosition(v);

		PositionList<Position<E>> lista = new ListaDE<Position<E>>();

		for (TNodo<E> n : p.getHijos()) {
			lista.addLast(n);// Originalmente lista.addLast(n)
		}
		return lista;
	}

	@Override
	/**
	 * IsInternal
	 * 
	 * @param Position v
	 * @return Testea si v es un nodo interno
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);

		return !nodo.getHijos().isEmpty();
	}

	@Override
	/**
	 * IsExternal
	 * 
	 * @param Position v
	 * @return Testea si v es una hoja
	 */
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return nodo.getHijos().isEmpty();
	}

	@Override
	/**
	 * IsRoot
	 * 
	 * @param Position v
	 * @return Testea si v es la raiz o(1)
	 */

	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return nodo.getPadre() == null;
	}

	@Override
	/**
	 * CreatRoot
	 * 
	 * @param E e
	 * @return Crea un nodo raíz con rótulo e
	 */
	public void createRoot(E e) throws InvalidOperationException {

		if (root != null) {
			throw new InvalidOperationException("El árbol ya tiene raiz");
		} else {
			Position<E> padre = new TNodo<E>(e, null);
			root = (TNodo<E>) padre;
			size++;
		}

	}

	@Override
	/**
	 * addFirstChildren
	 * 
	 * @param Position p
	 * @param E        e
	 * @return Agrega un primer hijo al nodo p con rótulo e O(1)
	 */
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> retorno = null;

		if (isEmpty()) {
			throw new InvalidPositionException("El árbol está vacío");
		} else {
			TNodo<E> nodo = checkPosition(p);
			TNodo<E> nuevo = new TNodo<E>(e, nodo);
			nodo.getHijos().addFirst(nuevo);
			size++;
			retorno = nuevo;
		}

		return retorno;
	}

	/**
	 * addLastChilden
	 * 
	 * @param Position p
	 * @param E        e
	 * @return Agrega un ultimo hijo al nodo p con rótulo e
	 */
	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		if (isEmpty()) {
			throw new InvalidPositionException("El árbol está vacío");
		}

		TNodo<E> nodo = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e, nodo);
		nodo.getHijos().addLast(nuevo);
		size++;

		return nuevo;
	}

	@Override
	/**
	 * addBefore
	 * 
	 * @param Position p
	 * @param Position rb
	 * @param E        e
	 * @return Agrega un nodo con rótulo e como hijo de un nodo padre p dado. El
	 *         nuevo nodo se agregará delante de otro nodo hermano rb también dado.
	 */
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNodo<E> n = checkPosition(p);
		TNodo<E> hd = checkPosition(rb);
		TNodo<E> nuevo = new TNodo<E>(e, n);
		PositionList<TNodo<E>> hijos = n.getHijos();
		// Buscar dónde está rb en la lista de hijos de p
		boolean encontre = false;
		Position<TNodo<E>> pp;
		try {
			if (isEmpty() || n.getHijos().isEmpty()) {
				throw new InvalidPositionException("Arbol vacio");
			}
			pp = hijos.first();
			while (pp != null && !encontre)
				// Testeo si el elemento corriente de la lista de hijos de p es rb
				if (hd == pp.element())
					encontre = true; // Sí, es! => terminé el bucle
				else
					pp = (pp != hijos.last() ? hijos.next(pp) : null); // No es => avanzo
			if (!encontre) // => Hay un problema con los argumentos
				throw new InvalidPositionException("p no es padre de rb");
			hijos.addBefore(pp, nuevo); // Inserto al nodo nuevo delante de rb
			size++; // Incremento el tamaño del árbol
		} catch (EmptyListException | BoundaryViolationException e1) {
			e1.printStackTrace();
		}

		return nuevo; // Retorno el nodo creado
	}

	@Override
	/**
	 * addAfter
	 * 
	 * @param Position p
	 * @param Position lb
	 * @param Position E e
	 * @return Agrega un nodo con rótulo e como hijo de un nodo padre p dado. El
	 *         nuevo nodo se agregará detrás de otro hermano lb también dado
	 */

	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> nuevo = null, n, hd;
		PositionList<TNodo<E>> hijos;
		Position<TNodo<E>> pp;

		boolean encontre;

		if (size == 0) {
			throw new InvalidPositionException("Arbol vacio");
		}
		n = checkPosition(p);

		hd = checkPosition(lb);

		nuevo = new TNodo<E>(e, n);

		hijos = n.getHijos();

		if (n.getHijos().isEmpty()) {
			throw new InvalidPositionException("El nodo p no es padre de rb");
		}

		encontre = false;

		try {
			pp = hijos.first();

			while (pp != null && !encontre) {// busco a lb en la lista de hijos de de p
				if (hd.equals(pp.element())) {
					encontre = true;
				} else {
					pp = (!(pp == hijos.last()) ? hijos.next(pp) : null);
				}
			}
			if (!encontre) {
				throw new InvalidPositionException("El nodo p no es padre de rb");
			}

			hijos.addAfter(pp, nuevo);

			size++;
		} catch (EmptyListException | BoundaryViolationException e1) {
			e1.printStackTrace();
		}

		return nuevo;
	}

	@Override
	/**
	 * removeExternal
	 * 
	 * @param Position p
	 * @return chequear que el nodo sea externo y que no este vacio el arbol. Caso
	 *         contario, llama a remove().
	 */
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> n = checkPosition(p);
		if (!n.getHijos().isEmpty()) {
			throw new InvalidPositionException("No es un nodo Externo");
		} else {
			removeNode(n);
		}

	}

	@Override
	/**
	 * removeInternal
	 * 
	 * @param Position p
	 * @return chequear que el nodo sea interno y que no este vacio el arbol. Caso
	 *         contario, llama a remove().
	 */

	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> n = checkPosition(p);
		if (n.getHijos().isEmpty()) {
			throw new InvalidPositionException("No es un nodo interno");
		} else {
			removeNode(n);
		}

	}

	@Override
	/**
	 * removeNode
	 * 
	 * @param Position p
	 * @return remueve el nodo del árbol, independientemente de si es externo,
	 *         interno o raiz. (Fijarse el remove subido por la cátedra)
	 */

	public void removeNode(Position<E> p) throws InvalidPositionException {

		if (isEmpty()) {// Check si el arbol es vacio
			throw new InvalidPositionException("No se puede eliminar de un arbol vacio");
		}

		TNodo<E> n = checkPosition(p);// Check posicion nodo valida
		try {
			if (n == root)// el nodo que se pretende eliminar es la raiz
				if (root.getHijos().size() == 1) {// la raiz tiene un solo hijo
					Position<TNodo<E>> rN = root.getHijos().first();
					rN.element().setPadre(null);
					root.getHijos().remove(rN);
					root = rN.element();
				} else if (size == 1)// el arbol tiene un unico nodo
					root = null;
				else// se quiere eliminar la raiz pero no es posible por la estructura del arbol
					throw new InvalidPositionException(
							"Solo se puede eliminar la raiz si es el unico elemento o si tiene un solo hijo");
			else// Se quiere eliminar un nodo interno o un nodo hoja.
			{
				TNodo<E> padre = n.getPadre();
				PositionList<TNodo<E>> hPadre = padre.getHijos(); // hijos del padre (hermanos de n)
				PositionList<TNodo<E>> hN = n.getHijos();// hijos de n

				// buscar a n dentro de los hijos del padre
				Position<TNodo<E>> posDeN;
				Position<TNodo<E>> cursor = hPadre.first();
				while (cursor.element() != n && cursor != null) {
					if (cursor == hPadre.last())
						cursor = null;
					else
						cursor = hPadre.next(cursor);
				}
				if (cursor != null)
					posDeN = cursor;
				else
					throw new InvalidPositionException("La estructura no corresponde a un arbol valido");

				// si n tiene hijos, se recorren e insertan ordenados en el lugar del padre

				while (!hN.isEmpty()) {
					Position<TNodo<E>> hijoN = hN.first();
					hPadre.addBefore(posDeN, hijoN.element());
					hijoN.element().setPadre(padre);
					hN.remove(hijoN);
				}
				// eliminamos a n de la lista
				hPadre.remove(posDeN);
			}
			// decrementamos el tamaño de la estructura
			size--;
		} catch (EmptyListException | BoundaryViolationException e) {
			throw new InvalidPositionException("La estructura no corresponde a un arbol valido");
		}
	}

	/**
	 * Clone con acceso a la estrucutura
	 * 
	 * @return Clone del arbol que recibe el mensaje
	 */

	// Metodo cascara
	public Tree<E> cloneConAcceso() {
		// Creo un nuevo arbol, vacio en primer instancia
		Tree<E> arbolClonado = new ArbolGenerico<E>();

		if (root != null) {// Si la raiz no es nula
			try {
				arbolClonado.createRoot(root.element()); // Clono la raiz del arbol que recibe el mensaje de forma
															// superficial
				clonarRecursivo(root, arbolClonado.root(), arbolClonado); // llamo recursivamente
			} catch (InvalidOperationException | EmptyTreeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return arbolClonado;
	}

	// Metodo recursivo para clonar un arbol
	// Si quiero hacer un clone en profundidad hijo.element.clone
	private void clonarRecursivo(TNodo<E> nodo, Position<E> nodoArbolClonado, Tree<E> arbolClonado) {
		for (TNodo<E> hijo : nodo.getHijos()) {// para cada hijo de nodo
			try {
				Position<E> hijoClon = arbolClonado.addLastChild(nodoArbolClonado, hijo.element()); // clonar hijo como
																									// hijo de
																									// nodoarbolClonado
				clonarRecursivo(hijo, hijoClon, arbolClonado);
			} catch (InvalidPositionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * checkPosition
	 * 
	 * @param Position v
	 * @return El nodo en la posicion v, caso contrario una InvalidPositionException
	 */
	protected TNodo<E> checkPosition(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = null;
		if (v == null) {
			throw new InvalidPositionException("Posicion Invalida");
		} else {
			nodo = (TNodo<E>) v;
		}
		return nodo;
	}

	private void recPreOrden(Position<E> p, PositionList<Position<E>> l) {
		l.addLast(p); // La visita de v consiste en encolar v en l

		try {
			for (Position<E> c : children(p)) { // Para cada hijo p de v hacer
				recPreOrden(c, l);
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * preOrden
	 * 
	 * @return Devuelve una lista iterable con los nodos del arbol de izquierda a
	 *         derecha
	 */
	public Iterable<Position<E>> preOrden() {
		PositionList<Position<E>> list = new ListaDE<Position<E>>();
		if (!isEmpty()) {
			try {
				recPreOrden(root(), list);
			} catch (EmptyTreeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * posOrden
	 * 
	 * @return Devuelve una lista iterable con los nodos del arbol de derecha a
	 *         izquierda
	 */
	public Iterable<Position<E>> posOrden() {
		PositionList<Position<E>> list = new ListaDE<Position<E>>();

		if (!isEmpty()) {
			try {
				recPosOrden(root(), list);
			} catch (EmptyTreeException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	private void recPosOrden(Position<E> p, PositionList<Position<E>> l) {
		try {
			for (Position<E> c : children(p)) { // Para cada hijo p de v hacer
				recPosOrden(c, l); // preorden de p
			}
			l.addLast(p); // La visita de v consiste en encolar v en l
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
	}

	private void recInOrden(Position<E> p, PositionList<Position<E>> l) {
		try {
			if (isExternal(p)) {// Si v es hoja en T
				l.addLast(p); // La visita consiste en encolar????
			} else {

			}
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ----------------------------------------------------------------

	/**
	 * Profundidad de un nodo
	 * 
	 * @param Tree     T
	 * @param Position v
	 * @return Longitud del camino de la raíz de T al nodo v = cantidad de ancestros
	 *         propios de v
	 */
	public int profundidad(Tree<E> T, Position<E> v) {
		int retorno = 0;

		try {
			if (T.isRoot(v)) {
				retorno = 0;
			} else {
				retorno = 1 + profundidad(T, T.parent(v));
			}
		} catch (InvalidPositionException | BoundaryViolationException e) {

			e.printStackTrace();
		}

		return retorno;
	}

	/**
	 * Altura de un arbol
	 * 
	 * @param Tree     T
	 * @param Position v
	 * @return La altura de un árbol T no vacío es la máxima profundidad de las
	 *         hojas de T
	 */
	public int altura(Tree<E> T, Position<E> v) {
		int retorno = 0;
		try {
			if (T.isExternal(v)) {
				retorno = 0;
			} else {
				int h = 0;
				for (Position<E> w : T.children(v)) {
					h = Math.max(h, altura(T, w));
				}
				retorno = 1 + h;
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public void imprimirPorNivel(Tree<E> arbolParmetro) {

		try {
			// Creo una cola
			Queue<Position<E>> cola = new ColaEnlazada<Position<E>>();
			Position<E> v;
			// Encolo la raiz del arbol pasado por parametro
			cola.enqueue(arbolParmetro.root());
			cola.enqueue(null);
			// Mientras la cola no sea vacia
			while (!cola.isEmpty()) {
				// Desencolo el elemento en dicha posicion
				v = cola.dequeue();
				// Si v no es nulo muestro el elemento
				if (v != null) {
					System.out.print(v.element() + " ");
					// para cada hijo de w de v en t hacer
					for (Position<E> w : arbolParmetro.children(v))
						cola.enqueue(w);

				} else {
					System.out.println();
					if (!cola.isEmpty())
						cola.enqueue(null);
				}

			}

		} catch (EmptyQueueException | EmptyTreeException | InvalidPositionException e) {
			e.printStackTrace();
		}
	}

}

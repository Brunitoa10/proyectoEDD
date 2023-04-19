package Programa;

import Auxiliar.DirectorioException;
import Auxiliar.EmptyStackException;
import Auxiliar.EmptyTreeException;
import Auxiliar.InvalidOperationException;
import Auxiliar.InvalidPositionException;
import TDAArbol.ArbolGenerico;
import TDAArbol.TNodo;
import TDAArbol.Tree;
import TDADirectorio.Directorio;
import TDADirectorio.Entry;
import TDADirectorio.Pair;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;
import TDAPila.PilaEnlazada;
import TDAPila.Stack;

public class mainLogic {
	Tree<Entry<String, PositionList<String>>> mainArbol;

	/**
	 * Constructor
	 * 
	 * @returns Inicializada a mainArbol en null
	 */
	public mainLogic() {
		mainArbol = null;
	}

	/**
	 * Metodo para cargar el arbol
	 * 
	 * @param Path ruta para formar el arbol
	 * @return Tree
	 */
	public Tree<Entry<String, PositionList<String>>> createTree(String path) {
		
		Tree<Entry<String, PositionList<String>>> tree = new ArbolGenerico<Entry<String, PositionList<String>>>();
		Directorio d = new Directorio(path);

		// primero creamos el arbol pero sin asignar ninguna lista como valor en las
		// entradas
		// al final de cada iteracion se asignara la lista
		try {
			// creamos el nodo raiz
			Pair<String, PositionList<String>> sign = new Pair<String, PositionList<String>>(d.getRutaCompleta(),
					new ListaDE<String>());
			tree.createRoot(sign);

			// agregamos los archivos
			for (String archive : d.archivosDelDirectorio()) {
				tree.addLastChild(tree.root(), new Pair<String, PositionList<String>>(archive, null));
			}
			// agregamos los directorios
			for (String dir : d.subdirectoriosDelDirectorio()) {
				Position<Entry<String, PositionList<String>>> pos = tree.addFirstChild(tree.root(),
						new Pair<String, PositionList<String>>(dir, new ListaDE<String>()));
				tree = subTree(tree, pos, dir);
			}

			// agrega las rutas absolutas de los archivos y directorios a la lista del
			// rotulo
			for (Position<Entry<String, PositionList<String>>> i : tree.children(tree.root())) {// para cada entrada que
																								// sea hijo de la raiz
				tree.root().element().getValue().addFirst(i.element().getKey());// agregar su ruta absoluta a la lista
																				// de la raiz
			}
		} catch (DirectorioException | InvalidPositionException | EmptyTreeException | InvalidOperationException e) {
			e.printStackTrace();
		}
		mainArbol = tree;
		return tree;
	}

	/**
	 * Metodo para cargar subArboles dentro del metodo creteTree
	 * 
	 * @param Tree arbol de la clase mainLogic
	 * @param p    posicion
	 * @param path ruta ingresada por parametro desde la interfaz grafica
	 * @return tree subArbol del arbol general
	 */
	private Tree<Entry<String, PositionList<String>>> subTree(Tree<Entry<String, PositionList<String>>> tree,
			Position<Entry<String, PositionList<String>>> p, String path) {
		Directorio d = new Directorio(path);
		try {
			// agregamos los archivos
			for (String archive : d.archivosDelDirectorio()) {
				tree.addLastChild(p, new Pair<String, PositionList<String>>(archive, null));
			}
			// agregamos los directorios
			for (String dir : d.subdirectoriosDelDirectorio()) {
				Position<Entry<String, PositionList<String>>> pos = tree.addFirstChild(p,
						new Pair<String, PositionList<String>>(dir, new ListaDE<String>()));
				tree = subTree(tree, pos, dir);
			}
			// agrega las rutas absolutas de los archivos y directorios a la lista del
			// rotulo
			for (Position<Entry<String, PositionList<String>>> i : tree.children(p)) {// para cada entrada que sea hijo
																						// de la raiz
				if (p.element() != null) {
					p.element().getValue().addFirst(i.element().getKey());// agregar su ruta absoluta a la lista de la
																			// raiz
				}
			}
		} catch (DirectorioException | InvalidPositionException e) {
			e.printStackTrace();
		}
		return tree;
	}

	/**
	 * CheckPosition
	 * 
	 * @param n Posicion a partir de la cual se crea el nodo de arbol.
	 * @return Nodo del arbol creado a partir de la posicion
	 * @throws InvalidPositionException si la posicion es nula, si fue eliminada
	 *                                  anteriormente o si no es de este arbol.
	 */
	private TNodo<Entry<String, PositionList<String>>> checkPosition(
			Position<Entry<String, PositionList<String>>> location) throws InvalidPositionException {
		TNodo<Entry<String, PositionList<String>>> retorna = null;
		try {
			retorna = (TNodo<Entry<String, PositionList<String>>>) location;
			if (location == null) {
				throw new InvalidPositionException("Error, posicion nula.");
			}
			if (location.element() == null) {
				throw new InvalidPositionException("El elemento fue eliminado previamente.");
			}
			return retorna;
		} catch (ClassCastException e) {
			throw new InvalidPositionException("El valor ingresado no es un nodo de este arbol.");
		}
	}

	/**
	 * Ancestro común más cercano entre dos rútulos
	 * 
	 * @param String path1
	 * @param String path2
	 * @return Posicion del ancestro comun mas cercano
	 */

	public Position<Entry<String, PositionList<String>>> closeCommonAncestor(String path1, String path2) {
		Position<Entry<String, PositionList<String>>> toReturn = null;
		Stack<Position<Entry<String, PositionList<String>>>> stack1 = new PilaEnlazada<Position<Entry<String, PositionList<String>>>>();
		Stack<Position<Entry<String, PositionList<String>>>> stack2 = new PilaEnlazada<Position<Entry<String, PositionList<String>>>>();
		Position<Entry<String, PositionList<String>>> aux = null;
		boolean flag = false;

		try {
			TNodo<Entry<String, PositionList<String>>> pos1 = checkPosition(this.FindShell(path1));// O(n)
			TNodo<Entry<String, PositionList<String>>> pos2 = checkPosition(this.FindShell(path2));// O(n)
			stack1 = returnRoot(stack1, pos1, mainArbol.root());
			stack2 = returnRoot(stack2, pos2, mainArbol.root());

			while (!flag && !stack1.isEmpty() && !stack2.isEmpty()) {
				if (stack1.top().element().getKey().equals(stack2.top().element().getKey())) {
					aux = stack1.pop();
					stack2.pop();
					if (!stack1.top().element().getKey().equals(stack2.top().element().getKey())) {
						flag = true;
					}
				}
			}
			toReturn = aux;
		} catch (EmptyTreeException | InvalidPositionException | EmptyStackException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	private Stack<Position<Entry<String, PositionList<String>>>> returnRoot(
			Stack<Position<Entry<String, PositionList<String>>>> stack, TNodo<Entry<String, PositionList<String>>> pos,
			Position<Entry<String, PositionList<String>>> lRoot) {
		if (pos.getPadre() != null) {
			stack.push(pos);
			stack = returnRoot(stack, pos.getPadre(), lRoot);
		} else {
			stack.push(pos);
		}
		return stack;
	}

	/**
	 * Mostrar Archivos
	 * 
	 * @param String path
	 * @return Archivos del directorio
	 */
	public String printFiles(String path) {
		String exit = "";
		try {
			Position<Entry<String, PositionList<String>>> ruta = this.FindShell(path);
			if (ruta == null) {
				exit = "No se encontró el nodo";
			} else {
				for (Position<Entry<String, PositionList<String>>> archivo : mainArbol.children(ruta)) {
					if (archivo.element().getValue() == null) {
						exit += archivo.element().getKey() + "\n";
					}
				}
			}
		} catch (EmptyTreeException | InvalidPositionException e) {
			e.printStackTrace();
		}
		return exit;
	}

	/**
	 * Mostrar archivos del subDirectorio
	 * 
	 * @param String path
	 * @return subDirectorios del directorio
	 */
	public String printSubDirectory(String path) {
		String exit = "";
		try {
			Position<Entry<String, PositionList<String>>> ruta = this.FindShell(path);
			if (ruta == null) {
				path = "No se encontro el nodo";
			} else {
				for (Position<Entry<String, PositionList<String>>> directorio : mainArbol.children(ruta)) {
					if (directorio.element().getValue() != null) {
						exit += directorio.element().getKey() + "\n";
					}
				}
			}
		} catch (EmptyTreeException | InvalidPositionException e) {
			e.printStackTrace();
		}

		return exit;
	}

	// find sehl sirve para verificar que existe dentro del arbol
	private Position<Entry<String, PositionList<String>>> FindShell(String p)
			throws EmptyTreeException, InvalidPositionException {
		Position<Entry<String, PositionList<String>>> check = null;// variable auxiliar para verificar que el retorno no
																	// sea nulo
		Position<Entry<String, PositionList<String>>> toReturn = null;

		if (mainArbol.isEmpty()) {
			throw new EmptyTreeException("el arbol no puede ser vacio");
		} else {
			if (p == null) {
				throw new InvalidPositionException("La ruta indicada es inexistente");
			} else {

				// si el elmento buscado es la raiz, entonces retorno es lo buscado y lo retorna
				if (mainArbol.root().element().getKey().equals(p)) {
					toReturn = mainArbol.root();
				} else {
					if (mainArbol.children(mainArbol.root()) != null && toReturn == null) {// si la raiz tiene hijos y
																							// no se encontro nada
						for (Position<Entry<String, PositionList<String>>> run : mainArbol.children(mainArbol.root())) {// buscar
																														// en
																														// cada
																														// hijo
																														// de
																														// la
																														// raiz
							check = find(run, p); // se llena el auxiliar con el supuesto nodo buscado
							if (check != null) {
								toReturn = check;// si lo encontro entonces lo retorna
							}
						}
					}
				}
			}

		}

		return toReturn;
	}

	private Position<Entry<String, PositionList<String>>> find(Position<Entry<String, PositionList<String>>> tempPos,
			String p) throws InvalidPositionException {
		Position<Entry<String, PositionList<String>>> check = null;// variable auxiliar para verificar que el retorno no
																	// sea nulo
		Position<Entry<String, PositionList<String>>> toReturn = null;

		// si el elmento buscado es encontrado lo retorna
		if (tempPos.element().getKey().equals(p)) {
			toReturn = tempPos;
		} else {// si no, si el nodo tiene hijos busca en cada uno de el
			try {
				if (mainArbol.children(mainArbol.root()) != null) {
					for (Position<Entry<String, PositionList<String>>> l : mainArbol.children(tempPos)) {
						check = find(l, p);// se llena el auxiliar con el supuesto nodo buscado
						if (check != null) {
							toReturn = check;
						} // si lo encontro entonces lo retorna
					}
				}
			} catch (InvalidPositionException | EmptyTreeException e) {
				e.printStackTrace();
			}
		} // Fin else
		return toReturn;
	}

	/**
	 * Obtener una direccion absoluta dado un directorio y una direccion relativa
	 * 
	 * @param String relative Direccion relativa
	 * @param String absolute Directorio
	 * @return Direccion Absoluta
	 */

	public String getAbsoluteAddress(String relative, String absolute) {
		String returnable = "";
		boolean finished = false;
		Stack<String> pila = new PilaEnlazada<String>();
		if (relative.charAt(0) != '.' && relative.charAt(0) != '\\') {// if we dont have "..\\" we dont do anything
			returnable = absolute.concat("\\" + relative);
		} else {
			// creates the stack
			String aux = "";
			int absoluteLength = absolute.length();
			// Remember we eliminate the \\ before add the directory to the stack
			for (int i = 0; i < absoluteLength; i++) {
				if (absolute.charAt(i) != '\\') {
					aux += absolute.charAt(i);
				} else {
					pila.push(aux);
					aux = "";// reset of the directory name
				}

			}
			// push the last directory in the stack
			pila.push(aux);
			// for every "\\" we will pop 1 item form the stack
			int relativeLength = relative.length();
			for (int i = 0; i < relativeLength && !finished; i++) {
				if (relative.charAt(0) == '\\') {
					try {
						pila.pop();

					} catch (EmptyStackException e) {
						e.printStackTrace();
					}
				}
				// eliminate the dots
				relative = relative.substring(1);
				// if finds a letter, ends.
				// this will return the directory without the \\ before the letter
				if (relative.charAt(0) != '.' && relative.charAt(0) != '\\') {
					finished = true;
				}
			}
			// start build of the returnable string
			while (!pila.isEmpty()) {
				try {
					String popeo = pila.pop();
					returnable = popeo + "\\" + returnable;
				} catch (EmptyStackException e) {
					e.printStackTrace();
				}
			}
			returnable = returnable.concat(relative);
		}

		return returnable;
	}

	/**
	 * Listar todos los archivos del sistema
	 * 
	 * @return Lista de archivos del sistema
	 */

	public String list() {
		String retorno = "";
		if (!mainArbol.isEmpty()) {
			try {
				Position<Entry<String, PositionList<String>>> raiz = mainArbol.root();
				retorno = listAux(raiz, retorno);
			} catch (EmptyTreeException e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	private String listAux(Position<Entry<String, PositionList<String>>> ruta, String toReturn) {
		try {
			for (Position<Entry<String, PositionList<String>>> p : mainArbol.children(ruta)) {
				if (p.element().getValue() == null) {
					toReturn += p.element().getKey() + "\n";
				}
			}
			for (Position<Entry<String, PositionList<String>>> p : mainArbol.children(ruta)) {
				if (p.element().getValue() != null) {
					toReturn = listAux(p, toReturn);
				}
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}

		return toReturn;
	}

	/**
	 * Grado del arbol
	 * 
	 * @param String path
	 * @return Grado del arbol ingresado
	 */

	public int degreeTree(String path) {
		int degreeT = 0;

		try {
			degreeT = degreeAux(mainArbol, mainArbol.root(), degreeT);
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return degreeT;
	}

	private int degreeAux(Tree<Entry<String, PositionList<String>>> tempTree,
			Position<Entry<String, PositionList<String>>> p, int toReturn) {
		int cantHijos = 0;

		if (p.element().getValue() != null) {
			cantHijos = p.element().getValue().size();
			toReturn = Math.max(cantHijos, toReturn); // si el grado de este nodo es mayor al del anterior lo cambia
			try {
				if (tempTree.children(p) != null) {// si el nodo tiene hijos tambien prueba con ellos
					for (Position<Entry<String, PositionList<String>>> pos : tempTree.children(p)) {
						toReturn = degreeAux(tempTree, pos, toReturn);
					}
				}
			} catch (InvalidPositionException e) {
				e.printStackTrace();
			}
		}

		return toReturn;
	}

	/**
	 * Grado del Nodo
	 * 
	 * @param String path
	 * @return Grado del nodo ingresado por parametro
	 */

	public int degreeNode(String path) {
		int degreeN = 0;

		try {
			Position<Entry<String, PositionList<String>>> p = this.FindShell(path);
			degreeN = p.element().getValue().size();
		} catch (EmptyTreeException | InvalidPositionException e) {
			e.printStackTrace();
		}

		return degreeN;
	}

	private int rootPathNode(int toReturn, TNodo<Entry<String, PositionList<String>>> p) {
		try {
			if (p.getPadre() != mainArbol.root()) {
				toReturn++;
				toReturn = rootPathNode(toReturn, p.getPadre());
			} else {
				toReturn++;
			}
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * Profunidad
	 * 
	 * @param String path
	 * @return Profundidad de un nodo
	 */

	public int depth(String path) {
		int toReturn = 1;
		TNodo<Entry<String, PositionList<String>>> p;
		try {
			p = checkPosition(this.FindShell(path));
			if (p.element().getKey() != mainArbol.root().element().getKey()) {
				toReturn = rootPathNode(toReturn, p);
			}

		} catch (InvalidPositionException | EmptyTreeException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * Altura de un arbol
	 * 
	 * @return Altura del arbol
	 */
	public int height() {
		int retorno = 0;
		try {
			if (mainArbol.isExternal(mainArbol.root())) {
				retorno = 0;
			} else {
				int h = 0;
				for (Position<Entry<String, PositionList<String>>> w : mainArbol.children(mainArbol.root())) {
					h = Math.max(h, depth(w.element().getKey()));
				}
				retorno = 1 + h;
			}
		} catch (InvalidPositionException | EmptyTreeException e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
package TDALista;

import java.util.Iterator;

import Auxiliar.BoundaryViolationException;
import Auxiliar.EmptyListException;
import Auxiliar.InvalidPositionException;

public class ListaDE<E> implements PositionList<E>{
	
	//Atributos de instancia
	private Dnode<E> header;
	private Dnode<E> trailer;
	protected int size;
	
	/**
	 * Constructor
	 * @returns Creo una nueva lista vacia
	 */
	public ListaDE() {
		header = new Dnode<E>();
		trailer = new Dnode<E>(null,this.header,null);
		header.setSiguiente(trailer);;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new EmptyListException("Lista vacia en metodo first");
		}
		return header.getSiguiente(); // retorno el siguiente xq el primer es dummy
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(isEmpty()) {
			throw new EmptyListException("Lista vacia en metodo last");
		}
		return trailer.getAnterior(); // retorno el anterior por que el ultimo es dummy
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Dnode<E> aux = this.checkPosition(p);
		aux = aux.getSiguiente();
		
		if(aux == this.trailer) {
			throw new BoundaryViolationException("No tiene siguiente en next");
		}
		return (Position<E>) aux;
	} 
	
	@Override
	public Position<E> prev(final Position<E> p) throws InvalidPositionException, BoundaryViolationException {
        Dnode<E> aux = this.checkPosition(p);
        aux = aux.getAnterior();
        if (aux == this.header) {
            throw new BoundaryViolationException("No tiene anterior en prev");
        }
        return aux;
    }
	@Override
	public void addFirst(final E element) {
        Dnode<E> aux = new Dnode<E>(element, header, header.getSiguiente());
        header.getSiguiente().setAnterior(aux);
        header.setSiguiente(aux);
        size++;
    }

	@Override
	public void addLast(final E element) {
        Dnode<E> aux = new Dnode<E>(element, trailer.getAnterior(), trailer);
        trailer.getAnterior().setSiguiente(aux);
        trailer.setAnterior(aux);
        size++;
    }

	@Override
	public void addAfter(final Position<E> p, final E element) throws InvalidPositionException {
        Dnode<E> aux = this.checkPosition(p);
        Dnode<E> nuevo = new Dnode<E>(element, aux, aux.getSiguiente());
        aux.getSiguiente().setAnterior(nuevo);
        aux.setSiguiente(nuevo);
        size++;
    }
	@Override
	 public void addBefore(final Position<E> p, final E element) throws InvalidPositionException {
        Dnode<E> aux = this.checkPosition(p);
        Dnode<E> nuevo = new Dnode<E>(element, aux.getAnterior(), aux);
        aux.getAnterior().setSiguiente(nuevo);
        aux.setAnterior(nuevo);
        size++;
    }

	@Override
	public E remove(final Position<E> p) throws InvalidPositionException {
        Dnode<E> aeliminar = this.checkPosition(p);
        aeliminar.getSiguiente().setAnterior(aeliminar.getAnterior());
        aeliminar.getAnterior().setSiguiente(aeliminar.getSiguiente());
        size--;
        return aeliminar.element();
    }
    
    @Override
    public E set(final Position<E> p, final E element) throws InvalidPositionException {
        final Dnode<E> tochange = this.checkPosition(p);
        final E aux = tochange.element();
        tochange.setElemento(element);
        return aux;
    }

	@Override
	 public Iterator<E> iterator() {
        return new ElementIterator<E>(this);
    }

	@Override
	 public Iterable<Position<E>> positions() {
        final PositionList<Position<E>> retorno = new ListaDE<Position<E>>();
        if (!this.isEmpty()) {
            try {
                Position<E> recorro;
                for (recorro = this.first(); recorro != this.last(); recorro = this.next(recorro)) {
                	retorno.addLast(recorro);
                }
                retorno.addLast(recorro);
            }
            catch (InvalidPositionException | BoundaryViolationException | EmptyListException e) {
                e.printStackTrace();
            }
        }
        return retorno;
    }
	
	private Dnode<E> checkPosition(final Position<E> p)throws InvalidPositionException {
        if (p == null || p == this.header || p == this.trailer || this.isEmpty()) {
            throw new InvalidPositionException("Posicion Invalida");
        }else
        try {
            final Dnode<E> temp = (Dnode<E>)p;
            if (temp.getAnterior() == null || temp.getSiguiente() == null) {
                throw new InvalidPositionException("Posicion Invalida");
            }
            return temp;
        }
        catch (ClassCastException e) {
            throw new InvalidPositionException("Posicion Invalida");
        }
    }
}

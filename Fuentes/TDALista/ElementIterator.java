package TDALista;

import java.util.NoSuchElementException;

import Auxiliar.BoundaryViolationException;
import Auxiliar.EmptyListException;
import Auxiliar.InvalidPositionException;

import java.util.Iterator;

public class ElementIterator<E> implements Iterator<E>
{
    PositionList<E> lista;
    Position<E> cursor;
    
    public ElementIterator(final PositionList<E> lista) {
        try {
            this.lista = lista;
            if (lista.isEmpty()) {
                this.cursor = null;
            }
            else {
                this.cursor = lista.first();
            }
        }
        catch (EmptyListException ex) {}
    }
    
    @Override
    public boolean hasNext() {
        return this.cursor != null;
    }
    
    @Override
    public E next() throws NoSuchElementException {
        E toReturn = null;
        try {
            if (this.cursor == null) {
                throw new NoSuchElementException();
            }
            toReturn = this.cursor.element();
            this.cursor = ((this.cursor == this.lista.last()) ? null : this.lista.next(this.cursor));
        }
        catch (InvalidPositionException ex) {}
        catch (BoundaryViolationException ex2) {}
        catch (EmptyListException ex3) {}
        return toReturn;
    }
    
    @Override
    public void remove() {
    }
}



/*package TDAIterador;


import java.util.*;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;


public class ElementIterator<E> implements Iterator<E>{
	protected PositionList<E> lista; // Lista a iterar
	protected Position<E> cursor;
	
	public ElementIterator(PositionList<E> L) {
		lista = L;
		if(lista.isEmpty()) {
			cursor = null;
		}else {
			try {
				cursor = lista.first();
			} catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean hasNext() {
		return cursor != null;
	}

	@Override
	public E next() throws NoSuchElementException{
		E salida = null;
		if(cursor == null) {
			throw new NoSuchElementException("Error: No hay siguiente");
		}
		try {
			E toReturn = cursor.element(); //Salvo el elemento
			cursor = (cursor == lista.last()) ? null : lista.next(cursor);
			salida = toReturn;
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
				e.printStackTrace();
		} //Avanzo una posicion con el comando nazi del profe		
		
		return salida;
	}
	
}*/

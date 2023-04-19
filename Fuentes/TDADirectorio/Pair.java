package TDADirectorio;

/**
 * Define los datos y operaciones aplicables sobre una entrada.
 * @author Juan Ignacio S�nchez.
 * @param <K> Tipo de dato a almacenar en la clave de la entrada.
 * @param <V> Tipo de dato a almacenar en el valor de la entrada.
 */
public class Pair<K,V> implements Entry<K,V> {
	
	/**
	 * Clave de la entrada.
	 */
	private K clave;
	
	/**
	 * Valor de la entrada.
	 */
	private V valor;
	
	/**
	 * Inicializa una entrada con clave y valor.
	 * @param clave Clave a establecer como la clave de la entrada.
	 * @param valor Valor a establecer como el valor de la entrada..
	 */
	public Pair (K clave,V valor) {
		this.clave=clave;
		this.valor=valor;
	}
	
	@Override
	public K getKey() {
		return clave;
	}
	
	@Override
	public V getValue() {
		return valor;
	}
	
	
	public String toString() {
		return "("+clave+","+valor+")";
		
	}
}
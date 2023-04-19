package TDADirectorio;

/**
 * Define los datos y operaciones aplicables sobre una entrada.
 * @author Juan Ignacio S�nchez.
 * @param <K> Tipo de dato a almacenar en la clave de la entrada.
 * @param <V> Tipo de dato a almacenar en el valor de la entrada.
 */
public interface Entry<K,V> {
	
	/**
	 * Devuelve la clave de la entrada.
	 * @return Clave de la entrada.
	 */
	public K getKey();
	
	/**
	 * Devuelve el valor de la entrada.
	 * @return Valor de la entrada.
	 */
	public V getValue();
}

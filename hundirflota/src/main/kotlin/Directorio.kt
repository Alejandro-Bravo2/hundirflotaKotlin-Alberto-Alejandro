package org.alejandro.kotlin
import java.io.File

/**
 * Clase para guardar el directorio y trabajar con el
 * @param rutaDirectorio Es la ruta absoluta del directorio al que se va a comprobar si existe o no
 */
class Directorio(val rutaDirectorio : String){
    val ruta = File(rutaDirectorio)
    /** Metodo encargado de revisar si esa ruta del directorio existe o no
    * Return: Devuelve true en caso de que exista y false en caso de que no exista
     */
    fun revisarDirectorio () : Boolean{
        val existencia = this.ruta.isDirectory()
        return existencia
    }
}




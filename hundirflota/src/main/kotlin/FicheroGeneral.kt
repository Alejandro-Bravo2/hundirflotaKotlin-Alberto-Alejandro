package org.alejandro.kotlin
import java.io.File
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/** Clase para manejar el fichero general
 * @param nombreFichero: Ruta del fichero general no hace falta usar ruta absoluta o relativa
 * solo usa nombres por ejemplo hola.json
 */
class FicheroGeneral(val nombreFichero:String) {
    /**Metodo para crear el fichero general
     *
     */
    fun crearFichero() {
        val diccionarioConfig = mutableMapOf<String, Any>(
            "nombre_partida" to "Alejandro",
            "dimensiones_tablero" to 5,
            "tiempo_refresco" to 2,
            "tiempo_ataque" to 30,
            "configuracion_barcos" to mapOf<String, Any>(
                "Portaaviones" to mapOf<String, Any>("tamaño" to 5, "numero" to 1),
                "Submarino" to mapOf("tamaño" to 2, "numero" to 2),
                "Destructor" to mapOf("tamaño" to 1, "numero" to 3)
            ),
            "turnos_jugados" to 0,
            "turno_actual" to "j1"
        )
        val gson = Gson()
        val jsonString = gson.toJson(diccionarioConfig)

        val file = File(this.nombreFichero)
        file.writeText(jsonString)
    }

    /** Este metodo sirve para leer el contenido del fichero general
     * @return: Devuelve el diccionario que contiene el fichero general
     */
    fun leerFichero(): Map<String, Any> {
        val fichero = File(this.nombreFichero)
        val jsonString = fichero.readText() // leemos el contenido del fichero
        val gson = Gson() // Gson sirve para serializar nuestro diccionario a un tipo de dato que pueda manejar Json
        val formato = object : TypeToken<Map<String, Any>>() {}.type
        /*ESTA LINEA ES SUPER IMPORTANTE PORQUE COMO KOTLIN NO PUEDE MANEJAR
        VALORES QUE NO LE HAYAMOS INDICADO, SI A LA HORA DE LEER NO LE DECIMOS
         EL TIPO DE TIPO NOS DARÍA UN ERROR POR LO QUE TENEMOS QUE INDICARLE
          EL TIPO DE MAPA QUE VAMOS A UTILIZAR
          TypeToken lo que hace es capturar la informacion que vaya entre {} y
          la convierte en un diccionario de string to any
        */
        return gson.fromJson(jsonString, formato)
        // Devuelve el contenido del fichero usando el tipo que le indicamos con formato

    }

    /** Este metodo sirve para actualizar el contenido del diccionario
     * @param diccionario: Diccionario el cual se va a escribir en la ruta del fichero general
     */
    fun escribirFichero(diccionario: Map<String, Any> ) {

        val gson = Gson()
        val jsonString = gson.toJson(diccionario)
        val fichero = File(this.nombreFichero)
        fichero.writeText(jsonString)
    }
}


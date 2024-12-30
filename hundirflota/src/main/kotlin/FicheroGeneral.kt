package org.alejandro.kotlin
import java.io.File
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.annotations.ApiStatus.NonExtendable

/** Clase para manejar el fichero general
 */
class FicheroGeneral() {
    /**Metodo para crear el fichero general
     *
     */
    val nombreFichero:String = "general.json"
    fun crearFichero() {
        val diccionarioConfig = mutableMapOf<String, Any>(
            "nombre_partida" to "None",
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
    fun leerFichero(): MutableMap<String, Any> {
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
        val diccionario : MutableMap<String, Any> = gson.fromJson(jsonString, formato)
        diccionario.forEach{ (key, value) ->
            /*Esto lo necesitamos ya que cuando lo leamos por defecto serán todos los valores double
            y los necesitamos cambiar para poder trabajar con ellos como deseamos, para por ejemplo
            los tiempo de espera
             */
            if (value is Double) {
                diccionario[key] = value.toInt()
            }
        }
        return diccionario
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

    /**
     * Esta función revisará si el turno ha cambiado al otro jugdor,
     * en caso de que si haya cambiado devolvera True y en caso
     * de que no haya cambiado devolverá False
     * @param jugador: Jugador es un string que será j1 o j2,
     * pero este jugador es el que esta en esta terminal,
     */
    fun revisarCambioTurno(jugador:String) : Boolean {
        val diccionarioConfig : MutableMap<String, Any> = this.leerFichero()
        if (diccionarioConfig["turno_actual"].toString() == jugador) {
            return true
        } else {
            return false
        }
    }

    /**
     * Este metodo se encargará de verificar si el fichero general
     * esta creado o no
     * @return: Devolverá true en caso de que si esté creado o false en caso de que no esté creado.
     */
    fun comprobarExistencia() : Boolean {
        if (File(this.nombreFichero).exists()){
            return true
        } else {
            return false
        }
    }

    /**
     * Metodo encargado de revisar que jugadores están esperando
     * @return: Devuelve el nombre del jugador que serían o j1 o j2. En caso de que ya
     * existiese una partida devolvería None
     */
    fun revisarJugadores(): String{
        val diccionario = leerFichero()
        if(diccionario["nombre_partida"].toString() == "None"){
            return "j1"
        } else if (diccionario["nombre_partida"].toString() == "j1"){
            return "j2"
        } else{ // Esto simplemente lo he puesto porque el compilador necesita ver que siempre se devolverá algo
            return "None"
        }
    }

    /**
     * Metodo encargado de agregar el nombre del actual jugador al
     * nombre Partida del diccionario, en caso que exista por ejemplo
     * j1 pues agregará j2
     */
    fun agregarJugadorAPartida(){
        var diccionario = leerFichero()
        if (diccionario["nombre_partida"].toString() == "None") {
            diccionario["nombre_partida"] = "j1"
            escribirFichero(diccionario)
        } else if (diccionario["nombre_partida"].toString() == "j1") {
            diccionario["nombre_partida"] = "j1 vs j2"
            escribirFichero(diccionario)
        }
    }
}

/*
BACKUP POR SI ACASO, ESTE ES FUNCIONAL TAMBIEN PERO LE DEBES INTRODUCIR EL NOMBRE TU


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
    fun leerFichero(): MutableMap<String, Any> {
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
        val diccionario : MutableMap<String, Any> = gson.fromJson(jsonString, formato)
        diccionario.forEach{ (key, value) ->
            /*Esto lo necesitamos ya que cuando lo leamos por defecto serán todos los valores double
            y los necesitamos cambiar para poder trabajar con ellos como deseamos, para por ejemplo
            los tiempo de espera
             */
            if (value is Double) {
                diccionario[key] = value.toInt()
            }
        }
        return diccionario
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

fun main(){
    val ficheroGeneral = FicheroGeneral("general.json")
    ficheroGeneral.crearFichero()
}
 */



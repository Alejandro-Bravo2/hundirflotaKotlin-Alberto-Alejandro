package org.alejandro.kotlin

import java.io.File
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FicheroUsuario(val nombreJugador: String) {
    val nombreFichero = nombreJugador + ".json" // ruta del fichero del jugador

    /**Metodo para crear el fichero del usuario
     *
     */
    fun crearFichero() {
        val configuracionUsuario = mutableMapOf<String,Any>("nombre" to nombreJugador, "tablero" to mutableListOf<MutableList<String>>(
            mutableListOf("B", "~", "B", "~", "B"), mutableListOf("B", "B", "B", "B", "B"), mutableListOf("~", "~", "~", "~", "~"),
            mutableListOf("B", "~", "B", "B", "~"), mutableListOf("B", "~", "~", "~", "~")
        ), "barco" to mutableMapOf<String, Any>(
            "portaaviones1" to mutableMapOf(
                "coordenadas" to mutableListOf<MutableList<Int>>(mutableListOf(1,0), mutableListOf(1,1), mutableListOf(1,2), mutableListOf(1,3), mutableListOf(1,4)),
                "estado" to mutableMapOf<String, String>("[1,0]" to "B", "[1,1]" to "B", "[1,2]" to "B", "[1,3]" to "B", "[1,4]" to "B")
            ),
            "submarino1" to mutableMapOf<String, Any>(
                "coordenadas" to mutableListOf<MutableList<Int>>(mutableListOf(3,0), mutableListOf(4,0)),
                "estado" to mutableMapOf<String, String>("[3,0]" to "B", "[4,0]" to "B"),
            ),
            "submarino2" to mutableMapOf<String, Any>(
                "coordenadas" to mutableListOf<MutableList<Int>>(mutableListOf(3,2), mutableListOf(3,3)),
                "estado" to mutableMapOf<String, String>("[3,2]" to "B", "[3,3]" to "B")
            ),
            "destructor1" to mutableMapOf<String, Any>(
                "coordenadas" to mutableListOf<MutableList<Int>>(mutableListOf<Int>(0,2)),
                "estado" to mutableMapOf<String, String>("[0,2]" to "B")
            ),
            "destructor2" to mutableMapOf<String, Any>(
                "coordenadas" to mutableListOf<MutableList<Int>>(mutableListOf<Int>(0,0)),
                "estado" to mutableMapOf<String, String>("[0,0]" to "B")
            ),
            "destructor3" to mutableMapOf<String, Any>(
                "coordenadas" to mutableListOf<MutableList<Int>>(mutableListOf<Int>(0,4)),
                "estado" to mutableMapOf<String, String>("[0,4]" to "B")
            )),
            "movimientos" to mutableListOf<Any>())
        //Esto era lo que yo tenía antes lo pongo aqui por si acaso "movimientos" to mutableListOf<Any>(mutableMapOf<String, Any>()))

        val gson = Gson()
        val jsonString = gson.toJson(configuracionUsuario)
        val nombreFichero = nombreJugador + ".json"
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
        // posiblemente el return lo deba cambiar para que no devuelva double pero vamos a probar primero.
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
     * Metodo encargo de verificar si el fichero del usuario existe o no
     * @return: True en caso de que si exista y False en caso de que no exista
     */
    fun comprobarExistencia() : Boolean {
        if (File(this.nombreFichero).exists()) {
            return true
        } else {
            return false
        }
    }
}


/*
BACKUP
class FicheroUsuario(val nombreFichero: String) {
    /**Metodo para crear el fichero del usuario
     *
     */
    fun crearFichero() {
        val configuracionUsuario = mutableMapOf<String,Any>("nombre" to "j1", "tablero" to mutableListOf<List<String>>(), "barco" to mutableMapOf<String, Any>(
            "portaaviones1" to mutableMapOf(
                "coordenadas" to mutableListOf<Any>(mutableListOf<List<String>>()),
                "estado" to mutableMapOf<String, String>()
            ),
            "submarino1" to mutableMapOf<String, Any>(
                "coordenadas" to mutableListOf<Any>(),
                "estado" to mutableMapOf<String, String>()
            ),
            "submarino2" to mutableMapOf<String, Any>(
                "coordenadas" to mutableListOf<Any>(),
                "estado" to mutableMapOf<String, String>()
            ),
            "destructor1" to mutableMapOf<String, Any>(
                "coordenadas" to mutableListOf<Any>(),
                "estado" to mutableMapOf<String, String>()
            ),
            "destructor2" to mutableMapOf<String, Any>(
                "coordenadas" to mutableListOf<Any>(),
                "estado" to mutableMapOf<String, String>()
            ),
            "destructor3" to mutableMapOf<String, Any>(
                "coordenadas" to mutableListOf<Any>(),
                "estado" to mutableMapOf<String, String>()
            ),
            "movimientos" to mutableListOf<Any>(mutableMapOf<String, Any>())))

        val gson = Gson()
        val jsonString = gson.toJson(configuracionUsuario)

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
        // posiblemente el return lo deba cambiar para que no devuelva double pero vamos a probar primero.
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

 */
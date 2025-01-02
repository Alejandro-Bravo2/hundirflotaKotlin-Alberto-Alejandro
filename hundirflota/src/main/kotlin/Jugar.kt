package org.alejandro.kotlin

fun jugar(Fjugador: FicheroUsuario, Fgeneral : FicheroGeneral, nombreJugador: String){
    val jugador2 = crearInstanciaOtroJugador(nombreJugador)
    var datosJugador2 = jugador2.leerFichero()
    var tableroAtaque : MutableMap<String,Any>
    var tableroReal : MutableList<MutableList<String>>
    var tableroAtaqueActualizado : MutableList<MutableList<String>>
    val ganador = false
    while(ganador == false){ //tengo que hacer una función que compruebe que todos los estados de los barcos esten vivos o no
        var diccionario = Fgeneral.leerFichero()
        if (diccionario["turno_actual"] == nombreJugador){
            datosJugador2 = jugador2.leerFichero().toMutableMap()
            tableroReal = datosJugador2["tablero"] as MutableList<MutableList<String>>
            tableroAtaque = actualizarTablero(datosJugador2)
            tableroAtaqueActualizado = datosJugador2["tablero"] as MutableList<MutableList<String>>
            atacante(tableroReal,datosJugador2,Fjugador,jugador2,Fgeneral,nombreJugador)

        } else{
            // crear funcion de posicion defensa
        }
    }

}

fun actualizarTablero(diccionarioJugador2: MutableMap<String, Any>) : MutableMap<String, Any>{
    var tablero = diccionarioJugador2["tablero"] as MutableList<MutableList<String>>
    for (filas in tablero){
        for (columna in filas.indices){
            if (filas[columna] == "B"){
                filas[columna] = "~"
            }
        }
    }
    diccionarioJugador2["tablero"] = tablero
    return diccionarioJugador2
}

fun main(){
    val configuracionUsuario = mutableMapOf<String,Any>("nombre" to "j1", "tablero" to mutableListOf<MutableList<String>>(
        mutableListOf("B", "~", "B", "~", "B"), mutableListOf("B", "T", "H", "H", "B"), mutableListOf("~", "~", "~", "~", "~"),
        mutableListOf("B", "~", "B", "B", "~"), mutableListOf("B", "~", "~", "~", "~")
    ), "barco" to mutableMapOf<String, Any>(
        "portaaviones1" to mutableMapOf(
            "coordenadas" to mutableListOf<MutableList<Int>>(mutableListOf(1,0), mutableListOf(1,1), mutableListOf(1,2), mutableListOf(1,3), mutableListOf(1,4)),
            "estado" to mutableMapOf<String, String>("[1, 0]" to "B", "[1, 1]" to "B", "[1, 2]" to "B", "[1, 3]" to "B", "[1, 4]" to "B")
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
        ),
        "movimientos" to mutableListOf<Any>(mutableMapOf<String, Any>())))


    val actualizado = actualizarTablero(configuracionUsuario)
    println(actualizado)
}
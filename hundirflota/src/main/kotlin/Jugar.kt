package org.alejandro.kotlin

fun jugar(Fjugador: FicheroUsuario, Fgeneral : FicheroGeneral, nombreJugador: String){
    val jugador2 = crearInstanciaOtroJugador(nombreJugador)
    var datosJugador2 = jugador2.leerFichero()
    var tableroAtaque : MutableMap<String,Any>
    var datosJugador1 = Fjugador.leerFichero()
    var tableroReal : MutableList<MutableList<String>>
    var tableroDefensor : MutableList<MutableList<String>>
    var tableroDefensorDict : MutableMap<String,Any>
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
            datosJugador1 = Fjugador.leerFichero().toMutableMap()
            tableroDefensorDict = actualizarTablero(datosJugador1)
            tableroDefensor  = datosJugador1["tablero"] as MutableList<MutableList<String>>
            defensor(tableroDefensor, datosJugador1, Fjugador, jugador2, Fgeneral,nombreJugador)
        }
    }

}

fun defensor(tableroDefensorActualizado: MutableList<MutableList<String>>,tableroDefensor : MutableMap<String, Any>, Fjugador: FicheroUsuario, Fjugador2 : FicheroUsuario, Fgeneral: FicheroGeneral, nombreJugador: String){
    var datosDiccionario = Fjugador.leerFichero()
    var cambioJugador = false
    var otroJugador : String
    if (nombreJugador == "j1"){
        otroJugador = "j2"
    } else{
        otroJugador = "j1"
    }
    while (cambioJugador == false ){
        datosDiccionario = Fjugador.leerFichero()
        println("Turno del jugador $otroJugador.")
        println("Tablero de estado: ")
        for (fila in tableroDefensorActualizado){
            println(fila)
        }
        println("Esperando ataque...")
        tiempoEspera()
        cambioJugador = Fgeneral.revisarCambioTurno(nombreJugador)
        limpiarPantalla()
    }
    println("Turno del jugador $otroJugador.")
    println("Tablero de estado:")
    var diccionarioSinOcultar = Fjugador.leerFichero().toMutableMap()
    var diccionarioOculto = actualizarTablero(diccionarioSinOcultar)
    var diccionarioOtroJugador = Fjugador2.leerFichero().toMutableMap()
    for (fila in diccionarioSinOcultar){
        println(fila)
    }
    var (ultimoAtaque : String, resultado : String) = encontrarUltimoMovimiento(diccionarioOtroJugador)
    println("Ataque: $ultimoAtaque")
    println("Resultado del ataque: $resultado")
    tiempoEspera()
    limpiarPantalla()
}

fun encontrarUltimoMovimiento(diccionarioJugador: MutableMap<String, Any>) : Pair<String,String>{
    var coordenadas = diccionarioJugador["movimientos"] as MutableList<MutableList<Any>>
    var ultima = coordenadas.last() as MutableMap<String, String>
    var ultimaCoordenada = ultima["coordenadas"] as String
    var resultado = ultima["resultado"] as String
    return Pair(ultimaCoordenada,resultado)
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


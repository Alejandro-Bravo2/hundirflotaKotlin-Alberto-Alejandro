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

    var nombreGeneral : String
    var comprobacionGanador : Boolean
    var ganador = false
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
        if (nombreJugador == "j1"){
            var (nombreGanador, comprobacionGanador) = revisarGanadorPartida(Fjugador,jugador2)
            ganador = comprobacionGanador
            if (ganador){
                println("El ganador es $nombreGanador")
            }
        } else{
            var (nombreGanador, comprobacionGanador) = revisarGanadorPartida(jugador2,Fjugador)
            ganador = comprobacionGanador
            if (ganador){
                println("El ganador es $nombreGanador")
            }
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
    var tableroOcultoDefensor = diccionarioOculto["tablero"] as MutableList<MutableList<String>>
    for (fila in tableroOcultoDefensor){
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
    var ultimaCoordenada = ultima["coordenada"] as String
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

fun revisarGanadorPartida(Fjugador1 : FicheroUsuario, Fjugador2: FicheroUsuario) : Pair<String, Boolean>{
    var diccionarioJugador1 = Fjugador1.leerFichero() as MutableMap<String,Any>
    var diccionarioJugador2 = Fjugador2.leerFichero() as MutableMap<String,Any>

    val numeroBarcos = 6 //Este es el número de barcos
    var barcosHundidosComprobado = 0

    //Revisar jugador1
    var barcosJug1 = diccionarioJugador1["barco"] as MutableMap<String,Any>
    for (barco in barcosJug1.keys){
        var barcoHundido = false
        var barcoActual = barcosJug1[barco] as MutableMap<String,Any>
        var estados = barcoActual["estado"] as MutableMap<String,String>
        for (estadoBarco in estados.values){
            if (estadoBarco == "H"){
                barcoHundido = true
            }
        }
        if (barcoHundido){
            barcosHundidosComprobado ++
        }
    }




    //Jugador 2
    var barcosHundidosConfirmado2 = 0
    var barcosJug2 = diccionarioJugador2["barco"] as MutableMap<String,Any>
    for (barco in barcosJug2.keys){
        var barcoHundido = false
        var barcoActual = barcosJug2[barco] as MutableMap<String,Any>
        var estados = barcoActual["estado"] as MutableMap<String,String>
        for (estadoBarco in estados.values){
            if (estadoBarco == "H"){
                barcoHundido = true
            }
        }
        if (barcoHundido){
            barcosHundidosConfirmado2 ++
        }
    }
    if (barcosHundidosComprobado == numeroBarcos){
        return Pair("j2", true)
    }
    else if (barcosHundidosConfirmado2 == numeroBarcos){
        return Pair("j1", true)
    } else{
        return Pair("None", false)
    }
}


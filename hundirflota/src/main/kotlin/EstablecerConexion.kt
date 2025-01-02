package org.alejandro.kotlin

fun establecerConexion(){
    val general = FicheroGeneral()
    val existencia = general.comprobarExistencia()
    if (!existencia){
        general.crearFichero()
    }
    val nombreJugador = general.revisarJugadores()
    general.agregarJugadorAPartida()
    var diccionarioGeneral = general.leerFichero()
    while (diccionarioGeneral["nombre_partida"] != "j1 vs j2"){
        println("Estas en la cola de espera...")
        tiempoEspera()
        diccionarioGeneral = general.leerFichero()
    }

    val ficheroJugador = FicheroUsuario(nombreJugador)
    val existenciaFicheroJugador = ficheroJugador.comprobarExistencia()
    if (!existenciaFicheroJugador){
        ficheroJugador.crearFichero()
    }
    tiempoEspera()
    jugar(ficheroJugador, general, nombreJugador)

}
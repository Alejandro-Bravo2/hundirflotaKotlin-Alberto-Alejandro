package org.alejandro.kotlin

fun establecerConexion(FicheroJugadorMod : FicheroUsuario, PosibleNombreJugador : String){

    var nombreJugador : String
    var ficheroJugador : FicheroUsuario

    val general = FicheroGeneral()
    val existencia = general.comprobarExistencia()
    if (!existencia){
        general.crearFichero()
    }
    if (PosibleNombreJugador == "None"){
        nombreJugador = general.revisarJugadores()
        general.agregarJugadorAPartida()
        ficheroJugador = FicheroUsuario(nombreJugador)
        val existenciaFicheroJugador = ficheroJugador.comprobarExistencia()
        if (!existenciaFicheroJugador){
            ficheroJugador.crearFichero()
        }
    } else{
        nombreJugador = PosibleNombreJugador
        ficheroJugador = FicheroJugadorMod
    }

    var diccionarioGeneral = general.leerFichero()
    while (diccionarioGeneral["nombre_partida"] != "j1 vs j2"){
        println("Estas en la cola de espera...")
        tiempoEspera()
        diccionarioGeneral = general.leerFichero()
    }


    tiempoEspera()
    jugar(ficheroJugador, general, nombreJugador)

}
package org.alejandro.kotlin

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


fun main(){
    val general = FicheroGeneral()
    val datos = general.revisarJugadores()
    println(datos)
    general.agregarJugadorAPartida()
    val ficheroJugador = FicheroUsuario(datos)
    ficheroJugador.crearFichero()
    val partida = ficheroJugador.leerFichero()
    println(partida)
}
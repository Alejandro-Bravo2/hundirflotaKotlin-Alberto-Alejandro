package org.alejandro.kotlin

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(){
    val jugador1 = FicheroUsuario("jugador1.json")
    jugador1.crearFichero()
    val datos1 = jugador1.leerFichero()
    println(datos1)
    val general = FicheroGeneral()
    general.crearFichero()
    cambioTurno()
}
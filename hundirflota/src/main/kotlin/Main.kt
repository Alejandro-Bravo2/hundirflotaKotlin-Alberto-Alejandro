package org.alejandro.kotlin

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


fun main(){
    var Fjugador : FicheroUsuario = FicheroUsuario("j9000")
    var nombrejugador : String = "None"
    val opcionMenu = menu()
    when (opcionMenu) {
        1 -> {
            establecerConexion(Fjugador, nombrejugador)
        }
        2 -> {
            configurarBarcos()
        }
        3 -> {
            return
        }
    }
}

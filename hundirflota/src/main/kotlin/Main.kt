package org.alejandro.kotlin

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


fun main(){
    val opcionMenu = menu()
    when (opcionMenu) {
        1 -> {
            establecerConexion()
        }
        2 -> {
            configurarBarcos()
        }
        3 -> {
            return
        }
    }
}

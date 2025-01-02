package org.alejandro.kotlin

fun limpiarPantalla(){
    if (System.getProperty("os.name").contains("Windows")) {
        Runtime.getRuntime().exec("cmd /c cls")
    } else{
        Runtime.getRuntime().exec("clear")
    }
}
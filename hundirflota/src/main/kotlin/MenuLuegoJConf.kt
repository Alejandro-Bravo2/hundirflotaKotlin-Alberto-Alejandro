package org.alejandro.kotlin


fun menuLuegoConf(Fjugador : FicheroUsuario){
    val rangoOpciones = Regex("[1-3]")
    limpiarPantalla()
    println("-----MENU------")
    println("Has establecido una nueva configuración para tu partida. ¿Que deseas hacer ahora?")
    println("1- Jugar")
    println("2- Modificar mi partida")
    println("3- Salir")

    var entrada : Int = 9
    while (entrada == 9) {
        try {
            print("Eliga una opción que esté dentro del menú => ")
            entrada = readln().toInt()
            if (!rangoOpciones.matches(entrada.toString())) {
                entrada = 9
                println("Tienes que introducir un valor que esté dentro del rango de las opciones del menú")
            }
        } catch (e: NumberFormatException) {
            println("Por fabor debes introducir un valor numerico")
        }
    }

    when (entrada) {
        1 -> {
            establecerConexion(Fjugador)
        }
        2 -> {
            var FicheroJugador = configurarBarcos()
        }
        3 -> {
            return
        }
    }
}


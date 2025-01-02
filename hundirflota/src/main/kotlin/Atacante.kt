package org.alejandro.kotlin
import kotlinx.coroutines.*


fun atacante(tableroRealAtacante : MutableList<MutableList<String>>,diccionarioAtacante: MutableMap<String, Any>,FjugadorACTUAL : FicheroUsuario ,Fjugador2 : FicheroUsuario, Fgeneral : FicheroGeneral, nombreJugador: String ){
    var listaCoordenadas : MutableList<Int> = mutableListOf()

    println("Turno del jugador $nombreJugador.")
    println("Tablero de ataque:")
    var tableroOculto = actualizarTablero(diccionarioAtacante) as MutableMap<String, Any>
    var tableroFilas = tableroOculto["tablero"] as MutableList<MutableList<String>>
    for (filas in tableroFilas){
        println(filas)

    }
    val datosGeneralDict = Fgeneral.leerFichero()
    val segundosMax = datosGeneralDict["tiempo_ataque"] as Int
    println("--- Tiempo de espera maximo: $segundosMax  ---")
    val tiempoInicio = System.currentTimeMillis()
    val tiempoFin = System.currentTimeMillis()
    print("Introduce coordenadas para atacar(fila,columna): ")


    val coordenadas = readln()
    /* Esta parte sería la de esperar como máximo 30 segundos pero por ahora lo voy a dejar
    while (((tiempoFin - tiempoInicio) / 1000) < segundosMax){
        println("AAA")
        val datos = readln()
    }*/
    limpiarPantalla()
    val nombreRival : String
    if (nombreJugador == "j1"){
        nombreRival = "j2"
    } else{
        nombreRival = "j1"
    }
    var ejes : Int
    for (coor in coordenadas.indices){
        if (coordenadas[coor].isDigit()){
            ejes = coordenadas[coor].toString().toInt()
            if (ejes >= 0 && ejes <= 4){
                listaCoordenadas.add(ejes)
            }
        }
    }
    if (listaCoordenadas.size != 2){
        println("Has introducido mal las coordenadas, ATAQUE FALLIDO, Por defecto atacará a 0,0")
        listaCoordenadas.clear()
        listaCoordenadas = mutableListOf(0,0)
    }
    println("Turno del jugador $nombreJugador")
    val ejex = listaCoordenadas[0]
    val ejey = listaCoordenadas[1]

    val coordenadasParaComprobacionDiccionario = "[" + ejex + "," + ejey + "]"

    var barcos =  diccionarioAtacante["barco"] as MutableMap<String, Any>
    var diccionarioEsteJugador = FjugadorACTUAL.leerFichero() as MutableMap<String,Any>
    val (diccionarioJugENEMIGO, diccionarioJug1, resultadoAtaque) = actualizarTableroAtaque(diccionarioEsteJugador, diccionarioAtacante, coordenadasParaComprobacionDiccionario, ejex, ejey)

    FjugadorACTUAL.escribirFichero(diccionarioJug1)
    Fjugador2.escribirFichero(diccionarioJugENEMIGO)

    var diccionarioAtacanteActualizadoOculto = actualizarTablero(diccionarioJugENEMIGO) as MutableMap<String, Any>
    var tableroOcultoActualizado = diccionarioAtacanteActualizadoOculto["tablero"] as MutableList<MutableList<String>>
    for (fila in tableroOcultoActualizado){
        println(fila)
    }
    println("Ataque $ejex, $ejey")
    println("Resultado del ataque: $resultadoAtaque")

    cambioTurno()
}


/**
 * Función encargada de añadir el ataque al tablero, actualizar estados, actualizar
 * estado de la coordenada de dicho barco y añadir movimiento
 * @param configuracionUsuario: Diccionario con toda la configuración del usuario
 * @param coordenadasConcadenadas: Coordenadas concadenadas por ejemplo: "[4,0]"
 * @param ejex: Coordenada de la fila del tablero empieza por 0 y acaba en 4
 * @param ejey: Coordenada de la columna del la fila empieza por 0 y accaba en 4
 */
fun actualizarTableroAtaque(
    configuracionEsteJugador : MutableMap<String, Any>,
    configuracionUsuario: MutableMap<String, Any>,
    coordenadasConcadenadas: String,
    ejex: Int,
    ejey: Int) : Triple<MutableMap<String, Any>, MutableMap<String, Any>, String> {
    var resultadoAtaque : String = ""
    val barcos = configuracionUsuario["barco"] as MutableMap<String, Any>
    for (Nbarco in barcos.keys) {
        var barcoActual = barcos[Nbarco] as MutableMap<String, Any>
        var estado = barcoActual.get("estado") as MutableMap<String, String>
        if (coordenadasConcadenadas in estado.keys) {
            estado[coordenadasConcadenadas] = "T"
            var tablero = configuracionUsuario["tablero"] as MutableList<MutableList<String>>
            var filaTablero = tablero[ejex]
            var columnaTablero = filaTablero[ejey]
            columnaTablero = "T"
            tablero[ejex] = filaTablero
            configuracionUsuario["tablero"]
            //configuracionUsuario["tablero"] = tablero.add(ejex)


            if ("B" !in estado.values) {
                for (coorEstado in estado.keys) {
                    estado[coorEstado] = "H"
                    var ejexCambiar = coorEstado[1].toString().toInt()
                    var ejeYCambiar = coorEstado[3].toString().toInt()
                    var filaCambiar = tablero[ejexCambiar]
                    filaCambiar[ejeYCambiar] = "H"
                    var movimientos = configuracionEsteJugador["movimientos"] as MutableList<MutableMap<String, Any>>
                    movimientos.add(mutableMapOf<String, Any>("coordenada" to coordenadasConcadenadas, "resultado" to "H"))
                    resultadoAtaque = "Hundido"
                }
            } else {
                var filaTCambiar = tablero[ejex]
                filaTCambiar[ejey] = "T"
                var movimientos = configuracionEsteJugador["movimientos"] as MutableList<MutableMap<String, Any>>
                movimientos.add(mutableMapOf<String, Any>("coordenada" to coordenadasConcadenadas, "resultado" to "T"))
                resultadoAtaque = "Tocado"
            }
        } else{
            var tablero = configuracionUsuario["tablero"] as MutableList<MutableList<String>>
            var tableroFila = tablero[ejex]
            if (tableroFila[ejey] == "~"){
                tableroFila[ejey] = "A"
                var movimientos = configuracionEsteJugador["movimientos"] as MutableList<MutableMap<String, Any>>
                movimientos.add(mutableMapOf<String, Any>("coordenada" to coordenadasConcadenadas, "resultado" to "A"))
                resultadoAtaque = "Agua"
            }
        }

    }
    return Triple(configuracionUsuario, configuracionEsteJugador, resultadoAtaque)
}


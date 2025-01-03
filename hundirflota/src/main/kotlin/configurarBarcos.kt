package org.alejandro.kotlin

fun configurarBarcos(){

    var nombreJugador : String

    val Fgeneral = FicheroGeneral()
    val existenciaGeneral = Fgeneral.comprobarExistencia()
    if (existenciaGeneral){
        nombreJugador = Fgeneral.revisarJugadores()
        Fgeneral.agregarJugadorAPartida()
    } else{
        Fgeneral.crearFichero()
        nombreJugador = Fgeneral.revisarJugadores()
        Fgeneral.agregarJugadorAPartida()
    }



    val ficheroJugadorConfigurando = FicheroUsuario(nombreJugador)
    val existencia = ficheroJugadorConfigurando.comprobarExistencia()
    var coordenadasBarcos : MutableList<Int> = mutableListOf()
    var barcosClavesRemplazarEnDiccionarioExistnte : MutableList<String> = mutableListOf()
    var coorTemporal : MutableList<Int> = mutableListOf()
    if(!existencia){
        ficheroJugadorConfigurando.crearFichero()
        var datosDict = ficheroJugadorConfigurando.leerFichero() as MutableMap<String, Any>
        var diccionarioConTableroOculto = actualizarTablero(datosDict)
        var barcosDict = diccionarioConTableroOculto["barco"] as MutableMap<String, Any>
        for (barco in barcosDict.keys){
            var barcoCoor = barcosDict.get(barco) as MutableMap<String, Any>
            var numCoordenadas = barcoCoor.get("coordenadas") as MutableList<MutableList<String>>
            for (tmp in numCoordenadas){
                var entradaCorrecta = true
                while (entradaCorrecta){
                    println("Dime las coordenadas en la que quieres posicionar el barco: $barco, ejemplo: 1,2")
                    var coordenadas = readln()
                    for (coor in coordenadas){
                        if (coor == '-'){
                            println("El símbolo '-' ha sido eliminado... ")
                        }
                        if (coor.isDigit()){
                            var num = coor.toString().toInt()
                                coorTemporal.add(num)

                        }
                    }
                    try {
                        if (coorTemporal[0] < 0 || coorTemporal[1] > 4){
                            println("¡RECUERDA QUE EL TABLERO ES UN 5x5 y se empieza contando por 0, por lo que solo podrás escribir desde el 0 hasta el 4!")
                            entradaCorrecta = true
                            coorTemporal.clear()
                        }
                        else if (coorTemporal.size != 2){
                            entradaCorrecta = true
                            coorTemporal.clear()
                            println("¡RECUERDA ESCRIBIR BIEN LAS COORDENADAS! Tienes que escribir las coordenadas en este formato: 1,2")
                        }
                        else {
                            entradaCorrecta = false
                        }
                    } catch (ex: Exception) {
                        entradaCorrecta = true

                    }
                }
                var tablero = diccionarioConTableroOculto["tablero"] as MutableList<MutableList<String>>

                var copiaCoorTemporal = coorTemporal.deepCopyList()

                tablero[copiaCoorTemporal[0]][copiaCoorTemporal[1]] = "B"


                var agregarDict = "[" + copiaCoorTemporal[0] + "," + copiaCoorTemporal[1] + "]"
                barcosClavesRemplazarEnDiccionarioExistnte.add(agregarDict)
                coordenadasBarcos.add(copiaCoorTemporal[0])
                coordenadasBarcos.add(copiaCoorTemporal[1])
                coorTemporal.clear()
                copiaCoorTemporal.clear()
            }
            var Ncoor = barcoCoor.get("coordenadas") as MutableList<MutableList<String>>
            var contador = 0
            for (numeroCoor in Ncoor.indices){
                Ncoor[numeroCoor][0] = coordenadasBarcos[contador].toString()
                contador++
                Ncoor[numeroCoor][1] = coordenadasBarcos[contador].toString()
                contador++
            }
            var estadosCoordenadas = barcoCoor.get("estado") as MutableMap<String, String>
            var contadorEstados = 0
            val estadosCoordenanadasCopia = deepCopy(estadosCoordenadas)
            estadosCoordenadas.clear()
            for (estadoCoor in estadosCoordenanadasCopia.keys){

                estadosCoordenadas[barcosClavesRemplazarEnDiccionarioExistnte[contadorEstados]] = "B"
                contadorEstados++
            }
            barcosClavesRemplazarEnDiccionarioExistnte.clear()
            coordenadasBarcos.clear()

        }
        diccionarioConTableroOculto["barco"] = barcosDict
        ficheroJugadorConfigurando.escribirFichero(diccionarioConTableroOculto)
    }
    menuLuegoConf(ficheroJugadorConfigurando, nombreJugador)

}

/**
 * Deepcopy es necesario porque de esta forma
 * si queremos copiar un diccionario en otra variable
 * sin que se copie la dirección de memoria lo que tenemos que usar
 * es deepCopy. Ya que kotlin no tiene metodos para hacer el .copy() como python
 * y copiar los valores del diccionario no la dirección de memoria
 */
fun <K, V> deepCopy(map: Map<K, V>): MutableMap<K, V> {
    val copia = mutableMapOf<K, V>()
    for ((key, value) in map) {
        copia[key] = when (value) {
            is MutableMap<*, *> -> deepCopy(value as Map<K, V>) as V // Clonar mapas anidados
            is MutableList<*> -> value.toMutableList() as V         // Clonar listas mutables
            else -> value                                           // Copiar valores inmutables directamente
        }
    }
    return copia
}

fun main(){
    configurarBarcos()
}



fun <T> List<T>.deepCopyList(): MutableList<T> {
    return this.map {
        when (it) {
            is List<*> -> it.deepCopyList() as T // Recursividad para listas anidadas
            is Set<*> -> it.toMutableSet() as T
            else -> it // Para tipos inmutables
        }
    }.toMutableList()
}


fun devolvernombreFichero(){

}
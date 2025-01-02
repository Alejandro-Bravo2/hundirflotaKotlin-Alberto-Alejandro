package org.alejandro.kotlin

fun configurarBarcos(){
    val ficheroJugadorConfigurando = FicheroUsuario("j3")
    val existencia = ficheroJugadorConfigurando.comprobarExistencia()

    var barcosClavesRemplazarEnDiccionarioExistnte : MutableList<String> = mutableListOf()
    var coorTemporal : MutableList<String> = mutableListOf()
    if(!existencia){
        ficheroJugadorConfigurando.crearFichero()
        var datosDict = ficheroJugadorConfigurando.leerFichero() as MutableMap<String, Any>
        var barcosDict = datosDict["barco"] as MutableMap<String, Any>
        for (barco in barcosDict.keys){
            var numCoordenadas = barcosDict.get("coordenadas") as MutableList<MutableList<String>>
            for (tmp in numCoordenadas){
                var entradaCorrecta = false
                while (!entradaCorrecta){
                    println("Dime las coordenadas en la que quieres posicionar el barco: $barco, ejemplo: 1,2")
                    var coordenadas = readln()
                    for (coor in coordenadas){
                        if (coor.isDigit()){
                            coorTemporal.add(coor.toString())
                        }
                    }
                    if (coorTemporal.size != 2){
                        entradaCorrecta = true
                        println("¡RECUERDA ESCRIBIR BIEN LAS COORDENADAS! Tienes que escribir las coordenadas en este formato: 1,2")
                    }
                }
                var agregarDict = "[" + coorTemporal[0] + "," + coorTemporal[1] + "]"
                barcosClavesRemplazarEnDiccionarioExistnte.add(agregarDict)
            }
            var Ncoor = barcosDict.get("coordenadas") as MutableList<MutableList<String>>
            for (numeroCoor in Ncoor.indices){
                Ncoor[numeroCoor][0] = barcosClavesRemplazarEnDiccionarioExistnte[numeroCoor]
                // Lo que me falta es remplazar las coordenadas del diccionario con las que estoy cojiendo
            }
        }
    }
}
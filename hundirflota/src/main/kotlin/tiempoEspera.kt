package org.alejandro.kotlin

/**
 * Esta función lo que hace es esperar el tiempo
 * pero de refresco, este tiempo es el que tenemos guardado
 * como tiempo_refresco y es de 2 segundos
 * Tenemos que convertir el dato de integer a long porque
 * el timesleep lo que maneja son long no enteros
 * PERO ESTO LO HACE DENTRO DE LA FUNCIÓN POR LO QUE NO
 * HAY QUE PREOCUPARSE,
 *
 */
fun tiempoEspera(){
    val diccionarioGeneral = FicheroGeneral()
    val diccionarioConfig : MutableMap<String, Any> = diccionarioGeneral.leerFichero()
    Thread.sleep(((diccionarioConfig["tiempo_refresco"] as Int).toLong())*1000)
}

/**
 * Esta función lo que hace es esperar el tiempo
 * pero el de turnos, este tiempo es el que tenemos guardado
 * como tiempo_ataque y es de 30 segundos
 *
 *
 */
fun tiempoEsperaTurno(){
    val diccionarioGeneral = FicheroGeneral()
    val diccionarioConfig : MutableMap<String, Any> = diccionarioGeneral.leerFichero()
    Thread.sleep(((diccionarioConfig["tiempo_ataque"] as Int).toLong())*1000)
}

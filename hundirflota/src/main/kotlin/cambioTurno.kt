package org.alejandro.kotlin


/**
 * Función encarga de cambiar el turno, ESTA FUNCIÓN NO REVISA SI EL TURNO
 * HA SIDO CAMBIADO, SI NO QUE LO CAMBIA Y .
 */
fun cambioTurno(){
    val diccionarioGeneral = FicheroGeneral()
    var diccionarioConfiguracionGeneral : MutableMap<String, Any> = diccionarioGeneral.leerFichero()
    if (diccionarioConfiguracionGeneral["turno_actual"] == "j1"){
        diccionarioConfiguracionGeneral["turno_actual"] = "j2"
    } else if (diccionarioConfiguracionGeneral["turno_actual"] == "j2"){
        diccionarioConfiguracionGeneral["turno_actual"] = "j1"
    }
    diccionarioGeneral.escribirFichero(diccionarioConfiguracionGeneral)
}

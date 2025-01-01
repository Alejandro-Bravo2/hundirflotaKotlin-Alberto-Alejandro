fun actualizarTableroDefensor(diccionarioTablero: List<String>, coordenada: List<Int>, resultado: String){
    var diccionarioTablero = leerDatosLocal("j1.json")
    for (i in diccionarioTablero["tablero"]){
        for (j in diccionarioTablero["tablero"]){
            if (diccionarioTablero["tablero"][i][j] == coordenada){
                if (diccionarioTablero["tablero"][i][j] == "~"){
                    diccionarioTablero["tablero"][i][j] = resultado
                }
                if (diccionarioTablero["tablero"][i][j] == "B"){
                    diccionarioTablero["tablero"][i][j] = resultado
                }
            }
        }
    }
    return diccionarioTablero
}
fun mostrarTableroActualizado(turnoActual: Int) {
    var diccionarioTablero = leerDatosLocal("configuracion.json")
    if ("j2" == turnoActual) {
        print(diccionarioTablero["tablero"])
    }
    if ("j1" == turnoActual) {
        for (i in diccionarioTablero["tablero"]) {
            for (j in diccionarioTablero["tablero"][i]){
                if (diccionarioTablero["tablero"][i][j] == "B"){
                    diccionarioTablero["tablero"][i][j] = "~"
                }
            }
        }
    }
}
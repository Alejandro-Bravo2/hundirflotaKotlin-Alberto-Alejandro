fun ataque(): List<Int> {
    println("Introduzca una coordenada para atacar a un barco en formato => x, y")
    var coordenadaDeAtaqueAux = readln().split(",")
    var coordenadaDeAtaque: List<Int> = listOf()
    for (k in coordenadaDeAtaqueAux) {
        coordenadaDeAtaque.append(coordenadaDeAtaqueAux[k].toInt())
    }
    return coordenadaDeAtaque
}
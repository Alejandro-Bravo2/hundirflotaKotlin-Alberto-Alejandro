import org.alejandro.kotlin.actualizarTableroAtaque

fun main(){
    /*
    val segundosMax = 30
    println("--- Tiempo de espera maximo: $segundosMax  ---")
    val tiempoInicio = System.currentTimeMillis()
    val tiempoFin = System.currentTimeMillis()
    while (((tiempoFin - tiempoInicio) / 1000) < segundosMax){
        println("AAA")
        val datos = readln()
    }*/
    val configuracionUsuario = mutableMapOf<String,Any>("nombre" to "j1", "tablero" to mutableListOf<MutableList<String>>(
        mutableListOf("B", "~", "B", "~", "B"), mutableListOf("B", "B", "B", "B", "B"), mutableListOf("~", "~", "~", "~", "~"),
        mutableListOf("B", "~", "B", "B", "~"), mutableListOf("T", "~", "~", "~", "~")
    ), "barco" to mutableMapOf<String, Any>(
        "portaaviones1" to mutableMapOf(
            "coordenadas" to mutableListOf<MutableList<Int>>(mutableListOf(1,0), mutableListOf(1,1), mutableListOf(1,2), mutableListOf(1,3), mutableListOf(1,4)),
            "estado" to mutableMapOf<String, String>("[1,0]" to "B", "[1,1]" to "B", "[1,2]" to "B", "[1,3]" to "B", "[1,4]" to "B")
        ),
        "submarino1" to mutableMapOf<String, Any>(
            "coordenadas" to mutableListOf<MutableList<Int>>(mutableListOf(3,0), mutableListOf(4,0)),
            "estado" to mutableMapOf<String, String>("[3,0]" to "B", "[4,0]" to "T"),
        ),
        "submarino2" to mutableMapOf<String, Any>(
            "coordenadas" to mutableListOf<MutableList<Int>>(mutableListOf(3,2), mutableListOf(3,3)),
            "estado" to mutableMapOf<String, String>("[3,2]" to "B", "[3,3]" to "B")
        ),
        "destructor1" to mutableMapOf<String, Any>(
            "coordenadas" to mutableListOf<MutableList<Int>>(mutableListOf<Int>(0,2)),
            "estado" to mutableMapOf<String, String>("[0,2]" to "B")
        ),
        "destructor2" to mutableMapOf<String, Any>(
            "coordenadas" to mutableListOf<MutableList<Int>>(mutableListOf<Int>(0,0)),
            "estado" to mutableMapOf<String, String>("[0,0]" to "B")
        ),
        "destructor3" to mutableMapOf<String, Any>(
            "coordenadas" to mutableListOf<MutableList<Int>>(mutableListOf<Int>(0,4)),
            "estado" to mutableMapOf<String, String>("[0,4]" to "B")
        )),
        "movimientos" to mutableListOf<Any>(mutableMapOf<String, Any>()))
    val coordenadas2 = "[0,1]"
    val ejex = 0
    val ejey = 1
    //val configUser = actualizarTableroAtaque(configuracionUsuario, coordenadas2,ejex,ejey)
    //println(configUser)

    val coordenadas3 = "[0,4]"
    val ejex2 = 0
    val ejey2 = 4
    //val configUser2 = actualizarTableroAtaque(configUser, coordenadas3,ejex2,ejey2)
    //println(configUser2)
}
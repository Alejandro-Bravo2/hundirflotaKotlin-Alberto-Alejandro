import org.alejandro.kotlin.FicheroUsuario
import org.alejandro.kotlin.configurarBarcos
import org.alejandro.kotlin.establecerConexion
import org.alejandro.kotlin.menu

fun main(){
    var Fjugador : FicheroUsuario = FicheroUsuario("j3")
    val opcionMenu = menu()
    when (opcionMenu) {
        1 -> {
            establecerConexion(Fjugador)
        }
        2 -> {
            Fjugador = configurarBarcos()
        }
        3 -> {
            return
        }
    }
}
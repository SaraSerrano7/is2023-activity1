import cat.udl.eps.is.List.apply
import cat.udl.eps.is.Hondt.*
import scala.util.Sorting

val votos = Map(
    "A" -> 50000,
    "B" -> 30000,
    "C" -> 10000
)

val escaños = 5

val lista_divisiones = dividir(50000, escaños).reverse

//paso 1: Mapeamos cada partido con las divisiones de sus votos
val mapa_divisiones = votos.map( (partido: String, n_votos: Int) => (partido, dividir(n_votos, escaños).reverse) )

//paso 2: Juntamos en una sola lista todas las divisiones de todos los votos

val todos_los_votos = mapa_divisiones.flatMap((partido: String, l: List[Int]) => l).toList

//paso 3: Ordenamos dichos votos en orden descendente

val todos_los_votos_ordenados = todos_los_votos.sorted(Ordering.Int.reverse)

//paso 4: Nos quedamos los primeros n valores (sea n el número de escaños)

val max_votos = todos_los_votos_ordenados.take(escaños)

//paso 5: Buscamos a qué partidos pertenecen esos votos máximos

val lista_partidos_con_escaños: List[String] = buscar_partido(List[String](), max_votos, mapa_divisiones)

//paso 6: Mapeamos a cada partido con la suma de los escaños conseguidos

val resultado_final = agrupar_escaños(lista_partidos_con_escaños, Map[String, Int]())
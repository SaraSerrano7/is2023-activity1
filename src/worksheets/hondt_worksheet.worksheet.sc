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

//paso 1: conseguir las divisiones de votos para cada partido
val mapa_divisiones = votos.map( (partido: String, n_votos: Int) => (partido, dividir(n_votos, escaños).reverse) )

//paso 2: conseguir los listas de valores y concatenarlos

val todos_los_votos = mapa_divisiones.flatMap((partido: String, l: List[Int]) => l).toList

//paso 3: ordenar valores

val todos_los_votos_ordenados = mapa_divisiones.toList.sorted  

val todos_los_votos_ordenados2 = todos_los_votos.sorted(Ordering.Int.reverse)

//paso 4: nos quedamos los primeros n valores (sea n el número de escaños)

val max_votos2 = todos_los_votos_ordenados2.take(escaños)

//paso 5: buscamos a que partido pertenece cada escaño

val lista_partidos_con_escaños: List[String] = buscar_partido(List[String](), max_votos2, mapa_divisiones)

//paso 6: mapeamos

val resultado_final = agrupar_escaños(lista_partidos_con_escaños, Map[String, Int]())
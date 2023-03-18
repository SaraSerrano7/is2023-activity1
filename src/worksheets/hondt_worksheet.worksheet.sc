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


val todos_los_votosFlatmap = mapa_divisiones.flatMap((partido: String, l: List[Int]) => l)

val todos_los_votos = mapa_divisiones.flatMap((partido: String, l: List[Int]) => l).toList

//paso 3: ordenar valores

val todos_los_votos_ordenados2 = todos_los_votos.sorted(Ordering.Int.reverse)

val todos_los_votos_ordenados = mapa_divisiones.toList.sorted//.sortBy(_._2)  //todos_los_votos.sortBy(_)


//paso 4: nos quedamos los primeros n valores (sea n el número de escaños)

val max_votos2 = todos_los_votos_ordenados2.take(escaños)

//paso 5: buscamos a que partido pertenece cada escaño

val lista_partidos_con_escaños: List[String] = buscar_partido(max_votos2, mapa_divisiones)

//paso 6: mapeamos

//val resultado_2 = resultado.sorted

val resultado_final = agrupar_escaños(lista_partidos_con_escaños, Map[String, Int]())

//val resultado_3 = re



//val mapa_divisiones_ordenado = lista_divisiones.sortBy()



val divisionMap: Map[String, List[Int]] = Map()

//val divisiones: Map[String, List[Int]] = votos.map((partido: String, n_votos: Int) => (partido, List()))

val n_partidos = votos.size


val partidos = votos.keys

//vuelta 1 de n_partidos
val listA = List(50000/1, 50000/2, 50000/3, 50000/4, 50000/5)

//aqui deberia tener una manera de acceder, tipo votos[1]
//val division1 = divisionMap + ()

//vuelta 2 de n_partidos
val listB = List(30000/1, 30000/2, 30000/3, 30000/4, 30000/5)

//vuelta 3 de n_partidos
val listC = List(10000/1, 10000/2, 10000/3, 10000/4, 10000/5)

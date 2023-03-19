package cat.udl.eps.is

import scala.List
import cat.udl.eps.is.List.foldLeft
import src.worksheets.`hondt_worksheet.worksheet`.lista_divisiones

// Eleccions Parlament Catalunya 2021 (Lleida)
// https://www.parlament.cat/pcat/parlament/que-es-el-parlament/procediment-electoral/
// https://gencat.cat/eleccions/resultatsparlament2021/resultados/3/catalunya/lleida

// Consulteu la llista de mètodes a StdLib

object Hondt {

  // El paràmetres d'entrada són
  // - els vots que ha rebut cada partit (només els que han passat el tall del 5%)
  // - el nombre d'escons a repartir
  // La sortida ha de ser, per cada partit, el nombre d'escons que li pertoquen

  // Recordeu que dins d'una funció podeu fer servir `val` per guardar resultats intermedis
  // i crear funcions auxiliars (si calen)

  def hondt2(votes: Map[String, Int], n: Int): Map[String, Int] = ???
    //paso 1: conseguir las divisiones de votos para cada partido 
    ///val mapa_divisiones = votes.map( (partido: String, n_votos: Int) => (partido, dividir(n_votos, n).reverse) )
    //val v2ListaDivisiones = mapa_divisiones.flatMap((partido: String, l: List[Int]) => l).toList

  def buscarMaximosVotos(l: List[Int]): List[Int]= ???

  def buscarEscaños2(mapaEscaños: Map[String, List[Int]], escaños: Int, listaEscaños: List[Int]): Map[String, Int] = ???
    



  def hondt(votes: Map[String, Int], n: Int): Map[String, Int] = 
    //paso 1: conseguir las divisiones de votos para cada partido
    val mapa_divisiones = votes.map( (partido: String, n_votos: Int) => (partido, dividir(n_votos, n).reverse) )
    //paso 2: conseguir los listas de valores y concatenarlos
    val todos_los_votos = mapa_divisiones.flatMap((partido: String, l: List[Int]) => l).toList
    //paso 3: ordenar valores
    val todos_los_votos_ordenados2 = todos_los_votos.sorted(Ordering.Int.reverse)
    //paso 4: nos quedamos los primeros n valores (sea n el número de escaños)
    val max_votos2 = todos_los_votos_ordenados2.take(n)
    //paso 5: buscamos a que partido pertenece cada escaño
    val lista_partidos_con_escaños: List[String] = buscar_partido(List[String](), max_votos2, mapa_divisiones)
    //paso 6: mapeamos
    agrupar_escaños(lista_partidos_con_escaños, Map[String, Int]())

    

  def dividir(n_votos: Int, escaños: Int): List[Int] = 
    if escaños > 1 then
      (n_votos / escaños).toInt :: dividir(n_votos, escaños-1)
    else
      n_votos :: Nil

  @annotation.tailrec
  def buscar_partido(acc: List[String], max_votos: List[Int], mapa_divisiones: Map[String, List[Int]]): List[String] = 
    def buscar_votos(voto: Int, mapa_divisiones: Map[String, List[Int]]): Map[Int, String] =//List[String] = 
      //NO mapa_divisiones.foreach((partido: String, lista_votos: List[Int]) => if lista_votos.contains(voto) then partido: String else "": String)
      
      //mapa_divisiones.flatMap((partido: String, lista_votos: List[Int]) => if lista_votos.contains(voto) then (List(partido)) else (List[String]())).toList
      
      mapa_divisiones.map((partido: String, lista_votos: List[Int]) => if lista_votos.contains(voto) then (1, partido) else (0, partido))

      //NO mapa_divisiones.map((partido: String, lista_votos: List[Int]) => if lista_votos.contains(voto) then partido.toList else "".toList)
    
    max_votos match
      case head :: next => 
        val mapa_partido_con_escaño = buscar_votos(head, mapa_divisiones) 
        val partido_con_escaño: String = mapa_partido_con_escaño.apply(1)
        val newAcc = partido_con_escaño :: acc 
        buscar_partido(newAcc, next, mapa_divisiones)
      case Nil => acc 
      /*
      podria acumular el resultado. ademas de hacerlo tailrecursive, lo dejo sorted

      */

  def agrupar_escaños(l: List[String], acc: Map[String, Int]): Map[String, Int] =
    //def contar_escaños(l: List[String], acc: Map[String, Int])
    
    
    l match
      case head :: next => if acc.contains(head) then 
        val count = acc.apply(head)
        val newAcc: Map[String, Int] = acc + (head -> (count + 1))
        agrupar_escaños(next, newAcc) else
        val newAcc: Map[String, Int] = acc + (head -> 1) 
        agrupar_escaños(next, newAcc)
      case Nil => acc

}

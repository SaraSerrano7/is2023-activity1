package cat.udl.eps.is

import scala.List

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

  def hondt(votes: Map[String, Int], n: Int): Map[String, Int] = ???

}

package cat.udl.eps.is

import scala.List
import cat.udl.eps.is.List.foldLeft

/*
  A List (no pun intended) on useful methods on lists to explore (they can be useful
  for all problems involving scala standard lists)

  - (1 to 5) = 1, 2, 3, 4, 5
  - (1 until 5) = 1, 2, 3, 4
  - Iterator
    def iterate[A](a: A)(f: A -> A): Iterator[A]
  - Iterable[A]
    def toList: List[A]
  - List[Int]
    def sum: List[Int]
    def prod: List[Int]
    def sortBy[B](f: A => B)(given ord: Ordering[B]): List[A]
  - List[List[A]]
    def flatten: List[A]
  - List[A]
    def head: A
    def tail: List[A]
    def tails: Iterator[List[A]]
    def map[B](f: A => B): List[B]
    def flatMap[B](f: A => List[B]): List[B]
    def filter(p: A => Boolean): List[A]
    def forall(p: A => Boolean): Boolean
    def exist(p: A => Boolean): Boolean
    def take(n: Int): List[A]
    def drop(n: Int): List[A]
    def takeWhile(p: A => Boolean): List[A]
    def dropWhile(p: A => Boolean): List[A]
    def groupBy[K](f: A => K): Map[K, List[A]]
    def groupMapReduce[K, B](key: A => K)(f: A => B)(reduce: (B, B) => B): Map[K, B]
  - Map[K, V]
    def map[K2, V2](f: ((K, V)) => (K2, V2)): Map[K2, V2]
    dev values: Iterable[V]
  - (A, B) // Tuple
     t._1 = t(0) = A
     t._2 = t(1) = B
 */

object StdLib {

  // 1. The result is a map that, for each length, returns the
  // number of words of this particular length

/*

hay palabras -> añadimos al mapa
  necesitamos un acumulador -> el mapa actual --> funcion auxiliar, acc inicial --> mapa vacío

ya no hay -> ordenamos numéricamente las parejas

*/

  def countLengths(words: List[String]): Map[Int, Int] = 
    def addPair(oldMap: Map[Int, Int], len: Int): Map[Int, Int] = 
      if oldMap.exists(_._1 == len) then // miramos si esta longitud la hemos visto antes
        val currentValue = oldMap.apply(len) // buscamos cuantas veces ha aparecido la longitud 
        oldMap + (len -> (currentValue + 1)) // añadimos una aparición más
        else oldMap + (len -> 1) // no hemos encontrado esta longitud -> añadimos que la hemos visto 1 vez
    // una vez terminado el foldLeft, ordenamos las claves para mayor armonía visual
    foldLeft(words, Map[Int, Int]())(addPair).toSeq.sortBy(_._1).toMap

  // 2. The result is the sum of the first n powers of b
  // that is, b^0 + b^1 + .... + b^(n-1)
  def sumFirstPowersOf(b: Int, n: Int): Int = ???
}

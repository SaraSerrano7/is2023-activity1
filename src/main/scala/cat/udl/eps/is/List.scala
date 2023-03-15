package cat.udl.eps.is

import scala.annotation.tailrec

// Exercise in user defines lists

enum List[+A]:
  case Nil
  case Cons(head: A, tail: List[A])

object List:

  def apply[A](as: A*): List[A] =
    // This foldRight is the predefined one on Seq
    as.foldRight(Nil: List[A])(Cons.apply)

  @annotation.tailrec
  def foldLeft[A, B](l: List[A], acc: B)(f: (B, A) => B): B =
    l match
      case Nil         => acc
      case Cons(x, xs) => foldLeft(xs, f(acc, x))(f)

  // If we put a function in a single parameter list we can define the function
  // with {} instead of ()
  def reverse[A](l: List[A]): List[A] =
    foldLeft[A, List[A]](l, Nil) { (acc, x) =>
      Cons(x, acc)
    }

  def foldRight[A, B](l: List[A], acc: B)(f: (A, B) => B): B =
    foldLeft(reverse(l), acc) { (acc, x) =>
      f(x, acc)
    }

  def append[A](l: List[A], r: List[A]): List[A] =
    foldRight(l, r)(Cons.apply)

  def concat[A](l: List[List[A]]): List[A] =
    foldRight(l, Nil: List[A])(append)

  // ------------------------------------------------------------------

  // 1. partition (via foldRight)

  // Separa en dues llistes els que cumpleixen el predicats dels que no ho fan
  // - la primera llista conté els que el compleixen, la segona els que no
  // - l'ordre dels elements es manté, és a dir:
  //     partition(l, _ => true) == (l, Nil)
  //     partition(l, _ => false) == (Nil, l)
    
  //el error era que por mucho que las dos sean As, no son la misma A la de fuera que la de dentro.
  def partition[A](l: List[A])(p: A => Boolean): (List[A], List[A]) = 
    def decideList(elem: A, li: (List[A], List[A])): (List[A], List[A]) = 
      if p(elem) then (Cons(elem, li._1), li._2) else (li._1, Cons(elem, li._2))
   
    foldRight(l, (Nil:List[A], Nil: List[A]))( (h: A, acc: (List[A], List[A])) => decideList(h, acc)) //otra forma de arreglarlo: decideList[A](h, acc))
    
  /*  
    l match
      case Cons(h, t) if p(h) =>  (Cons(h, append(partition(t)(p) )), ???)/// (List[A], List[A])                                                             
      
      //(Cons(h, partition(t: List[A])), 
                                  //partition(t))
                                  

      case _ => (Nil: List[A], Nil: List[A])
    
*/


  // 2. partitionMap (via foldRight)

  // Semblant a l'anterior però fent servir un Either per decidir si a la llista
  // de l'esquerra o de la dreta

  def partitionMap[A, B, C](
      l: List[A]
  )(p: A => Either[B, C]): (List[B], List[C]) = 
    def decideListEither(elem: A, li: (List[B], List[C])):  (List[B], List[C]) =
      p(elem) match
        case Left(value: C) => (li._1, Cons(value, li._2))
        case Right(value: B) => (Cons(value, li._1), li._2)

    foldRight(l, (Nil: List[B], Nil: List[C]))( (h: A, acc: (List[B], List[C])) => decideListEither(h, acc))
    

  // 3. find (stack-safe)

  // Busca el primer element que compleix una condició

  @annotation.tailrec
  def find[A](l: List[A])(p: A => Boolean): Option[A] = 
    l match
      case Cons(h, t) => if p(h) then Some(h) else find(t)(p)
      case _ => None

  
 // def findViaFoldLeft[A](l: List[A])(p: A => Boolean): Option[A] = 
 //   foldLeft(l, None)( (r, rs) => if p(r) then Some(r))
    

  // 4. sort (insertion sort via foldLeft)

  // La funció que passem és indica si el primer paràmetre és més petit o igual (<=) que el segon
  // PISTA: feu servir una funció auxiliar per a inserir de forma ordenada

  def sort1[A](as: List[A])(lte: (A, A) => Boolean): List[A] =
    def insertionSort(l: List[A], a: A): List[A] = 
      l match
        case Cons(h, t) => if lte(a, h) then Cons(a, l) else Cons(h, insertionSort(t, a))
        case Nil => Cons(a, Nil)
    foldLeft(as, Nil)(insertionSort)
    


  // 5. digitsToNum (use a fold, choose wisely)

  // Podeu suposar que la llista està realment formada per dígits
  // List(1, 2, 3) -> 123

  def digitsToNum(l: List[Int]): Int =
    foldLeft(l, "")((acc: String, a: Int) => (a.toString() + acc)).toInt 

  // 6. mergeSorted

  // Fusiona dues llistes ordenades de forma que el resultat queda ordenat

  // Com el nom indica, barregem coses ordenades. Alguns no ho heu considerat així i heu concatenat
  // i ordenat. Si heu entès això, el que no té sentit és definir tota la casuística de si una llista
  // és buida i l'altra no.
  def mergeSorted[A](l1: List[A], l2: List[A])(
      ord: (A, A) => Boolean
  ): List[A] = 
    sort1(append(l1, l2))(ord)
  

  // 7. Veu una versió del digitsToNum que tracti l'error de que un dels números de la llista no és
  // un dígit -> la capçalera de la funció serà part del problema

//option de int, que esten entre 0 y 9
  def digitsToNumOption[A](l: List[A]): Option[A] =  
    l match
      case Cons(h: Int, t: List[Int]): List[Int]  => Some(digitsToNum(l))
      case _ => None
    

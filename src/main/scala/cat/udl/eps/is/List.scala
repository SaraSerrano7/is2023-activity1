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
        case Left(value: B) => (Cons(value, li._1), li._2)
        case Right(value: C) => (li._1, Cons(value, li._2))

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
    //con un foldLeft, obtenemos el resultdo al reves. Si le metemos un reverse, para eso hacemos un foldRight
    //foldLeft(l, "")((acc: String, a: Int) => (a.toString() + acc)).toInt 
    foldRight(l, "")((a: Int, acc: String) => (a.toString() + acc)).toInt

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

  //todo: limpiar esta basura
  def digitsToNumOption(l: List[Int]): Option[Int] =  
    /*
    2 casos:
        Nil -> lista vacía -> error, no se puede pasar algo vacío
        Cons -> tenemos elementos. Debemos recorrer la lista para asegurarnos que son digitos
          def recorrer(lista, posicion)
    */
    //lo hago con un booleano porque no tengo la funcion de length
    def checkList(li: List[Int]): Boolean = 
      li match
        case Cons(h, t) => h > 0 && h < 9 && checkList(t)
        case Nil => true
      
    l match
      //Si tengo elementos compruebo que son dígitos
        //evalúo primero h para ahorrarme vueltas
      case Cons(h, t)  => if h > 0 && h < 9 && checkList(t) then Some(digitsToNum(l)) else None
      //Si me dan lista vacía, None
      case _ => None
    /*
      //Recorremos la lista comprobando que todos los números estén entre 0 y 9
      case Cons(h, t) if h > 0 && h < 9   => digitsToNumOption(t)
      //Si hemos llegado hasta aquí, la lista no contenía números erróneos. 
      //En caso de tener una lista vacía desde el principio, no se considera un error, y ya se gestiona en
      case Nil => Some(digitsToNum(l))
      //Solo llegaremos aquí en caso de encontrar un número que no esté en el rango [0, 9]
      case _ => None
    */

    
  def digitsToNumOption2(l: List[Int]): Option[Int] = 
    def checkList2(li: List[Int], isDigit: Boolean): Option[Int] =
      li match
        //si quedan números, comprobamos el primero.
        //si es correcto, seguimos comprobando el resto y pasamos la marca true
        case Cons(head, tail) => if head >= 0 && head <= 9 then checkList2(tail, true) else None
        //aqui creo que el if sobra. Si alguno era incorrecto, ya se ha retornado None antes
        //case Nil => if isDigit then Some(digitsToNum(l)) else None
        case Nil => Some(digitsToNum(l))
    l match
      //Si nos pasan lista vacía, "error"
      case Nil => None
      //Si hay numeros, comprobamos que estén entre [0, 9]
      case Cons(head, tail) => checkList2(l, true) 
    
    
      

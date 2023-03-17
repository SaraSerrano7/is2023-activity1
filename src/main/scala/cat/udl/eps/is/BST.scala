package cat.udl.eps.is

import scala.List

enum BST[+A]:
  case Empty
  case Node(left: BST[A], value: A, right: BST[A])

  // 1. insert

  // La funció que passem ens indica si el primer paràmetre és més petit (<) que el segon
  
  //Esta versión no -> se asume que no se insertan elementos repetidos
  def insert[B >: A](a: B)(lt: (B, B) => Boolean): BST[B] =
    this match
      case Node(left, value, right) => if 
        lt(a, value) 
        then Node(left.insert(a)(lt), value, right) else Node(left, value, right.insert(a)(lt))
      case Empty => Node(Empty, a, Empty)
    
  def insert2[B >: A](a: B)(lt: (B, B) => Boolean): BST[B] =
    this match
      case Empty => Node(Empty, a, Empty)
      case Node(left, value, right) if lt(a, value) => Node(left.insert2(a)(lt), value, right)
      case Node(left, value, right) if lt(value, a) => Node(left, value, right.insert2(a)(lt))
      case _ => this
      
    

  // 2.find

  def find[B >: A](a: B)(lt: (B, B) => Boolean): Boolean = 
    this match
      case Node(left, value, right) => if lt(a, value)
        then true
        else left.find(a)(lt) || right.find(a)(lt)
      case Empty => false
    

  // 3.fold

  // També és un dels que no ho provocat majors problemes
  def fold[B](b: B)(f: (B, A, B) => B): B = 
    this match
      case Node(left, value, right) => f(left.fold(b)(f), value, right.fold(b)(f))
      case Empty => b

object BST:

  // Les llistes que farem servir són les de scala

  // 4. fromList
  /*
  Nota: podremos guardar la lista en forma de árbol no completo (o no equilibrado, no recuerdo)
  porque, despues en inorder para retornar el árbol como lista, al ser estas simple linked list,
  no afectará al desperdicio de espacio
  */
  def fromList[A](l: List[A])(lt: (A, A) => Boolean): BST[A] = ???
    /* l match
      case head :: next => Node(Empty, head, Empty).insert2(next)(lt).fromList(next)(lt)
      case Nil => Empty
*/ 
  // 5. inorder (via fold)
  def inorder[A](t: BST[A]): List[A] = ???

package cat.udl.eps.is

import scala.List

enum BST[+A]:
  case Empty
  case Node(left: BST[A], value: A, right: BST[A])

  // 1. insert

  // La funció que passem ens indica si el primer paràmetre és més petit (<) que el segon
  
  //se asume que no se insertan elementos repetidos
  def insert[B >: A](a: B)(lt: (B, B) => Boolean): BST[B] =
    this match
      case Node(left, value, right) => if 
        lt(a, value) 
        then Node(left.insert(a)(lt), value, right) else Node(left, value, right.insert(a)(lt))
      case Empty => Node(Empty, a, Empty)
    

  // 2.find

  //nota: se podría mejorar el insert usando primero el find, que si da true se retorna this (o con Option)
  def find[B >: A](a: B)(lt: (B, B) => Boolean): Boolean = 
    this match
      case Node(left, value, right) => if lt(a, value)
        then true
        else left.find(a)(lt) || right.find(a)(lt)
      case Empty => false
    

  // 3.fold

  // També és un dels que no ho provocat majors problemes
  def fold[B](b: B)(f: (B, A, B) => B): B = ???

object BST:

  // Les llistes que farem servir són les de scala

  // 4. fromList
  def fromList[A](l: List[A])(lt: (A, A) => Boolean): BST[A] = ???

  // 5. inorder (via fold)
  def inorder[A](t: BST[A]): List[A] = ???

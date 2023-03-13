package cat.udl.eps.is

import scala.List

enum BST[+A]:
  case Empty
  case Node(left: BST[A], value: A, right: BST[A])

  // 1. insert

  // La funció que passem ens indica si el primer paràmetre és més petit (<) que el segon
  def insert[B >: A](a: B)(lt: (B, B) => Boolean): BST[B] = ???

  // 2.find

  def find[B >: A](a: B)(lt: (B, B) => Boolean): Boolean = ???

  // 3.fold

  // També és un dels que no ho provocat majors problemes
  def fold[B](b: B)(f: (B, A, B) => B): B = ???

object BST:

  // Les llistes que farem servir són les de scala

  // 4. fromList
  def fromList[A](l: List[A])(lt: (A, A) => Boolean): BST[A] = ???

  // 5. inorder (via fold)
  def inorder[A](t: BST[A]): List[A] = ???

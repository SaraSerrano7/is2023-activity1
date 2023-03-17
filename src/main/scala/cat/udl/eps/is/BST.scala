package cat.udl.eps.is

import scala.List
import cat.udl.eps.is.List.reverse
import cat.udl.eps.is.List.append

enum BST[+A]:
  case Empty
  case Node(left: BST[A], value: A, right: BST[A])

  // 1. insert

  // La funció que passem ens indica si el primer paràmetre és més petit (<) que el segon
    
  def insert[B >: A](a: B)(lt: (B, B) => Boolean): BST[B] =
    this match
      case Empty => Node(Empty, a, Empty)
      case Node(left, value, right) if lt(a, value) => Node(left.insert(a)(lt), value, right)
      case Node(left, value, right) if lt(value, a) => Node(left, value, right.insert(a)(lt))
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
 // @annotation.tailrec
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
  def fromList[A](l: List[A])(lt: (A, A) => Boolean): BST[A] = 
    def toTree(l: List[A], tree: BST[A])(lt: (A, A) => Boolean): BST[A] = 
      l match
        case head :: next => 
          val newTree = tree.insert(head)(lt)
          toTree(next, newTree)(lt)
        case Nil => tree 
    toTree(l, Empty)(lt)
    
  // 5. inorder (via fold)

  def inorder[A](t: BST[A]): List[A] = 
    t match
      case Node(left, value, right) => inorder(left) ++ (value :: inorder(right)) 
      case Empty => Nil

  def inorderViaFold[A](t: BST[A]): List[A] = 
    t.fold(Nil){
      (l: List[A], v: A, r: List[A]) => l ++ (v :: r)
    }
    
    

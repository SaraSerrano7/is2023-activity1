import cat.udl.eps.is.BST.*

val node1 = Node(Empty, 1, Empty)

val node6 = Node(Empty, 6, Empty)

val node8 = Node(Empty, 8, Empty)

val node7 = Node(node6, 7, node8)

val node3 = Node(node1, 3, Empty)

val node5 = Node(node3, 5, node7)

node5.insert(2)( _ < _)

node5.insert(10)( _ < _)

node5.find(7)(_ == _)

node5.insert(5)(_ < _)

node5.insert2(5)(_ < _)

node5

//----------------

val initial = Empty

val values = List(1, 2, 3, 4)

//Cons(1, (2, 3, 4))

val step1 = initial.insert2(1)(_ < _)

//Cons(2, (3, 4))

val step2 = step1.insert2(2)(_ < _)

//Cons(3, (4))

val step3 = step2.insert2(3)(_ < _)

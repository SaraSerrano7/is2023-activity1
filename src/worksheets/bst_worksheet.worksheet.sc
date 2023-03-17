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
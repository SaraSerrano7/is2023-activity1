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

//----------------

val initial = Empty

val values = List(1, 2, 3, 4)

//Cons(1, (2, 3, 4))

val step1 = initial.insert(1)(_ < _)

//Cons(2, (3, 4))

val step2 = step1.insert(2)(_ < _)

//Cons(3, (4))

val step3 = step2.insert(3)(_ < _)

//Cons(4, (Nil))

val step4 = step3.insert(4)(_ < _)


fromList(values)(_ < _)

val unordered = List(3, 1, 4, 2)

fromList(unordered)(_ < _)

val listToTree1 = fromList(unordered)(_ < _)

val listToTree2 = fromList(values)(_ < _)

val treeToList1 = inorder(listToTree1)

val treeToList2 = inorder(listToTree2)
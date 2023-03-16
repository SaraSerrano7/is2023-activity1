import cat.udl.eps.is.List
import cat.udl.eps.is.List.*

val l = List(1, 2, 3, 4, 5)

reverse(l)

def myFunc(a: Int): Boolean = 
    a % 2 == 0

myFunc(2)

val l1aaaaaaa = List(Nil: List[Int], 1, Nil: List[Int], 3, Nil: List[Int])

val unsorted = List(4, 1, 3, 2)

//sort1(unsorted)

var one = 1

var oneToString = one.toString()

var oneToStringToInt = oneToString.toInt

var str = "1"

var strAndOneToString = str + oneToString

var chanchan: Int = (str + one.toString()).toInt

digitsToNum(unsorted)

var unsorted2 = List(4, 3)

def insertionSort(l: List[Int], a: Int): List[Int] = 
      l match
        case Cons(h, t) => if a < h then Cons(a, l) else Cons(h, insertionSort(t, a))
        case Nil => Cons(a, Nil)

insertionSort(Cons(1, List(3, 4)), 2)

sort1(unsorted)(_ >= _)

find(unsorted)(_ % 5 == 0)

var l1 = List(1, 3, 5)

var l2 = List(4, 6)

mergeSorted(l1, l2)(_ < _)

val empty_list = List[Int]()

val digitListOk = List(1, 2, 3, 4)

val digitsListNotOk = List(1, 2, -4, 24)

digitsToNumOption(empty_list)

digitsToNumOption(digitListOk)

digitsToNumOption(digitsListNotOk)

val partitionList = List(1, 2, 3, 4, 5)

partition(partitionList)(_ % 2 == 1)
//partitionMap(partitionList)(_ / 5)
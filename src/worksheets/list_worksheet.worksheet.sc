import cat.udl.eps.is.List
import cat.udl.eps.is.List.*

val l = List(1, 2, 3, 4, 5)

reverse(l)

def myFunc(a: Int): Boolean = 
    a % 2 == 0

myFunc(2)

val l1 = List(Nil: List[Int], 1, Nil: List[Int], 3, Nil: List[Int])

val unsorted = List(4, 1, 3, 2)

sort1(unsorted)

var one = 1

var oneToString = one.toString()

var oneToStringToInt = oneToString.toInt

var str = "1"

var strAndOneToString = str + oneToString

var chanchan: Int = (str + one.toString()).toInt

digitsToNum(unsorted)

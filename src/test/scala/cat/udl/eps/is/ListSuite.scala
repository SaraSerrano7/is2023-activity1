package cat.udl.eps.is

import munit.FunSuite
import List.*

class ListSuite extends FunSuite {

  test("sort1") {
    val l = Cons(3, Cons(1, Cons(2, Nil)))
    val result = Cons(3, Cons(2, Cons(1, Nil)))
    assertEquals(sort1(l)(_ >= _), result)
  }
}

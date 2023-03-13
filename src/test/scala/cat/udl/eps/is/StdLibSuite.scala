package cat.udl.eps.is

import munit.FunSuite
import scala.List

class StdLibSuite extends FunSuite {
  test("sumFirstPowersOf") {
    assertEquals(StdLib.sumFirstPowersOf(2, 8), 255)
  }
}

package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val numbers1to5 = (x: Int) => x > 0 && x <= 5
    val numbers3to8 = (x: Int) => x >= 3 && x <= 8
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s2, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains only the elements that are in both sets") {
    new TestSets {
      val s = intersect(numbers1to5, numbers3to8)
      assert(contains(s, 3), "Intersect should contain 3, but it does not")
      assert(contains(s, 5), "Intersect should contain 5, but it does not")
      assert(!contains(s, 2), "Intersect contains 2, but it should not")
      assert(!contains(s, 6), "Intersect contains 7, but it should not")
      assert(!contains(s, 8), "Intersect contains 7, but it should not")
    }
  }

  test("difference contains elements of the 1st set that are not in the 2nd set") {
    new TestSets {
      val s = diff(numbers1to5, numbers3to8)
      assert(contains(s, 1), "Difference should contain 1, but it does not")
      assert(contains(s, 2), "Difference should contain 2, but it does not")
      assert(!contains(s, 3), "Intersect contains 3, but it should not")
      assert(!contains(s, 4), "Intersect contains 4, but it should not")
      assert(!contains(s, 5), "Intersect contains 5, but it should not")
    }
  }

  test("filter contains elements of the set for which holds given predicate") {
    new TestSets {
      val s = filter(numbers1to5, x => x % 2 == 0)
      assert(contains(s, 2), "The filtered set should contain 2, but it does not")
      assert(contains(s, 4), "The filtered set should contain 4, but it does not")
      assert(!contains(s, 3), "Intersect contains 3, but it should not")
      assert(!contains(s, 5), "Intersect contains 5, but it should not")
    }
  }

  test("forall confirms that all elements of a set satisfy a given condition") {
    new TestSets {
      assert(forall(numbers1to5, x => x > 0), "Universal quantifier not working properly")
      assert(!forall(numbers1to5, x => x % 2 == 0), "Universal quantifier not working properly")
    }
  }

  test("exists confirms that at least one elements of a set satisfy a given condition") {
    new TestSets {
      assert(exists(numbers1to5, x => x > 0), "Existential quantifier not working properly")
      assert(!exists(numbers1to5, x => x > 10), "Existential quantifier not working properly")
    }
  }

  test("map maps all the elements of one set with a given function") {
    new TestSets {
      val s = map(numbers3to8, (x: Int) => x * x)
      assert(contains(s, 9), "The filtered set should contain 9, but it does not")
      assert(contains(s, 49), "The filtered set should contain 49, but it does not")
      assert(!contains(s, 3), "Intersect contains 3, but it should not")
      assert(!contains(s, 5), "Intersect contains 5, but it should not")
      assert(!contains(s, 8), "Intersect contains 8, but it should not")
    }
  }


}

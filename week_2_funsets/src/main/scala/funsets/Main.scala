package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))
  val set1 = (x: Int) => x > 0 && x < 10
  printSet(set1)
  val set2 = map(set1, x => x * x)
  printSet(set2)
}

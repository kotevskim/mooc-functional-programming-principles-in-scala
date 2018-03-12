package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (r <- 0 to 10) {
      for (c <- 0 to r)
        print(pascal(c, r) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int =
      if (r == 0 || c == 0 || r == c) 1
      else pascal(c-1, r-1) + pascal(c, r-1)
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def loop(chars: List[Char], openParentheses: Int) : Boolean =
        if (chars.isEmpty && openParentheses == 0) true
        else if (openParentheses < 0 || (chars.isEmpty && openParentheses != 0)) false
        else if (chars.head == '(') loop(chars.tail, openParentheses+1)
        else if (chars.head == ')') loop(chars.tail, openParentheses-1)
        else loop(chars.tail, openParentheses)

      loop(chars, 0)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int =
      if (money == 0) 1
      else if (money < 0 || coins.isEmpty) 0
      else countChange(money, coins.tail) + countChange(money-coins.head, coins)
  }

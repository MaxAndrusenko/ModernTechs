object first extends App {

  // Define a Monoid trait
  case class Vector(x: Int, y: Int)

  trait Monoid[A] {
    def empty: A

    def combine(x: A, y: A): A
  }
    implicit val vectorMonoid: Monoid[Vector] = new Monoid[Vector] {
      def empty: Vector = Vector(0, 0)

      def combine(x: Vector, y: Vector): Vector = Vector(x.x + y.x, x.y + y.y)
    }

    // Функція вищих порядків для застосування функції f n раз
    def applyNTimes[A](f: A => A, n: Int, a: A): A =
      if (n <= 0) a
      else applyNTimes(f, n - 1, f(a))

    // Приклад використання для збільшення числа на 2 тричі
    val result = applyNTimes((x: Int) => x + 2, 3, 5) // Очікуване значення: 11
    println(s"Result: $result")

    // Функція, яка виконує додавання двох чисел
    def add(x: Int, y: Int): Int = x + y

    // Каррінг для функції додавання
    def addCurried(x: Int)(y: Int): Int = x + y

    // Приклад використання каррінгу
    val partiallyApplied = addCurried(5) _ // Формуємо частково застосовану функцію
    val result2 = partiallyApplied(3) // Очікуване значення: 8
    println(s"Result2: $result2")

    // Функція вищих порядків для збільшення кожного елемента списку на 1
    def incrementList(list: List[Int]): List[Int] =
      list.map(_ + 1)

    // Функція вищих порядків для відфільтрування парних чисел
    def filterEven(list: List[Int]): List[Int] =
      list.filter(_ % 2 == 0)

    // Приклади використання функцій високого рівня для списків
    val numbers = List(1, 2, 3, 4, 5)
    val incremented = incrementList(numbers) // Очікуваний результат: List(2, 3, 4, 5, 6)
    val evenNumbers = filterEven(numbers) // Очікуваний результат: List(2, 4)
    println(s"Incremented: $incremented")
    println(s"Even numbers: $evenNumbers")

    // Використання моноїда для векторів
    val vector1 = Vector(3, 4)
    val vector2 = Vector(1, 2)
    val combinedVector = vectorMonoid.combine(vector1, vector2)

    println(s"Combined vector: $combinedVector")

}

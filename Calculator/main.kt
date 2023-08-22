fun main() {
    val a = Calculator()
    val b = Calculator(3)
    println("${a.calculate(3,4, AddOperation())}")
    println("${a.calculate(7,4,'-')}")
    println("${b.calculate(3,MultiplyOperation())}")
    println("${b.calculate(3,'/')}")
    println("${b.calculate(5,'%')}")
}

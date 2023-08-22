class Calculator(var num: Int = 0) {
    fun calculate(a: Int, b: Int, operator: AbstractOperation): Int {
        return operator.make(a, b)
    }
    fun calculate(a: Int, operator: AbstractOperation): Int {
        return operator.make(num, a)
    }

    fun calculate(a: Int, b: Int, operator: Char): Int {
        val selectedOperation = when (operator) {
            '+' -> AddOperation()
            '-' -> SubtractOperation()
            '*' -> MultiplyOperation()
            '/' -> DivideOperation()
            '%' -> ModuloOperation()
            else -> throw UnsupportedOperationException("이 맛이 아니야")
        }
        return selectedOperation.make(a, b)
    }

    fun calculate(a: Int, operation: Char): Int {
        val selectedOperation = when (operation) {
            '+' -> AddOperation()
            '-' -> SubtractOperation()
            '*' -> MultiplyOperation()
            '/' -> DivideOperation()
            '%' -> ModuloOperation()
            else -> throw UnsupportedOperationException("이 맛이 아니야")
        }
        return selectedOperation.make(num, a)
    }

}

class UnsupportedOperationException(message: String) : Exception(message)

abstract class AbstractOperation {
    abstract fun make(a: Int, b: Int): Int
}

class AddOperation : AbstractOperation() {
    override fun make(a: Int, b: Int): Int = a + b
}

class SubtractOperation : AbstractOperation() {
    override fun make(a: Int, b: Int): Int = a - b
}

class MultiplyOperation : AbstractOperation() {
    override fun make(a: Int, b: Int): Int = a * b
}

class DivideOperation : AbstractOperation() {
    override fun make(a: Int, b: Int): Int {
        require(b!=0){
            UnsupportedOperationException("0으로 나눌 수 없어요.")
        }
        return a / b
    }
}

class ModuloOperation : AbstractOperation() {
    override fun make(a: Int, b: Int): Int = a % b
}
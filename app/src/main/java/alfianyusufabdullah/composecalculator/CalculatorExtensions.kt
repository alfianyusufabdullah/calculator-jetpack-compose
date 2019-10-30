package alfianyusufabdullah.composecalculator

import androidx.compose.State

fun Operation.read() = when (this) {
    Operation.NONE -> ""
    Operation.ADDITION -> "+ "
    Operation.DIVISION -> "/ "
    Operation.MULTIPLICATION -> "* "
    Operation.SUBTRACTION -> "- "
}

fun Operation.doOperation(valueOne: Int, valueTwo: Int) = when (this) {
    Operation.NONE -> 0
    Operation.ADDITION -> valueOne + valueTwo
    Operation.DIVISION -> valueOne / valueTwo
    Operation.MULTIPLICATION -> valueOne * valueTwo
    Operation.SUBTRACTION -> valueOne - valueTwo
}

fun State<Operation>.setOperation(operation: Operation, action: () -> Unit) {
    value = operation
    action()
}

fun State<String>.add(newValue: String) {
    if (value.isEmpty() && newValue == "0") return
    if (value.length < 10) {
        value += newValue
    }
}
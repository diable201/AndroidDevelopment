package com.example.myapplication

import androidx.lifecycle.ViewModel
import java.util.*


class Brain : ViewModel() {

    enum class State {
        Zero,
        AccumulateDigit,
        ComputePending,
        Compute,
        Separator,
        Clear
    }

    var resultText = ""
    var equationText = ""

    private var digits = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    private var nonZeroDigits = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
    private var operations = arrayOf("+", "-", "×", "÷")
    private var equals = arrayOf("=")
    private var separators = arrayOf(".")
    private var clear = arrayOf("⌫")

    private var operationPriority: Map<String, Int> = mapOf("+" to 1, "-" to 1, "×" to 2, "÷" to 2)

    private var stackOfNumbers: Stack<String> = Stack<String>()
    private var stackOfOperations: Stack<String> = Stack<String>()

    private var currentState: State = State.Zero

    fun processSignal(message: String) {
        when (currentState) {
            State.Zero -> processZeroState(message, false)
            State.AccumulateDigit -> processAccumulateDigitState(message, false)
            State.ComputePending -> processComputePendingState(message, false)
            State.Compute -> processAccumulateDigitState(message, false)
            State.Separator -> processSeparatorState(message, false)
            State.Clear -> processClearState(message, false)
        }
    }

    private fun processClearState(msg: String, incoming: Boolean) {
        if (incoming) {
            currentState = State.Clear
            stackOfNumbers.clear()
            stackOfOperations.clear()
            clearEquationText("0")
        } else {
            processZeroState(msg, false)
        }
    }

    private fun processZeroState(msg: String, incoming: Boolean) {
        if (incoming) {
            currentState = State.Zero
        } else {
            if (nonZeroDigits.contains(msg) || digits.contains(msg)) {
                clearEquationText()
                processAccumulateDigitState(msg, true)
            }
        }
    }

    private fun processAccumulateDigitState(msg: String, incoming: Boolean) {
        if (incoming) {
            currentState = State.AccumulateDigit
            if (stackOfNumbers.count() == 0) {
                stackOfNumbers.push(msg)
            } else {
                val number: String = stackOfNumbers.peek() + msg
                stackOfNumbers.pop()
                stackOfNumbers.push(number)
            }
            appendToEquationDisplay(msg)
        } else {
            when {
                digits.contains(msg) -> processAccumulateDigitState(msg, true)
                operations.contains(msg) -> processComputePendingState(msg, true)
                equals.contains(msg) -> processComputeState(msg, true)
                separators.contains(msg) -> processSeparatorState(msg, true)
                clear.contains(msg) -> processClearState(msg, true)
            }
        }
    }

    private fun processComputePendingState(msg: String, incoming: Boolean) {
        if (incoming) {
            if (operations.contains(msg)) {
                currentState = State.ComputePending
                stackOfNumbers.push("")
                stackOfOperations.push(msg)
                appendToEquationDisplay(msg)
            }
        } else {
            if (digits.contains(msg)) {
                processAccumulateDigitState(msg, true)
            }
            if (clear.contains(msg)) {
                processClearState(msg, true)
            }
        }
    }

    private fun processSeparatorState(msg: String, incoming: Boolean) {
        if (incoming) {
            currentState = State.Separator
            if (!stackOfNumbers.peek().contains(separators[0])) {
                val number: String = stackOfNumbers.peek() + msg
                stackOfNumbers.pop()
                stackOfNumbers.push(number)
                appendToEquationDisplay(msg)
            }
        } else {
            processAccumulateDigitState(msg, false)
        }
    }

    private fun computeOperation(a: Float, b: Float, op: String): String {
        return when (op) {
            "+" -> (a + b).toString()
            "-" -> (a - b).toString()
            "×" -> (a * b).toString()
            "÷" -> (a / b).toString()
            else -> "0"
        }
    }

    private fun processComputeState(msg: String, incoming: Boolean) {
        if (incoming) {
            currentState = State.Compute
            while (stackOfNumbers.count() > 1) {
                val curOp = stackOfOperations.peek()
                stackOfOperations.pop()
                while (stackOfOperations.isNotEmpty()) {
                    val nextOp = stackOfOperations.peek()
                    if (operationPriority[nextOp]!! < operationPriority[curOp]!!) {
                        break
                    }
                    val b = stackOfNumbers.peek().toFloat()
                    stackOfNumbers.pop()
                    val a = stackOfNumbers.peek().toFloat()
                    stackOfNumbers.pop()

                    val result = computeOperation(a, b, nextOp)

                    stackOfNumbers.push(result)
                    stackOfOperations.pop()
                }
                val b = stackOfNumbers.peek().toFloat()
                stackOfNumbers.pop()
                val a = stackOfNumbers.peek().toFloat()
                stackOfNumbers.pop()

                val result = computeOperation(a, b, curOp)

                stackOfNumbers.push(result)
            }
            updateResultDisplay(stackOfNumbers.peek())
            updateEquationDisplay(stackOfNumbers.peek())
        }
    }

    private fun clearEquationText(placeHolder: String = "") {
        equationText = placeHolder
        resultText = placeHolder
    }

    private fun appendToEquationDisplay(text: String) {
        equationText += text
    }

    private fun updateEquationDisplay(text: String) {
        equationText = text
    }

    private fun updateResultDisplay(text: String) {
        resultText = text
    }
}
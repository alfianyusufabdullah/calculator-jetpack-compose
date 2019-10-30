package alfianyusufabdullah.composecalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.themeTextStyle

class MainActivity : AppCompatActivity() {

    private val numberPad = listOf(
        arrayOf("1", "4", "7"),
        arrayOf("2", "5", "8", "0"),
        arrayOf("3", "6", "9")
    )

    private val operationPad = arrayOf(
        Operation.ADDITION,
        Operation.DIVISION,
        Operation.MULTIPLICATION,
        Operation.SUBTRACTION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val previewNumberState = +state { "" }
            val previewNumberTempState = +state { "" }
            val operationState = +state { Operation.NONE }

            MaterialTheme {
                Column(crossAxisAlignment = CrossAxisAlignment.End) {
                    Padding(padding = EdgeInsets(right = 32.dp, top = 32.dp)) {
                        Text(previewNumberTempState.value, style = +themeTextStyle { h5 })
                    }
                    Padding(padding = EdgeInsets(right = 32.dp, top = 10.dp, bottom = 32.dp)) {
                        Row {
                            Text(operationState.value.read(), style = +themeTextStyle { h3 })
                            Text(previewNumberState.value, style = +themeTextStyle { h3 })
                        }
                    }
                    Center {
                        Row {
                            numberPad.forEachIndexed { index, pad ->
                                Column {
                                    pad.forEach {
                                        buttonNumberPad(it) { value ->
                                            previewNumberState.add(value)
                                        }
                                    }
                                    if (index == numberPad.lastIndex) {
                                        buttonDoOperationPad {
                                            try {
                                                val valueOne =
                                                    previewNumberTempState.value.toInt()
                                                val valueTwo = previewNumberState.value.toInt()

                                                previewNumberState.value =
                                                    "${operationState.value.doOperation(
                                                        valueOne,
                                                        valueTwo
                                                    )}"
                                                previewNumberTempState.value = ""
                                                operationState.value = Operation.NONE
                                            } catch (e: Exception) {
                                                e.printStackTrace()
                                            }
                                        }
                                    }
                                }
                            }

                            WidthSpacer(15.dp)
                            Column {
                                operationPad.forEach {
                                    buttonOperationPad(it) { operation ->
                                        operationState.setOperation(operation) {
                                            if (previewNumberTempState.value.isEmpty() && previewNumberState.value.isNotEmpty()) {
                                                previewNumberTempState.value =
                                                    previewNumberState.value
                                                previewNumberState.value = ""
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

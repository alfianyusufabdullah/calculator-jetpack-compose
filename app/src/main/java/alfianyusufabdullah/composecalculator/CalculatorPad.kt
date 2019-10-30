package alfianyusufabdullah.composecalculator

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.layout.Padding
import androidx.ui.material.surface.Card
import androidx.ui.material.themeTextStyle
import androidx.ui.text.TextStyle

@Composable
fun buttonNumberPad(text: String, action: (String) -> Unit) {
    Padding(padding = 5.dp) {
        Card(shape = RoundedCornerShape(50.dp), elevation = 5.dp) {
            Clickable(onClick = { action(text) }) {
                Container(height = 75.dp, width = 75.dp) {
                    Text(text, style = +themeTextStyle { h6 })
                }
            }
        }
    }
}

@Composable
fun buttonOperationPad(operation: Operation, action: (Operation) -> Unit) {
    Padding(padding = 5.dp) {
        Card(shape = RoundedCornerShape(50.dp), elevation = 5.dp, color = Color.Gray) {
            Clickable(onClick = { action(operation) }) {
                Container(height = 75.dp, width = 75.dp) {
                    Text(operation.read(), style = TextStyle(color = Color.White, fontSize = 20.sp))
                }
            }
        }
    }
}

@Composable
fun buttonDoOperationPad(action: () -> Unit) {
    Padding(padding = 5.dp) {
        Card(shape = RoundedCornerShape(50.dp), elevation = 5.dp, color = Color.LightGray) {
            Clickable(onClick = { action() }) {
                Container(height = 75.dp, width = 75.dp) {
                    Text("=", style = +themeTextStyle { h6 })
                }
            }
        }
    }
}
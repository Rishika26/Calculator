package com.example.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorScreen(
    uiState: UiState=UiState(),
    onEvent: (CalculatorEvent) -> Unit={}
) {
    Column(
//        verticalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Spacer(modifier =Modifier.height(100.dp))
        TextField(value = uiState.num1, onValueChange = {
            onEvent(CalculatorEvent.Num1(it))
        },
            label = { Text(text = "Number 1")},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            isError = uiState.isError,
            supportingText = {
                if(uiState.isError){
                    Text(
                        text = "invalid input",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        )
        TextField(value =uiState.num2 , onValueChange = {
            onEvent(CalculatorEvent.Num2(it))
        },
            label = { Text(text = "Number 2")},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            isError = uiState.isError,
            supportingText = {
                if(uiState.isError){
                    Text(
                        text = "invalid input",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()

        ) {
            ElevatedButton(onClick = { onEvent(CalculatorEvent.Add) },
                modifier = Modifier.weight(1f)) { Text(text = "+") }
            ElevatedButton(onClick = { onEvent(CalculatorEvent.Sub) },
                modifier = Modifier.weight(1f)) { Text(text = "-") }
            ElevatedButton(onClick = { onEvent(CalculatorEvent.Mul) },
                modifier = Modifier.weight(1f)) { Text(text = "*") }
            ElevatedButton(onClick = { onEvent(CalculatorEvent.Div) },
                modifier = Modifier.weight(1f)) { Text(text = "/") }
        }
        ElevatedButton(onClick = {onEvent(CalculatorEvent.Clear)},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) { Text(text = "Clear")}
        Text(text = uiState.ans, style = MaterialTheme.typography.displayLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen()
}
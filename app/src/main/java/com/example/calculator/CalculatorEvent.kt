package com.example.calculator

sealed class CalculatorEvent {
    object Add: CalculatorEvent()
    object Sub: CalculatorEvent()
    object Mul: CalculatorEvent()
    object Div: CalculatorEvent()
    object Clear: CalculatorEvent()
    //Add class hai jo object ki tarah behave kregi
    data class Num1(val value: String) : CalculatorEvent()
    data class Num2(val value: String) : CalculatorEvent()
    //data class is special class that handles data
}
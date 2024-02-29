package com.example.calculator

import android.text.method.TextKeyListener.clear
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.lang.Error

//uistste data ko handle karne wali class
data class UiState(
    val num1: String = "",
    val num2: String = "",
    val ans: String = "",
    val isError: Boolean = false
)

class CalcViewModel:ViewModel() {
    //step 1 - setup ui state flow
    private val _uiState = MutableStateFlow(UiState())

    //mutable state flow stream of data hai jo data handle krta h
    var uiState: StateFlow<UiState> = _uiState.asStateFlow()


    //    step 3 - create functions to handle events
    private fun add() {
        //ctrl alt T
        try {
            val n1 = _uiState.value.num1.toDouble()
            val n2 = _uiState.value.num2.toDouble()
            _uiState.update {
                it.copy(ans = (n1+n2).toString(), isError = false)
            }
        } catch (e: Exception) {
            _uiState.update { it.copy(isError = true, ans = "🌋🌋") }
        }
    }
    private fun clear() {
        _uiState.update {
            it.copy(num1 = "", num2 = "", ans = "", isError = false)
        }
    }
    private fun division() {
        val n1 = _uiState.value.num1.toDouble()
        val n2 = _uiState.value.num2.toDouble()
        _uiState.update {
            it.copy(ans = (n1/n2).toString())
        }
    }
    private fun multiplication() {val n1 = _uiState.value.num1.toDouble()
        val n2 = _uiState.value.num2.toDouble()
        _uiState.update {
            it.copy(ans = (n1*n2).toString())
        }}
    private fun subtract() {
        val n1 = _uiState.value.num1.toDouble()
        val n2 = _uiState.value.num2.toDouble()
        _uiState.update {
            it.copy(ans = (n1-n2).toString())
        }
    }

    //    step 2 - handle events from user
    fun onEvent(event: CalculatorEvent) {
        when (event) {
            CalculatorEvent.Add -> add()
            CalculatorEvent.Clear -> clear()
            CalculatorEvent.Div -> division()
            CalculatorEvent.Mul -> multiplication()
            CalculatorEvent.Sub -> subtract()
            is CalculatorEvent.Num1 -> {
//                jab koi value type karoge vo show hogi
                _uiState.update { it.copy(num1 = event.value) }
            }

            is CalculatorEvent.Num2 -> {
                _uiState.update {
                    it.copy(num2 = event.value)
                }
            }

        }


    }
}
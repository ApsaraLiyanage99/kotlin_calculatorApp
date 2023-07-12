package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var display: TextView
    private lateinit var subDisplay: TextView
    private var numInput1: Double = 0.0
    private var operator: String = ""
    private var isDecimalClicked = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cal_interface)

        display = findViewById(R.id.displayValue)
        subDisplay = findViewById(R.id.subDisplayValue)

        //number buttons
        val button1: Button = findViewById(R.id.button_1)
        button1.setOnClickListener { appendNumber("1") }

        val button2: Button = findViewById(R.id.button_2)
        button2.setOnClickListener { appendNumber("2") }

        val button3: Button = findViewById(R.id.button_3)
        button3.setOnClickListener { appendNumber("3") }

        val button4: Button = findViewById(R.id.button_4)
        button4.setOnClickListener { appendNumber("4") }

        val button5: Button = findViewById(R.id.button_5)
        button5.setOnClickListener { appendNumber("5") }

        val button6: Button = findViewById(R.id.button_6)
        button6.setOnClickListener { appendNumber("6") }

        val button7: Button = findViewById(R.id.button_7)
        button7.setOnClickListener { appendNumber("7") }

        val button8: Button = findViewById(R.id.button_8)
        button8.setOnClickListener { appendNumber("8") }

        val button9: Button = findViewById(R.id.button_9)
        button9.setOnClickListener { appendNumber("9") }

        val button0: Button = findViewById(R.id.button_0)
        button0.setOnClickListener { appendNumber("0") }

        //operation buttons
        val btnPlus: Button = findViewById(R.id.button_plus)
        btnPlus.setOnClickListener { setOperator("+") }

        val btnMinus: Button = findViewById(R.id.button_minus)
        btnMinus.setOnClickListener { setOperator("-") }

        val btnMulti: Button = findViewById(R.id.button_multi)
        btnMulti.setOnClickListener { setOperator("x") }

        val btnDiv: Button = findViewById(R.id.button_div)
        btnDiv.setOnClickListener { setOperator("/") }

        //decimal point
        val buttonDecimal: Button = findViewById(R.id.button_decimal)
        buttonDecimal.setOnClickListener { appendDecimalPoint() }

        //equal button
        val buttonEquals: Button = findViewById(R.id.button_equals)
        buttonEquals.setOnClickListener { calculateResult() }

        //clear button
        val buttonClear: Button = findViewById(R.id.button_clear)
        buttonClear.setOnClickListener { clearDisplay() }

        //cancel button
        val buttonCancel: Button = findViewById(R.id.button_cancel)
        buttonCancel.setOnClickListener { removeLastDigit() }

    }

    @SuppressLint("SetTextI18n")
//    private fun appendNumber(number: String) {
//        val currentDisplay = display.text.toString()
//        display.text = currentDisplay + number
//        subDisplay.text = subDisplay.text.toString() + "" + number
//    }
    private fun appendNumber(number: String) {
        if (isDecimalClicked) {
            val currentDisplay = display.text.toString()
            display.text = if (currentDisplay == "0" || currentDisplay == "+" || currentDisplay == "-" || currentDisplay == "x" || currentDisplay == "/") number else "$currentDisplay$number"
            subDisplay.text = subDisplay.text.toString() + "" + number
        } else {
            val currentDisplay = display.text.toString()
            display.text = if (currentDisplay == "0" || currentDisplay == "+" || currentDisplay == "-" || currentDisplay == "x" || currentDisplay == "/") number else "$currentDisplay$number"
            subDisplay.text = subDisplay.text.toString() + "" + number
        }
    }

    private fun setOperator(op: String) {
        numInput1 = display.text.toString().toDouble()
        operator = op
        display.text = operator
        subDisplay.text = subDisplay.text.toString() + "" + operator + ""
        isDecimalClicked = false
    }

    private fun calculateResult() {
        val numInput2 = display.text.toString().toDouble()
        val result = when (operator) {

            "+" -> numInput1 + numInput2
            "-" -> numInput1 - numInput2
            "x" -> numInput1 * numInput2
            "/" -> numInput1 / numInput2

            else -> throw IllegalArgumentException("Invalid operator")
        }
        if (result % 1 == 0.0) {
            display.text = result.toInt().toString()
        } else {
            display.text = result.toString()
        }
    }

    private fun appendDecimalPoint() {
        if (!isDecimalClicked) {
            val currentDisplay = display.text.toString()
            val currentSubDisplay = subDisplay.text.toString()
            display.text = if (currentDisplay.isEmpty()) "0." else "$currentDisplay."
            subDisplay.text = if (currentSubDisplay.isEmpty()) "0." else "$currentSubDisplay."
            isDecimalClicked = true
        }
    }

    private fun clearDisplay() {
        display.text = "0"
        subDisplay.text = ""
        operator = ""
        numInput1 = 0.0
        isDecimalClicked = false
    }

    private  fun removeLastDigit() {
        val currentDisplay = display.text.toString()
        val currentSubDisplay = subDisplay.text.toString()
        if (currentDisplay.isNotEmpty()) {
            display.text = currentDisplay.substring(0, currentDisplay.length - 1)
            subDisplay.text = currentDisplay.substring(0, currentSubDisplay.length - 1)
            if(display.text == "") display.text = "0"
        }
    }
}

package com.example.myownapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class CalculatorActivity : AppCompatActivity() {
    lateinit var resultText: TextView
    lateinit var valueText: TextView
    var resultValue = ""
    var values = mutableListOf<Double>()
    var operators = mutableListOf<Char>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)

        resultText = findViewById(R.id.resultText)
        valueText = findViewById(R.id.valueText)

        val button_ac: Button = findViewById(R.id.btnAc)
        button_ac.setOnClickListener { clearAll() }

        val button_9: Button = findViewById(R.id.btn9)
        button_9.setOnClickListener { addValue(9) }

        val button_8: Button = findViewById(R.id.btn8)
        button_8.setOnClickListener { addValue(8) }

        val button_7: Button = findViewById(R.id.btn7)
        button_7.setOnClickListener { addValue(7) }

        val button_6: Button = findViewById(R.id.btn6)
        button_6.setOnClickListener { addValue(6) }

        val button_5: Button = findViewById(R.id.btn5)
        button_5.setOnClickListener { addValue(5) }

        val button_4: Button = findViewById(R.id.btn4)
        button_4.setOnClickListener { addValue(4) }

        val button_3: Button = findViewById(R.id.btn3)
        button_3.setOnClickListener { addValue(3) }

        val button_2: Button = findViewById(R.id.btn2)
        button_2.setOnClickListener { addValue(2) }

        val button_1: Button = findViewById(R.id.btn1)
        button_1.setOnClickListener { addValue(1) }

        val button_0: Button = findViewById(R.id.btn0)
        button_0.setOnClickListener { addValue(0) }

        val button_dot: Button = findViewById(R.id.btnDot)
        button_dot.setOnClickListener { addChar('.') }

        val button_negation : Button = findViewById(R.id.btnNegation)
        button_negation.setOnClickListener { addNegation() }

        val button_delete : Button = findViewById(R.id.btnDelete)
        button_delete.setOnClickListener { deleteLastDigit() }

        val button_multiplication: Button = findViewById(R.id.btnMultiplication)
        button_multiplication.setOnClickListener { addOperation('*') }

        val button_divide: Button = findViewById(R.id.btnDivide)
        button_divide.setOnClickListener { addOperation('/') }

        val button_substraction: Button = findViewById(R.id.btnSubstraction)
        button_substraction.setOnClickListener { addOperation('-') }

        val button_addition: Button = findViewById(R.id.btnAddition)
        button_addition.setOnClickListener { addOperation('+') }

        val button_equality: Button = findViewById(R.id.btnEquality)
        button_equality.setOnClickListener { equality() }

        val redirectButton: Button = findViewById(R.id.backBtn)
        redirectButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteLastDigit() {
        if (resultValue.isNotEmpty()) {
            resultValue = resultValue.dropLast(1)
            if (resultValue.isEmpty()) {
                resultValue = "0"
            }
            resultText.text = formatResult(resultValue.toDouble())
        }
    }

    private fun addNegation() {
        if (resultValue.isNotEmpty()) {
            val currentValue = resultValue.toDouble()
            val negatedValue = -currentValue
            resultValue = negatedValue.toString()
            resultText.text = formatResult(negatedValue)
        }
    }

    private fun clearAll() {
        values.clear()
        operators.clear()
        resultValue = ""
        resultText.text = "0.0"
        valueText.text = ""
    }

    private fun equality() {
        if (resultValue.isNotEmpty()) {
            values.add(resultValue.toDouble())
        }

        var result = values.first()
        for (i in 0 until operators.size) {
            result = when (operators[i]) {
                '+' -> result + values[i + 1]
                '-' -> result - values[i + 1]
                '*' -> result * values[i + 1]
                '/' -> if (values[i + 1] != 0.0) result / values[i + 1] else Double.NaN
                else -> Double.NaN
            }
        }

        updateValueText()
        resultText.text = formatResult(result)
        values.clear()
        operators.clear()
        resultValue = result.toString()
    }

    private fun addOperation(operation: Char) {
        if (resultValue.isNotEmpty()) {
            values.add(resultValue.toDouble())
            resultValue = ""
        }
        operators.add(operation)
        updateValueText()
    }

    private fun addChar(char: Char) {
        resultValue += char
        resultText.text = formatResult(resultValue.toDouble())
        updateValueText()
    }

    private fun addValue(number: Int) {
        resultValue += number.toString()
        resultText.text = formatResult(resultValue.toDouble())
        updateValueText()
    }

    private fun updateValueText() {
        val expression = StringBuilder()
        for (i in values.indices) {
            expression.append(formatResult(values[i]))
            if (i < operators.size) {
                expression.append(" ").append(operators[i]).append(" ")
            }
        }
        valueText.text = expression.toString()
    }

    private fun formatResult(value: Double): String {
        val decimalFormat = DecimalFormat("#.###")
        return decimalFormat.format(value)
    }
}

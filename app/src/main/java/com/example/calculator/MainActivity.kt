package com.example.calculator
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder



class MainActivity : AppCompatActivity() {
    lateinit var tvExpression: EditText
    lateinit var result: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        tvExpression = findViewById(R.id.tvExpression)
        result = findViewById(R.id.result)

        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)
        val btnAdd: Button = findViewById(R.id.btnAdd)
        val btnSub: Button = findViewById(R.id.btnSub)
        val btnMul: Button = findViewById(R.id.btnMul)
        val btnDiv: Button = findViewById(R.id.btnDiv)
        val btnEqual: Button = findViewById(R.id.btnEqual)
        val btnDot: Button = findViewById(R.id.btnDot)
        val btn: Button = findViewById(R.id.btn)
        val btnC: Button = findViewById(R.id.btnC)
        val btnCE: Button = findViewById(R.id.btnCE)
        val btnBS: Button = findViewById(R.id.btnBS)

        fun appendVal(string: String, isClear: Boolean) {
            if (isClear) {
                tvExpression.text.clear()
            } else {
                tvExpression.append(string)
            }
        }

        btn0.setOnClickListener { appendVal("0", false) }
        btn1.setOnClickListener { appendVal("1", false) }
        btn2.setOnClickListener { appendVal("2", false) }
        btn3.setOnClickListener { appendVal("3", false) }
        btn4.setOnClickListener { appendVal("4", false) }
        btn5.setOnClickListener { appendVal("5", false) }
        btn6.setOnClickListener { appendVal("6", false) }
        btn7.setOnClickListener { appendVal("7", false) }
        btn8.setOnClickListener { appendVal("8", false) }
        btn9.setOnClickListener { appendVal("9", false) }
        btnDot.setOnClickListener { appendVal(".", false) }


        fun removeLastChar() {
            val currentText = tvExpression.text.toString()
            if (currentText.isNotEmpty()) {
                tvExpression.setText(currentText.dropLast(1))
            }
        }

        fun clearAll() {
            tvExpression.text.clear()
            result.text.clear()
        }

        fun clearEntry() {
            val currentText = tvExpression.text.toString()
            val lastNumberIndex = currentText.lastIndexOfAny(charArrayOf('+', '-', '*', '/'))

            if (lastNumberIndex != -1) {
                tvExpression.setText(currentText.substring(0, lastNumberIndex + 1))
            } else {
                tvExpression.text.clear()
            }
        }

        btnCE.setOnClickListener { clearEntry() }
        btnDiv.setOnClickListener { appendVal(" / ", false) }
        btnMul.setOnClickListener { appendVal(" * ", false) }
        btnSub.setOnClickListener { appendVal(" - ", false) }
        btnAdd.setOnClickListener { appendVal(" + ", false) }
        btnC.setOnClickListener { clearAll() }

        btnBS.setOnClickListener { removeLastChar() }


        btnEqual.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val re = expression.evaluate()
                val longResult = re.toLong()
                if (re == longResult.toDouble()) {
                    result.setText(longResult.toString())
                } else {
                    result.setText(re.toString())
                }
            } catch (e: Exception) {
                result.setText("Lỗi")
            }
        }
    }
}

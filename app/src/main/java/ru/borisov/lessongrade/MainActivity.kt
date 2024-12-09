package ru.borisov.lessongrade

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var gradeET: EditText
    lateinit var buttonGeneratedBTN: Button
    lateinit var resultTV: TextView
    private var selectedViewId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gradeET = findViewById(R.id.editTextGradeET)
        registerForContextMenu(gradeET)
        resultTV = findViewById(R.id.resultTV)
        registerForContextMenu(resultTV)
        buttonGeneratedBTN = findViewById(R.id.buttonGenerateBTN)
        buttonGeneratedBTN.setOnClickListener(this)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?,
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        selectedViewId = v?.id ?: 0
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_color -> {
                when (selectedViewId) {
                    R.id.editTextGradeET -> setColorForGradeET()
                    R.id.resultTV -> setColorForResultTV()
                }
            }

            R.id.menu_exit -> finish()
            else -> return super.onContextItemSelected(item)
        }

        return true
    }

    private fun setColorForResultTV() {
        when (resultTV.text.toString().toInt()) {
            in 1..10 -> R.color.red
            in 11..20 -> R.color.orange
            in 21..30 -> R.color.yellow
            in 31..40 -> R.color.green
            in 41..50 -> R.color.blue
            else -> null
        }?.let { resultTV.setBackgroundColor(getColor(it)) }
    }

    private fun setColorForGradeET() {
        when (gradeET.text.toString().toInt()) {
            1 -> R.color.orange
            2 -> R.color.yellow
            3 -> R.color.green
            4 -> R.color.blue
            5 -> R.color.red
            else -> null
        }?.let { gradeET.setBackgroundColor(getColor(it)) }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonGenerateBTN -> {
                val generateNumber = (1..50).random().toString()
                println(generateNumber)
                resultTV.text = generateNumber
            }
        }
    }
}
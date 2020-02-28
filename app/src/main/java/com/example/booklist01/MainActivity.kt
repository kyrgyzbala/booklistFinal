package com.example.booklist01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mSearchKey = findViewById<EditText>(R.id.search_key)
        val mSearchButton = findViewById<Button>(R.id.search_button_id)

        mSearchKey.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                mSearchKey.hint = ""
            } else {
                mSearchKey.hint = getString(R.string.search_hint)
            }
        }
        mSearchButton.setOnClickListener {
            val mIntent = Intent(this, ResultActivity::class.java).apply {
                putExtra("SEARCH_KEY", mSearchKey.text.toString())
            }
            startActivity(mIntent)
        }
    }
}

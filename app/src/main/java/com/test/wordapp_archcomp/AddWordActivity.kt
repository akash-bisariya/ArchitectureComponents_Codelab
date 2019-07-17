package com.test.wordapp_archcomp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_word.*

class AddWordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)

        btn_add.setOnClickListener {
            val intent = Intent()
            intent.putExtra("word",et_new_word.text.toString())
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

    }
}

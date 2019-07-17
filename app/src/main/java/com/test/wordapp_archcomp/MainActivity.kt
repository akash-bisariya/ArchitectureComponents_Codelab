package com.test.wordapp_archcomp

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private var wordAdapter:WordAdapter = WordAdapter(this)
    private lateinit var mWordViewModel:WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        rv_words.adapter = wordAdapter
        rv_words.layoutManager = LinearLayoutManager(this)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            startActivityForResult(Intent(this@MainActivity,AddWordActivity::class.java),1000)

        }

        mWordViewModel.allWords.observe(this, Observer { words: List<Word>? ->
            words?.let {
                wordAdapter.setWords(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK)
            data?.let {
                mWordViewModel.insert(Word(it.extras.getString("word")))
            }
    }
}

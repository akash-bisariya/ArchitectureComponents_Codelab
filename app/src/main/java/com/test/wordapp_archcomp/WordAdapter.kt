package com.test.wordapp_archcomp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class WordAdapter(private val context:Context) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private var mWords:ArrayList<Word> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): WordViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_words_recylcer,parent,false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount() =  mWords.size

    override fun onBindViewHolder(holder: WordViewHolder, pos: Int) {
        holder.tvWord.text = mWords[pos].mWord
        Log.d("======word list",""+mWords[pos].mWord)
    }

    class WordViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvWord: TextView = view.findViewById(R.id.tv_word)
    }

    fun setWords(wordList:List<Word>){
        mWords.clear()
        mWords.addAll(wordList)
        notifyDataSetChanged()
    }

}
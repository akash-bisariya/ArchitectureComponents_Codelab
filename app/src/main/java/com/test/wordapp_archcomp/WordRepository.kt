package com.test.wordapp_archcomp

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class WordRepository(private val wordDao: IWordDao) {

    val allWords:LiveData<List<Word>> = wordDao.getAllWords()

    @WorkerThread
    fun insert(word:Word){
        wordDao.insert(word)
    }

}
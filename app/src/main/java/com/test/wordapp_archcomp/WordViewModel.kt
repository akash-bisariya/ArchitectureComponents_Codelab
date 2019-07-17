package com.test.wordapp_archcomp

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.*

class WordViewModel(application: Application) : AndroidViewModel(application) {
    val viewModelScope = Job()
    val coroutinescope = CoroutineScope(Dispatchers.IO + viewModelScope)
    val repository:WordRepository
    val allWords:LiveData<List<Word>>
    init {
        val wordsDao = WordRoomDB.getDatabase(application,coroutinescope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    fun insert(word:Word){
        coroutinescope.launch {
            repository.insert(word)
        }
    }

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        coroutinescope.cancel()
    }

}
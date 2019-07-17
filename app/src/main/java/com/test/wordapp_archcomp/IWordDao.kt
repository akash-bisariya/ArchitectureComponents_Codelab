package com.test.wordapp_archcomp

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface IWordDao {
    @Insert
    fun insert(word:Word)

    @Query("Delete FROM Word")
    fun deleteAll()

    @Query("Select * from Word Order by word ASC")
    fun getAllWords():LiveData<List<Word>>

}
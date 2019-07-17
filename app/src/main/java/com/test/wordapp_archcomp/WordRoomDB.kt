package com.test.wordapp_archcomp

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDB : RoomDatabase() {

    abstract fun wordDao(): IWordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): WordRoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            return INSTANCE ?: synchronized(this) {

                Room.databaseBuilder(
                    context, WordRoomDB::class.java,
                    "Word_Database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

            }
        }

        private class WordDatabaseCallback(val coroutineScope: CoroutineScope) : RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)


                coroutineScope.launch(Dispatchers.IO) {
                    if(INSTANCE!=null)
                        populateDatabase(INSTANCE!!.wordDao())
                }

//                INSTANCE?.let { it: WordRoomDB? ->
//                    coroutineScope.launch(Dispatchers.Main){
//                        populateDatabase(it!!.wordDao())
//                    }
//                }
            }
        }

        fun populateDatabase(wordDao:IWordDao){
            Log.d("populateDatabase","populateDatabase")
            wordDao.deleteAll()
            wordDao.insert(Word("Hello"))
            wordDao.insert(Word("World"))
        }
    }



}
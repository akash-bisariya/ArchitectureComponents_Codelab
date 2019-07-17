package com.test.wordapp_archcomp

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity
class Word(@NonNull @ColumnInfo(name = "word") @PrimaryKey var mWord:String)
package com.example.data.dataSource.local.database.dao

import androidx.room.*

@Dao
interface BaseDAO<T> {
    @Insert
    fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(obj: List<T>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    fun insertAllIgnore(obj: List<T>)

    @Update
    fun update(obj: T): Int

    @Delete
    fun delete(obj: T)
}
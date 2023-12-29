package com.example.mywatertracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Query("SELECT * FROM account_table")
    fun getAll(): Flow<List<AccountEntity>>

    @Insert
    fun insertAll(accounts: List<AccountEntity>)

    @Insert
    fun insert(account: AccountEntity)

    @Query("DELETE FROM account_table")
    fun deleteAll()

    @Query("SELECT AVG(amountDrank) FROM account_table")
    fun getAvg(): Float

    @Query("SELECT MIN(amountDrank) FROM account_table")
    fun getMin(): Float

    @Query("SELECT MAX(amountDrank) FROM account_table")
    fun getMax(): Float
}
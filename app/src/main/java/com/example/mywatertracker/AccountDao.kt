package com.example.mywatertracker

import android.accounts.Account
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
}
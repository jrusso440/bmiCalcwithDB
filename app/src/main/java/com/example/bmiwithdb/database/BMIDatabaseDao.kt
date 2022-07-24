package com.example.bmiwithdb.database
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BMIDatabaseDao {
    @Insert
    suspend fun insert(BMI: CurrentBMI)
    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param BMI new value to write
     */
    @Update
    suspend  fun update(bmi: CurrentBMI)

    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key startTimeMilli to match
     */
    @Query("SELECT * from bmi_history_database WHERE BMIId = :key")
    suspend fun get(key: Long): CurrentBMI?
    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM bmi_history_database")
    suspend fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM bmi_history_database ORDER BY BMIId DESC")
    fun getAllBMI(): LiveData<List<CurrentBMI>>

    /**
     * Selects and returns the latest night.
     */
    @Query("SELECT * FROM bmi_history_database ORDER BY BMIId DESC LIMIT 1")
    suspend fun getBMI(): CurrentBMI?
}

}
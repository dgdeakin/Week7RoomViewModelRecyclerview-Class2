package com.application.week7roomclass2.data;

import android.support.v4.os.IResultReceiver;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Person person);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);


    @Query("DELETE FROM person_table")
    void deleteAll();

    @Query("SELECT * FROM person_table ORDER BY name ASC")
    LiveData<List<Person> > getStudents();
}

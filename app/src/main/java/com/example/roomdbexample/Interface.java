package com.example.roomdbexample;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Interface {
    @Query("select * from users")
    List<users> getusers();

    @Delete()
    void deleteTaskFromId(users users);

    @Query("select * from users where taskid in(:id)")
    public users getUserById(int id);

    @Insert
    void Insertuser(users insertuser);


}

package com.example.roomdbexample;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {users.class},version = 1,exportSchema = false)
public abstract class Appdatabase extends RoomDatabase {
    abstract Interface anInterface();
}

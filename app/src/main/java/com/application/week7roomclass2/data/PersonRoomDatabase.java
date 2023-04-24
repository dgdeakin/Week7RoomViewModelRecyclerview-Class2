package com.application.week7roomclass2.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Person.class}, version = 1)
public abstract class PersonRoomDatabase extends RoomDatabase {

    public abstract PersonDAO personDAO();

    private static volatile PersonRoomDatabase INSTANCE;

    static final int THREADS = 4;
    static final ExecutorService databaseWriterExecuter =
            Executors.newFixedThreadPool(4);


    public static PersonRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (PersonRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PersonRoomDatabase.class, "person_database")
                            .addCallback(databaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static RoomDatabase.Callback databaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriterExecuter.execute(() ->{
                PersonDAO personDAO = INSTANCE.personDAO();
                personDAO.deleteAll();

                Person person = new Person(0, "Name", "email@email");
                personDAO.insert(person);

                Person person1 = new Person(0, "Name1", "email@email1");
                personDAO.insert(person1);

            });
        }
    };

}

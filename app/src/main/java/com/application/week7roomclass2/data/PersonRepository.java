package com.application.week7roomclass2.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PersonRepository {


    PersonDAO personDAO;

    LiveData<List<Person>> personList;

    public PersonRepository(Application application) {

        PersonRoomDatabase db = PersonRoomDatabase.getDatabase(application);
        personDAO = db.personDAO();
        personList = personDAO.getStudents();
    }


    public LiveData<List<Person>> getPersons() {
        return  personList;
    }

    public void insert(Person person){
        PersonRoomDatabase.databaseWriterExecuter.execute(()->{
            personDAO.insert(person);
        });

    }

    public void update(Person person)
    {
        PersonRoomDatabase.databaseWriterExecuter.execute(()->{
            personDAO.update(person);
        });
    }

    public void delete(Person person)
    {
        PersonRoomDatabase.databaseWriterExecuter.execute(()->{
            personDAO.delete(person);
        });
    }
}

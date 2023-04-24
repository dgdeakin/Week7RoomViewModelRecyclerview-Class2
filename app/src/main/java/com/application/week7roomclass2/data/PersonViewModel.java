package com.application.week7roomclass2.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    PersonRepository personRepository;
    LiveData<List<Person>> persons;


    public PersonViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
        persons = personRepository.getPersons();
    }


    public LiveData<List<Person>> getPersons() {
        return persons;
    }


    public void insert(Person person){
        personRepository.insert(person);
    }

    public void update(Person person){
        personRepository.update(person);
    }

    public void delete(Person person){
        personRepository.delete(person);
    }


}

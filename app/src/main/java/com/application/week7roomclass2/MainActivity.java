package com.application.week7roomclass2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.application.week7roomclass2.data.Person;
import com.application.week7roomclass2.data.PersonViewModel;
import com.application.week7roomclass2.recyclerview.PersonListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PersonViewModel personViewModel;

    PersonListAdapter personListAdapter;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);

        personListAdapter = new PersonListAdapter(new PersonListAdapter.PersonDiff(), this, personViewModel);

        recyclerView.setAdapter(personListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                Person person = new Person(0,
                        data.getStringExtra("add_value1"),
                        data.getStringExtra("add_value2"));
                personViewModel.insert(person);
            }
        }
        else if(requestCode == 2){
            if(resultCode == RESULT_OK){
                Person person = new Person(Integer.parseInt(data.getStringExtra("id")),
                        data.getStringExtra("update_value1"),
                        data.getStringExtra("update_value2"));
                personViewModel.update(person);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        personViewModel.getPersons().observe(this, persons ->{
            personListAdapter.submitList(persons);
                }
                );
    }
}
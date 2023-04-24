package com.application.week7roomclass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.application.week7roomclass2.data.Person;

public class UpdateActivity extends AppCompatActivity {

    EditText name, email;
    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name = findViewById(R.id.editTextText2);
        email = findViewById(R.id.editTextTextEmailAddress2);

        Person person = (Person) getIntent().getSerializableExtra("model");

        name.setText(person.getName());
        email.setText(person.getEmail());

        updateButton = findViewById(R.id.button2);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameValue = name.getText().toString();
                String emailValue = email.getText().toString();
                int id = person.getId();


                Intent intent = new Intent();
                intent.putExtra("id", String.valueOf(id));
                intent.putExtra("update_value1", nameValue);
                intent.putExtra("update_value2", emailValue);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
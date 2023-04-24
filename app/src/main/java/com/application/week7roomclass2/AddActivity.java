package com.application.week7roomclass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText name, email;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = findViewById(R.id.editTextText);
        email = findViewById(R.id.editTextTextEmailAddress);

        addButton = findViewById(R.id.button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                String nameValue = name.getText().toString();
                String emailValue = email.getText().toString();

                intent.putExtra("add_value1", nameValue);
                intent.putExtra("add_value2", emailValue);
                setResult(RESULT_OK, intent);

                finish();
            }

        });
    }
}
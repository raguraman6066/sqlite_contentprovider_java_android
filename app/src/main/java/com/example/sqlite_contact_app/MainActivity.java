package com.example.sqlite_contact_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText person_name, person_age;
    Button save, load;
    TextView person_result;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        person_name = findViewById(R.id.person_name);
        person_age = findViewById(R.id.person_age);
        person_result = findViewById(R.id.person_result);
        save = findViewById(R.id.button);
        load = findViewById(R.id.button2);
        helper = new DatabaseHelper(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (person_name.getText().toString().equalsIgnoreCase("") || person_age.getText().toString().equalsIgnoreCase("")) {
                    person_name.setError("This cannot be empty");
                    person_age.setError("This cannot be empty");
                } else {
                    String name = person_name.getText().toString();
                    int age = Integer.parseInt(person_age.getText().toString());
                    //todo:perform insert operation
                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.COL_2, name);
                    values.put(DatabaseHelper.COL_3
                            , age);
                    helper.insertData(values);
                    Toast.makeText(MainActivity.this, "Data is inserted successfully", Toast.LENGTH_SHORT).show();

                    person_name.getText().clear();
                    person_age.getText().clear();
                    person_name.requestFocus();
                }


            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_result.setText("");
                Cursor c = helper.getAllData();
                //bring cursor to point the first row of the table
                c.moveToFirst();
                do {
                    int id = c.getInt(0);
                    String name = c.getString(1);
                    int age = c.getInt(2);
                    person_result.append(id+". "+name+" "+age+"\n");
                } while (c.moveToNext());
            }
        });
    }
}

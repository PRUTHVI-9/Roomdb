package com.example.roomdbexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {
    EditText add_task,add_dis;
    TextView add_date;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        add_task=findViewById(R.id.add_task);
        add_dis=findViewById(R.id.add_dis);
        add_date=findViewById(R.id.add_date);
        add=findViewById(R.id.add);
        Calendar calendar =Calendar.getInstance();

        add_date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTaskActivity.this);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int DayofMonth, int Month, int Year) {
                        int temp= Month+1;
                         Log.e("TAG", "onDateSet: " +temp);
                        add_date.setText(DayofMonth+"-"+Month+"-"+Year);
                    }
                });
                datePickerDialog.show();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Appdatabase appdatabase = Room.databaseBuilder(AddTaskActivity.this,Appdatabase.class,"ToDoList").allowMainThreadQueries().build();
                users userdata = new users();
                if (validateFields()){
                    userdata.TaskTitle = add_task.getText().toString();
                    userdata.TaskDiscription = add_dis.getText().toString();
                    userdata.date = add_date.getText().toString();
                    appdatabase.anInterface().Insertuser(userdata);
                    setResult(RESULT_OK);
                    finish();

                    Log.e("TAG", "onClick: "+userdata.TaskTitle );
                }
            }
        });
    }

    private boolean validateFields() {
        if(add_task.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(AddTaskActivity.this, "Please enter a valid title", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(add_dis.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(AddTaskActivity.this, "Please enter a valid description", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(add_date.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(AddTaskActivity.this, "Please enter date", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
}
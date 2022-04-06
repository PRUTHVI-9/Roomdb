package com.example.roomdbexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView addtask;
    RecyclerView recycler;
    public static int REQUST_ADD_TASK = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addtask = findViewById(R.id.addtask);
        recycler = findViewById(R.id.recycler);

        getdatafromdatabase();

        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,AddTaskActivity.class),REQUST_ADD_TASK);

            }
        });

        

    }

    private void getdatafromdatabase() {
        Appdatabase getdatabasevalue = Room.databaseBuilder(MainActivity.this,Appdatabase.class,"ToDoList").allowMainThreadQueries().build();
        List<users> data = new ArrayList<>();
        data = getdatabasevalue.anInterface().getusers();

        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        recycler.setAdapter(new RoomAdapter(MainActivity.this,data));
    }

    private void layoutAnimation(RecyclerView recyclerView){
        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController =
                AnimationUtils.loadLayoutAnimation(context,R.anim.layout_slide_ride);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", "onActivityResult: requestCode -"+requestCode );
        Log.e("TAG", "onActivityResult: resultCode -"+resultCode );

        if (requestCode == REQUST_ADD_TASK){
            if (resultCode == -1){
                getdatafromdatabase();
            }
        }
    }
}
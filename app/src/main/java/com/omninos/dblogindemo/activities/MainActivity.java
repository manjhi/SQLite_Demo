package com.omninos.dblogindemo.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.omninos.dblogindemo.R;
import com.omninos.dblogindemo.adapter.MyAdapter;
import com.omninos.dblogindemo.helperClass.DatabaseHelper;
import com.omninos.dblogindemo.modelClass.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<User> list = new ArrayList<>();
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        SetUp();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        databaseHelper = new DatabaseHelper(this);
    }

    private void SetUp() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        getList();
    }

    private void getList() {

        list = databaseHelper.getAllUser();
        MyAdapter adapter = new MyAdapter(MainActivity.this, list);
        recyclerView.setAdapter(adapter);
    }
}

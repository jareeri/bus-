package com.example.bus.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bus.R;

public class listviewdriver extends AppCompatActivity {

    ListView l;
    String tutorials[]
            = {"Algorithms", "Data Structures",
            "Languages", "Interview Corner",
            "GATE", "ISRO CS",
            "UGC NET CS", "CS Subjects",
            "Web Technologies"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewdriver);
        l = findViewById(R.id.list);
        ArrayAdapter<String> arr;
        arr
                = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                tutorials);
        l.setAdapter(arr);
    }
}

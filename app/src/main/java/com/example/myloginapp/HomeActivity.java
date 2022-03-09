package com.example.myloginapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myloginapp.Data.CartStoge;
import com.example.myloginapp.Data.DTOClip;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ArrayList<DTOClip> listdata;
   Button button;
   EditText editText;
    CartStoge cartStoge = new CartStoge(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.homesrceen);
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recycler_video);
        button = findViewById(R.id.btnAdd);
        editText = findViewById(R.id.txtLink);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_video = editText.getText().toString().split("v=")[1];
                System.out.println(id_video);
                cartStoge.addtolist(new DTOClip(1,id_video));
                load(myrv);
            }
        });
             load(myrv);
    }
    public void load(RecyclerView myrv){
        listdata = new ArrayList<>();
        listdata = cartStoge.getallcart();
        System.out.println(listdata.size());
        MainApdater myAdapter = new MainApdater(this,listdata);
        myrv.setLayoutManager(new GridLayoutManager(this,1));
        myrv.setAdapter(myAdapter);
    }
}

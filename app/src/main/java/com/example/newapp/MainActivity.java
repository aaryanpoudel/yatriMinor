package com.example.newapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    EditText placeID;
    EditText priceID;
    Button addID;
    DatabaseReference databasePlace;

    ListView listviewPlace;
    List<plist>dlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databasePlace = FirebaseDatabase.getInstance().getReference("place");

        placeID=(EditText) findViewById(R.id.placeID);
        priceID=(EditText)findViewById(R.id.priceID);
        addID=(Button)findViewById(R.id.addID);
        listviewPlace=(ListView)findViewById(R.id.listviewPlace);
        dlist=new ArrayList<>();

        addID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addplace();
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        databasePlace.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dlist.clear();
                for(DataSnapshot PlistSnapshot: dataSnapshot.getChildren()){
                    plist Plist =PlistSnapshot.getValue(plist.class);
                    dlist.add(Plist);
                }
                dlist adapter = new dlist(MainActivity.this,dlist);
                listviewPlace.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void addplace(){
        String name = placeID.getText().toString().trim();
        String number=priceID.getText().toString().trim();
        if(!TextUtils.isEmpty(name)){
            String id = databasePlace.push().getKey();
            plist Plist = new plist(id,name,number);
            databasePlace.child(id).setValue(Plist);
            Toast.makeText(this,"added place",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"you should enter place",Toast.LENGTH_LONG).show();
        }
    }
}

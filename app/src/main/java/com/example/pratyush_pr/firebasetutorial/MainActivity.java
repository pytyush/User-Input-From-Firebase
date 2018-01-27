package com.example.pratyush_pr.firebasetutorial;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private Button mFirebaseBtn;
    private EditText mText,mEmail;

    private DatabaseReference mDatabase;
// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseBtn = (Button) findViewById(R.id.firebase_btn);
        mText = (EditText)findViewById(R.id.textbox);
        mEmail = (EditText) findViewById(R.id.emailbox);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mText.getText().toString().trim();
                String email = mEmail.getText().toString().trim();

                HashMap<String, String> dataMap =new HashMap<String, String>();
                dataMap.put("Name", name);
                dataMap.put("Email",email);

                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }
}

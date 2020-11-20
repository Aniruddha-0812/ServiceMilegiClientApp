package com.example.prototype_1.ServiceMilegiOne.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prototype_1.ServiceMilegiOne.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ElectricianActivity extends AppCompatActivity {

    private String booking;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrician);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null )
            user_id = user.getUid();


        Intent intent = getIntent();
        booking = intent.getStringExtra("job");

        Button book = findViewById(R.id.Book_electrician);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book_Now();

            }
        });
    }

    private void Book_Now(){


        db.collection("Users").document(user_id).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String email = documentSnapshot.getString("email");
                        String first_name = documentSnapshot.getString("first_name");
                        String last_name = documentSnapshot.getString("last_name");
                        String mob_no = documentSnapshot.getString("mob_no");

                        Map<String, String> job = new HashMap<>();
                        job.put("email", email);
                        job.put("first_name", first_name);
                        job.put("last_name", last_name);
                        job.put("mob_no", mob_no);
                        job.put("job", booking);

                        db.collection("Orders").document(user_id).set(job)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){
                                            Toast.makeText(getApplicationContext() , "Order Placed Successfully" ,Toast.LENGTH_LONG).show();

                                        }
                                        else{
                                            Toast.makeText(getApplicationContext() , "Failed To Place Order" ,Toast.LENGTH_LONG).show();

                                        }

                                        startActivity(new Intent(ElectricianActivity.this , MainActivity.class));

                                    }
                                });



                    }

                });


    }

}
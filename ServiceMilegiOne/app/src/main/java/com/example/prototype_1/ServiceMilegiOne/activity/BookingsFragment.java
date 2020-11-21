package com.example.prototype_1.ServiceMilegiOne.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prototype_1.ServiceMilegiOne.R;
import com.example.prototype_1.ServiceMilegiOne.utils.Bookings_Adapter;
import com.example.prototype_1.ServiceMilegiOne.utils.Orders;
import com.example.prototype_1.ServiceMilegiOne.utils.User;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;


public class BookingsFragment extends Fragment  {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private Bookings_Adapter adapter;
    private DocumentReference documentReference;
    private FirebaseUser mFirebaseUser;
    private String User_id;
    private  String final_job;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bookings, container, false);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mAuth.getCurrentUser();
        User_id = mFirebaseUser.getUid();

        set_up_recycleView(view);

        return view;
    }

    private void set_up_recycleView(View view) {

        Query query = db.collection("Users").document(User_id).collection("Orders");

        FirestoreRecyclerOptions<Orders> options = new FirestoreRecyclerOptions.Builder<Orders>()
                .setQuery(query, Orders.class)
                .build();

        adapter = new Bookings_Adapter(options);

        recyclerView = view.findViewById(R.id.firestore_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new Bookings_Adapter.onItem_clickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Orders orders = documentSnapshot.toObject(Orders.class);
                final_job = orders.getJob();

                String doc_id = documentSnapshot.getId();
                cancel_order(doc_id);
            }
        });

    }


    private void cancel_order(String doc_id) {

        Map<String, Object> map = new HashMap<>();
        map.put("cancel", true);
        map.put("job" ,final_job);
        map.put("is_complete" ,false);

        documentReference = db.collection("Users").document(User_id).collection("Orders").document(doc_id);


        documentReference.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Edit","Not updated");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
    }


}
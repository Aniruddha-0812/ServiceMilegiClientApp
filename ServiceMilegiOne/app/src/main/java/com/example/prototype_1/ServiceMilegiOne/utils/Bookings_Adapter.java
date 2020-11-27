package com.example.prototype_1.ServiceMilegiOne.utils;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prototype_1.ServiceMilegiOne.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Calendar;
import java.util.Locale;

public class Bookings_Adapter extends FirestoreRecyclerAdapter<Orders, Bookings_Adapter.Holder> {


    public Bookings_Adapter(@NonNull FirestoreRecyclerOptions<Orders> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull Orders model) {
        holder.job.setText(model.getJob());
        holder.status.setText(model.isComplete() + "");
        //String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        holder.date.setText(model.getMessageTime().toDate().toString());
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tile, parent, false);
        return new Holder(v);
    }
    class Holder extends RecyclerView.ViewHolder{

        TextView job, status, date;
        Button  clear;

        public Holder(@NonNull View itemView) {
            super(itemView);

            job = itemView.findViewById(R.id.order_name);
            status = itemView.findViewById(R.id.status);
            clear = itemView.findViewById(R.id.on_clear);
            date = itemView.findViewById(R.id.time);

            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (view.equals(clear)) {
                        removeAt(getAdapterPosition());
                        Toast.makeText(view.getContext(), "Item removed successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }
        public void removeAt( int position){
            getSnapshots().getSnapshot(position).getReference().delete();
        }
    }

}

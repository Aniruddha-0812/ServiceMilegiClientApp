package com.example.prototype_1.ServiceMilegiOne.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prototype_1.ServiceMilegiOne.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class Bookings_Adapter extends FirestoreRecyclerAdapter<Orders, Bookings_Adapter.Holder> {

    private  onItem_clickListener listener;
    private Boolean Cancel;

    public Bookings_Adapter(@NonNull FirestoreRecyclerOptions<Orders> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull Orders model) {
        holder.job.setText(model.getJob());
        holder.status.setText(model.isStatus() + " ");
        Cancel =  model.isCancel();

        if(Cancel)
        {
            holder.cancel.setEnabled(false);
        }

    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tile, parent, false);
        return new Holder(v);
    }
    class Holder extends RecyclerView.ViewHolder{

        TextView job, status;
        Button cancel;

        public Holder(@NonNull View itemView) {
            super(itemView);
            job = itemView.findViewById(R.id.order_name);
            status = itemView.findViewById(R.id.status);
            cancel = itemView.findViewById(R.id.on_cancel);

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null)
                    {
                        listener.onItemClick(getSnapshots().getSnapshot(position) , position);

                    }


                }
            });
        }



    }

    public  interface   onItem_clickListener
    {
        void onItemClick(DocumentSnapshot documentSnapshot , int position);
    }

    public  void setOnItemClickListener(onItem_clickListener listener){
        this.listener = listener;

    }
}

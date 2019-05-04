package com.example.completenilin.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.completenilin.Data.User;
import com.example.completenilin.Managers.UsersManager;
import com.example.completenilin.ProfileActivity;
import com.example.completenilin.R;


public class UsersAdapter extends RecyclerView.Adapter {

    private static final String TAG = "UsersAdapter";
    UsersManager usersManager;
    Context context;

    public UsersAdapter(Context context) {

    usersManager = UsersManager.getInstance(context);
    this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_row, viewGroup, false);
        Log.d(TAG, "im here");
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

       User user = usersManager.getUsers().get(i);
        ((MyViewHolder) viewHolder).id.setText("user " + user.getId());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(context, ProfileActivity.class).putExtra("userPosition", i);
                context.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + usersManager.getUsers().size());
        return usersManager.getUsers().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView id;

        public MyViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.user_id);
        }
    }
}

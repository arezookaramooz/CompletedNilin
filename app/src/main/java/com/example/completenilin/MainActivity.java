package com.example.completenilin;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.completenilin.Adapters.UsersAdapter;
import com.example.completenilin.Retrofit.UsersNetworkClient;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private UsersAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        Log.d(TAG, "Hi");

        mySwipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        recyclerView = findViewById(R.id.users_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UsersAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                UsersNetworkClient usersNetworkClient = new UsersNetworkClient(MainActivity.this, adapter);
                usersNetworkClient.start();

                mySwipeRefreshLayout.setRefreshing(false);
            }
        });

        Log.d(TAG, "im here 1");

        UsersNetworkClient usersNetworkClient = new UsersNetworkClient(MainActivity.this, adapter);
        usersNetworkClient.start();
        Log.d(TAG, "im here 2");
    }

    @Override
    protected void onResume() {

        super.onResume();
        adapter.notifyDataSetChanged();
    }

}

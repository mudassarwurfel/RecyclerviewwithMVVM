package com.example.recyclerviewwithmvvm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.recyclerviewwithmvvm.Activity.Model.NicePlaces;
import com.example.recyclerviewwithmvvm.Activity.adapters.ListAdapter;
import com.example.recyclerviewwithmvvm.Activity.viewModels.MyActivityViewModel;
import com.example.recyclerviewwithmvvm.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyActivityViewModel myActivityViewModel;
    private ListAdapter myAdapter;
    private Button mButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rvList);
        mButtonAdd = findViewById(R.id.btnAdd);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myActivityViewModel.addNewValue(new NicePlaces(R.mipmap.ic_launcher, "New Value"));
            }
        });

        myActivityViewModel = ViewModelProviders.of(this).get(MyActivityViewModel.class);
        myActivityViewModel.init();
        myActivityViewModel.getListOfNicePlaces().observe(this, new Observer<List<NicePlaces>>() {
            @Override
            public void onChanged(List<NicePlaces> nicePlaces) {
                myAdapter.notifyDataSetChanged();
            }
        });


        myActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(getApplicationContext(), "Progress showing", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Progress hide", Toast.LENGTH_SHORT).show();
                    mRecyclerView.smoothScrollToPosition(myActivityViewModel.getListOfNicePlaces().getValue().size() - 1);
                }
            }
        });


        myAdapter = new ListAdapter(getApplicationContext(), myActivityViewModel.getListOfNicePlaces().getValue());
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }
}

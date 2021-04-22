package com.example.ceodeaja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ceodeaja.adapter.AdapterData;
import com.example.ceodeaja.api.ApiRequestData;
import com.example.ceodeaja.api.RetroServer;
import com.example.ceodeaja.model.DataModel;
import com.example.ceodeaja.model.ResponModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<>();

    private SwipeRefreshLayout swlData;
    private ProgressBar pbData;

    private FloatingActionButton fabTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvData = findViewById(R.id.rv_binatang);
        swlData = findViewById(R.id.swl_data);
        pbData = findViewById(R.id.pb_data);

        fabTambah = findViewById(R.id.fab_tambah_hewan);

        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        //        findAnimalList();

        swlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swlData.setRefreshing(true);
                findAnimalList();
                swlData.setRefreshing(false);
            }
        });

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateAnimalActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        findAnimalList();
    }

    public void findAnimalList() {
        pbData.setVisibility(View.VISIBLE);
        ApiRequestData arData = RetroServer.connectRetrofit().create(ApiRequestData.class);
        Call<ResponModel> tampilData = arData.findAllAnimals();

        tampilData.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                int status = response.body().getStatus();
                String message = response.body().getMessage();
                Log.d("danangkonang", message);

                //Toast.makeText(MainActivity.this, "status" + status, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();
                adData = new AdapterData(MainActivity.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

                pbData.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,"gagal" + t.getMessage(),Toast.LENGTH_SHORT).show();
                pbData.setVisibility(View.INVISIBLE);
            }
        });
    }
}
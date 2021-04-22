package com.example.ceodeaja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ceodeaja.api.ApiRequestData;
import com.example.ceodeaja.api.RetroServer;
import com.example.ceodeaja.model.ResponModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAnimalActivity extends AppCompatActivity {

    private EditText etName, etColor, etDescription;
    private Button btnSimpan;
    private String name, color, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_animal);

        etName = findViewById(R.id.et_nama);
        etColor = findViewById(R.id.et_color);
        etDescription = findViewById(R.id.et_description);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                color = etColor.getText().toString();
                description = etDescription.getText().toString();
                if(name.trim().equals("")){
                    etName.setError("name harus diisi");
                }
                else if(color.trim().equals("")){
                    etColor.setError("color wajib di isi");
                }
                else if(description.trim().equals("")){
                    etDescription.setError("harus diisi");
                }
                else {
                    createDataAnimal();
//                    Log.i("console","hewan: " + name + "warna: " + color +  "desc: "+ description);
                }
            }
        });
        
    }

    private void createDataAnimal(){
        ApiRequestData arData = RetroServer.connectRetrofit().create(ApiRequestData.class);
        Call<ResponModel> simpandata = arData.createAnimal(name, color, description);
        Log.d("danangkonang", "masuk bosss");
        simpandata.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                String message = response.body().getMessage();
                Log.d("danangkonang", "sukses bosss "+ message);
            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {
                Log.d("danangkonang", "error bosss " + t.getMessage());
            }
        });
//        simpanData.enqueue(new Callback<ResponModel>() {
//            @Override
//            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
//                int status = response.body().getStatus();
//                String message = response.body().getMessage();
//
//                Toast.makeText(CreateAnimalActivity.this, "status " + status + " message: " + message, Toast.LENGTH_SHORT).show();
//                //                finish();
//            }
//
//            @Override
//            public void onFailure(Call<ResponModel> call, Throwable t) {
//                Toast.makeText(CreateAnimalActivity.this, "pesan err: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
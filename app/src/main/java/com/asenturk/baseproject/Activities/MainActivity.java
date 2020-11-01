package com.asenturk.baseproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.asenturk.baseproject.API.APIClient;
import com.asenturk.baseproject.API.Services.TodoService;
import com.asenturk.baseproject.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnTestRetrofit;

    //To Do Service
    private TodoService todoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        RegisterHandlers();
    }

    private void Init(){
        btnTestRetrofit=findViewById(R.id.btnTestRetrofit);

        todoService = APIClient.getClient().create(TodoService.class);
    }

    private void RegisterHandlers(){
        btnTestRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create a call
                Call<ResponseBody> call=todoService.getTodoById(1);
                //Execute the call
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful())
                            Toast.makeText(MainActivity.this, "Retrofit executed successfully!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "ERROR: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
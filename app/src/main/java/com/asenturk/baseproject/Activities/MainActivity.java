package com.asenturk.baseproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.asenturk.baseproject.API.APIClient;
import com.asenturk.baseproject.API.Services.TodoService;
import com.asenturk.baseproject.Entities.Category;
import com.asenturk.baseproject.Entities.Item;
import com.asenturk.baseproject.R;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {

    private Button btnTestRetrofit;
    private Button btnActiveAndroid;
    private Button btnTestWebView;

    //To Do Service
    private TodoService todoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //To make activity full-screen (No status bar-no title bar)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        File temp =getDatabasePath("TempDB");


        //SQLITE-ORM TOOL REGISTER
        ActiveAndroid.initialize(this);

        Init();
        RegisterHandlers();
    }

    private void Init(){
        btnTestRetrofit=findViewById(R.id.btnTestRetrofit);
        btnActiveAndroid=findViewById(R.id.btnActiveAndroid);
        btnTestWebView=findViewById(R.id.btnTestWebView);

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


        btnActiveAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert operation with ActiveAndroid
                Category category = new Category();
                category.name="Kategori_"+ Calendar.getInstance().getTime().toString();
                category.save();

                //select all categories with ActiveAndroid
                List<Category> result = new Select().from(Category.class).execute();

                Item item = new Item();
                item.category=result.get(0);
                item.name="deneme2";
                item.save();
                //delete operation with ActiveAndroid
                //Category.delete(Category.class,category.getId());

                Toast.makeText(MainActivity.this, "Active android executed successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        btnTestWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
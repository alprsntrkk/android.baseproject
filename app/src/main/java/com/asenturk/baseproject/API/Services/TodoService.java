package com.asenturk.baseproject.API.Services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TodoService {
    @GET("todos")
    Call<ResponseBody> getTodoById(@Query("id") int id);
}

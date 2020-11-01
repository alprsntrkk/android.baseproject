package com.asenturk.baseproject.API;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    //retrofit instance
    private static Retrofit retrofit=null;
    //rest-api base-url *URL MUST END WITH '/'*
    private static String Base_Url="http://jsonplaceholder.typicode.com/";

    //method to get retrofit instance
    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
        }
        return retrofit;
    }

}
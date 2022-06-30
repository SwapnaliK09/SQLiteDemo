package com.example.databasedemo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DBAPIInterface {
    @GET("entries")
    Call<String> STRING_CALL();
}

package com.example.booklist01;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("v1/volumes?q=android")
    Call<BooksT> getBooks();

    @GET("v1/volumes")
    Call<BooksT> getBookSearchRes(@Query("q") String searchKey);

}

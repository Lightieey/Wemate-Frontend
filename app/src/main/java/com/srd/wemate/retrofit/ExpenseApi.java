package com.srd.wemate.retrofit;


import com.srd.wemate.expense.Expense;
import com.srd.wemate.expense.ExpenseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ExpenseApi {

    @GET("/expense/get-all")
    Call<List<ExpenseData>> getAllExpense();

    @POST("/expense/save")
    Call<ExpenseData> save(@Body ExpenseData expense);
}

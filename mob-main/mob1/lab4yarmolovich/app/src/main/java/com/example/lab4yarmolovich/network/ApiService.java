package com.example.lab4yarmolovich.network;

import com.example.lab4yarmolovich.model.Element;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface ApiService {
    @GET("users") // Получение списка пользователей
    Call<List<Element>> getElements();
}
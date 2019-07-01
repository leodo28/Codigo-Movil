package com.santiago.canchas;

import com.santiago.canchas.Models.Cancha;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiciosCanchas {
    @GET("/servicios/canchas/")
    Call<List<Cancha>> listarCanchas();
}

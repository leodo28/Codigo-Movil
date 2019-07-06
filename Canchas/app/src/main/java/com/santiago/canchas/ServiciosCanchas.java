package com.santiago.canchas;

import com.santiago.canchas.Models.Cancha;
import com.santiago.canchas.Models.Reserva;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiciosCanchas {
    @GET("/servicios/canchas/")
    Call<List<Cancha>> listarCanchas();

    @POST("/servicios/reservas/")
    @FormUrlEncoded
    Call<Reserva>registrarReserva(@Field("fecha")String fecha,
                                  @Field("hora")String hora,
                                  @Field("usuario")int usuario,
                                  @Field("cancha")int cancha,
                                  @Field("numero_cancha")int numero_cancha);
}

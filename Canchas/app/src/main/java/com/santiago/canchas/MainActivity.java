package com.santiago.canchas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.santiago.canchas.Models.Cancha;
import com.santiago.canchas.Models.Reserva;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static String url = "http://leodo28.pythonanywhere.com";
    AdapterCancha adaptador;
    List<Cancha> canchas = new ArrayList<>();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        lv = findViewById(R.id.listv);


        ServiciosCanchas canchaservicios = retrofit.create(ServiciosCanchas.class);
        Call<List<Cancha>> call = canchaservicios.listarCanchas();
        call.enqueue(new Callback<List<Cancha>>() {
            @Override
            public void onResponse(Call<List<Cancha>> call, Response<List<Cancha>> response) {
                switch (response.code()){
                    case 200:
                        canchas = response.body();
                        adaptador = new AdapterCancha(canchas,getApplicationContext(),R.layout.item_cancha);
                        lv.setAdapter(adaptador);

                        for(Cancha c : canchas){
                            Log.d("cancha", c.getId()+"");
                                                    }
                    break;
                }
            }

            @Override
            public void onFailure(Call<List<Cancha>> call, Throwable t) {

            }
        });

        Call<Reserva> registrar=
                canchaservicios.registrarReserva("2019-06-28","23:59:00",
                        1,1,1);
        registrar.enqueue(new Callback<Reserva>() {
            @Override
            public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                Log.e("Codigo cancha",response.code()+"");
                Log.d("reserva",response.code()+"");
                switch(response.code()){
                    case 200:
                        Reserva reservas = response.body();
                        Log.d("cancha",reservas.getFecha()+"");
                        break;
                }
            }

            @Override
            public void onFailure(Call<Reserva> call, Throwable t) {
                Log.e("Error Cancha",t.getMessage());
            }
        });
    }
}

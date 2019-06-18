package com.example.tecsup.tiendita;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class Main2Activity extends AppCompatActivity {

        ListView lv;
        static String API_URL="https://viveyupi.com";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            //resultado = findViewById(R.id.textres);
            lv = (ListView) findViewById(R.id.listv);

         Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            Main2Activity.Yupi yupi = retrofit.create(Main2Activity.Yupi.class);

            Call<List<Proveedor>> call = yupi.proveedores();

            // Fetch and print a list of the contributors to the library.
            call.enqueue(new Callback<List<Proveedor>>() {
                @Override
                public void onResponse(Call<List<Proveedor>> call, Response<List<Proveedor>> response) {
                    switch(response.code()){
                        case 200:
                            List<Proveedor> proveedores =response.body();
                            AdaptadorProveedores adapter = new AdaptadorProveedores(getApplicationContext(), proveedores);
                            lv.setAdapter(adapter);

                            break;
                        case 401:
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onFailure(Call<List<Proveedor>> call, Throwable t) {
                    Log.e("error",t.toString());

                }
            });


/*
            Call<Proveedor> call_editar = yupi.agregar_provedor("TECSUP","2027376236213","Urb. Monterrey");
            call_editar.enqueue(new Callback<Proveedor>() {
                @Override
                public void onResponse(Call<Proveedor> call, Response<Proveedor> response) {
                    switch(response.code()){
                        case 200:
                            Proveedor token =response.body();
                            Log.d("Empresa",token.razon_social+" "+ token.ruc+" "+token.direccion);
                            break;
                        case 401:
                            break;
                        default:
                            break;
                    }
                }

                @Override
                public void onFailure(Call<Proveedor> call, Throwable t) {
                    Log.d("e",t.toString());
                }
            });*/

        }

        public interface Yupi {
            @GET("/api/proveedores/")
            Call<List<Proveedor>> proveedores();

            @POST("/api/proveedores/")
            @FormUrlEncoded
            Call<Proveedor> agregar_provedor(
                    @Field("razon_social") String rzsc,
                    @Field("ruc") String ruc,
                    @Field("direccion") String dir);
        }
 }


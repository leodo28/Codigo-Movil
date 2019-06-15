package com.example.tecsup.tiendita;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {
    TextView resultado;
    ListView lv;
    static String API_URL="https://viveyupi.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //resultado = findViewById(R.id.textres);
        lv = (ListView) findViewById(R.id.listv);

        //probaremos si esta conectado
        /*if (isConnected()) {
            String r = GET();
            resultado.setText(r);
            Toast.makeText(this, "SI esta conectado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "NO esta conectado", Toast.LENGTH_SHORT).show();
        }*/


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        Yupi yupi = retrofit.create(Yupi.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<Categoria>> call = yupi.categorias(1);

        // Fetch and print a list of the contributors to the library.
        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                switch(response.code()){
                    case 200:
                        List<Categoria> categorias =response.body();
                            AdapterCategory adapter = new AdapterCategory(getApplicationContext(), categorias);
                            lv.setAdapter(adapter);

                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Log.e("error",t.toString());

            }
        });

    }
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;

    }

    private String GET(){
        String s="{\"nombre\":\"Santiago\",\n"+"\"edad\":\"23\""+"}";
        JSONObject js;
        try {
            js = new JSONObject(s);
            String nombre = js.getString("nombre");
            int edad = js.getInt("edad");
            return nombre;
           // return js.toString();
        } catch (JSONException e) {
            return e.toString();
        }
    }

    public static class Contributor {
            public final String login;
            public final int contributions;

            public Contributor(String login, int contributions) {
                this.login = login;
                this.contributions = contributions;
            }
        }
        public interface GitHub {
            @GET("/repos/{owner}/{repo}/contributors")
            Call<List<Contributor>> contributors(
                    @Path("owner") String owner,
                    @Path("repo") String repo);
        }

    public interface Yupi {
        @GET("/api/categorias/nivel/{nivel}/")
        Call<List<Categoria>> categorias(
                @Path("nivel") Integer nivel);

    }
}

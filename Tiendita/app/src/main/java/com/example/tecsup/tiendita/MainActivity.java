package com.example.tecsup.tiendita;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {
    TextView resultado;
    static String API_URL="https://api.github.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado = findViewById(R.id.textres);

        //probaremos si esta conectado
        if (isConnected()) {
            String r = GET();
            resultado.setText(r);
            Toast.makeText(this, "SI esta conectado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "NO esta conectado", Toast.LENGTH_SHORT).show();
        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        GitHub github = retrofit.create(GitHub.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<Contributor>> call = github.contributors("square", "retrofit");

        // Fetch and print a list of the contributors to the library.
        List<Contributor> contributors = null;
        try {
            contributors = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }
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
}

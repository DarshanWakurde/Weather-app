package com.example.wetherown;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ImageView background,search,day;

    TextView city,temp,cond;
    EditText cityedit;
    RequestQueue requestQueue ;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestQueue= Volley.newRequestQueue(getApplicationContext());

        background=findViewById(R.id.back);
        search=findViewById(R.id.IVSearch);
        day=findViewById(R.id.idIVIcon);




        city=findViewById(R.id.Vcity);
        temp=findViewById(R.id.idTempreture);
        cond=findViewById(R.id.textView);

        cityedit=findViewById(R.id.cityname);





search.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        getWeatherInfo(cityedit.getText().toString());
    }
});


    }



    private void getWeatherInfo(String cityName){
        city.setText(cityName);



String uri="https://api.weatherapi.com/v1/forecast.json?key=2f7e60baba0b4ed48b363559231009&q=".concat(cityName).concat("&days=1&aqi=no&alerts=no");

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Picasso.get().load("https:".concat(response.getJSONObject("current").getJSONObject("condition").getString("icon"))).into(day);
                    cond.setText(response.getJSONObject("current").getJSONObject("condition").getString("text"));
                    temp.setText(response.getJSONObject("current").getString("temp_c")+"");


                    if(response.getJSONObject("current").getString("is_day").equals("1")){

                        Picasso.get().load("https://w0.peakpx.com/wallpaper/525/262/HD-wallpaper-sunny-day-bright-clouds-color-nature-new-nice-sky-sun.jpg").into(background);
                    }
                    else {

                        Picasso.get().load("https://wallpapercave.com/wp/wp5139965.jpg").into(background);

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Erroe",error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
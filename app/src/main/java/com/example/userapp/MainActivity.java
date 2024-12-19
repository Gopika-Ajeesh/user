package com.example.userapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv1=(TextView) findViewById(R.id.te);
        callApi();

    }

    private void callApi() {
        String apiUrl="https://jsonplaceholder.typicode.com/users";
        JsonArrayRequest request=new JsonArrayRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        StringBuilder result = new StringBuilder();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject ob = null;
                            try {
                                ob = response.getJSONObject(i);
                                String s1 = ob.getString("name");
                                String s2 = ob.getString("username");
                                String s3 = ob.getString("phone");
                                String s4 = ob.getString("website");

                                result.append("Name:").append(s1).append("\n");
                                result.append("Userame:").append(s2).append("\n");
                                result.append("Phone:").append(s3).append("\n");
                                result.append("Website:").append(s4).append("\n");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);

                            }
                            tv1.setText(result.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}
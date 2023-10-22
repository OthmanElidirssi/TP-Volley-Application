package com.example.volleyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nom;
    private EditText prenom;
    private Spinner ville;
    private RadioButton m;
    private RadioButton f;
    private Button add;

    private Button view;

    RequestQueue requestQueue;
    String insertUrl = "http://192.168.1.35:8087/api/student/save";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        ville = findViewById(R.id.ville);
        add = findViewById(R.id.add);
        m = findViewById(R.id.m);
        f = findViewById(R.id.f);
        view=findViewById(R.id.view);
        add.setOnClickListener(this);
        view.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this,StudentsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View v) {
        Log.d("ok", "ok");
        if (v == add) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());

            JSONObject params = new JSONObject();
            try {
                params.put("nom", nom.getText().toString());
                params.put("prenom", prenom.getText().toString());
                params.put("ville", ville.getSelectedItem().toString());
                params.put("sexe", m.isChecked() ? "homme" : "femme");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, insertUrl, params,
                    response -> {

                        nom.setText("");
                        prenom.setText("");
                        try {
                            String message=response.getString("message");
                            showSimpleDialog(MainActivity.this,message);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    },
                    error -> {

                        showSimpleDialog(MainActivity.this, new String(error.networkResponse.data));
                    }
            );

            requestQueue.add(request);
        }
    }
    public void showSimpleDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, which) -> {
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


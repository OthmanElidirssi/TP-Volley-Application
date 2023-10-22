package com.example.volleyapplication.student;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.List;

public class StudentApi {

    public static void getAllStudents(final Context context,StudentService studentService,final VolleyCallback callback) {
        String url = "http://192.168.1.35:8087/api/student/all";

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<Student>>() {}.getType();
                        studentService.addAll(gson.fromJson(response, listType));

                        callback.onSuccess(studentService.findAll());
                    } catch (Exception e) {
                        callback.onError(e.getMessage());
                    }
                },
                error -> callback.onError(error.getMessage()));

        queue.add(stringRequest);
    }
    public static void deleteStudent(Integer studentId, Context context, final VolleyCallback callback) {

        String url = "http://192.168.1.35:8087/api/student/delete/" + studentId;

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                response ->{
                    callback.onSuccess(null);
                },
                error -> callback.onError(error.getMessage()));

        queue.add(stringRequest);
    }

    public interface VolleyCallback {
        void onSuccess(List<Student> students);

        void onError(String errorMessage);
    }
}

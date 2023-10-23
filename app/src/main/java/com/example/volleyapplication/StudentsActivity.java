package com.example.volleyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.volleyapplication.student.Student;
import com.example.volleyapplication.student.StudentAdapter;
import com.example.volleyapplication.student.StudentApi;
import com.example.volleyapplication.student.StudentService;
import com.example.volleyapplication.utility.Utility;

import java.util.ArrayList;
import java.util.List;

public class StudentsActivity extends AppCompatActivity {

    List<Student> students;

    StudentAdapter adapter;

    StudentService studentService;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        students = new ArrayList<>();
        studentService = new StudentService(students);

        StudentApi.getAllStudents(this, studentService, new StudentApi.VolleyCallback() {
            @Override
            public void onSuccess(List<Student> students) {
                StudentsActivity.this.setListViewAdapter();
            }

            @Override
            public void onError(String errorMessage) {
                Log.d("Error ", "This was Not a Success");
            }
        });


    }

    public void setListViewAdapter() {
        this.listView = findViewById(R.id.list);
        this.adapter = new StudentAdapter(this, R.layout.student, studentService.findAll());
        this.listView.setAdapter(adapter);
        this.listView.setOnItemClickListener((parent, view, position, id) -> {
            Student student = studentService.findAll().get(position);
            getAlertBox(this, student).show();
        });
    }

    public AlertDialog getAlertBox(Context context, Student student) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.action_popup, null);
        dialogBuilder.setView(dialogView);
        Button update = dialogView.findViewById(R.id.update);
        Button delete = dialogView.findViewById(R.id.delete);

        AlertDialog alertDialog = dialogBuilder.create();
        delete.setOnClickListener(v -> {

            Utility.showSimpleDialog(StudentsActivity.this, "Etes vous sure de vouloir supprimer",
                    (arg1, arg2) -> {
                        StudentApi.deleteStudent(student.getId(), context, new StudentApi.VolleyCallback() {
                            @Override
                            public void onSuccess(List<Student> students) {
                                studentService.delete(student);
                                adapter.notifyDataSetChanged();
                                alertDialog.dismiss();
                            }

                            @Override
                            public void onError(String errorMessage) {

                            }
                        });

                    }, (arg1, arg2) -> {
                    });

        });


        update.setOnClickListener(v -> {
        });

        return alertDialog;
    }

}

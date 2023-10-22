package com.example.volleyapplication.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.volleyapplication.R;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private List<Student> students;
    private int resource;
    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> students) {
        super(context, resource, students);
        this.students=students;
        this.resource=resource;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item=convertView;
        if( convertView==null){
            item= LayoutInflater.from(getContext()).inflate(this.resource,parent,false);
        }
        TextView student_id=item.findViewById(R.id.student_id);
        TextView student_nom=item.findViewById(R.id.student_nom);
        TextView student_prenom=item.findViewById(R.id.student_prenom);
        TextView student_ville=item.findViewById(R.id.student_ville);
        TextView student_sexe=item.findViewById(R.id.student_sex);

        Student currentStudent=students.get(position);

        String nom=currentStudent.getNom();
        String id=String.valueOf(currentStudent.getId());
        String prenom=currentStudent.getPrenom();
        String ville=currentStudent.getVille();
        String sex=currentStudent.getSexe();

        student_id.setText(id);
        student_nom.setText(nom);
        student_prenom.setText(prenom);
        student_ville.setText(ville);
        student_sexe.setText(sex);
        return item;
    }
}

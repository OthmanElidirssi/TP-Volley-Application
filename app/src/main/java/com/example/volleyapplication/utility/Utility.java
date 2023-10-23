package com.example.volleyapplication.utility;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Utility {

    public static void showSimpleDialog(Context context, String message, DialogInterface.OnClickListener positive, DialogInterface.OnClickListener negative) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(message);
        builder.setPositiveButton("OK", positive);
        builder.setNegativeButton("Annulez", negative);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


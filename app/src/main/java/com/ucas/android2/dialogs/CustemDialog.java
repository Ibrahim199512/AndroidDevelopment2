package com.ucas.android2.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ucas.android2.R;

public class CustemDialog extends Dialog {
    Context context;

    public CustemDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        Button yse = findViewById(R.id.yes);
        Button no = findViewById(R.id.no);
        yse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Yes", Toast.LENGTH_SHORT).show();
                CustemDialog.this.dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "No", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }
}
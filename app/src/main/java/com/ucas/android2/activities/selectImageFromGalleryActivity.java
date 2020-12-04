package com.ucas.android2.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ucas.android2.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class selectImageFromGalleryActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 100;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image_from_gallery);


        image = findViewById(R.id.image);

        findViewById(R.id.select_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(PICK_IMAGE);

            }
        });
    }

    public void selectImage(int requestCode) {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");
        Intent pickIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
        startActivityForResult(chooserIntent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == PICK_IMAGE) {
            final Uri imageUri = data.getData();
            if (imageUri == null) {
                return;
            }
            final InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
                Bitmap postImageBbitmap = BitmapFactory.decodeStream(imageStream);
//                Log.e("postImageBbitmap", postImageBbitmap.toString());
                image.setImageBitmap(postImageBbitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
package com.example.tiara.TiaraSabrina_1202150259_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class PencariGambar extends AppCompatActivity {
    //membuat variable
    Button btFindImage; //variable dari button
    EditText inputUrl; //variable dari EditText
    ImageView imageResult; //variable dari imageview
    ProgressDialog progressDialog; //variable dari progress dialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari_gambar);

        //menemukan id dan menyimpan nilai ke variable
        btFindImage=(Button)findViewById(R.id.btFindImage);
        inputUrl=(EditText)findViewById(R.id.inputUrl);
        imageResult=(ImageView)findViewById(R.id.imageResult);

        //membuat aksi pd button
        btFindImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });
    }

    //mengeksecute class simpleasynctask
    private void loadImage(){
        String imgUrl = inputUrl.getText().toString();
        //AsyncTask mencari gambar di internet
        new simpleasynctask().execute(imgUrl);
    }

    //class untuk menload image
    class simpleasynctask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Membuat Progress Dialog
            progressDialog = new ProgressDialog(PencariGambar.this);

//            // Judul Progress Dialog
//            mProgressDialog.setTitle("Downloading image");

            // Seting message Progress Dialog
            progressDialog.setMessage("Loading...");

            // menampilkan Progress Dialog
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                // mendownload gambar dari url
                URL url = new URL(params[0]);
                // mengkonversikan gambar ke bitmat (decode to bitmap)
                bitmap = BitmapFactory.decodeStream((InputStream)url.getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            // menampung gambar ke imageView dan menampilkannya
            imageResult.setImageBitmap(result);

            // menghilangkan Progress Dialog
            progressDialog.dismiss();
        }


    }
}

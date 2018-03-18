package com.example.tiara.TiaraSabrina_1202150259_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuUtama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

    }
    //membuat intent pada button cariGambar
    public void cariGambar (View view){
        Intent intent = new Intent(MenuUtama.this,PencariGambar.class);
        startActivity(intent);
    }

    //membuat intent pada button listMahasiswa
    public void listMahasiswa (View view){
        Intent intent = new Intent(MenuUtama.this,ListNamaMahasiswa.class);
        startActivity(intent);
    }

}

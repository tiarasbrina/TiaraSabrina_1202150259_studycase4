package com.example.tiara.TiaraSabrina_1202150259_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class ListNamaMahasiswa extends AppCompatActivity {
    ListView listMahasiswa; //membuat variable dari ListView
    String[] arrayNamaMahasiswa = {"TIARA", "AZIZ", "TASYA",
            "TANIA", "PIPIT", "DINA", "PIUL",
            "DETIR", "INDAH", "AFRA"}; //membuat array
    private ProgressBar progressBar; //membuat variable PrograssBar
    private simpleasynctask addItem; //membuat addItem
    private Button startTask; //membuat variable dari Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listnamamahasiswa);
        //menemukan id dan menyimpan nilai di variable yang telah dibuat sebelumnya
        listMahasiswa = (ListView) findViewById(R.id.namaMahasiswa);
        progressBar = findViewById(R.id.progressBar);
        startTask = findViewById(R.id.btStart);
        //membuat adapter
        listMahasiswa.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        //menaruh aksi ke button
        startTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //process adapter with asyncTask
                addItem = new simpleasynctask();
                addItem.execute();
            }
        });
    }

    class simpleasynctask extends AsyncTask<Void, String, String> {

        ArrayAdapter<String> adapterDaftarNama; //membuat variable adapter
        private int count = 1; //menginisasi count
        ProgressDialog progressDialog = new ProgressDialog(ListNamaMahasiswa.this); //membuat progress doalog

        @Override
        protected void onPreExecute() {
            adapterDaftarNama = (ArrayAdapter<String>)listMahasiswa.getAdapter(); //mengambil array dari adapter
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); //mengatur style progress dialog
            progressDialog.setTitle("Loading Data"); //set title progress dialog
            progressDialog.setCancelable(false); // set cancleable progress dialog
            progressDialog.setMessage("Wait a second...."); //set message progress dialog
            progressDialog.setProgress(0); //set progress progress dialog
            //set button cancel progress dialog saat diklik
            progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    addItem.cancel(true);
                    progressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            progressDialog.show(); //menampilkan progress dialog
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapterDaftarNama.add(values[0]);

            Integer currentStatus = (int) ((count / (float) arrayNamaMahasiswa.length) * 100);
            progressBar.setProgress(currentStatus);

            //set progress only working for horizontal loading
            progressDialog.setProgress(currentStatus);

            //set message will not working when using horizontal loading
            progressDialog.setMessage(String.valueOf(currentStatus + "%"));
            count++;
        }

        @Override
        protected String doInBackground(Void... voids) {
            //publish progress dari array
            for (String daftarnama : arrayNamaMahasiswa) {
                publishProgress(daftarnama);
                try {
                    Thread.sleep(800); //mengatur waktu tunda
                }catch (InterruptedException e){
                        e.printStackTrace();
                }
                if (isCancelled()){
                    addItem.cancel(true); //mengatur cancel
                }
            }
            return "Seluruh nama mahasiswa telah terlampir"; //

        }

        @Override
        protected void onPostExecute (String result){
            //membuat toast
            Toast toast = Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG);
            toast.show();
            progressBar.setVisibility(View.GONE);     //hide progreebar
            //remove progress dialog
            progressDialog.dismiss();
            listMahasiswa.setVisibility(View.VISIBLE);


        }

    }
}
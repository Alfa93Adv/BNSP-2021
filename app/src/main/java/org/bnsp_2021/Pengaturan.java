package org.bnsp_2021;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Pengaturan extends AppCompatActivity {

    private EditText editPasswordLama, editPasswordBaru;
    private Button saveBtn, backBtn;
    private  SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);

        saveBtn = findViewById(R.id.button_simpan);
        backBtn = findViewById(R.id.button_kembali);
        editPasswordLama = findViewById(R.id.editText_passwordlama);
        editPasswordBaru = findViewById(R.id.editText_passwordbaru);
        sqLiteHelper = new SQLiteHelper(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpassword = editPasswordLama.getText().toString();
                String newpassword = editPasswordBaru.getText().toString();

                if(TextUtils.isEmpty(oldpassword) || TextUtils.isEmpty(newpassword)){
                    Toast.makeText(Pengaturan.this,"Silakan Masukkan Password Lama dan Password Baru.", Toast.LENGTH_LONG).show();
                }else{
                    boolean update = sqLiteHelper.updatePassword(1, oldpassword, newpassword);
                    if (!update){
                        Toast.makeText(Pengaturan.this,"Password Salah, Silakan Coba Lagi.",Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(Pengaturan.this,"Berhasil Update Password.",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Pengaturan.this, Beranda.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pengaturan.this, Beranda.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}

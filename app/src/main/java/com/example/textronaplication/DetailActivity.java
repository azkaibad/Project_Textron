package com.example.textronaplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.textronaplication.db.DBManager;
import com.example.textronaplication.helper.Rupiah;

public class DetailActivity extends AppCompatActivity {
    TextView tvNama, tvHarga, tvDes;
    EditText etQt;
    ImageView img;
    Button btnCart;
    String stNama, stHarga, stDes, stImg, stQt;
    DBManager dbManager;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        klik();
    }
    void init(){
        tvNama = findViewById(R.id.tv_nama);
        tvHarga = findViewById(R.id.tv_harga);
        tvDes = findViewById(R.id.tv_des);
        img = findViewById(R.id.img_produk);
        btnCart = findViewById(R.id.btn_cart);
        etQt = findViewById(R.id.et_qt);
        dbManager = new DBManager(this);
        dbManager.open();
        bundle = getIntent().getExtras();
        stNama = bundle.getString("nama");
        stHarga = bundle.getString("harga");
        stDes = bundle.getString("des");
        stImg = bundle.getString("img");
        String rupiah = new Rupiah().getRupiah(Double.parseDouble(stHarga));
        tvNama.setText(stNama);
        tvHarga.setText(rupiah);
        tvDes.setText(stDes);
        Glide.with(DetailActivity.this).load(stImg).centerCrop().into(img);
    }
    void klik(){
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stQt = etQt.getText().toString();
                dbManager.insert(stNama,stHarga,stImg,stQt, stDes);
                Toast.makeText(DetailActivity.this, "Berhasil menambahkan keranjang",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
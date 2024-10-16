package com.example.ishoppinglist.Activity;

import static com.example.ishoppinglist.database.Database.productoList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.adapters.ProductAdapter;
import com.example.ishoppinglist.models.Producto;

public class DetailActivity extends AppCompatActivity {
    private TextView tvName, tvNote, tvStatus, tvID, tvGluten, tvLactosa;
    Button btnBack, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        // Inicializar elementos de la interfaz de usuario
        tvName = findViewById(R.id.tvName);
        tvNote = findViewById(R.id.tvNote);
        tvID = findViewById(R.id.tvID);
        tvStatus = findViewById(R.id.tvStatus);
        tvGluten = findViewById(R.id.tvGluten);
        tvLactosa = findViewById(R.id.tvLactosa);
        btnBack = findViewById(R.id.btnBack);
        btnEdit = findViewById(R.id.btnEdit);


        // Obtengo el intent que ha iniciado la activity
        Intent intent = getIntent();

        // Recupero el valor de la clave `product`
        Producto p = (Producto) intent.getSerializableExtra("product");

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent editDetails = new Intent(DetailActivity.this, EditDetailsActivity.class);

                // Pasar el producto completo

                editDetails.putExtra("productid", p);

                startActivity(editDetails);
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelDetails = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(cancelDetails);
            }
        });




        Log.i("product", p.toString());
        tvName.setText(p.getNombre());
        tvNote.setText(p.getNota());
        tvID.setText("Id: "+String.valueOf(p.getId()));
        if (p.getEstadoCompra() == true) {
            tvStatus.setText("Pendiente de compra");
        }

        if (p.getEstadoCompra() == false) {
            tvStatus.setText("No Pendiente");
        }

        if(p.isGluten()){
            tvGluten.setText("Con Gluten");
        }
        else {
            tvGluten.setText("Sin Gluten");
        }
        if(p.isLactosa()){
            tvLactosa.setText("Con Lactosa");
        }
        else {
            tvLactosa.setText("Sin Lactosa");
        }


    }
}
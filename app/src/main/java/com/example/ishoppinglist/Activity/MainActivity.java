package com.example.ishoppinglist.Activity;

import static com.example.ishoppinglist.database.Database.inicializeList;
import static com.example.ishoppinglist.database.Database.productoList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
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

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyPermission;

public class MainActivity extends AppCompatActivity {
    private Button btnAddSystem, btnAddPending;
    private TextView textView;
    private ListView lvProduct;
    private Spinner spinner2;
    private  ArrayList<Producto> filteredList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.textView);
        lvProduct = findViewById(R.id.lvProduct);
        spinner2 = findViewById(R.id.spinner2);


        String[] datos = new String[]{"Todos", "Lactosa", "Gluten"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);

        spinner2.setAdapter(arrayAdapter);


        inicializeList();


        lvProduct.setOnItemClickListener((parent, view, position, id) -> {
            Producto p = filteredList.get(position);
            Log.i("product click", p.toString());

            Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
            detailIntent.putExtra("product", p);  // Pasando el producto como Serializable
            startActivity(detailIntent);
        });


        Intent detailIntent = getIntent();
        if (detailIntent.getSerializableExtra("product") != null) {
            textView.setText(detailIntent.getSerializableExtra("product").toString());
        }
        btnAddSystem = findViewById(R.id.btnAddSystem);
        btnAddSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addsystem = new Intent(MainActivity.this, AddSystemActivity.class);
                startActivity(addsystem);
            }
        });
        btnAddPending = findViewById(R.id.btnAddPending);
        btnAddPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPending = new Intent(MainActivity.this, AddPendingActivity.class);
                startActivity(addPending);
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
// Filtrar la lista dependiendo de la selección del spinner
                switch (position) {
                    case 1: // Lactosa
                        filteredList = new ArrayList<>();
                        for (Producto producto : productoList) {
                            if (producto.isLactosa()) {
                                filteredList.add(producto);
                            }
                        }
                        break;
                    case 2: // Gluten
                        filteredList = new ArrayList<>();
                        for (Producto producto : productoList) {
                            if (producto.isGluten()) {
                                filteredList.add(producto);
                            }
                        }
                        break;
                    default: // Todos
                        filteredList = new ArrayList<>();
                        for (Producto producto : productoList) {
                            if (producto.getEstadoCompra()) {
                                filteredList.add(producto);
                            }
                        }
                        break;

                }
                ProductAdapter adapter = new ProductAdapter(MainActivity.this, 0, filteredList);
                lvProduct.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada si no hay selección
            }
        });


    }
}

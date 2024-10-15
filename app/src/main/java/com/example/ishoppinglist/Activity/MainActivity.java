package com.example.ishoppinglist.Activity;

import static com.example.ishoppinglist.database.Database.inicializeList;
import static com.example.ishoppinglist.database.Database.productoList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
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
        inicializeList();
//Lista que filtre solo aquellos productos que se tengan que comprar
        List<Producto> filteredList = new ArrayList<Producto>();
        for (Producto producto : productoList) {
            if (producto.getEstadoCompra() == true) {
                filteredList.add(producto);
            }
        }

        //Creo el adaptador con la lista filtrada como parametro para que se muestren solo aquellos productos que esten en la lista
        ProductAdapter adapter = new ProductAdapter(MainActivity.this, 0, filteredList);
        //Asignamos el adaptador


        lvProduct.setAdapter(adapter);

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

    }
}

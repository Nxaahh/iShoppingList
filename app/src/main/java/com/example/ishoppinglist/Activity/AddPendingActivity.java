package com.example.ishoppinglist.Activity;

import static com.example.ishoppinglist.database.Database.productoList;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class AddPendingActivity extends AppCompatActivity {
    TextView textView2;
    Spinner spProducts;
    Button btnSave, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_pending);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView2 = findViewById(R.id.textView2);
        spProducts = findViewById(R.id.spProducts);
        //Lista que filtre solo aquellos productos que se tengan que comprar
        List<Producto> notPending = new ArrayList<Producto>();
        for (Producto producto : productoList) {
            if (producto.getEstadoCompra()== false) {
                notPending.add(producto);
            }
        }
        ProductAdapter adapter = new ProductAdapter(AddPendingActivity.this, 0, notPending);//Podemos cambiar el arraylist dependiendo de si queremos que se muestren los productos pendientes o todos
        //Obtengo el intnetn que ha iniciado la Activity
        Intent intent = getIntent();
        //Recupero el valor de la clave `Person`
        Producto p = (Producto) intent.getSerializableExtra("Product");

        spProducts.setAdapter(adapter);
        btnBack = findViewById(R.id.btnBack);
        //Click del boton
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backMain = new Intent(AddPendingActivity.this,MainActivity.class);
                startActivity(backMain);
            }
        });
        btnSave = findViewById(R.id.btnSave);
        //Click del boton
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hace que se guarden los atributos del producto guardado
                Producto selectedProduct = (Producto) spProducts.getSelectedItem();
                if (selectedProduct != null) {
                    boolean currentEstadoCompra = selectedProduct.getEstadoCompra();
                    selectedProduct.setEstadoCompra(!currentEstadoCompra);
                }
                 Intent addPending = new Intent(AddPendingActivity.this,MainActivity.class);
                startActivity(addPending);
            }
        });

    }
}
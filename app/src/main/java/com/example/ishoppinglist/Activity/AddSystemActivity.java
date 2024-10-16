package com.example.ishoppinglist.Activity;

import static com.example.ishoppinglist.database.Database.productoList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.models.Producto;

public class AddSystemActivity extends AppCompatActivity {
    EditText etName,etNote;
    Switch swPending, swLactosa, swGluten;
    Button btnCancel,btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_system);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etName=findViewById(R.id.etName);
        etNote=findViewById(R.id.etNote);
        swPending=findViewById(R.id.swPending);
        swLactosa=findViewById(R.id.swLactosa);
        swGluten=findViewById(R.id.swGluten);



        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validación de los campos de texto, mostrando un mensaje Toast si algún campo está vacío
                if (etName.getText().toString().equals("")) {
                    Toast.makeText(AddSystemActivity.this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                } else if (etNote.getText().toString().equals("")) {
                    Toast.makeText(AddSystemActivity.this, "La nota no puede estar vacía", Toast.LENGTH_SHORT).show();
                } else {
                    // Si todos los campos están completos, se crea un objeto Producto
                    String nombre = etName.getText().toString();
                    String nota = etNote.getText().toString();
                    boolean estadoCompra = swPending.isChecked();  // Estado del Switch (comprado o pendiente)
                    boolean contieneLactosa = swLactosa.isChecked();
                    boolean contieneGluten = swGluten.isChecked();

                    // Crear el objeto Producto
                    Producto newProduct = new Producto();
                    int id=productoList.size()+1;
                    newProduct.setId(id);
                    newProduct.setNombre(nombre);
                    newProduct.setNota(nota);
                    newProduct.setEstadoCompra(estadoCompra);
                    newProduct.setLactosa(contieneLactosa);
                    newProduct.setGluten(contieneGluten);
                    productoList.add(newProduct);

                    // Enviar el producto a la MainActivity
                    Intent addProducts = new Intent(AddSystemActivity.this, MainActivity.class);
                    addProducts.putExtra("product", newProduct);  // Pasar el producto como un objeto serializable
                    startActivity(addProducts);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelAdd = new Intent(AddSystemActivity.this, MainActivity.class);
                startActivity(cancelAdd);
            }
        });







    }
}
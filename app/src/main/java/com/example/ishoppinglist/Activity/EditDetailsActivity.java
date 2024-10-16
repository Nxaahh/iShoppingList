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

public class EditDetailsActivity extends AppCompatActivity {
    EditText etName, etNote;
    Switch swPending, swLactosa, swGluten;
    Button btnCancel, btnSave;
    Producto editingProduct;  // Producto que estamos editando, si existe
    int productIndex = -1;    // Índice del producto que estamos editando en la lista

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

        etName = findViewById(R.id.etName);
        etNote = findViewById(R.id.etNote);
        swPending = findViewById(R.id.swPending);
        swLactosa = findViewById(R.id.swLactosa);
        swGluten = findViewById(R.id.swGluten);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        // Verificar si el intent contiene un producto para editar
        Intent intent = getIntent();
        editingProduct = (Producto) intent.getSerializableExtra("productid");

        // Si estamos editando un producto, llenamos los campos y obtenemos su índice
        if (editingProduct != null) {
            etName.setText(editingProduct.getNombre());
            etNote.setText(editingProduct.getNota());
            swPending.setChecked(editingProduct.getEstadoCompra());
            swGluten.setChecked(editingProduct.isGluten());
            swLactosa.setChecked(editingProduct.isLactosa());

            // Buscar el índice del producto en la lista
            for (int i = 0; i < productoList.size(); i++) {
                if (productoList.get(i).getId() == editingProduct.getId()) {
                    productIndex = i;
                    break;
                }
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validación de los campos de texto
                if (etName.getText().toString().equals("")) {
                    Toast.makeText(EditDetailsActivity.this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                } else if (etNote.getText().toString().equals("")) {
                    Toast.makeText(EditDetailsActivity.this, "La nota no puede estar vacía", Toast.LENGTH_SHORT).show();
                } else {
                    String nombre = etName.getText().toString();
                    String nota = etNote.getText().toString();
                    boolean estadoCompra = swPending.isChecked();// Estado del Switch
                    boolean contieneLactosa = swLactosa.isChecked();
                    boolean contieneGluten = swGluten.isChecked();

                    if (productIndex != -1) {
                        // Si estamos editando un producto existente, lo actualizamos en la lista
                        productoList.get(productIndex).setNombre(nombre);
                        productoList.get(productIndex).setNota(nota);
                        productoList.get(productIndex).setEstadoCompra(estadoCompra);
                        productoList.get(productIndex).setLactosa(contieneLactosa);
                        productoList.get(productIndex).setGluten(contieneGluten);
                    } else {
                        // Si es un nuevo producto, lo añadimos a la lista
                        Producto newProduct = new Producto();
                        newProduct.setNombre(nombre);
                        newProduct.setNota(nota);
                        newProduct.setEstadoCompra(estadoCompra);
                        newProduct.setGluten(contieneGluten);
                        newProduct.setLactosa(contieneLactosa);
                        productoList.add(newProduct);
                    }
                    Toast.makeText(EditDetailsActivity.this, "Producto Editado", Toast.LENGTH_SHORT).show();
                    // Volver a MainActivity
                    Intent returnIntent = new Intent(EditDetailsActivity.this, MainActivity.class);
                    startActivity(returnIntent);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent(EditDetailsActivity.this, MainActivity.class);
                startActivity(cancelIntent);
            }
        });
    }
}

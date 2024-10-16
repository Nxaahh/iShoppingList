package com.example.ishoppinglist.adapters;

import static com.example.ishoppinglist.database.Database.productoList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.models.Producto;

import org.w3c.dom.Text;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Producto> {
    private List<Producto> product;


    //Llamada alconstructor del padre

    public ProductAdapter(Context context, int resource, List<Producto> product) {
        super(context, resource, product);
        this.product = product;

    }


    @NonNull
    @Override
    public android.view.View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Producto p = this.product.get(position);

        // Si todavía no se ha creado la vista
        if (convertView == null) {
            // Método inflate
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        // Obtener referencias a los TextView o demás elementos de la vista
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvNote = convertView.findViewById(R.id.tvNote);

        // Establecer los valores del producto
        tvName.setText(p.getNombre());
        tvNote.setText(p.getNota());

        // Cambiar el color de fondo dependiendo del contenido
        if (p.isLactosa() && p.isGluten()) {
            // Contiene lactosa y gluten
            convertView.setBackgroundColor(0xFFB2EBF2); // Color #B2EBF2
        } else if (p.isLactosa()) {
            // Contiene solo lactosa
            convertView.setBackgroundColor(0xFFF8BBD0); // Color #F8BBD0
        } else if (p.isGluten()) {
            // Contiene solo gluten
            convertView.setBackgroundColor(0xFFD7CCC8); // Color #D7CCC8
        } else {
            // No contiene lactosa ni gluten, fondo por defecto
            convertView.setBackgroundColor(0xFFFFFFFF); // Fondo blanco
        }

        // Retornar la vista configurada
        return convertView;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Producto p = productoList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product,parent,false);
        }
    TextView tvName =convertView.findViewById(R.id.tvName);
    tvName.setText(p.getNombre());
    return convertView;



    }

}

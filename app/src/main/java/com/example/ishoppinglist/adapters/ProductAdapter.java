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

        //Si todavia no se ha creado la vista
        if (convertView == null) {
            //Metodo inflate
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);

        }

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvNote = convertView.findViewById(R.id.tvNote);
        TextView tvStatus = convertView.findViewById(R.id.tvStatus);


        tvName.setText(p.getNombre());
        tvNote.setText(p.getNota());
        //cambiar a spiner
        if (p.getEstadoCompra() == true) {
            tvStatus.setText("Pendiente de compra");
        }
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

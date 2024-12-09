package com.example.trabajofinal3;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorPlacas extends RecyclerView.Adapter<AdaptadorPlacas.PlacaViewHolder> {

    private List<String> placas;
    private int[] imagenesPlacas;
    private int placaSeleccionada = -1;  // controla la placa seleccionada
    private String[] consejos;

    // Constructor para recibir placas, imagenes y consejos (un item)
    public AdaptadorPlacas(List<String> placas, int[] imagenesPlacas, String[] consejos) {
        this.placas = placas;
        this.imagenesPlacas = imagenesPlacas;
        this.consejos = consejos;
    }

    @NonNull
    @Override
    public PlacaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_placa, parent, false);
            return new PlacaViewHolder(view);
        } catch (Exception e) {
            // Registrar error en caso de fallo al inflar la vista
            Log.e("AdaptadorPlacas", "Error al crear la vista para el item de placa: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PlacaViewHolder holder, int position) {
        try {
            if (position == placaSeleccionada) {
                holder.textPlaca.setText(consejos[position]);  // Mostrar consejo completo
            } else {
                holder.textPlaca.setText(placas.get(position));  // Mostrar solo el nombre de la placa
            }
            holder.imagenPlaca.setImageResource(imagenesPlacas[position]);

            // capturar el clic en el item
            holder.itemView.setOnClickListener(v -> {
                placaSeleccionada = position;
                notifyDataSetChanged();  // Notificar que los datos han cambiado y actualizar la vista
            });

        } catch (Exception e) {
            // Registrar cualquier otro tipo de error que pueda ocurrir
            Log.e("AdaptadorPlacas", "Error al enlazar los datos del item de placa: " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        try {
            return placas.size();
        } catch (Exception e) {
            Log.e("AdaptadorPlacas", "Error al obtener el numero de elementos en el RecyclerView: " + e.getMessage());
            return 0;
        }
    }

    // Metodo para deseleccionar una placa
    public void deseleccionarPlaca() {
        try {
            placaSeleccionada = -1;  // Deseleccionar cualquier placa y notificar al adaptador
            notifyDataSetChanged();
        } catch (Exception e) {
            Log.e("AdaptadorPlacas", "Error al deseleccionar la placa: " + e.getMessage());
        }
    }

    static class PlacaViewHolder extends RecyclerView.ViewHolder {
        TextView textPlaca;
        ImageView imagenPlaca;
        public PlacaViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                textPlaca = itemView.findViewById(R.id.textPlaca);  // Enlazar el TextView que muestra el nombre de la placa
                imagenPlaca = itemView.findViewById(R.id.imagenPlaca);  // Enlazar el ImageView que muestra la imagen de la placa
            } catch (Exception e) {
                Log.e("AdaptadorPlacas", "Error al enlazar los views en PlacaViewHolder: " + e.getMessage());
            }
        }
    }
}

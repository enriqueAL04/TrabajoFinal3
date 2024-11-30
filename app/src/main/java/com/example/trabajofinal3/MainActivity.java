package com.example.trabajofinal3;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private AdaptadorPlacas adaptador;
    private List<String> placas; // lista de placas que se mostrarán en el RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // intenta cargar el layout y captura de error en log
        try {
            setContentView(R.layout.activity_main);
        } catch (Exception e) {
            Log.e("AdaptadorPlacas", "Error al establecer el layout: " + e.getMessage());
            return;
        }

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // inicializa RecyclerView y establecer su LayoutManager
        RecyclerView recyclerView = findViewById(R.id.reciclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // obtener las placas, imágenes y consejos de la clase "DatosPlacas"
        placas = DatosPlacas.obtenerPlacas();
        int[] imagenesPlacas = DatosPlacas.obtenerImagenes();
        List<String> consejos = DatosPlacas.obtenerConsejos();

        // Intentar inicializar el adaptador y asociarlo al RecyclerView
        try {
            adaptador = new AdaptadorPlacas(placas, imagenesPlacas, consejos.toArray(new String[0]));
            recyclerView.setAdapter(adaptador);
        } catch (Exception e) {
            Log.e("AdaptadorPlacas", "Error al inicializar el adaptador: " + e.getMessage());
        }

        // Configuracion del botón de soporte para mostrar correo en un Toast
        ImageButton botonSoporte = findViewById(R.id.botonSoporte);
        botonSoporte.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Correo: CatPonentes@gmail.com", Toast.LENGTH_SHORT).show());

        // Configuracion del Switch para filtrar placas por más recientes o más antiguas
        Switch switchFiltro = findViewById(R.id.switchPrueba);
        switchFiltro.setOnCheckedChangeListener((buttonView, isChecked) -> {
            try {
                filtrarPlacas(isChecked);  // Filtrar placas al cambiar el estado del Switch
                String mensaje = isChecked ? "Filtrando por más recientes" : "Filtrando por más antiguos";
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("AdaptadorPlacas", "Error al aplicar el filtro con el Switch: " + e.getMessage());
            }
        });

        // Configuracion del boton de deseleccionar todas las placas
        findViewById(R.id.botonDeseleccionar).setOnClickListener(v -> {
            adaptador.deseleccionarPlaca();
            recyclerView.scrollToPosition(0);
        });
    }

    // Metodo para obtener el ID de una placa a partir de su nombre
    private int getPlacaId(String placa) {
        try {
            return Integer.parseInt(placa.split("\\.")[0]);  // Extraer el ID de la placa
        } catch (Exception e) {
            Log.e("AdaptadorPlacas", "Error al obtener el ID de la placa: " + placa + " " + e.getMessage());
            return 0;  // Retornar 0 si ocurre un error
        }
    }

    // Metodo para filtrar las placas según su fecha (más recientes o más antiguas)
    private void filtrarPlacas(boolean masRecientes) {
        try {
            if (masRecientes) {
                Collections.sort(placas, (p1, p2) -> Integer.compare(getPlacaId(p2), getPlacaId(p1)));
            } else {
                Collections.sort(placas, (p1, p2) -> Integer.compare(getPlacaId(p1), getPlacaId(p2)));
            }
            adaptador.notifyDataSetChanged();  // notifica al adaptador que los datos han cambiado
        } catch (Exception e) {
            Log.e("AdaptadorPlacas", "Error al filtrar las placas: " + e.getMessage());
        }
    }
}

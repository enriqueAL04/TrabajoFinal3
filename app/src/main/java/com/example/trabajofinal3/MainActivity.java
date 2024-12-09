package com.example.trabajofinal3;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AdaptadorPlacas adaptador;
    private List<String> placas; // lista de placas que se mostraran en el RecyclerView

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

        // obtener las placas, imagenes y consejos de la clase "DatosPlacas"
        placas = DatosPlacas.obtenerPlacas();
        int[] imagenesPlacas = DatosPlacas.obtenerImagenes();
        List<String> consejos = DatosPlacas.obtenerConsejos();

        // intentar inicializar el adaptador y asociarlo al RecyclerView
        try {
            adaptador = new AdaptadorPlacas(placas, imagenesPlacas, consejos.toArray(new String[0]));
            recyclerView.setAdapter(adaptador);
        } catch (Exception e) {
            Log.e("AdaptadorPlacas", "Error al inicializar el adaptador: " + e.getMessage());
        }

        // configuracion del boton de soporte para mostrar correo en un Snackbar
        ImageButton botonSoporte = findViewById(R.id.botonSoporte);
        botonSoporte.setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(v, "Correo: CatPonentes@gmail.com", Snackbar.LENGTH_SHORT);
            snackbar.setAction("Cerrar", v1 -> snackbar.dismiss());
            snackbar.show();
        });

        // configuracion del Switch para filtrar placas por mas recientes o mas antiguas
        Switch switchFiltro = findViewById(R.id.switchPrueba);
        switchFiltro.setOnCheckedChangeListener((buttonView, isChecked) -> {
            try {
                filtrarPlacas(isChecked);  // filtrar placas al cambiar el estado del Switch
                String mensaje = isChecked ? "Filtrando por mas recientes" : "Filtrando por mas antiguos";
                Snackbar.make(findViewById(android.R.id.content), mensaje, Snackbar.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("AdaptadorPlacas", "Error al aplicar el filtro con el Switch: " + e.getMessage());
            }
        });

        // configuracion del boton de deseleccionar todas las placas
        findViewById(R.id.botonDeseleccionar).setOnClickListener(v -> {
            adaptador.deseleccionarPlaca();
            recyclerView.scrollToPosition(0);
        });

        // configuracion del TabLayout
        TabLayout tabLayout = findViewById(R.id.tabLayoutMenu);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // verifica que tab fue seleccionado
                if (tab.getText().toString().equals("Procesadores")) {
                    // muestra un snackbar con el boton cerrar
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Proximamente", Snackbar.LENGTH_SHORT);
                    snackbar.setAction("Cerrar", v -> snackbar.dismiss());
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Estas en Placas Bases", Snackbar.LENGTH_SHORT);
                    snackbar.setAction("Cerrar", v -> snackbar.dismiss());
                    snackbar.show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        // configuracion del TabLayout secundario
        TabLayout tabLayoutMenu2 = findViewById(R.id.tabLayoutMenu2);
        tabLayoutMenu2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tabText = tab.getText().toString();
                if (tabText.equals("PC completos recomendados")) {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Proximamente", Snackbar.LENGTH_SHORT);
                    snackbar.setAction("Cerrar", v -> snackbar.dismiss());
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Proximamente", Snackbar.LENGTH_SHORT);
                    snackbar.setAction("Cerrar", v -> snackbar.dismiss());
                    snackbar.show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        // configuracion del Popup para el cambio de colores segun el daltonismo
        TextView textViewDaltonismo = findViewById(R.id.textViewDaltonismo);
        textViewDaltonismo.setOnLongClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
            popupMenu.getMenuInflater().inflate(R.menu.menu_daltonismo, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.itemDeuteranopia) {
                    textViewDaltonismo.setTextColor(Color.parseColor("#FFB400")); // deuteranopia
                } else if (itemId == R.id.itemProtanopia) {
                    textViewDaltonismo.setTextColor(Color.parseColor("#B45F06")); // protanopia
                } else if (itemId == R.id.itemTritanopia) {
                    textViewDaltonismo.setTextColor(Color.parseColor("#0066CC")); // tritanopia
                } else {
                    return false;
                }
                return true;
            });
            popupMenu.show();
            return true; // indica que salio como lo previsto
        });
    }

    // metodo para obtener el ID de una placa a partir de su nombre
    private int getPlacaId(String placa) {
        try {
            return Integer.parseInt(placa.split("\\.")[0]);  // extraer el ID de la placa
        } catch (Exception e) {
            Log.e("AdaptadorPlacas", "Error al obtener el ID de la placa: " + placa + " " + e.getMessage());
            return 0;  // retornar 0 si ocurre un error
        }
    }

    // metodo para filtrar las placas segun su fecha (mas recientes o mas antiguas)
    private void filtrarPlacas(boolean masRecientes) {
        try {
            if (masRecientes) {
                Collections.sort(placas, (p1, p2) -> Integer.compare(getPlacaId(p2), getPlacaId(p1)));
            } else {
                Collections.sort(placas, (p1, p2) -> Integer.compare(getPlacaId(p1), getPlacaId(p2)));
            }
            adaptador.notifyDataSetChanged();
        } catch (Exception e) {
            Log.e("AdaptadorPlacas", "Error al aplicar el filtro: " + e.getMessage());
        }
    }
}

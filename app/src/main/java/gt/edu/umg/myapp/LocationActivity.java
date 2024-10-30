package gt.edu.umg.myapp;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    EditText txtLatitud, txtLongitud;
    GoogleMap mMap; //representa el mapa

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
//campos de longitud y latitud
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);

        //obtiene el fragmento del mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        //se define para poder inicializar aqui el mapa y navegar
        LatLng jutiapa = new LatLng(14.288310,-89.893731);
        mMap.addMarker(new MarkerOptions().position(jutiapa).title("Jutiapa")); // Para que aparezca el marcador rojo
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jutiapa));
    }
// Metodo llamado porque permite explorar en el mapa
    @Override
    public void onMapClick(@NonNull LatLng latLng) {

        // Actualizar los campos de texto con las coordenadas del punto donde se hizo clic
        txtLatitud.setText(String.valueOf(latLng.latitude));
        txtLongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();
        LatLng jutiapa = new LatLng(latLng.latitude,latLng.longitude);// Crear un nuevo objeto LatLng con las coordenadas del clic
        mMap.addMarker(new MarkerOptions().position(jutiapa).title(""));// Añadir un nuevo marcador en la ubicación donde se hizo clic
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jutiapa)); // Mueve la camra a la posicon donde se hizo clic
    }


//este metodo basicamente es para cuando se mantiene presionado y se selecciona el lugar especifico
    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText(String.valueOf(latLng.latitude));
        txtLongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();//limpia los marcadores del mapa
        LatLng jutiapa = new LatLng(latLng.latitude,latLng.longitude); // Crear un nuevo objeto LatLng con las coordenadas del clic largo
        mMap.addMarker(new MarkerOptions().position(jutiapa).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jutiapa));  // Mover la cámara a la nueva posición
    }
    }






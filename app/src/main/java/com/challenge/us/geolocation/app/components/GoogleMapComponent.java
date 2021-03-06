package com.challenge.us.geolocation.app.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import com.challenge.us.geolocation.R;
import com.challenge.us.geolocation.data.model.MarkerData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * <ul>
 *     <li>Deve-se utilizar o {@link #setListener(MapListener)} para obter dados de interações
 *     com o {@link GoogleMap}</li>
 * </ul>
 **/
public class GoogleMapComponent extends ConstraintLayout implements OnMapReadyCallback {

    private final static String TAG = GoogleMapComponent.class.getSimpleName();

    private GoogleMap googleMap;
    private MapListener listener;

    public interface MapListener {

        /**
         * Chamado quando o {@link GoogleMap} estiver pronto para ser utilizado
         */
        void mapIsReady();

        void onMarkerClick(MarkerData markerData);
    }

    public GoogleMapComponent(@NonNull Context context) {
        super(context);
        onCreate(context);
    }

    public GoogleMapComponent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        onCreate(context);
    }

    public GoogleMapComponent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onCreate(context);
    }

    private void onCreate(Context context) {
        setDefaultMapListener();
        inflate(context, R.layout.component_google_map, this);
    }

    /**
     * Inicializar o {@link GoogleMap}
     */
    public void init(@NonNull FragmentManager fragmentManager) {
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager
                .findFragmentById(R.id.component_google_map_fragment_container);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Log.e(TAG, "Failed to initialize the map!");
        }
    }

    /**
     * Inicializa com o {@link #listener} padrão
     */
    private void setDefaultMapListener() {
        this.listener = new MapListener() {

            @Override
            public void mapIsReady() {
                Log.i(TAG, "GoogleMapsComponent is ready.");
            }

            @Override
            public void onMarkerClick(MarkerData markerData) {
                Log.i(TAG, markerData.toString());
            }
        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        listener.mapIsReady();
    }

    @SuppressLint("MissingPermission")
    public void setMyLocationEnabled(boolean enable) {
        googleMap.setMyLocationEnabled(enable);
        View locationButton = ((View) findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
        ((View) findViewById(Integer.parseInt("3")).getParent()).findViewById(Integer.parseInt("4")).setVisibility(GONE);
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        rlp.setMargins(0, 80, 180, 0);
    }

    /**
     * @param listener <ul>
     *                 <li>Responsável por obter dados de interações com o {@link GoogleMap}</li>
     *                 <li>Exemplo do {@link #getCurrentLatLan()}</li>
     *                 </ul>
     */
    public void setListener(MapListener listener) {
        this.listener = listener;
    }

    /**
     * <ul>
     *     <li>Remover todos os {@link MarkerOptions} ativos no {@link GoogleMap}</li>
     * </ul>
     */
    public void cleaMarkers() {
        googleMap.clear();
    }


    public void addMarker(MarkerData markerData) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(markerData.getLat(), markerData.getLng()))
                .title(markerData.getLocationName());

        Marker marker = googleMap.addMarker(markerOptions);
        marker.setTag(markerData);
        googleMap.setOnMarkerClickListener(mrk -> {
            listener.onMarkerClick((MarkerData) mrk.getTag());
            return true;
        });
    }

    /**
     * <ul>
     *      <li>Limpa toodos o {@link MarkerOptions} atuais do {@link GoogleMap}</li>
     *      <li>Adicionar o e move o {@link GoogleMap} para a posição do novo {@link MarkerOptions}</li>
     * </ul>
     *
     * @param markerData {@link MarkerData}
     */
    public void setMarkerOption(MarkerData markerData) {
        cleaMarkers();
        addMarker(markerData);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(markerData.getLat(), markerData.getLng()), 16.0F));
    }

    /**
     * <ul>
     *      <li>Remove todos os {@link MarkerOptions} atuais do {@link GoogleMap}</li>
     *      <li>Adicionar o e move o {@link GoogleMap} para a posição do novo {@link MarkerOptions}</li>
     * </ul>
     *
     * @param lat   Latitude
     * @param lng   Longitude
     * @param label Label a ser apresentada no {@link MarkerOptions} <br>
     */
    public void setMarkerOption(double lat, double lng, String label) {
        cleaMarkers();
        LatLng latLng = new LatLng(lat, lng);
        addMarker(new MarkerData(label, lat, lng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
    }

    /**
     * <li>Retorna a localização em {@link LatLng}, atual do dispositivo.</li>
     */
    public LatLng getCurrentLatLan() {
        return googleMap.getCameraPosition().target;
    }
}

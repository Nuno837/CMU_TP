package pt.ipp.estg.cmu_tp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    private Marker marker;
    double lat = 0.0;
    double lng = 0.0;
    double latitude = 0.0;
    double longitude = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        myLocation();
    }

    private void addMarkerPOI(List<POI> poi){

        for(int i=0; i<poi.size();i++) {
            LatLng latLng = new LatLng(poi.get(i).adressInfo.getLatitude(), poi.get(i).adressInfo.getLongitude());



            marker = mMap.addMarker(new MarkerOptions()
                    .position(latLng));

            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.mini_logo));
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {

                return true;
            }
        });

    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate myLocation = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title("minha localização"));
        mMap.animateCamera(myLocation);
    }

    private void actualizarPosição(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }

    private void atualizarPOI(Location location){
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarPosição(location);
            atualizarPOI(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void myLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarPosição(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,locationListener);
        getPOI();
    }

    public void getPOI() {



        Map<String, String> data = new HashMap<>();
        data.put("output", "json");
        data.put("maxresults", "100");
        data.put("compact", "true");
        data.put("verbose", "false");
        data.put("latitude", Double.toString(lat));
        data.put("longitude", Double.toString(lng));


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.openchargemap.io/v3/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface openChargeMapAPI = retrofit.create(ApiInterface.class);
        Call<List<POI>> call = openChargeMapAPI.getPOI(data);

        call.enqueue(new Callback<List<POI>>() {
            @Override
            public void onResponse(Call<List<POI>> call, Response<List<POI>> response) {
                ArrayList<POI> poi = new ArrayList<>();
                poi = (ArrayList<POI>) response.body();
                Log.d("TAG", "onReponse: Received information" + response.body().toString());
                Toast.makeText(MapsActivity.this, "recebida", Toast.LENGTH_SHORT).show();
                addMarkerPOI(poi);

            }

            @Override
            public void onFailure(Call<List<POI>> call, Throwable t) {
                Log.e("TAG", "Something went wrong" + t.getMessage());
                Toast.makeText(MapsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
package pt.ipp.estg.cmu_tp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.ipp.estg.cmu_tp.Adapters.Adapter_Posto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class POI_info extends AppCompatActivity {

    RecyclerView recyclerView_a;
    Adapter_Posto adapter_posto;
    Double latitude = 0.0;
    Double longitude = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poi_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myLocation();

        Map<String, String> data = new HashMap<>();
        data.put("output", "json");
        data.put("maxresults", "100");
        data.put("compact", "true");
        data.put("verbose", "false");
        data.put("latitude", Double.toString(latitude));
        data.put("longitude", Double.toString(longitude));

        recyclerView_a = (RecyclerView) findViewById(R.id.POI_posto);
        recyclerView_a.setHasFixedSize(true);
        recyclerView_a.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView_a.addItemDecoration(itemDecoration);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.openchargemap.io/v3/").addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface openChargeMapAPI = retrofit.create(ApiInterface.class);
        Call<List<POI>> call = openChargeMapAPI.getPOI(data);

        call.enqueue(new Callback<List<POI>>() {
            @Override
            public void onResponse(Call<List<POI>> call, Response<List<POI>> response) {
                ArrayList<POI> poi = new ArrayList<>();
                poi = (ArrayList<POI>) response.body();
                Log.d("TAG", "onReponse: Received information" + response.body().toString());
                Toast.makeText(POI_info.this, "recebida", Toast.LENGTH_SHORT).show();
                adapter_posto = new Adapter_Posto(POI_info.this, poi);
                recyclerView_a.setAdapter(adapter_posto);

            }

            @Override
            public void onFailure(Call<List<POI>> call, Throwable t) {
                Log.e("TAG", "Something went wrong" + t.getMessage());
                Toast.makeText(POI_info.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
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
        atualizarPOI(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,locationListener);
    }
}

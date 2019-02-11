package pt.ipp.estg.cmu_tp;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @GET("poi/")
    Call<List<POI>> getPOI(@QueryMap Map<String, String> options);
}
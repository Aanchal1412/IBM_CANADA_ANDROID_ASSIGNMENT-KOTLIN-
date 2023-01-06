import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MyAPICall {

    @GET("vehicle/random_vehicle")
    fun responseModel(@Query("size") `val`: Int): Call<List<DataModel>>
}


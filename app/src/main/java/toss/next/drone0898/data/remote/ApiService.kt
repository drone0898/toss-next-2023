package toss.next.drone0898.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("")
    suspend fun getSomeApi(
        @Query("someQuery") query:String
    )
}
package inc.verdant.istore.data

import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("/api/products")
    suspend fun getProducts(): Response<List<Product>>

    /*@GET("api/pr")
    suspend fun getProductById() : Response<Product>*/
}
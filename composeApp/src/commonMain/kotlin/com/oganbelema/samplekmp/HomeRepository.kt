package com.oganbelema.samplekmp

import com.oganbelema.samplekmp.data.Product
import com.oganbelema.samplekmp.network.apiClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.flow

class HomeRepository {

    private suspend fun getProductsApi(): List<Product> {
        val response = apiClient.get("https://fakestoreapi.com/products")
        return response.body()
    }

    fun getProducts() = flow {
        emit(getProductsApi())
    }
}

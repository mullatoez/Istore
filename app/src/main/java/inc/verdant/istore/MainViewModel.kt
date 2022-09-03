package inc.verdant.istore

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import inc.verdant.istore.data.Product
import inc.verdant.istore.data.ProductRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _info = MutableLiveData<Int>()
    val info: LiveData<Int> = _info
    private val productRepository = ProductRepository()

    val selectedProduct = MutableLiveData<Product>()

    val products: LiveData<List<Product>> = liveData {
        val data = productRepository.getProducts()
        emit(data)
    }

    init {
        Log.i("MainViewModel", "created")
        _info.value = 0
    }

    fun loadData() {
        _info.value = _info.value!! + 10
        Log.i("MainViewModel", "loadData ${_info.value}")
    }
}
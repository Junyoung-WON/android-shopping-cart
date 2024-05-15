package woowacourse.shopping.productdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import woowacourse.shopping.ShoppingRepository
import woowacourse.shopping.domain.Product
import woowacourse.shopping.domain.ShoppingCartItem

class ProductDetailViewModel(
    private val repository: ShoppingRepository
): ViewModel() {
    private val _product: MutableLiveData<Product> = MutableLiveData()
    val product: LiveData<Product> get() = _product

    private val _isAddSuccess: MutableLiveData<Boolean> = MutableLiveData(false)
    val isAddSuccess: LiveData<Boolean> get() = _isAddSuccess

    fun loadProductDetail(productId: Long) {
        runCatching {
            repository.productById(productId)
        }.onSuccess {
            _product.value = it
        }.onFailure {
            Log.d(this::class.java.simpleName, "$it")
        }
    }

    fun addProductToCart(productId: Long) {
        runCatching {
            val cartItem = ShoppingCartItem(_product.value!!)
            val userId = repository.userId()
            val shoppingCart = repository.shoppingCart(userId)
            repository.updateShoppingCart(shoppingCart.addItem(cartItem))
        }.onSuccess {
            _isAddSuccess.value = true
        }.onFailure {
            _isAddSuccess.value = false
        }
    }
}
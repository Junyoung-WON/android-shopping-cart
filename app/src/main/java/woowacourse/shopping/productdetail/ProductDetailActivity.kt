package woowacourse.shopping.productdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import woowacourse.shopping.R
import woowacourse.shopping.databinding.ActivityProductDetailBinding
import woowacourse.shopping.productlist.ProductUiModel
import woowacourse.shopping.productlist.toProductUiModel
import woowacourse.shopping.util.ViewModelFactory

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding

    private val viewModel: ProductDetailViewModel by viewModels() { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        val productId = intent.getLongExtra(EXTRA_PRODUCT_ID, -1L)

        viewModel.loadProductDetail(productId)

        viewModel.product.observe(this) {
            showProductDetailView(it.toProductUiModel())
        }

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_product_detail_close -> finish()
            else -> {}
        }
        return true
    }

    private fun showProductDetailView(productUi: ProductUiModel) {
        with(binding) {
            Glide.with(this@ProductDetailActivity)
                .load(productUi.imageUrl)
                .into(ivProductDetailProduct)
            tvProductDetailName.text = productUi.name
            tvProductDetailPrice.text = getString(R.string.product_price_format, productUi.price)
        }
    }

    companion object {
        private const val EXTRA_PRODUCT_ID = "productId"

        fun newInstance(
            context: Context,
            productId: Long,
        ) = Intent(context, ProductDetailActivity::class.java).apply {
            this.putExtra(
                EXTRA_PRODUCT_ID,
                productId,
            )
        }
    }
}

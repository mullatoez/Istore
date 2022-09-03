package inc.verdant.istore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.snackbar.Snackbar
import inc.verdant.istore.data.Product
import inc.verdant.istore.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.info.observe(this, Observer {
            displaySnackBar(it)
        })

        binding.button.setOnClickListener {
            //displaySnackBar(it)
            //viewModel.loadData()
           viewModel.products.observe(this, Observer {
               Log.i("MyTag", "onCreate: $it")
           })
        }

    }

    private fun displaySnackBar(count: Int) {
        Snackbar.make(binding.root, "Current value $count", Snackbar.LENGTH_LONG).apply {
            show()
        }
    }

    private fun clickSnackBar() {
        return Toast.makeText(this, "Hey... you clicked me", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.shareIcon -> handleShare()
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun handleShare(): Boolean {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "I have this number ${viewModel.info.value}")
        }
        startActivity(intent)
        return true
    }
}
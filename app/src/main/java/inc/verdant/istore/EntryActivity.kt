package inc.verdant.istore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import inc.verdant.istore.databinding.ActivityEntryBinding

class EntryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) return

        /* val homeFragment = HomeFragment()
         val cartFragment = CartFragment()

         handleFragmentSwitching(homeFragment)

         binding.bottomNavMenu.setOnItemSelectedListener {
             when (it.itemId) {
                 R.id.homeIcon -> handleFragmentSwitching(homeFragment)

                 R.id.cartIcon -> handleFragmentSwitching(cartFragment)
             }
             true
         }
 */
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavMenu.setupWithNavController(navController)
    }

    private fun handleFragmentSwitching(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }
}

/* binding.buttonEntry.setOnClickListener {
           val intent = Intent(this,MainActivity::class.java)
           startActivity(intent)
       }*/

/**/
package inc.verdant.istore.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String,
    val imageFile: String,
    val description: String,
    val size: Int,
    val price: Double
) : Parcelable
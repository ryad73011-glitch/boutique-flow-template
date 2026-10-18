package com.example.data.model

import androidx.annotation.DrawableRes

enum class ProductCategory(val id: String) {
  ALL("all"),
  KARAKOU("karakou"),
  CAFTAN("caftan"),
  GANDOURA("gandoura"),
  ACCESSOIRES("accessoires");

  fun getTitle(lang: AppLanguage): String {
    return when (this) {
      ALL -> if (lang == AppLanguage.ARABIC) "الكل" else "Toutes les pièces"
      KARAKOU -> if (lang == AppLanguage.ARABIC) "كاراكو عاصمي" else "Karakou Algérois"
      CAFTAN -> if (lang == AppLanguage.ARABIC) "قفاطين ملكية" else "Caftans Royaux"
      GANDOURA -> if (lang == AppLanguage.ARABIC) "قندورة عصرية" else "Gandouras Modernes"
      ACCESSOIRES -> if (lang == AppLanguage.ARABIC) "إكسسوارات وحلي" else "Pochettes & Accessoires"
    }
  }
}

data class Product(
  val id: String,
  val reference: String,
  val category: ProductCategory,
  val nameFr: String,
  val nameAr: String,
  val subtitleFr: String,
  val subtitleAr: String,
  val priceDzd: Long,
  @DrawableRes val imageRes: Int,
  val descriptionFr: String,
  val descriptionAr: String,
  val craftsmanshipFr: String,
  val craftsmanshipAr: String,
  val fabricFr: String,
  val fabricAr: String,
  val availableSizes: List<String>,
  val isFeatured: Boolean = false,
  val inStock: Boolean = true
) {
  fun getName(lang: AppLanguage): String = if (lang == AppLanguage.ARABIC) nameAr else nameFr
  fun getSubtitle(lang: AppLanguage): String = if (lang == AppLanguage.ARABIC) subtitleAr else subtitleFr
  fun getDescription(lang: AppLanguage): String = if (lang == AppLanguage.ARABIC) descriptionAr else descriptionFr
  fun getCraftsmanship(lang: AppLanguage): String = if (lang == AppLanguage.ARABIC) craftsmanshipAr else craftsmanshipFr
  fun getFabric(lang: AppLanguage): String = if (lang == AppLanguage.ARABIC) fabricAr else fabricFr

  fun formattedPrice(lang: AppLanguage): String {
    val formattedNumber = String.format("%,d", priceDzd).replace(',', ' ')
    return if (lang == AppLanguage.ARABIC) "$formattedNumber د.ج" else "$formattedNumber DZD"
  }
}

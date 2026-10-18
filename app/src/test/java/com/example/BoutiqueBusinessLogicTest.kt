package com.example

import com.example.data.model.AppLanguage
import com.example.data.model.ProductCategory
import com.example.data.repository.ProductRepository
import com.example.ui.MainViewModel
import com.example.ui.Screen
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class BoutiqueBusinessLogicTest {

  @Test
  fun testProductCatalogIsPopulated() {
    val products = ProductRepository.products
    assertTrue("Products catalog should not be empty", products.isNotEmpty())
    products.forEach { product ->
      assertNotNull("Product id must not be null", product.id)
      assertTrue("Product price must be greater than zero", product.priceDzd > 0)
      assertTrue("Product must have available sizes", product.availableSizes.isNotEmpty())
      assertNotNull("Product nameFr must not be blank", product.nameFr.isNotBlank())
      assertNotNull("Product nameAr must not be blank", product.nameAr.isNotBlank())
    }
  }

  @Test
  fun testCategoryFiltering() {
    val karakous = ProductRepository.filterProducts(ProductCategory.KARAKOU, "")
    assertTrue("Should find Karakou products", karakous.isNotEmpty())
    karakous.forEach {
      assertEquals(ProductCategory.KARAKOU, it.category)
    }

    val caftans = ProductRepository.filterProducts(ProductCategory.CAFTAN, "")
    assertTrue("Should find Caftan products", caftans.isNotEmpty())
    caftans.forEach {
      assertEquals(ProductCategory.CAFTAN, it.category)
    }
  }

  @Test
  fun testSearchQuery() {
    val results = ProductRepository.filterProducts(ProductCategory.ALL, "Soltana")
    assertTrue("Search for 'Soltana' should return matching product", results.isNotEmpty())
    assertEquals("karakou-soltana", results.first().id)
  }

  @Test
  fun testWhatsAppMessageFormattingFrench() {
    val product = ProductRepository.products.first()
    val message = ProductRepository.buildWhatsAppMessage(product, "38", AppLanguage.FRENCH)
    assertTrue("Message must mention Maison El Djazair", message.contains("Maison El Djazair"))
    assertTrue("Message must mention product model", message.contains(product.nameFr))
    assertTrue("Message must mention reference", message.contains(product.reference))
    assertTrue("Message must mention selected size", message.contains("38"))
    assertTrue("Message must mention DZD", message.contains("DZD"))
  }

  @Test
  fun testWhatsAppMessageFormattingArabic() {
    val product = ProductRepository.products.first()
    val message = ProductRepository.buildWhatsAppMessage(product, "Sur-mesure", AppLanguage.ARABIC)
    assertTrue("Message must mention Dar El Djazair in Arabic", message.contains("دار الجزائر"))
    assertTrue("Message must mention product Arabic name", message.contains(product.nameAr))
    assertTrue("Message must mention selected size", message.contains("Sur-mesure"))
    assertTrue("Message must mention DZD in Arabic", message.contains("د.ج"))
  }

  @Test
  fun testViewModelNavigationAndFavorites() {
    val viewModel = MainViewModel()

    // Test initial state
    assertEquals(Screen.Home, viewModel.uiState.value.currentScreen)
    assertEquals(AppLanguage.FRENCH, viewModel.uiState.value.language)

    // Language toggle
    viewModel.toggleLanguage()
    assertEquals(AppLanguage.ARABIC, viewModel.uiState.value.language)

    // Navigation
    viewModel.navigateTo(Screen.Catalog)
    assertEquals(Screen.Catalog, viewModel.uiState.value.currentScreen)

    // Sub-navigation to detail
    viewModel.navigateTo(Screen.ProductDetail("karakou-soltana"))
    assertEquals(Screen.ProductDetail("karakou-soltana"), viewModel.uiState.value.currentScreen)

    // Back navigation pops stack
    val popped = viewModel.navigateBack()
    assertTrue(popped)
    assertEquals(Screen.Catalog, viewModel.uiState.value.currentScreen)

    // Favorites toggling
    assertFalse(viewModel.uiState.value.favoriteProductIds.contains("karakou-soltana"))
    viewModel.toggleFavorite("karakou-soltana")
    assertTrue(viewModel.uiState.value.favoriteProductIds.contains("karakou-soltana"))
    viewModel.toggleFavorite("karakou-soltana")
    assertFalse(viewModel.uiState.value.favoriteProductIds.contains("karakou-soltana"))
  }
}

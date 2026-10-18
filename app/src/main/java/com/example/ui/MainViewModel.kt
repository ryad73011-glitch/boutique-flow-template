package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.data.model.AppLanguage
import com.example.data.model.Product
import com.example.data.model.ProductCategory
import com.example.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface Screen {
  data object Home : Screen
  data object Catalog : Screen
  data class ProductDetail(val productId: String) : Screen
  data object About : Screen
  data object Contact : Screen
  data object Favorites : Screen
}

data class BoutiqueUiState(
  val language: AppLanguage = AppLanguage.FRENCH,
  val currentScreen: Screen = Screen.Home,
  val screenStack: List<Screen> = listOf(Screen.Home),
  val selectedCategory: ProductCategory = ProductCategory.ALL,
  val searchQuery: String = "",
  val favoriteProductIds: Set<String> = emptySet(),
  val contactFormSent: Boolean = false,
  val contactFormSenderName: String = "",
  val contactFormEmail: String = "",
  val contactFormPhone: String = "",
  val contactFormMessage: String = ""
) {
  val filteredProducts: List<Product>
    get() = ProductRepository.filterProducts(selectedCategory, searchQuery)

  val featuredProducts: List<Product>
    get() = ProductRepository.products.filter { it.isFeatured }

  val favoriteProducts: List<Product>
    get() = ProductRepository.products.filter { favoriteProductIds.contains(it.id) }
}

class MainViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(BoutiqueUiState())
  val uiState: StateFlow<BoutiqueUiState> = _uiState.asStateFlow()

  fun toggleLanguage() {
    _uiState.update { state ->
      val newLang = if (state.language == AppLanguage.FRENCH) AppLanguage.ARABIC else AppLanguage.FRENCH
      state.copy(language = newLang)
    }
  }

  fun setLanguage(lang: AppLanguage) {
    _uiState.update { it.copy(language = lang) }
  }

  fun navigateTo(screen: Screen) {
    _uiState.update { state ->
      if (state.currentScreen == screen) state
      else state.copy(
        currentScreen = screen,
        screenStack = state.screenStack + screen
      )
    }
  }

  fun navigateBack(): Boolean {
    val state = _uiState.value
    if (state.screenStack.size > 1) {
      val newStack = state.screenStack.dropLast(1)
      _uiState.update {
        it.copy(
          currentScreen = newStack.last(),
          screenStack = newStack
        )
      }
      return true
    }
    return false
  }

  fun selectCategory(category: ProductCategory) {
    _uiState.update { it.copy(selectedCategory = category) }
  }

  fun updateSearchQuery(query: String) {
    _uiState.update { it.copy(searchQuery = query) }
  }

  fun toggleFavorite(productId: String) {
    _uiState.update { state ->
      val updated = if (state.favoriteProductIds.contains(productId)) {
        state.favoriteProductIds - productId
      } else {
        state.favoriteProductIds + productId
      }
      state.copy(favoriteProductIds = updated)
    }
  }

  fun updateContactForm(name: String, email: String, phone: String, message: String) {
    _uiState.update {
      it.copy(
        contactFormSenderName = name,
        contactFormEmail = email,
        contactFormPhone = phone,
        contactFormMessage = message
      )
    }
  }

  fun submitContactForm() {
    _uiState.update { it.copy(contactFormSent = true) }
  }

  fun resetContactForm() {
    _uiState.update {
      it.copy(
        contactFormSent = false,
        contactFormSenderName = "",
        contactFormEmail = "",
        contactFormPhone = "",
        contactFormMessage = ""
      )
    }
  }
}

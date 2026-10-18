package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.model.AppLanguage
import com.example.data.repository.ProductRepository
import com.example.ui.MainViewModel
import com.example.ui.Screen
import com.example.ui.components.LuxuryBottomNavBar
import com.example.ui.components.LuxuryTopAppBar
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.CatalogScreen
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.FavoritesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ProductDetailScreen
import com.example.ui.theme.MaisonElDjazairTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MaisonElDjazairTheme {
        BoutiqueApp()
      }
    }
  }
}

@Composable
fun BoutiqueApp(
  viewModel: MainViewModel = viewModel()
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val isRtl = uiState.language == AppLanguage.ARABIC
  val layoutDirection = if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr

  BackHandler(enabled = uiState.screenStack.size > 1) {
    viewModel.navigateBack()
  }

  CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
    Scaffold(
      modifier = Modifier.fillMaxSize(),
      containerColor = MaterialTheme.colorScheme.background,
      topBar = {
        // Only show main top bar if not in fullscreen product detail
        if (uiState.currentScreen !is Screen.ProductDetail) {
          LuxuryTopAppBar(
            currentScreen = uiState.currentScreen,
            language = uiState.language,
            favoriteCount = uiState.favoriteProductIds.size,
            onNavigateBack = { viewModel.navigateBack() },
            onToggleLanguage = { viewModel.toggleLanguage() },
            onOpenFavorites = { viewModel.navigateTo(Screen.Favorites) }
          )
        }
      },
      bottomBar = {
        if (uiState.currentScreen !is Screen.ProductDetail) {
          LuxuryBottomNavBar(
            currentScreen = uiState.currentScreen,
            language = uiState.language,
            onNavigate = { screen -> viewModel.navigateTo(screen) }
          )
        }
      }
    ) { innerPadding ->
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding)
      ) {
        AnimatedContent(
          targetState = uiState.currentScreen,
          transitionSpec = { fadeIn() togetherWith fadeOut() },
          label = "ScreenTransition"
        ) { targetScreen ->
          when (targetScreen) {
            is Screen.Home -> {
              HomeScreen(
                language = uiState.language,
                featuredProducts = uiState.featuredProducts,
                favoriteIds = uiState.favoriteProductIds,
                onProductClick = { product ->
                  viewModel.navigateTo(Screen.ProductDetail(product.id))
                },
                onToggleFavorite = { productId ->
                  viewModel.toggleFavorite(productId)
                },
                onNavigate = { screen -> viewModel.navigateTo(screen) }
              )
            }

            is Screen.Catalog -> {
              CatalogScreen(
                language = uiState.language,
                products = uiState.filteredProducts,
                selectedCategory = uiState.selectedCategory,
                searchQuery = uiState.searchQuery,
                favoriteIds = uiState.favoriteProductIds,
                onSelectCategory = { category -> viewModel.selectCategory(category) },
                onSearchQueryChanged = { query -> viewModel.updateSearchQuery(query) },
                onProductClick = { product ->
                  viewModel.navigateTo(Screen.ProductDetail(product.id))
                },
                onToggleFavorite = { productId -> viewModel.toggleFavorite(productId) },
                onResetFilters = {
                  viewModel.selectCategory(com.example.data.model.ProductCategory.ALL)
                  viewModel.updateSearchQuery("")
                }
              )
            }

            is Screen.ProductDetail -> {
              val product = ProductRepository.getProductById(targetScreen.productId)
                ?: uiState.featuredProducts.first()

              ProductDetailScreen(
                product = product,
                language = uiState.language,
                isFavorite = uiState.favoriteProductIds.contains(product.id),
                onToggleFavorite = { productId -> viewModel.toggleFavorite(productId) },
                onNavigateBack = { viewModel.navigateBack() }
              )
            }

            is Screen.About -> {
              AboutScreen(
                language = uiState.language,
                onNavigate = { screen -> viewModel.navigateTo(screen) }
              )
            }

            is Screen.Contact -> {
              ContactScreen(
                language = uiState.language,
                uiState = uiState,
                onUpdateForm = { name, email, phone, msg ->
                  viewModel.updateContactForm(name, email, phone, msg)
                },
                onSubmitForm = { viewModel.submitContactForm() },
                onResetForm = { viewModel.resetContactForm() }
              )
            }

            is Screen.Favorites -> {
              FavoritesScreen(
                language = uiState.language,
                favoriteProducts = uiState.favoriteProducts,
                onProductClick = { product ->
                  viewModel.navigateTo(Screen.ProductDetail(product.id))
                },
                onToggleFavorite = { productId -> viewModel.toggleFavorite(productId) },
                onExploreCatalog = { viewModel.navigateTo(Screen.Catalog) }
              )
            }
          }
        }
      }
    }
  }
}

package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.data.model.AppLanguage
import com.example.data.model.Product
import com.example.ui.Screen
import com.example.ui.components.ProductCard
import com.example.ui.theme.Charcoal900
import com.example.ui.theme.GoldAccent

@Composable
fun FavoritesScreen(
  language: AppLanguage,
  favoriteProducts: List<Product>,
  onProductClick: (Product) -> Unit,
  onToggleFavorite: (String) -> Unit,
  onExploreCatalog: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("favorites_screen")
  ) {
    // Header
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 12.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = if (language == AppLanguage.ARABIC) "قطعكم المفضلة" else "Votre Sélection Privée",
        style = MaterialTheme.typography.headlineLarge,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
      )
      Text(
        text = "${favoriteProducts.size} " + (if (language == AppLanguage.ARABIC) "قطعة" else "pièces"),
        style = MaterialTheme.typography.bodyMedium,
        color = GoldAccent
      )
    }

    if (favoriteProducts.isEmpty()) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(32.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          Box(
            modifier = Modifier
              .size(64.dp)
              .clip(CircleShape)
              .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.FavoriteBorder,
              contentDescription = null,
              tint = GoldAccent,
              modifier = Modifier.size(32.dp)
            )
          }

          Text(
            text = if (language == AppLanguage.ARABIC) "قائمة المفضلة فارغة" else "Aucune pièce favorite",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
          )

          Text(
            text = if (language == AppLanguage.ARABIC)
              "اضغط على رمز القلب في أي قطعة لحفظها في مجموعتك المفضلة."
            else
              "Enregistrez vos créations favorites en cliquant sur l'icône cœur lors de votre exploration.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          Button(
            onClick = onExploreCatalog,
            colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.testTag("favorites_explore_button")
          ) {
            Text(
              text = if (language == AppLanguage.ARABIC) "تصفح التشكيلة" else "Découvrir la collection",
              color = Charcoal900,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
    } else {
      LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
      ) {
        items(favoriteProducts, key = { it.id }) { product ->
          ProductCard(
            product = product,
            language = language,
            isFavorite = true,
            onProductClick = onProductClick,
            onToggleFavorite = onToggleFavorite
          )
        }
      }
    }
  }
}

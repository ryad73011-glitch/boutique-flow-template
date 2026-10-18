package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.Product
import com.example.data.model.ProductCategory
import com.example.ui.components.ProductCard
import com.example.ui.theme.Charcoal900
import com.example.ui.theme.GoldAccent

@Composable
fun CatalogScreen(
  language: AppLanguage,
  products: List<Product>,
  selectedCategory: ProductCategory,
  searchQuery: String,
  favoriteIds: Set<String>,
  onSelectCategory: (ProductCategory) -> Unit,
  onSearchQueryChanged: (String) -> Unit,
  onProductClick: (Product) -> Unit,
  onToggleFavorite: (String) -> Unit,
  onResetFilters: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("catalog_screen")
  ) {
    // 1. Search Bar
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
      OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChanged,
        modifier = Modifier
          .fillMaxWidth()
          .testTag("search_input"),
        placeholder = {
          Text(
            text = if (language == AppLanguage.ARABIC) "ابحث عن قطعة، كاراكو، قفطان..." else "Rechercher une pièce, un tissu, une référence...",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
          )
        },
        leadingIcon = {
          Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = GoldAccent
          )
        },
        trailingIcon = {
          if (searchQuery.isNotEmpty()) {
            IconButton(
              onClick = { onSearchQueryChanged("") },
              modifier = Modifier.testTag("clear_search_button")
            ) {
              Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Effacer",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = GoldAccent,
          unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
          focusedContainerColor = MaterialTheme.colorScheme.surface,
          unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
      )
    }

    // 2. Category Filter Pills
    LazyRow(
      contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(ProductCategory.entries) { category ->
        val isSelected = category == selectedCategory
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
              if (isSelected) GoldAccent else MaterialTheme.colorScheme.surfaceVariant
            )
            .clickable { onSelectCategory(category) }
            .padding(horizontal = 14.dp, vertical = 8.dp)
            .testTag("category_chip_${category.id}"),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = category.getTitle(language),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) Charcoal900 else MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }

    // 3. Results Header & Count
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = if (language == AppLanguage.ARABIC)
          "${products.size} قطعة معروضة"
        else
          "${products.size} ${if (products.size > 1) "créations" else "création"}",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
      )

      if (searchQuery.isNotEmpty() || selectedCategory != ProductCategory.ALL) {
        Text(
          text = if (language == AppLanguage.ARABIC) "إلغاء التصفية" else "Réinitialiser",
          style = MaterialTheme.typography.labelSmall,
          color = GoldAccent,
          modifier = Modifier
            .clickable { onResetFilters() }
            .padding(4.dp)
        )
      }
    }

    // 4. Products Grid or Empty State
    if (products.isEmpty()) {
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
              imageVector = Icons.Default.Checkroom,
              contentDescription = null,
              tint = GoldAccent,
              modifier = Modifier.size(32.dp)
            )
          }

          Text(
            text = if (language == AppLanguage.ARABIC) "لم يتم العثور على أي قطعة" else "Aucune pièce trouvée",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
          )

          Text(
            text = if (language == AppLanguage.ARABIC)
              "جرب البحث بكلمات أخرى أو اختر تصنيفاً آخر لمشاهدة المجموعات."
            else
              "Essayez de modifier votre recherche ou de changer de catégorie pour explorer nos créations.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          Button(
            onClick = onResetFilters,
            colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.testTag("reset_filters_button")
          ) {
            Text(
              text = if (language == AppLanguage.ARABIC) "عرض جميع القطع" else "Voir toute la collection",
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
        modifier = Modifier
          .fillMaxSize()
          .testTag("products_grid")
      ) {
        items(products, key = { it.id }) { product ->
          ProductCard(
            product = product,
            language = language,
            isFavorite = favoriteIds.contains(product.id),
            onProductClick = onProductClick,
            onToggleFavorite = onToggleFavorite
          )
        }
      }
    }
  }
}

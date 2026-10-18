package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.Product
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldAccentLight

@Composable
fun ProductCard(
  product: Product,
  language: AppLanguage,
  isFavorite: Boolean,
  onProductClick: (Product) -> Unit,
  onToggleFavorite: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(8.dp))
      .border(0.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(8.dp))
      .clickable { onProductClick(product) }
      .testTag("product_card_${product.id}"),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surface
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(modifier = Modifier.fillMaxWidth()) {
      // Image container with 3:4 aspect ratio
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(0.78f)
      ) {
        Image(
          painter = painterResource(id = product.imageRes),
          contentDescription = product.getName(language),
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
        )

        // Category Tag
        Box(
          modifier = Modifier
            .padding(8.dp)
            .align(Alignment.TopStart)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.88f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Text(
            text = product.category.getTitle(language),
            style = MaterialTheme.typography.labelSmall,
            color = GoldAccent,
            fontWeight = FontWeight.SemiBold
          )
        }

        // Favorite Button
        IconButton(
          onClick = { onToggleFavorite(product.id) },
          modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(4.dp)
            .size(36.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.75f))
            .testTag("favorite_btn_${product.id}")
        ) {
          Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = if (isFavorite) "Retirer des favoris" else "Ajouter aux favoris",
            tint = if (isFavorite) GoldAccent else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(18.dp)
          )
        }
      }

      // Information block
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(12.dp)
      ) {
        Text(
          text = product.reference,
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
          letterSpacing = 0.8.sp
        )

        Text(
          text = product.getName(language),
          style = MaterialTheme.typography.titleMedium,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.SemiBold,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          color = MaterialTheme.colorScheme.onSurface,
          modifier = Modifier.padding(top = 2.dp)
        )

        Text(
          text = product.getSubtitle(language),
          style = MaterialTheme.typography.bodyMedium,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
          modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = product.formattedPrice(language),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = GoldAccent
          )

          Text(
            text = if (language == AppLanguage.ARABIC) "عرض التفاصيل" else "Détails",
            style = MaterialTheme.typography.labelSmall,
            color = GoldAccentLight,
            fontWeight = FontWeight.Medium
          )
        }
      }
    }
  }
}

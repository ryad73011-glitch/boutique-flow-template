package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.ui.Screen
import com.example.ui.theme.GoldAccent

@Composable
fun LuxuryTopAppBar(
  currentScreen: Screen,
  language: AppLanguage,
  favoriteCount: Int,
  onNavigateBack: () -> Unit,
  onToggleLanguage: () -> Unit,
  onOpenFavorites: () -> Unit,
  modifier: Modifier = Modifier
) {
  val isDetailOrSubScreen = currentScreen !is Screen.Home

  Surface(
    modifier = modifier.fillMaxWidth(),
    color = MaterialTheme.colorScheme.background,
    tonalElevation = 4.dp
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding()
        .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .height(48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Left side: Back button or Brand Monogram
        if (isDetailOrSubScreen) {
          IconButton(
            onClick = onNavigateBack,
            modifier = Modifier.testTag("back_button")
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = if (language == AppLanguage.ARABIC) "رجوع" else "Retour",
              tint = MaterialTheme.colorScheme.onBackground
            )
          }
        } else {
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = if (language == AppLanguage.ARABIC) "د.ج" else "MD",
              color = GoldAccent,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Serif
            )
          }
        }

        // Center: Brand Name in Serif
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier.testTag("brand_title")
        ) {
          Text(
            text = if (language == AppLanguage.ARABIC) "دار الجزائر" else "MAISON EL DJAZAIR",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            letterSpacing = 1.8.sp,
            color = MaterialTheme.colorScheme.onBackground
          )
          Text(
            text = if (language == AppLanguage.ARABIC) "خياطة راقية جزائرية" else "HAUTE COUTURE • ALGER",
            style = MaterialTheme.typography.labelSmall,
            letterSpacing = 1.2.sp,
            color = GoldAccent
          )
        }

        // Right side: Language Switcher & Wishlist Badge
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          // Language toggle button
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(16.dp))
              .background(MaterialTheme.colorScheme.surfaceVariant)
              .clickable { onToggleLanguage() }
              .padding(horizontal = 10.dp, vertical = 5.dp)
              .testTag("language_toggle_button"),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = if (language == AppLanguage.FRENCH) "العربية" else "FR",
              style = MaterialTheme.typography.labelSmall,
              fontWeight = FontWeight.SemiBold,
              color = GoldAccent
            )
          }

          // Wishlist icon
          IconButton(
            onClick = onOpenFavorites,
            modifier = Modifier.testTag("wishlist_button")
          ) {
            BadgedBox(
              badge = {
                if (favoriteCount > 0) {
                  Badge(
                    containerColor = GoldAccent,
                    contentColor = MaterialTheme.colorScheme.surface
                  ) {
                    Text("$favoriteCount")
                  }
                }
              }
            ) {
              Icon(
                imageVector = if (favoriteCount > 0) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = if (language == AppLanguage.ARABIC) "المفضلة" else "Favoris",
                tint = if (favoriteCount > 0) GoldAccent else MaterialTheme.colorScheme.onBackground
              )
            }
          }
        }
      }
    }
  }
}

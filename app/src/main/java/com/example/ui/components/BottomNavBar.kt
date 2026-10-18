package com.example.ui.components

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Checkroom
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.ui.Screen
import com.example.ui.theme.GoldAccent

@Composable
fun LuxuryBottomNavBar(
  currentScreen: Screen,
  language: AppLanguage,
  onNavigate: (Screen) -> Unit,
  modifier: Modifier = Modifier
) {
  NavigationBar(
    modifier = modifier.navigationBarsPadding(),
    containerColor = MaterialTheme.colorScheme.surface,
    tonalElevation = 8.dp
  ) {
    // 1. Home
    val isHome = currentScreen is Screen.Home
    NavigationBarItem(
      selected = isHome,
      onClick = { onNavigate(Screen.Home) },
      icon = {
        Icon(
          imageVector = if (isHome) Icons.Filled.Home else Icons.Outlined.Home,
          contentDescription = if (language == AppLanguage.ARABIC) "الرئيسية" else "Accueil"
        )
      },
      label = {
        Text(
          text = if (language == AppLanguage.ARABIC) "الرئيسية" else "Accueil",
          fontSize = 11.sp,
          fontWeight = if (isHome) FontWeight.SemiBold else FontWeight.Normal
        )
      },
      colors = NavigationBarItemDefaults.colors(
        indicatorColor = MaterialTheme.colorScheme.surfaceVariant,
        selectedIconColor = GoldAccent,
        selectedTextColor = GoldAccent,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
      ),
      modifier = Modifier.testTag("nav_home")
    )

    // 2. Catalog
    val isCatalog = currentScreen is Screen.Catalog || currentScreen is Screen.ProductDetail
    NavigationBarItem(
      selected = isCatalog,
      onClick = { onNavigate(Screen.Catalog) },
      icon = {
        Icon(
          imageVector = if (isCatalog) Icons.Filled.Checkroom else Icons.Outlined.Checkroom,
          contentDescription = if (language == AppLanguage.ARABIC) "التشكيلة" else "Catalogue"
        )
      },
      label = {
        Text(
          text = if (language == AppLanguage.ARABIC) "التشكيلة" else "Catalogue",
          fontSize = 11.sp,
          fontWeight = if (isCatalog) FontWeight.SemiBold else FontWeight.Normal
        )
      },
      colors = NavigationBarItemDefaults.colors(
        indicatorColor = MaterialTheme.colorScheme.surfaceVariant,
        selectedIconColor = GoldAccent,
        selectedTextColor = GoldAccent,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
      ),
      modifier = Modifier.testTag("nav_catalog")
    )

    // 3. About
    val isAbout = currentScreen is Screen.About
    NavigationBarItem(
      selected = isAbout,
      onClick = { onNavigate(Screen.About) },
      icon = {
        Icon(
          imageVector = if (isAbout) Icons.Filled.AutoAwesome else Icons.Outlined.AutoAwesome,
          contentDescription = if (language == AppLanguage.ARABIC) "عن الدار" else "La Maison"
        )
      },
      label = {
        Text(
          text = if (language == AppLanguage.ARABIC) "عن الدار" else "La Maison",
          fontSize = 11.sp,
          fontWeight = if (isAbout) FontWeight.SemiBold else FontWeight.Normal
        )
      },
      colors = NavigationBarItemDefaults.colors(
        indicatorColor = MaterialTheme.colorScheme.surfaceVariant,
        selectedIconColor = GoldAccent,
        selectedTextColor = GoldAccent,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
      ),
      modifier = Modifier.testTag("nav_about")
    )

    // 4. Contact / Atelier
    val isContact = currentScreen is Screen.Contact
    NavigationBarItem(
      selected = isContact,
      onClick = { onNavigate(Screen.Contact) },
      icon = {
        Icon(
          imageVector = if (isContact) Icons.Filled.Place else Icons.Outlined.Place,
          contentDescription = if (language == AppLanguage.ARABIC) "الورشة" else "Atelier"
        )
      },
      label = {
        Text(
          text = if (language == AppLanguage.ARABIC) "الورشة" else "Atelier",
          fontSize = 11.sp,
          fontWeight = if (isContact) FontWeight.SemiBold else FontWeight.Normal
        )
      },
      colors = NavigationBarItemDefaults.colors(
        indicatorColor = MaterialTheme.colorScheme.surfaceVariant,
        selectedIconColor = GoldAccent,
        selectedTextColor = GoldAccent,
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
      ),
      modifier = Modifier.testTag("nav_contact")
    )
  }
}

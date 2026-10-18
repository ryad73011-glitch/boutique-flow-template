package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.AppLanguage
import com.example.data.model.Product
import com.example.ui.Screen
import com.example.ui.components.ProductCard
import com.example.ui.theme.Charcoal900
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldAccentLight

@Composable
fun HomeScreen(
  language: AppLanguage,
  featuredProducts: List<Product>,
  favoriteIds: Set<String>,
  onProductClick: (Product) -> Unit,
  onToggleFavorite: (String) -> Unit,
  onNavigate: (Screen) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("home_screen_column"),
    contentPadding = PaddingValues(bottom = 24.dp)
  ) {
    // 1. Editorial Hero Section
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(1.1f)
      ) {
        Image(
          painter = painterResource(id = R.drawable.hero_algerois),
          contentDescription = "Collection Haute Couture Algérienne",
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
        )

        // Subtle gradient scrim
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.Transparent,
                  Charcoal900.copy(alpha = 0.5f),
                  Charcoal900.copy(alpha = 0.95f)
                ),
                startY = 200f
              )
            )
        )

        // Hero Content
        Column(
          modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(horizontal = 20.dp, vertical = 24.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(4.dp))
              .background(GoldAccent)
              .padding(horizontal = 10.dp, vertical = 4.dp)
          ) {
            Text(
              text = if (language == AppLanguage.ARABIC) "المجموعة الحصرية 2026" else "COLLECTION CAPSULE 2026",
              style = MaterialTheme.typography.labelSmall,
              color = Charcoal900,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.sp
            )
          }

          Text(
            text = if (language == AppLanguage.ARABIC) "فن الخياطة الراقية الأصيلة" else "L'Excellence du Patrimoine Algérois",
            style = MaterialTheme.typography.displayMedium,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )

          Text(
            text = if (language == AppLanguage.ARABIC)
              "كاراكو مطرز، قفاطين ملكية وقطع فريدة تحاكي عراقة القصبة والجزائر العاصمة."
            else
              "Karakous d'apparat, caftans royaux et silhouettes modernes brodées au fil d'or pur à Alger.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.9f)
          )

          Spacer(modifier = Modifier.height(4.dp))

          Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            Button(
              onClick = { onNavigate(Screen.Catalog) },
              colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
              shape = RoundedCornerShape(6.dp),
              modifier = Modifier.testTag("hero_explore_button")
            ) {
              Text(
                text = if (language == AppLanguage.ARABIC) "استكشف التشكيلة" else "Découvrir la Collection",
                color = Charcoal900,
                fontWeight = FontWeight.Bold
              )
            }

            OutlinedButton(
              onClick = { onNavigate(Screen.About) },
              colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
              border = ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.horizontalGradient(listOf(GoldAccent, GoldAccentLight))),
              shape = RoundedCornerShape(6.dp)
            ) {
              Text(
                text = if (language == AppLanguage.ARABIC) "قصة الدار" else "Notre Histoire",
                fontWeight = FontWeight.Medium
              )
            }
          }
        }
      }
    }

    // 2. Pillars of Maison El Djazair
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 20.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          PillarCard(
            icon = Icons.Default.Diamond,
            title = if (language == AppLanguage.ARABIC) "تطريز الفتلة والمجبود" else "Broderie Fetla & Medjboud",
            subtitle = if (language == AppLanguage.ARABIC) "خيوط ذهبية خالصة" else "Fil d'or pur cousu main",
            modifier = Modifier.weight(1f)
          )
          PillarCard(
            icon = Icons.Default.AutoAwesome,
            title = if (language == AppLanguage.ARABIC) "تفصيل ملكي حسب الطلب" else "Sur-Mesure Exclusif",
            subtitle = if (language == AppLanguage.ARABIC) "قطع حصرية مرقمة" else "Pièces numérotées",
            modifier = Modifier.weight(1f)
          )
          PillarCard(
            icon = Icons.Default.CheckCircle,
            title = if (language == AppLanguage.ARABIC) "ورشة حيدرة، الجزائر" else "Atelier d'Alger",
            subtitle = if (language == AppLanguage.ARABIC) "سيدي يحيى" else "Sidi Yahia, Hydra",
            modifier = Modifier.weight(1f)
          )
        }
      }
    }

    // 3. Featured Section Header
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = if (language == AppLanguage.ARABIC) "قطع الموسم الحصرية" else "Pièces Phares du Moment",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
          )
          Text(
            text = if (language == AppLanguage.ARABIC) "صياغة فنية بالطلب المباشر" else "Confection artisanale sur commande",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
          )
        }

        Text(
          text = if (language == AppLanguage.ARABIC) "عرض الكل" else "Tout voir",
          style = MaterialTheme.typography.labelLarge,
          color = GoldAccent,
          modifier = Modifier
            .clickable { onNavigate(Screen.Catalog) }
            .padding(4.dp)
        )
      }
    }

    // 4. Featured Products Carousel
    item {
      LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        items(featuredProducts, key = { it.id }) { product ->
          ProductCard(
            product = product,
            language = language,
            isFavorite = favoriteIds.contains(product.id),
            onProductClick = onProductClick,
            onToggleFavorite = onToggleFavorite,
            modifier = Modifier.width(230.dp)
          )
        }
      }
    }

    // 5. Editorial Quote Banner
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 24.dp)
          .border(1.dp, GoldAccent.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          Text(
            text = "« " + (if (language == AppLanguage.ARABIC)
              "كل قطعة تخرج من ورشتنا تحمل نفحات أصيلة من تاريخ الجزائر وقصبتها العريقة، مصاغة بأعلى معايير الهوت كوتور العالمية."
            else
              "Chaque création de notre atelier perpétue le faste séculaire de la Casbah d'Alger, sublimé par la précision de la haute couture contemporaine.") + " »",
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = FontFamily.Serif,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          Text(
            text = if (language == AppLanguage.ARABIC) "دار الجزائر — سيدي يحيى، حيدرة" else "Maison El Djazair — Hydra, Alger",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            color = GoldAccent,
            letterSpacing = 1.sp
          )
        }
      }
    }

    // 6. Direct WhatsApp Consultation Banner
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
          .clickable { onNavigate(Screen.Contact) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = if (language == AppLanguage.ARABIC) "حجز موعد قياس خاص" else "Prendre Rendez-vous à l'Atelier",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = if (language == AppLanguage.ARABIC)
                "استشارة شخصية مع خبيرات الخياطة الراقية للتفصيل على المقاس"
              else
                "Essayage privé & personnalisation de votre pièce de mariage ou soirée",
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
            )
          }

          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = GoldAccent,
            modifier = Modifier.padding(start = 8.dp)
          )
        }
      }
    }
  }
}

@Composable
private fun PillarCard(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  title: String,
  subtitle: String,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier,
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    shape = RoundedCornerShape(8.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = GoldAccent,
        modifier = Modifier.size(20.dp)
      )
      Text(
        text = title,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        maxLines = 2,
        color = MaterialTheme.colorScheme.onSurface
      )
      Text(
        text = subtitle,
        style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
        maxLines = 1
      )
    }
  }
}

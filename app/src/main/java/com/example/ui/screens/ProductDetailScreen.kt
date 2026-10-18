package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.Product
import com.example.data.repository.ProductRepository
import com.example.ui.theme.Charcoal900
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.GoldAccentLight
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductDetailScreen(
  product: Product,
  language: AppLanguage,
  isFavorite: Boolean,
  onToggleFavorite: (String) -> Unit,
  onNavigateBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var selectedSize by remember {
    mutableStateOf(product.availableSizes.firstOrNull() ?: "Standard")
  }
  var showOrderDialog by remember { mutableStateOf(false) }

  val whatsappMessage = remember(product, selectedSize, language) {
    ProductRepository.buildWhatsAppMessage(product, selectedSize, language)
  }

  fun launchWhatsApp() {
    try {
      val encodedText = URLEncoder.encode(whatsappMessage, StandardCharsets.UTF_8.toString())
      val uri = Uri.parse("https://api.whatsapp.com/send?phone=${ProductRepository.DEFAULT_WHATSAPP_PHONE}&text=$encodedText")
      val intent = Intent(Intent.ACTION_VIEW, uri)
      context.startActivity(intent)
    } catch (_: Exception) {
      showOrderDialog = true
    }
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("product_detail_screen")
  ) {
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(bottom = 100.dp)
    ) {
      // 1. Hero Image with Overlay Actions
      item {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.85f)
        ) {
          Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.getName(language),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
          )

          // Top action bar
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            IconButton(
              onClick = onNavigateBack,
              modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(Charcoal900.copy(alpha = 0.6f))
                .testTag("detail_back_button")
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Retour",
                tint = Color.White
              )
            }

            IconButton(
              onClick = { onToggleFavorite(product.id) },
              modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(Charcoal900.copy(alpha = 0.6f))
                .testTag("detail_favorite_button")
            ) {
              Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favoris",
                tint = if (isFavorite) GoldAccent else Color.White
              )
            }
          }

          // Category Badge on bottom left of image
          Box(
            modifier = Modifier
              .align(Alignment.BottomStart)
              .padding(16.dp)
              .clip(RoundedCornerShape(6.dp))
              .background(Charcoal900.copy(alpha = 0.85f))
              .padding(horizontal = 12.dp, vertical = 6.dp)
          ) {
            Text(
              text = product.category.getTitle(language),
              style = MaterialTheme.typography.labelSmall,
              color = GoldAccent,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }

      // 2. Main Product Info
      item {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "REF: ${product.reference}",
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
              letterSpacing = 1.sp
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(8.dp)
                  .clip(CircleShape)
                  .background(GoldAccent)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = if (language == AppLanguage.ARABIC) "متاح للطلب المباشر" else "Sur commande",
                style = MaterialTheme.typography.labelSmall,
                color = GoldAccent
              )
            }
          }

          Text(
            text = product.getName(language),
            style = MaterialTheme.typography.displayMedium,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
          )

          Text(
            text = product.getSubtitle(language),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          // Price Tag
          Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(8.dp)
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column {
                Text(
                  text = if (language == AppLanguage.ARABIC) "السعر التقديري" else "Prix de confection",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
                Text(
                  text = product.formattedPrice(language),
                  style = MaterialTheme.typography.headlineLarge,
                  fontWeight = FontWeight.Bold,
                  color = GoldAccent
                )
              }

              Text(
                text = if (language == AppLanguage.ARABIC) "شامل التطريز اليدوي" else "Broderie main incluse",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }

          HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant,
            modifier = Modifier.padding(vertical = 4.dp)
          )

          // 3. Size Selection
          Text(
            text = if (language == AppLanguage.ARABIC) "اختر المقاس المطلوب :" else "Sélectionnez votre taille :",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
          )

          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            product.availableSizes.forEach { size ->
              val isSelected = size == selectedSize
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(8.dp))
                  .border(
                    width = if (isSelected) 1.5.dp else 1.dp,
                    color = if (isSelected) GoldAccent else MaterialTheme.colorScheme.outlineVariant,
                    shape = RoundedCornerShape(8.dp)
                  )
                  .background(
                    if (isSelected) GoldAccent.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surface
                  )
                  .clickable { selectedSize = size }
                  .padding(horizontal = 16.dp, vertical = 10.dp)
                  .testTag("size_chip_$size"),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = size,
                  style = MaterialTheme.typography.labelLarge,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                  color = if (isSelected) GoldAccent else MaterialTheme.colorScheme.onSurface
                )
              }
            }
          }

          HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant,
            modifier = Modifier.padding(vertical = 4.dp)
          )

          // 4. Description
          Text(
            text = if (language == AppLanguage.ARABIC) "عن القطعة :" else "Description de la création :",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
          )

          Text(
            text = product.getDescription(language),
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          // 5. Craftsmanship & Fabric Cards
          Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(
              modifier = Modifier.padding(16.dp),
              verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                  imageVector = Icons.Default.Diamond,
                  contentDescription = null,
                  tint = GoldAccent,
                  modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = if (language == AppLanguage.ARABIC) "صناعة حرفية أصيلة" else "Artisanat & Broderie",
                  style = MaterialTheme.typography.titleMedium,
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onSurface
                )
              }
              Text(
                text = product.getCraftsmanship(language),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )

              HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                  imageVector = Icons.Default.AutoAwesome,
                  contentDescription = null,
                  tint = GoldAccent,
                  modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = if (language == AppLanguage.ARABIC) "الأقمشة والمواد" else "Tissus & Matières",
                  style = MaterialTheme.typography.titleMedium,
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onSurface
                )
              }
              Text(
                text = product.getFabric(language),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }
      }
    }

    // Floating Sticky WhatsApp Order Bar
    Surface(
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .fillMaxWidth(),
      color = MaterialTheme.colorScheme.surface,
      tonalElevation = 12.dp,
      shadowElevation = 8.dp
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = product.formattedPrice(language),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = GoldAccent
          )
          Text(
            text = if (language == AppLanguage.ARABIC) "مقاس $selectedSize" else "Taille $selectedSize",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        Button(
          onClick = { launchWhatsApp() },
          colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp),
          modifier = Modifier.testTag("whatsapp_order_button")
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Chat,
              contentDescription = null,
              tint = Charcoal900,
              modifier = Modifier.size(20.dp)
            )
            Text(
              text = if (language == AppLanguage.ARABIC) "طلب عبر واتساب" else "Commander via WhatsApp",
              color = Charcoal900,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
    }

    // Modal Dialog Preview
    if (showOrderDialog) {
      AlertDialog(
        onDismissRequest = { showOrderDialog = false },
        title = {
          Text(
            text = if (language == AppLanguage.ARABIC) "رسالة الطلب عبر واتساب" else "Votre message de commande",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
          )
        },
        text = {
          Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
              text = if (language == AppLanguage.ARABIC)
                "رقم الورشة المعتمد : ${ProductRepository.DEFAULT_WHATSAPP_PHONE}"
              else
                "Numéro WhatsApp de l'Atelier : ${ProductRepository.DEFAULT_WHATSAPP_PHONE}",
              style = MaterialTheme.typography.bodyMedium,
              fontWeight = FontWeight.SemiBold,
              color = GoldAccent
            )
            Card(
              colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
              Text(
                text = whatsappMessage,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(12.dp)
              )
            }
          }
        },
        confirmButton = {
          Button(
            onClick = {
              showOrderDialog = false
              val encodedText = URLEncoder.encode(whatsappMessage, StandardCharsets.UTF_8.toString())
              val uri = Uri.parse("https://wa.me/${ProductRepository.DEFAULT_WHATSAPP_PHONE.replace("+", "")}?text=$encodedText")
              val intent = Intent(Intent.ACTION_VIEW, uri)
              context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(containerColor = GoldAccent)
          ) {
            Text(
              text = if (language == AppLanguage.ARABIC) "متابعة إلى واتساب" else "Ouvrir WhatsApp",
              color = Charcoal900
            )
          }
        },
        dismissButton = {
          TextButton(onClick = { showOrderDialog = false }) {
            Text(if (language == AppLanguage.ARABIC) "إغلاق" else "Fermer")
          }
        }
      )
    }
  }
}

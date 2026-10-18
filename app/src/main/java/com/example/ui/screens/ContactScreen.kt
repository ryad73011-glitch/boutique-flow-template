package com.example.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.repository.ProductRepository
import com.example.ui.BoutiqueUiState
import com.example.ui.theme.Charcoal900
import com.example.ui.theme.GoldAccent

@Composable
fun ContactScreen(
  language: AppLanguage,
  uiState: BoutiqueUiState,
  onUpdateForm: (name: String, email: String, phone: String, message: String) -> Unit,
  onSubmitForm: () -> Unit,
  onResetForm: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current

  fun openWhatsApp() {
    try {
      val uri = Uri.parse("https://wa.me/${ProductRepository.DEFAULT_WHATSAPP_PHONE.replace("+", "")}")
      context.startActivity(Intent(Intent.ACTION_VIEW, uri))
    } catch (_: Exception) {}
  }

  fun openPhone() {
    try {
      val uri = Uri.parse("tel:${ProductRepository.ATELIER_PHONE.replace(" ", "")}")
      context.startActivity(Intent(Intent.ACTION_DIAL, uri))
    } catch (_: Exception) {}
  }

  fun openEmail() {
    try {
      val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:contact@maison-eldjazair.dz")
        putExtra(Intent.EXTRA_SUBJECT, "Demande de rendez-vous / Consultation Maison El Djazair")
      }
      context.startActivity(intent)
    } catch (_: Exception) {}
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("contact_screen"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Header
    item {
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
          text = if (language == AppLanguage.ARABIC) "تواصل مع ورشة الدار" else "L'Atelier & Rendez-vous",
          style = MaterialTheme.typography.displayMedium,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onBackground
        )
        Text(
          text = if (language == AppLanguage.ARABIC)
            "يسرنا استقبالكم في صالة العرض الخاصة بنا في حيدرة، الجزائر أو الإجابة على استفساراتكم عبر واتساب."
          else
            "Notre équipe vous accueille sur rendez-vous à Hydra pour vos essayages privés et commandes sur-mesure.",
          style = MaterialTheme.typography.bodyLarge,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    // 2. Direct Actions (WhatsApp, Phone, Email)
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        // WhatsApp button
        Card(
          modifier = Modifier
            .weight(1f)
            .clickable { openWhatsApp() }
            .testTag("contact_whatsapp_card"),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
          shape = RoundedCornerShape(10.dp)
        ) {
          Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(Icons.Default.Chat, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(24.dp))
            Text("WhatsApp", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Text(ProductRepository.DEFAULT_WHATSAPP_PHONE, style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp), color = MaterialTheme.colorScheme.onSurfaceVariant)
          }
        }

        // Phone button
        Card(
          modifier = Modifier
            .weight(1f)
            .clickable { openPhone() }
            .testTag("contact_phone_card"),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
          shape = RoundedCornerShape(10.dp)
        ) {
          Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(Icons.Default.Call, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(24.dp))
            Text(if (language == AppLanguage.ARABIC) "هاتف الورشة" else "Téléphone", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Text(ProductRepository.ATELIER_PHONE, style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp), color = MaterialTheme.colorScheme.onSurfaceVariant)
          }
        }

        // Email button
        Card(
          modifier = Modifier
            .weight(1f)
            .clickable { openEmail() }
            .testTag("contact_email_card"),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
          shape = RoundedCornerShape(10.dp)
        ) {
          Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(Icons.Default.Email, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(24.dp))
            Text("Email", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Text("Alger", style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp), color = MaterialTheme.colorScheme.onSurfaceVariant)
          }
        }
      }
    }

    // 3. Location and Hours Card
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(10.dp)
      ) {
        Column(
          modifier = Modifier.padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          Row(verticalAlignment = Alignment.Top) {
            Icon(Icons.Default.LocationOn, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = if (language == AppLanguage.ARABIC) "عنوان صالة العرض والورشة" else "Adresse de l'Atelier",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = if (language == AppLanguage.ARABIC) ProductRepository.ATELIER_ADDRESS_AR else ProductRepository.ATELIER_ADDRESS_FR,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }

          Row(verticalAlignment = Alignment.Top) {
            Icon(Icons.Default.Schedule, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = if (language == AppLanguage.ARABIC) "مواعيد العمل" else "Horaires d'Ouverture",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = if (language == AppLanguage.ARABIC) ProductRepository.ATELIER_HOURS_AR else ProductRepository.ATELIER_HOURS_FR,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }
      }
    }

    // 4. Appointment Request Form
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp)
      ) {
        Column(
          modifier = Modifier.padding(20.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          Text(
            text = if (language == AppLanguage.ARABIC) "طلب استشارة أو موعد قياس" else "Demande d'Essayage Privé",
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
          )

          if (uiState.contactFormSent) {
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(GoldAccent.copy(alpha = 0.15f))
                .padding(16.dp)
            ) {
              Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
              ) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = GoldAccent, modifier = Modifier.size(32.dp))
                Text(
                  text = if (language == AppLanguage.ARABIC) "تم استلام طلبكم بنجاح" else "Demande envoyée avec succès",
                  style = MaterialTheme.typography.titleMedium,
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                  text = if (language == AppLanguage.ARABIC)
                    "سيتصل بكم مستشار الدار في أقرب وقت لتأكيد موعدكم وتفاصيل طلبكم."
                  else
                    "Notre conseillère haute couture vous contactera sous 24h pour confirmer votre rendez-vous.",
                  style = MaterialTheme.typography.bodyMedium,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                TextButton(onClick = onResetForm) {
                  Text(if (language == AppLanguage.ARABIC) "إرسال طلب جديد" else "Nouvelle demande")
                }
              }
            }
          } else {
            // Name Field
            OutlinedTextField(
              value = uiState.contactFormSenderName,
              onValueChange = { onUpdateForm(it, uiState.contactFormEmail, uiState.contactFormPhone, uiState.contactFormMessage) },
              label = { Text(if (language == AppLanguage.ARABIC) "الاسم الكامل" else "Nom complet") },
              modifier = Modifier
                .fillMaxWidth()
                .testTag("contact_input_name"),
              singleLine = true,
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldAccent,
                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
              )
            )

            // Phone Field
            OutlinedTextField(
              value = uiState.contactFormPhone,
              onValueChange = { onUpdateForm(uiState.contactFormSenderName, uiState.contactFormEmail, it, uiState.contactFormMessage) },
              label = { Text(if (language == AppLanguage.ARABIC) "رقم الهاتف" else "Numéro de téléphone") },
              modifier = Modifier
                .fillMaxWidth()
                .testTag("contact_input_phone"),
              singleLine = true,
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldAccent,
                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
              )
            )

            // Message Field
            OutlinedTextField(
              value = uiState.contactFormMessage,
              onValueChange = { onUpdateForm(uiState.contactFormSenderName, uiState.contactFormEmail, uiState.contactFormPhone, it) },
              label = { Text(if (language == AppLanguage.ARABIC) "القطعة المرغوبة أو تفاصيل المناسبة" else "Pièce souhaitée, date de votre événement...") },
              modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .testTag("contact_input_message"),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = GoldAccent,
                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
              )
            )

            Button(
              onClick = onSubmitForm,
              enabled = uiState.contactFormSenderName.isNotBlank() && uiState.contactFormPhone.isNotBlank(),
              colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
              shape = RoundedCornerShape(8.dp),
              modifier = Modifier
                .fillMaxWidth()
                .testTag("contact_submit_button")
            ) {
              Text(
                text = if (language == AppLanguage.ARABIC) "تأكيد طلب الموعد" else "Confirmer la demande",
                color = Charcoal900,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
              )
            }
          }
        }
      }
    }
  }
}

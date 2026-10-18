package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.ui.Screen
import com.example.ui.theme.Charcoal900
import com.example.ui.theme.GoldAccent

@Composable
fun AboutScreen(
  language: AppLanguage,
  onNavigate: (Screen) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("about_screen"),
    contentPadding = PaddingValues(bottom = 32.dp)
  ) {
    // 1. Header Banner
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(1.3f)
      ) {
        Image(
          painter = painterResource(id = R.drawable.hero_algerois),
          contentDescription = "Maison El Djazair Atelier",
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
        )
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(Charcoal900.copy(alpha = 0.7f))
        )
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = if (language == AppLanguage.ARABIC) "تاريخ وأصالة الدار" else "LA MAISON EL DJAZAIR",
            style = MaterialTheme.typography.displayMedium,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = if (language == AppLanguage.ARABIC)
              "حراسة التراث الجزائري العريق وصناعته بروح الحداثة"
            else
              "Gardiens du patrimoine vestimentaire algérien depuis des générations",
            style = MaterialTheme.typography.bodyLarge,
            color = GoldAccent,
            textAlign = TextAlign.Center
          )
        }
      }
    }

    // 2. Story Content
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
      ) {
        Text(
          text = if (language == AppLanguage.ARABIC) "فلسفتنا في الخياطة الراقية" else "Notre Vision de la Haute Couture",
          style = MaterialTheme.typography.headlineLarge,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onBackground
        )

        Text(
          text = if (language == AppLanguage.ARABIC)
            "تأسست دار الجزائر في قلب العاصمة لتكريم التراث الغني والفريد للباس التقليدي الجزائري. من أزقة القصبة العتيقة حيث كانت أصوات إبر التطريز الذهبية تعزف ألحان الفتلة والمجبود، إلى ورشتنا الحديثة في حيدرة، نلتزم بالحفاظ على هذا الفن اليدوي النادر."
          else
            "Fondée au cœur d'Alger, Maison El Djazair est née de la volonté de célébrer la richesse inestimable du costume traditionnel algérien. Des ruelles pavées de la Casbah séculaire, où résonnaient les gestes immémoriaux des maîtresses brodeuses, jusqu'à notre atelier contemporain de Sidi Yahia à Hydra, nous préservons un savoir-faire rare et précieux.",
          style = MaterialTheme.typography.bodyLarge,
          lineHeight = 26.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // 3. Values Cards
        AboutValueCard(
          icon = Icons.Default.Diamond,
          title = if (language == AppLanguage.ARABIC) "حرفة يدوية خالصة 100%" else "100% Fait-Main Artisanal",
          description = if (language == AppLanguage.ARABIC)
            "كل سترة كاراكو وكل قفطان يخضع لأكثر من 100 ساعة من العمل اليدوي الدقيق بأيدي حرفيات جزائريات توارثن الصنعة أباً عن جد."
          else
            "Chaque veste de Karakou et chaque robe royale nécessite plus de 100 heures d'ouvrages minutieux réalisés par nos artisanes détentrices des secrets de broderie ancestrale."
        )

        AboutValueCard(
          icon = Icons.Default.AutoAwesome,
          title = if (language == AppLanguage.ARABIC) "أقمشة ملكية فاخرة" else "Matières Nobles & Raffinement",
          description = if (language == AppLanguage.ARABIC)
            "نختار أجود أنواع المخمل الحريري، والساتان دوشيس، وخيوط الذهب الخالص المستوردة خصيصاً لضمان رونق يدوم لأجيال."
          else
            "Sélection rigoureuse des velours de soie les plus denses, satins duchesse impériaux et fils d'or pur pour des pièces pensées comme des héritages familiaux."
        )

        AboutValueCard(
          icon = Icons.Default.History,
          title = if (language == AppLanguage.ARABIC) "تطريز الفتلة والمجبود والشبيكة" else "Les Arts de la Broderie Algéroise",
          description = if (language == AppLanguage.ARABIC)
            "إحياء التقنيات التاريخية العاصمية المعترف بها عالمياً: الفتلة بخيوطها اللامعة، والمجبود على الجلد، وتقنية الشبيكة الأندلسية الدقيقة."
          else
            "Renaissance des techniques historiques d'Alger : la Fetla en relief, le Medjboud au fil doré torsadé, et la dentelle Chbika d'inspiration arabo-andalouse."
        )

        // 4. CTA
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, GoldAccent.copy(alpha = 0.4f), RoundedCornerShape(12.dp)),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
          Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            Text(
              text = if (language == AppLanguage.ARABIC) "هل ترغبين في تفصيل قطعة خاصة بمناسبتك؟" else "Envie d'une création sur-mesure pour votre grand jour ?",
              style = MaterialTheme.typography.titleLarge,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold,
              textAlign = TextAlign.Center,
              color = MaterialTheme.colorScheme.onSurface
            )
            Button(
              onClick = { onNavigate(Screen.Contact) },
              colors = ButtonDefaults.buttonColors(containerColor = GoldAccent),
              shape = RoundedCornerShape(6.dp),
              modifier = Modifier.testTag("about_contact_button")
            ) {
              Text(
                text = if (language == AppLanguage.ARABIC) "تواصل مع الورشة" else "Prendre contact avec l'Atelier",
                color = Charcoal900,
                fontWeight = FontWeight.Bold
              )
            }
          }
        }
      }
    }
  }
}

@Composable
private fun AboutValueCard(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  title: String,
  description: String
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    shape = RoundedCornerShape(10.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      Box(
        modifier = Modifier
          .size(44.dp)
          .clip(RoundedCornerShape(8.dp))
          .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = GoldAccent,
          modifier = Modifier.size(24.dp)
        )
      }

      Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
          text = title,
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = description,
          style = MaterialTheme.typography.bodyMedium,
          lineHeight = 22.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }
  }
}

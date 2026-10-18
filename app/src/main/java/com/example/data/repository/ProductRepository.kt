package com.example.data.repository

import com.example.R
import com.example.data.model.AppLanguage
import com.example.data.model.Product
import com.example.data.model.ProductCategory

object ProductRepository {
  const val DEFAULT_WHATSAPP_PHONE = "+213555123456"
  const val BRAND_NAME_FR = "Maison El Djazair"
  const val BRAND_NAME_AR = "دار الجزائر للموضة"
  const val ATELIER_ADDRESS_FR = "14 Rue Sidi Yahia, Hydra, Alger, Algérie"
  const val ATELIER_ADDRESS_AR = "14 شارع سيدي يحيى، حيدرة، الجزائر العاصمة"
  const val ATELIER_PHONE = "+213 21 60 45 80"
  const val ATELIER_HOURS_FR = "Samedi – Jeudi : 10h00 – 19h30"
  const val ATELIER_HOURS_AR = "السبت – الخميس : 10:00 صباحاً – 07:30 مساءً"

  val products = listOf(
    Product(
      id = "karakou-soltana",
      reference = "MED-KK-01",
      category = ProductCategory.KARAKOU,
      nameFr = "Karakou Royal Soltana",
      nameAr = "كاراكو السلطانة الملكي",
      subtitleFr = "Velours de soie émeraude & broderie Fetla or pur",
      subtitleAr = "مخمل حريري بلون الزمرد وتطريز فتلة بماء الذهب",
      priceDzd = 145000,
      imageRes = R.drawable.product_karakou_vert,
      descriptionFr = "Le chef-d'œuvre emblématique de la maison. Veste cintrée confectionnée dans un velours de soie impérial, ornée de motifs géométriques et floraux inspirés du patrimoine ottoman algérois. Chaque pièce requiert plus de 120 heures de broderie manuelle.",
      descriptionAr = "تحفة دار الجزائر الخالدة. سترة مخصّرة من المخمل الحريري الفاخر بلون الزمرد الملكي، مطرّزة يدويًا بخيوط الفتلة الذهبية وفق التقاليد العاصمية العريقة، تستغرق أكثر من 120 ساعة من التطريز اليدوي الدقيق.",
      craftsmanshipFr = "Broderie Fetla & Medjboud fait-main, finitions boutons Dziriat argentés et dorés.",
      craftsmanshipAr = "تطريز يدوي أصيل بتقنية الفتلة والمجبود مع أزرار عاصمية مذهّبة.",
      fabricFr = "100% Velours de soie de Lyon & doublure satinée",
      fabricAr = "100% مخمل حريري فاخر وبطانة ساتان ناعمة",
      availableSizes = listOf("36", "38", "40", "42", "Sur-mesure"),
      isFeatured = true
    ),
    Product(
      id = "caftan-el-bahdja",
      reference = "MED-CF-02",
      category = ProductCategory.CAFTAN,
      nameFr = "Caftan d'Or El Bahdja",
      nameAr = "قفطان الذهب البهجة",
      subtitleFr = "Satin duchesse ivoire & passementerie fil d'or",
      subtitleAr = "ساتان دوشيس عاجي مع حزام وسفيفة مذهبة",
      priceDzd = 185000,
      imageRes = R.drawable.product_caftan_or,
      descriptionFr = "Une silhouette royale alliant l'opulence du satin duchesse ivoire à la finesse d'un travail d'orfèvrerie textile. Manches évasées impériales et ceinture Mdamma ciselée à la main pour une allure de cour féerique.",
      descriptionAr = "إطلالة ملكية ساحرة تجمع بين فخامة الساتان الدوشيس العاجي ودقة التطريز التراثي. أكمام ملكية منسدلة مع حزام مضمة مصوغ بعناية فائقة للمناسبات والأعراس الراقية.",
      craftsmanshipFr = "Sfifa artisanale tissée au fuseau, ceinture Mdamma orfèvre amovible, perlage délicat.",
      craftsmanshipAr = "سفيفة يدوية منسوجة مع مضمة حلي تراثية قابلة للتعديل وتطريز لؤلؤي دقيق.",
      fabricFr = "Satin Duchesse de soie lourde & lamé doré",
      fabricAr = "ساتان دوشيس حريري ثقيل وخيوط لاميه ذهبية",
      availableSizes = listOf("38", "40", "42", "44", "Sur-mesure"),
      isFeatured = true
    ),
    Product(
      id = "gandoura-casbah",
      reference = "MED-GN-03",
      category = ProductCategory.GANDOURA,
      nameFr = "Gandoura Moderne Casbah",
      nameAr = "قندورة القصبة العصرية",
      subtitleFr = "Crêpe de soie bleu nuit & plastron or brossé",
      subtitleAr = "كريب الحرير الأزرق الملكي مع صدرية مطرزة",
      priceDzd = 89000,
      imageRes = R.drawable.product_gandoura_bleu,
      descriptionFr = "Une interprétation épurée et contemporaine de la Gandoura traditionnelle. Silhouette fluide et tombé sculptural, rehaussée d'une encolure géométrique brodée au fil d'or mat. L'élégance intemporelle pour les réceptions modernes.",
      descriptionAr = "قراءة عصرية متجددة للقندورة التقليدية الأصيلة. قصة انسيابية أنيقة من كريب الحرير الأزرق الداكن تبرز روعة التطريز الهندسي الذهبي على الصدر والأكمام.",
      craftsmanshipFr = "Point de croix moderne et surpiqûres dorées discrètes, fentes latérales contemporaines.",
      craftsmanshipAr = "تطريز هندسي دقيق مع فتحات جانبية عصرية لراحة وأناقة استثنائية.",
      fabricFr = "Crêpe de soie lourd & organza contrastant",
      fabricAr = "كريب حريري ثقيل مع لمسات أورجانزا راقية",
      availableSizes = listOf("36", "38", "40", "42", "44"),
      isFeatured = true
    ),
    Product(
      id = "pochette-majdoub",
      reference = "MED-AC-04",
      category = ProductCategory.ACCESSOIRES,
      nameFr = "Minaudière Majdoub & Manchette",
      nameAr = "حقيبة سهرة مجبود وسوار ذهبي",
      subtitleFr = "Pochette velours noir brodée main & pompon de perles",
      subtitleAr = "حقيبة يد مخملية سوداء بتطريز المجبود وسوار أندلسي",
      priceDzd = 42000,
      imageRes = R.drawable.product_pochette_velours,
      descriptionFr = "Accessoire de haute parure conçu pour sublimer vos tenues de soirée. Réalisé en velours de soie noir profond, rehaussé d'une broderie d'arabesques au fil d'or torsadé Majdoub et orné d'un fermoir ciselé avec pompon de soie.",
      descriptionAr = "قطعة فنية من الإكسسوارات الراقية تتوّج إطلالات السهرات الفخمة. مصنوعة من المخمل الأسود ومطرّزة بحبال المجبود الذهبية مع قفل تراثي وشرابة حريرية متدلية.",
      craftsmanshipFr = "Fait-main par nos maîtres artisans maroquiniers et brodeurs à Alger.",
      craftsmanshipAr = "صناعة يدوية دقيقة في ورشتنا بالجزائر العاصمة على يد أمهر الحرفيين.",
      fabricFr = "Velours de coton ras & monture laiton doré",
      fabricAr = "مخمل قطني فاخر مع هيكل نحاسي مطلي بالذهب",
      availableSizes = listOf("Taille Unique"),
      isFeatured = false
    ),
    Product(
      id = "karakou-constantine",
      reference = "MED-KK-05",
      category = ProductCategory.KARAKOU,
      nameFr = "Karakou Nuit d'Alger",
      nameAr = "كاراكو ليالي الجزائر",
      subtitleFr = "Velours noir ébène & arabesques argentées et or",
      subtitleAr = "مخمل أسود داكن ونقوش عربية بالذهب والفضة",
      priceDzd = 135000,
      imageRes = R.drawable.hero_algerois,
      descriptionFr = "Inspiré des nuits étoilées de la baie d'Alger, ce Karakou combine le velours de soie noir intense avec un travail bicolore or et argent. Épaules sculptées et taille ajustée pour magnifier la silhouette féminine.",
      descriptionAr = "مستوحى من سحر ليالي خليج الجزائر الساهرة، يمزج هذا الكاراكو بين فخامة المخمل الأسود وبريق التطريز الثنائي بالذهب والفضة لخصر منحوت وأكتاف ملكية.",
      craftsmanshipFr = "Broderie Fetla bicolore, doublure jacquard sur-mesure.",
      craftsmanshipAr = "تطريز فتلة بلونين مع بطانة جاكار حصرية.",
      fabricFr = "Velours de soie & fil de cannetille or/argent",
      fabricAr = "مخمل حريري وخيوط قصب ذهبية وفضية",
      availableSizes = listOf("36", "38", "40", "42", "Sur-mesure"),
      isFeatured = true
    ),
    Product(
      id = "sarouel-mdouer",
      reference = "MED-GN-06",
      category = ProductCategory.GANDOURA,
      nameFr = "Ensemble Sarouel Mdouer Moderne",
      nameAr = "طقم سروال مدور عصري",
      subtitleFr = "Satin de soie champagne & coupe contemporaine",
      subtitleAr = "ساتان حريري بلون الشمبانيا وقصة عصرية",
      priceDzd = 98000,
      imageRes = R.drawable.product_caftan_or,
      descriptionFr = "L'alliance parfaite de la tradition algéroise et du minimalisme actuel. Le sarouel Mdouer traditionnel revisité avec un volume structuré et un tombé impeccable, accompagné de sa blouse brodée assortie.",
      descriptionAr = "التوازن المثالي بين الأصالة العاصمية والروح العصرية. السروال المدور الشهير يعاد ابتكاره بقصة انسيابية ساحرة وقميص مطرز متناسق.",
      craftsmanshipFr = "Plissé main et finitions haute couture invisibles.",
      craftsmanshipAr = "كسرات وثنيات يدوية مع درزات خفية راقية.",
      fabricFr = "100% Soie sauvage champagne",
      fabricAr = "100% حرير طبيعي بلون الشمبانيا الهادئ",
      availableSizes = listOf("36", "38", "40", "42"),
      isFeatured = false
    )
  )

  fun getProductById(id: String): Product? = products.find { it.id == id }

  fun filterProducts(
    category: ProductCategory,
    query: String
  ): List<Product> {
    return products.filter { product ->
      val matchesCategory = category == ProductCategory.ALL || product.category == category
      val matchesQuery = query.isBlank() ||
        product.nameFr.contains(query, ignoreCase = true) ||
        product.nameAr.contains(query, ignoreCase = true) ||
        product.reference.contains(query, ignoreCase = true) ||
        product.subtitleFr.contains(query, ignoreCase = true) ||
        product.subtitleAr.contains(query, ignoreCase = true)
      matchesCategory && matchesQuery
    }
  }

  fun buildWhatsAppMessage(
    product: Product,
    selectedSize: String,
    lang: AppLanguage
  ): String {
    return if (lang == AppLanguage.ARABIC) {
      "السلام عليكم دار الجزائر،\n\nأود طلب قطعة: ${product.nameAr}\nالمرجع: ${product.reference}\nالمقاس المطلوب: $selectedSize\nالسعر: ${product.formattedPrice(lang)}\n\nيرجى تزويدي بتفاصيل التوصيل وحجز الطلب. شكراً لكم."
    } else {
      "Bonjour Maison El Djazair,\n\nJe souhaite commander la pièce suivante :\n- Modèle : ${product.nameFr}\n- Référence : ${product.reference}\n- Taille : $selectedSize\n- Prix : ${product.formattedPrice(lang)}\n\nPourriez-vous me renseigner sur les délais de confection et modalités de livraison ? Merci !"
    }
  }
}

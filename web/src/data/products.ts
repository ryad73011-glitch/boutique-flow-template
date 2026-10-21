import { Product } from '../types';
import { storeConfig } from './storeConfig';

export const WHATSAPP_PHONE = storeConfig.whatsappDisplay;
export const ATELIER_PHONE = storeConfig.atelierPhone;
export const ATELIER_ADDRESS_FR = storeConfig.addressFr;
export const ATELIER_ADDRESS_AR = storeConfig.addressAr;

export const PRODUCTS: Product[] = [
  {
    id: 'karakou-soltana',
    reference: 'MED-KK-01',
    category: 'karakou',
    nameFr: 'Karakou Royal Soltana',
    nameAr: 'كاراكو السلطانة الملكي',
    subtitleFr: 'Velours de soie émeraude & broderie Fetla or pur',
    subtitleAr: 'مخمل حريري بلون الزمرد وتطريز فتلة بماء الذهب',
    priceDzd: 145000,
    imageUrl: 'https://images.unsplash.com/photo-1566174053879-31528523f8ae?auto=format&fit=crop&w=900&q=80',
    descriptionFr: "Le chef-d'œuvre emblématique de la maison. Veste cintrée confectionnée dans un velours de soie impérial, ornée de motifs géométriques et floraux inspirés du patrimoine ottoman algérois. Chaque pièce requiert plus de 120 heures de broderie manuelle.",
    descriptionAr: "تحفة دار الجزائر الخالدة. سترة مخصّرة من المخمل الحريري الفاخر بلون الزمرد الملكي، مطرّزة يدويًا بخيوط الفتلة الذهبية وفق التقاليد العاصمية العريقة.",
    craftsmanshipFr: "Broderie Fetla & Medjboud fait-main, finitions boutons Dziriat or.",
    craftsmanshipAr: "تطريز يدوي أصيل بتقنية الفتلة والمجبود مع أزرار عاصمية مذهّبة.",
    fabricFr: "100% Velours de soie & doublure satinée",
    fabricAr: "100% مخمل حريري فاخر وبطانة ساتان ناعمة",
    sizes: ['36', '38', '40', '42', 'Sur-mesure'],
    isFeatured: true
  },
  {
    id: 'caftan-el-bahdja',
    reference: 'MED-CF-02',
    category: 'caftan',
    nameFr: "Caftan d'Or El Bahdja",
    nameAr: 'قفطان الذهب البهجة',
    subtitleFr: 'Satin duchesse ivoire & passementerie fil d'or',
    subtitleAr: 'ساتان دوشيس عاجي مع حزام وسفيفة مذهبة',
    priceDzd: 185000,
    imageUrl: 'https://images.unsplash.com/photo-1515372039744-b8f02a3ae446?auto=format&fit=crop&w=900&q=80',
    descriptionFr: "Une silhouette royale alliant l'opulence du satin duchesse ivoire à la finesse d'un travail d'orfèvrerie textile. Manches évasées impériales et ceinture Mdamma ciselée à la main.",
    descriptionAr: "إطلالة ملكية ساحرة تجمع بين فخامة الساتان الدوشيس العاجي ودقة التطريز التراثي. أكمام ملكية منسدلة مع حزام مضمة مصوغ بعناية فائقة.",
    craftsmanshipFr: "Sfifa artisanale tissée au fuseau, ceinture Mdamma orfèvre amovible.",
    craftsmanshipAr: "سفيفة يدوية منسوجة مع مضمة حلي تراثية قابلة للتعديل.",
    fabricFr: 'Satin Duchesse de soie lourde & lamé doré',
    fabricAr: 'ساتان دوشيس حريري ثقيل وخيوط لاميه ذهبية',
    sizes: ['38', '40', '42', '44', 'Sur-mesure'],
    isFeatured: true
  },
  {
    id: 'gandoura-casbah',
    reference: 'MED-GN-03',
    category: 'gandoura',
    nameFr: 'Gandoura Moderne Casbah',
    nameAr: 'قندورة القصبة العصرية',
    subtitleFr: 'Crêpe de soie bleu nuit & plastron or brossé',
    subtitleAr: 'كريب الحرير الأزرق الملكي مع صدرية مطرزة',
    priceDzd: 89000,
    imageUrl: 'https://images.unsplash.com/photo-1539109136881-3be0616acf4b?auto=format&fit=crop&w=900&q=80',
    descriptionFr: "Une interprétation épurée et contemporaine de la Gandoura traditionnelle. Silhouette fluide et tombé sculptural, rehaussée d'une encolure géométrique brodée au fil d'or mat.",
    descriptionAr: "قراءة عصرية متجددة للقندورة التقليدية الأصيلة. قصة انسيابية أنيقة من كريب الحرير الأزرق الداكن تبرز روعة التطريز الهندسي الذهبي.",
    craftsmanshipFr: 'Point de croix moderne et surpiqûres dorées discrètes.',
    craftsmanshipAr: 'تطريز هندسي دقيق مع فتحات جانبية عصرية لراحة استثنائية.',
    fabricFr: 'Crêpe de soie lourd & organza contrastant',
    fabricAr: 'كريب حريري ثقيل مع لمسات أورجانزا راقية',
    sizes: ['36', '38', '40', '42', '44'],
    isFeatured: true
  },
  {
    id: 'pochette-majdoub',
    reference: 'MED-AC-04',
    category: 'accessoires',
    nameFr: 'Minaudière Majdoub & Manchette',
    nameAr: 'حقيبة سهرة مجبود وسوار ذهبي',
    subtitleFr: 'Pochette velours noir brodée main & pompon de perles',
    subtitleAr: 'حقيبة يد مخملية سوداء بتطريز المجبود وسوار أندلسي',
    priceDzd: 42000,
    imageUrl: 'https://images.unsplash.com/photo-1584917865442-de89df76afd3?auto=format&fit=crop&w=900&q=80',
    descriptionFr: "Accessoire de haute parure conçu pour sublimer vos tenues de soirée. Réalisé en velours de soie noir profond, rehaussé d'une broderie d'arabesques au fil d'or.",
    descriptionAr: "قطعة فنية من الإكسسوارات الراقية تتوّج إطلالات السهرات الفخمة مصنوعة من المخمل الأسود ومطرّزة بحبال المجبود الذهبية.",
    craftsmanshipFr: 'Fait-main par nos maîtres artisans maroquiniers et brodeurs à Alger.',
    craftsmanshipAr: 'صناعة يدوية دقيقة في ورشتنا بالجزائر العاصمة.',
    fabricFr: 'Velours ras & monture laiton doré',
    fabricAr: 'مخمل فاخر مع هيكل نحاسي مطلي بالذهب',
    sizes: ['Taille Unique'],
    isFeatured: false
  }
];

export function formatPrice(price: number, lang: 'fr' | 'ar'): string {
  const formatted = price.toLocaleString('fr-FR');
  return lang === 'ar' ? `${formatted} د.ج` : `${formatted} DZD`;
}

export function buildWhatsAppLink(product: Product, size: string, lang: 'fr' | 'ar'): string {
  const message = lang === 'ar'
    ? `السلام عليكم ${storeConfig.nameAr}،\n\nأود طلب قطعة: ${product.nameAr}\nالمرجع: ${product.reference}\nالمقاس المطلوب: ${size}\nالسعر: ${formatPrice(product.priceDzd, lang)}\n\nيرجى تزويدي بتفاصيل التوصيل والحجز. شكراً.`
    : `Bonjour ${storeConfig.name},\n\nJe souhaite commander la pièce suivante :\n- Modèle : ${product.nameFr}\n- Référence : ${product.reference}\n- Taille : ${size}\n- Prix : ${formatPrice(product.priceDzd, lang)}\n\nMerci de m'indiquer les délais et modalités !`;

  return `https://wa.me/${storeConfig.whatsappPhone}?text=${encodeURIComponent(message)}`;
}

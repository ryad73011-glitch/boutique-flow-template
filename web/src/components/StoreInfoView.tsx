import React, { useState } from 'react';
import { Language } from '../types';

interface StoreInfoViewProps {
  language: Language;
}

type InfoSection = {
  titleFr: string;
  titleAr: string;
  bodyFr: string;
  bodyAr: string;
};

const sections: InfoSection[] = [
  {
    titleFr: 'Comment passer une commande ?',
    titleAr: 'كيف أطلب منتجًا؟',
    bodyFr: 'Ajoutez les pièces et tailles souhaitées au panier, puis renseignez vos coordonnées. Le site prépare un récapitulatif dans WhatsApp. Vérifiez les détails et appuyez vous-même sur Envoyer. Une demande n’est pas une commande confirmée tant que la boutique ne l’a pas acceptée.',
    bodyAr: 'أضف المنتجات والمقاسات إلى السلة ثم أدخل معلومات التواصل. سيُجهّز الموقع ملخص الطلب في واتساب. راجع التفاصيل واضغط بنفسك على إرسال. لا يُعتبر الطلب مؤكّدًا حتى توافق عليه المتجر.',
  },
  {
    titleFr: 'Quels sont les frais et délais de livraison ?',
    titleAr: 'ما هي تكاليف وآجال التوصيل؟',
    bodyFr: 'Les frais, délais, zones desservies et modalités de paiement doivent être confirmés directement avec la boutique avant toute validation. Aucun tarif de livraison n’est garanti par ce modèle de démonstration.',
    bodyAr: 'يجب تأكيد تكاليف التوصيل ومدته والمناطق المتاحة وطريقة الدفع مباشرةً مع المتجر قبل تأكيد الطلب. هذه النسخة التجريبية لا تضمن سعر توصيل محددًا.',
  },
  {
    titleFr: 'Puis-je retourner ou échanger un article ?',
    titleAr: 'هل يمكنني إرجاع المنتج أو استبداله؟',
    bodyFr: 'La politique de retour et d’échange dépend du commerçant et du type de produit. Demandez les conditions applicables, les délais et les éventuels frais avant de confirmer. Pour les articles sur mesure, obtenez une confirmation écrite des conditions convenues.',
    bodyAr: 'تعتمد سياسة الإرجاع والاستبدال على المتجر ونوع المنتج. اطلب الشروط والآجال وأي رسوم قبل التأكيد. بالنسبة للمنتجات المصممة حسب الطلب، احصل على تأكيد مكتوب للشروط المتفق عليها.',
  },
  {
    titleFr: 'Comment mes informations sont-elles utilisées ?',
    titleAr: 'كيف تُستخدم معلوماتي؟',
    bodyFr: 'Les informations saisies dans le panier restent dans le navigateur pendant la préparation du message. Elles sont transmises à WhatsApp uniquement lorsque vous ouvrez le lien et envoyez vous-même le message. Le modèle ne dispose pas de compte client ni de base de données serveur pour les commandes. WhatsApp applique ses propres conditions et sa politique de confidentialité.',
    bodyAr: 'تبقى المعلومات التي تدخلها في السلة داخل المتصفح أثناء إعداد الرسالة. تُنقل إلى واتساب عند فتح الرابط وإرسال الرسالة بنفسك. لا تحتوي هذه النسخة على حسابات عملاء أو قاعدة بيانات خادمية للطلبات. يخضع واتساب لشروطه وسياسة الخصوصية الخاصة به.',
  },
];

export const StoreInfoView: React.FC<StoreInfoViewProps> = ({ language }) => {
  const [openIndex, setOpenIndex] = useState<number | null>(0);
  const isArabic = language === 'ar';

  return (
    <section style={{ maxWidth: '900px', margin: '0 auto', padding: '56px 24px 88px', width: '100%' }}>
      <header style={{ textAlign: 'center', marginBottom: '36px' }}>
        <p style={{ color: 'var(--color-gold-accent)', fontSize: '12px', letterSpacing: '2px', marginBottom: '8px' }}>
          {isArabic ? 'معلومات المتجر' : 'INFORMATIONS BOUTIQUE'}
        </p>
        <h1 style={{ fontFamily: 'var(--font-serif)', fontSize: 'clamp(28px, 5vw, 40px)', marginBottom: '12px' }}>
          {isArabic ? 'الأسئلة الشائعة والسياسات' : 'FAQ & Informations pratiques'}
        </h1>
        <p style={{ color: '#aaa', maxWidth: '680px', margin: '0 auto', fontSize: '14px' }}>
          {isArabic
            ? 'معلومات أساسية قبل الطلب. يجب على صاحب المتجر مراجعة هذه النصوص وتخصيصها قبل نشر الموقع.'
            : 'Les informations essentielles avant commande. Le propriétaire doit relire et personnaliser ces textes avant la mise en ligne.'}
        </p>
      </header>

      <div style={{ display: 'grid', gap: '12px' }}>
        {sections.map((section, index) => {
          const expanded = openIndex === index;
          return (
            <article key={section.titleFr} style={{ border: '1px solid var(--color-charcoal-700)', borderRadius: '10px', background: 'var(--color-charcoal-800)', overflow: 'hidden' }}>
              <button
                type="button"
                aria-expanded={expanded}
                onClick={() => setOpenIndex(expanded ? null : index)}
                style={{ width: '100%', display: 'flex', alignItems: 'center', justifyContent: 'space-between', gap: '16px', padding: '18px 20px', border: 0, background: 'transparent', color: 'var(--color-warm-cream)', textAlign: isArabic ? 'right' : 'left', cursor: 'pointer', fontSize: '15px', fontWeight: 600 }}
              >
                <span>{isArabic ? section.titleAr : section.titleFr}</span>
                <span aria-hidden="true" style={{ color: 'var(--color-gold-accent)', fontSize: '20px' }}>{expanded ? '−' : '+'}</span>
              </button>
              {expanded && (
                <p style={{ padding: '0 20px 20px', color: '#c4c1bd', fontSize: '14px', lineHeight: 1.9, textAlign: isArabic ? 'right' : 'left' }}>
                  {isArabic ? section.bodyAr : section.bodyFr}
                </p>
              )}
            </article>
          );
        })}
      </div>

      <aside style={{ marginTop: '24px', padding: '18px 20px', border: '1px solid var(--color-gold-dark)', borderRadius: '10px', background: 'rgba(200, 169, 126, 0.07)', color: '#d7c4a9', fontSize: '13px', lineHeight: 1.8 }}>
        <strong style={{ display: 'block', color: 'var(--color-gold-accent)', marginBottom: '6px' }}>
          {isArabic ? 'ملاحظة لصاحب المتجر' : 'À personnaliser avant publication'}
        </strong>
        {isArabic
          ? 'هذه نصوص إرشادية وليست استشارة قانونية. أضف هوية البائع ووسائل التواصل الحقيقية وشروط التوصيل والدفع والإرجاع المناسبة لنشاطك، وراجع الالتزامات القانونية المحلية قبل النشر.'
          : 'Ces textes sont indicatifs et ne constituent pas un conseil juridique. Ajoutez l’identité du vendeur, ses coordonnées réelles, les règles de livraison, de paiement et de retour adaptées à son activité, puis vérifiez les obligations locales avant publication.'}
      </aside>
    </section>
  );
};

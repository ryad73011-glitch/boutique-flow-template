import React, { useState } from 'react';
import { Language, Product } from '../types';
import { formatPrice } from '../data/products';
import { storeConfig } from '../data/storeConfig';

export interface CartItem {
  product: Product;
  size: string;
  quantity: number;
}

interface CartDrawerProps {
  items: CartItem[];
  language: Language;
  onClose: () => void;
  onRemove: (productId: string, size: string) => void;
  onClear: () => void;
  onChangeQuantity: (productId: string, size: string, quantity: number) => void;
}

export const CartDrawer: React.FC<CartDrawerProps> = ({
  items, language, onClose, onRemove, onClear, onChangeQuantity
}) => {
  const isArabic = language === 'ar';
  const [customerName, setCustomerName] = useState('');
  const [customerPhone, setCustomerPhone] = useState('');
  const [wilaya, setWilaya] = useState('');
  const [deliveryAddress, setDeliveryAddress] = useState('');
  const total = items.reduce((sum, item) => sum + item.product.priceDzd * item.quantity, 0);
  const message = isArabic
    ? [
        `السلام عليكم ${storeConfig.nameAr}، أريد الاستفسار عن هذا الطلب:`,
        '',
        ...items.map((item) => `• ${item.product.nameAr} — المقاس ${item.size} × ${item.quantity} — ${formatPrice(item.product.priceDzd * item.quantity, language)}`),
        '',
        `المجموع التقديري: ${formatPrice(total, language)}`,
        '',
        `الاسم: ${customerName}`,
        `الهاتف: ${customerPhone}`,
        `الولاية: ${wilaya}`,
        `عنوان التوصيل: ${deliveryAddress}`,
        'يرجى تأكيد التوفر وتكاليف ومدة التوصيل. هذا طلب استفسار وليس دفعًا إلكترونيًا.'
      ].join('\n')
    : [
        `Bonjour ${storeConfig.name}, je souhaite confirmer cette demande de commande :`,
        '',
        ...items.map((item) => `• ${item.product.nameFr} — taille ${item.size} × ${item.quantity} — ${formatPrice(item.product.priceDzd * item.quantity, language)}`),
        '',
        `Total estimatif : ${formatPrice(total, language)}`,
        '',
        `Client : ${customerName}`,
        `Téléphone : ${customerPhone}`,
        `Wilaya : ${wilaya}`,
        `Adresse de livraison : ${deliveryAddress}`,
        'Merci de confirmer la disponibilité, les frais et le délai de livraison. Ceci est une demande, pas un paiement en ligne.'
      ].join('\n');
  const checkoutUrl = `https://wa.me/${storeConfig.whatsappPhone}?text=${encodeURIComponent(message)}`;

  return (
    <div role="presentation" onClick={onClose} style={{ position: 'fixed', inset: 0, zIndex: 150, background: 'rgba(0,0,0,.72)', display: 'flex', justifyContent: isArabic ? 'flex-start' : 'flex-end' }}>
      <aside role="dialog" aria-modal="true" aria-label={isArabic ? 'سلة المشتريات' : 'Panier'} dir={isArabic ? 'rtl' : 'ltr'} onClick={(event) => event.stopPropagation()} style={{ width: 'min(100%, 440px)', height: '100%', overflowY: 'auto', background: 'var(--color-charcoal-900)', borderInlineStart: '1px solid var(--color-charcoal-600)', padding: '24px', display: 'flex', flexDirection: 'column', gap: '18px' }}>
        <header style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', gap: '12px' }}>
          <h2 style={{ fontFamily: 'var(--font-serif)', fontSize: '24px' }}>{isArabic ? 'سلة المشتريات' : 'Votre panier'}</h2>
          <button type="button" onClick={onClose} aria-label={isArabic ? 'إغلاق السلة' : 'Fermer le panier'} style={{ background: 'transparent', border: '1px solid var(--color-charcoal-600)', color: 'var(--color-warm-cream)', borderRadius: '6px', padding: '8px 12px', cursor: 'pointer' }}>✕</button>
        </header>

        {items.length > 0 && (
          <button type="button" onClick={onClear} style={{ alignSelf: 'flex-start', background: 'transparent', border: '1px solid var(--color-charcoal-600)', borderRadius: '5px', color: '#e6a2a2', padding: '7px 10px', fontSize: '12px', cursor: 'pointer' }}>
            {isArabic ? 'إفراغ السلة' : 'Vider le panier'}
          </button>
        )}

        {items.length === 0 ? (
          <div style={{ padding: '36px 12px', textAlign: 'center', color: '#aaa' }}>
            <p style={{ fontSize: '16px', marginBottom: '8px' }}>{isArabic ? 'السلة فارغة حاليًا' : 'Votre panier est vide'}</p>
            <p style={{ fontSize: '13px' }}>{isArabic ? 'افتح تفاصيل أي منتج لإضافته إلى السلة.' : 'Ouvrez un produit pour l’ajouter au panier.'}</p>
          </div>
        ) : (
          <>
            <div style={{ display: 'flex', flexDirection: 'column', gap: '14px' }}>
              {items.map((item) => (
                <article key={item.product.id + item.size} style={{ display: 'grid', gridTemplateColumns: '76px 1fr', gap: '12px', paddingBottom: '14px', borderBottom: '1px solid var(--color-charcoal-700)' }}>
                  <img src={item.product.imageUrl} alt={isArabic ? item.product.nameAr : item.product.nameFr} style={{ width: '76px', height: '96px', objectFit: 'cover', borderRadius: '4px' }} />
                  <div style={{ minWidth: 0 }}>
                    <strong style={{ display: 'block', fontSize: '14px' }}>{isArabic ? item.product.nameAr : item.product.nameFr}</strong>
                    <p style={{ fontSize: '12px', color: '#aaa', margin: '4px 0 8px' }}>{isArabic ? 'المقاس' : 'Taille'}: {item.size}</p>
                    <strong style={{ color: 'var(--color-gold-accent)', fontSize: '13px' }}>{formatPrice(item.product.priceDzd * item.quantity, language)}</strong>
                    <div style={{ display: 'flex', alignItems: 'center', gap: '8px', marginTop: '8px' }}>
                      <button type="button" aria-label={isArabic ? 'تقليل الكمية' : 'Diminuer la quantité'} onClick={() => onChangeQuantity(item.product.id, item.size, item.quantity - 1)} style={quantityButtonStyle}>−</button>
                      <span aria-live="polite">{item.quantity}</span>
                      <button type="button" aria-label={isArabic ? 'زيادة الكمية' : 'Augmenter la quantité'} disabled={item.quantity >= 99} onClick={() => onChangeQuantity(item.product.id, item.size, item.quantity + 1)} style={{ ...quantityButtonStyle, opacity: item.quantity >= 99 ? 0.45 : 1, cursor: item.quantity >= 99 ? 'not-allowed' : 'pointer' }}>+</button>
                      <button type="button" onClick={() => onRemove(item.product.id, item.size)} style={{ marginInlineStart: 'auto', border: 'none', background: 'transparent', color: '#e6a2a2', textDecoration: 'underline', cursor: 'pointer', fontSize: '12px' }}>{isArabic ? 'حذف' : 'Retirer'}</button>
                    </div>
                  </div>
                </article>
              ))}
            </div>
            <div style={{ marginTop: 'auto', paddingTop: '16px', borderTop: '1px solid var(--color-charcoal-700)' }}>
              <div style={{ display: 'flex', justifyContent: 'space-between', gap: '12px', fontSize: '16px', fontWeight: 700, marginBottom: '8px' }}>
                <span>{isArabic ? 'المجموع التقديري' : 'Total estimatif'}</span>
                <span style={{ color: 'var(--color-gold-accent)' }}>{formatPrice(total, language)}</span>
              </div>
              <p style={{ color: '#aaa', fontSize: '12px', marginBottom: '14px' }}>{isArabic ? 'لا يوجد دفع إلكتروني. سيفتح WhatsApp برسالة جاهزة، ويجب عليك الضغط على إرسال بعد تأكيد التفاصيل.' : 'Pas de paiement en ligne. WhatsApp s’ouvrira avec un message à vérifier puis à envoyer.'}</p>
              {storeConfig.whatsappConfigured ? (
                <form onSubmit={(event) => { event.preventDefault(); window.open(checkoutUrl, '_blank', 'noopener,noreferrer'); onClose(); }} style={{ display: 'flex', flexDirection: 'column', gap: '10px', marginTop: '16px' }}>
                  <h3 style={{ fontSize: '15px', fontWeight: 700 }}>{isArabic ? 'معلومات التوصيل' : 'Informations de livraison'}</h3>
                  <label style={fieldLabelStyle}>{isArabic ? 'الاسم الكامل' : 'Nom complet'}
                    <input required maxLength={100} autoComplete="name" value={customerName} onChange={(event) => setCustomerName(event.target.value)} style={fieldInputStyle} />
                  </label>
                  <label style={fieldLabelStyle}>{isArabic ? 'رقم الهاتف' : 'Téléphone'}
                    <input required type="tel" maxLength={30} autoComplete="tel" value={customerPhone} onChange={(event) => setCustomerPhone(event.target.value)} style={fieldInputStyle} />
                  </label>
                  <label style={fieldLabelStyle}>{isArabic ? 'الولاية' : 'Wilaya'}
                    <input required maxLength={80} autoComplete="address-level1" value={wilaya} onChange={(event) => setWilaya(event.target.value)} style={fieldInputStyle} />
                  </label>
                  <label style={fieldLabelStyle}>{isArabic ? 'عنوان التوصيل' : 'Adresse de livraison'}
                    <textarea required maxLength={300} autoComplete="street-address" value={deliveryAddress} onChange={(event) => setDeliveryAddress(event.target.value)} rows={2} style={{ ...fieldInputStyle, resize: 'vertical' }} />
                  </label>
                  <button type="submit" style={{ display: 'block', textAlign: 'center', padding: '14px', border: 0, borderRadius: '6px', background: 'var(--color-gold-accent)', color: 'var(--color-charcoal-900)', fontWeight: 700, cursor: 'pointer' }}>{isArabic ? 'متابعة الطلب عبر WhatsApp' : 'Continuer sur WhatsApp'}</button>
                </form>
              ) : (
                <p role="status" style={{ textAlign: 'center', padding: '14px', borderRadius: '6px', border: '1px solid var(--color-charcoal-600)', color: '#e6cba3', fontSize: '13px' }}>{isArabic ? 'هذه نسخة تجريبية. يجب على صاحب المتجر إعداد رقم WhatsApp الحقيقي قبل تفعيل الطلبات.' : 'Version de démonstration : le propriétaire doit configurer un vrai numéro WhatsApp avant d’activer les commandes.'}</p>
              )}
            </div>
          </>
        )}
      </aside>
    </div>
  );
};

const quantityButtonStyle: React.CSSProperties = {
  width: '28px', height: '28px', borderRadius: '4px', border: '1px solid var(--color-charcoal-600)',
  background: 'var(--color-charcoal-800)', color: 'var(--color-warm-cream)', cursor: 'pointer'
};


const fieldLabelStyle: React.CSSProperties = { display: 'flex', flexDirection: 'column', gap: '6px', fontSize: '12px', color: '#d0c9be' };
const fieldInputStyle: React.CSSProperties = { width: '100%', boxSizing: 'border-box', padding: '10px 11px', borderRadius: '5px', border: '1px solid var(--color-charcoal-600)', background: 'var(--color-charcoal-800)', color: 'var(--color-warm-cream)', fontSize: '14px' };

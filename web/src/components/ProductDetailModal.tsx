import React, { useEffect, useState } from 'react';
import { Product, Language } from '../types';
import { formatPrice, buildWhatsAppLink, WHATSAPP_PHONE } from '../data/products';
import { translations } from '../translations';
import { storeConfig } from '../data/storeConfig';

interface ProductDetailModalProps {
  product: Product;
  language: Language;
  onClose: () => void;
  onAddToCart: (product: Product, size: string) => void;
}

export const ProductDetailModal: React.FC<ProductDetailModalProps> = ({
  product,
  language,
  onClose,
  onAddToCart
}) => {
  const [selectedSize, setSelectedSize] = useState<string>(product.sizes[0] || 'Standard');
  const t = translations[language];

  useEffect(() => {
    const previousOverflow = document.body.style.overflow;
    document.body.style.overflow = 'hidden';
    const handleKeyDown = (event: KeyboardEvent) => {
      if (event.key === 'Escape') onClose();
    };
    window.addEventListener('keydown', handleKeyDown);
    return () => {
      document.body.style.overflow = previousOverflow;
      window.removeEventListener('keydown', handleKeyDown);
    };
  }, [onClose]);

  const whatsappUrl = buildWhatsAppLink(product, selectedSize, language);

  return (
    <div
      role="dialog"
      aria-modal="true"
      aria-labelledby="product-dialog-title"
      dir={language === 'ar' ? 'rtl' : 'ltr'}
      style={{
      position: 'fixed',
      top: 0,
      left: 0,
      right: 0,
      bottom: 0,
      backgroundColor: 'rgba(10, 10, 12, 0.85)',
      backdropFilter: 'blur(6px)',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      zIndex: 100,
      padding: '16px'
    }}>
      <div style={{
        backgroundColor: 'var(--color-charcoal-800)',
        border: '1px solid var(--color-charcoal-600)',
        borderRadius: '8px',
        maxWidth: '850px',
        width: '100%',
        maxHeight: '90vh',
        overflowY: 'auto',
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))',
        position: 'relative'
      }}>
        {/* Close Button */}
        <button
          onClick={onClose}
          style={{
            position: 'absolute',
            top: '12px',
            right: language === 'ar' ? 'auto' : '12px',
            left: language === 'ar' ? '12px' : 'auto',
            background: 'rgba(16, 16, 18, 0.7)',
            border: 'none',
            color: '#fff',
            borderRadius: '50%',
            width: '36px',
            height: '36px',
            fontSize: '18px',
            cursor: 'pointer',
            zIndex: 10
          }}
          aria-label={t.close}
        >
          ✕
        </button>

        {/* Image */}
        <div style={{ minHeight: '350px', position: 'relative' }}>
          <img
            src={product.imageUrl}
            alt={language === 'ar' ? product.nameAr : product.nameFr}
            style={{ width: '100%', height: '100%', objectFit: 'cover' }}
          />
        </div>

        {/* Content */}
        <div style={{ padding: '28px', display: 'flex', flexDirection: 'column', gap: '16px' }}>
          <div>
            <span style={{
              fontSize: '11px',
              color: 'var(--color-gold-accent)',
              letterSpacing: '1px',
              fontWeight: 600
            }}>
              REF: {product.reference}
            </span>
            <h2 id="product-dialog-title" style={{
              fontFamily: 'var(--font-serif)',
              fontSize: '22px',
              fontWeight: 'bold',
              color: 'var(--color-warm-cream)',
              marginTop: '4px'
            }}>
              {language === 'ar' ? product.nameAr : product.nameFr}
            </h2>
            <p style={{ fontSize: '13px', color: '#a09fa6', marginTop: '2px' }}>
              {language === 'ar' ? product.subtitleAr : product.subtitleFr}
            </p>
          </div>

          {/* Price */}
          <div style={{
            padding: '12px 16px',
            backgroundColor: 'var(--color-charcoal-700)',
            borderRadius: '6px',
            display: 'flex',
            justifyContent: 'space-between',
            alignItems: 'center'
          }}>
            <span style={{ fontSize: '20px', fontWeight: 'bold', color: 'var(--color-gold-accent)' }}>
              {formatPrice(product.priceDzd, language)}
            </span>
            <span style={{ fontSize: '12px', color: '#a09fa6' }}>
              {t.inStock}
            </span>
          </div>

          {/* Size picker */}
          <div>
            <label style={{ fontSize: '12px', fontWeight: 600, display: 'block', marginBottom: '8px' }}>
              {t.selectSize}
            </label>
            <div style={{ display: 'flex', flexWrap: 'wrap', gap: '8px' }}>
              {product.sizes.map((size) => (
                <button
                  key={size}
                  onClick={() => setSelectedSize(size)}
                  style={{
                    padding: '8px 14px',
                    borderRadius: '4px',
                    border: `1px solid ${selectedSize === size ? 'var(--color-gold-accent)' : 'var(--color-charcoal-600)'}`,
                    backgroundColor: selectedSize === size ? 'rgba(200, 169, 126, 0.15)' : 'var(--color-charcoal-900)',
                    color: selectedSize === size ? 'var(--color-gold-accent)' : 'var(--color-warm-cream)',
                    fontSize: '12px',
                    fontWeight: selectedSize === size ? 600 : 400,
                    cursor: 'pointer'
                  }}
                >
                  {size}
                </button>
              ))}
            </div>
          </div>

          {/* Description */}
          <p style={{ fontSize: '13px', color: '#ccc', lineHeight: 1.6 }}>
            {language === 'ar' ? product.descriptionAr : product.descriptionFr}
          </p>

          {/* Craftsmanship & fabric */}
          <div style={{
            fontSize: '12px',
            backgroundColor: 'var(--color-charcoal-900)',
            padding: '12px',
            borderRadius: '6px',
            border: '1px solid var(--color-charcoal-700)'
          }}>
            <p style={{ marginBottom: '6px' }}>
              <strong style={{ color: 'var(--color-gold-accent)' }}>{t.craftsmanship}: </strong>
              {language === 'ar' ? product.craftsmanshipAr : product.craftsmanshipFr}
            </p>
            <p>
              <strong style={{ color: 'var(--color-gold-accent)' }}>{t.materials}: </strong>
              {language === 'ar' ? product.fabricAr : product.fabricFr}
            </p>
          </div>

          <button
            type="button"
            onClick={() => onAddToCart(product, selectedSize)}
            style={{
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              gap: '8px',
              backgroundColor: 'transparent',
              color: 'var(--color-gold-accent)',
              border: '1px solid var(--color-gold-accent)',
              padding: '13px',
              borderRadius: '6px',
              fontWeight: 'bold',
              fontSize: '14px',
              cursor: 'pointer'
            }}
          >
            {language === 'ar' ? 'إضافة إلى السلة' : 'Ajouter au panier'}
          </button>

          {/* WhatsApp Action */}
          {storeConfig.whatsappConfigured ? (
          <a
            href={whatsappUrl}
            target="_blank"
            rel="noopener noreferrer"
            style={{
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              gap: '8px',
              backgroundColor: 'var(--color-gold-accent)',
              color: 'var(--color-charcoal-900)',
              padding: '14px',
              borderRadius: '6px',
              textDecoration: 'none',
              fontWeight: 'bold',
              fontSize: '14px',
              marginTop: 'auto'
            }}
          >
            <span>💬 {t.orderWhatsApp}</span>
          </a>
          ) : (
            <p role="status" style={{ fontSize: '12px', textAlign: 'center', color: '#e6cba3' }}>{language === 'ar' ? 'زر الطلب سيتفعّل بعد إعداد رقم المتجر الحقيقي.' : 'La commande sera activée après configuration du vrai numéro WhatsApp du magasin.'}</p>
          )}
          <span style={{ fontSize: '11px', textAlign: 'center', color: '#888' }}>
            {t.orderWhatsAppSubtitle} ({WHATSAPP_PHONE})
          </span>
        </div>
      </div>
    </div>
  );
};

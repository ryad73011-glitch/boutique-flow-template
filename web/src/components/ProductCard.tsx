import React from 'react';
import { Product, Language } from '../types';
import { formatPrice } from '../data/products';

interface ProductCardProps {
  product: Product;
  language: Language;
  onSelect: (product: Product) => void;
}

export const ProductCard: React.FC<ProductCardProps> = ({ product, language, onSelect }) => {
  return (
    <div
      role="button"
      tabIndex={0}
      aria-label={language === 'ar' ? `عرض تفاصيل ${product.nameAr}` : `Voir les détails de ${product.nameFr}`}
      onClick={() => onSelect(product)}
      onKeyDown={(event) => {
        if (event.key === 'Enter' || event.key === ' ') {
          event.preventDefault();
          onSelect(product);
        }
      }}
      style={{
        backgroundColor: 'var(--color-charcoal-800)',
        border: '1px solid var(--color-charcoal-700)',
        borderRadius: '6px',
        overflow: 'hidden',
        cursor: 'pointer',
        textAlign: language === 'ar' ? 'right' : 'left',
        color: 'inherit',
        display: 'flex',
        flexDirection: 'column',
        transition: 'transform 0.2s, border-color 0.2s'
      }}
      className="product-card"
    >
      <div style={{ position: 'relative', width: '100%', paddingTop: '125%', overflow: 'hidden' }}>
        <img
          src={product.imageUrl}
          alt={language === 'ar' ? product.nameAr : product.nameFr}
          style={{
            position: 'absolute',
            top: 0,
            left: 0,
            width: '100%',
            height: '100%',
            objectFit: 'cover',
            transition: 'transform 0.4s'
          }}
          loading="lazy"
        />
        <span style={{
          position: 'absolute',
          top: '12px',
          left: language === 'ar' ? 'auto' : '12px',
          right: language === 'ar' ? '12px' : 'auto',
          backgroundColor: 'rgba(16, 16, 18, 0.85)',
          color: 'var(--color-gold-accent)',
          fontSize: '11px',
          fontWeight: 600,
          padding: '4px 8px',
          borderRadius: '4px',
          letterSpacing: '0.5px'
        }}>
          {product.reference}
        </span>
      </div>

      <div style={{ padding: '16px', display: 'flex', flexDirection: 'column', flex: 1 }}>
        <h4 style={{
          fontFamily: 'var(--font-serif)',
          fontSize: '16px',
          fontWeight: 600,
          marginBottom: '4px',
          color: 'var(--color-warm-cream)'
        }}>
          {language === 'ar' ? product.nameAr : product.nameFr}
        </h4>
        <p style={{
          fontSize: '12px',
          color: '#a09fa6',
          marginBottom: '14px',
          overflow: 'hidden',
          textOverflow: 'ellipsis',
          whiteSpace: 'nowrap'
        }}>
          {language === 'ar' ? product.subtitleAr : product.subtitleFr}
        </p>
        <div style={{
          marginTop: 'auto',
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center'
        }}>
          <span style={{
            fontSize: '16px',
            fontWeight: 'bold',
            color: 'var(--color-gold-accent)'
          }}>
            {formatPrice(product.priceDzd, language)}
          </span>
          <span style={{
            fontSize: '11px',
            color: 'var(--color-gold-light)',
            fontWeight: 500
          }}>
            {language === 'ar' ? 'تفاصيل ←' : 'Détails →'}
          </span>
        </div>
      </div>

      <style>{`
        .product-card:hover, .product-card:focus-visible {
          transform: translateY(-4px);
          border-color: var(--color-gold-accent);
          outline: 2px solid var(--color-gold-accent);
          outline-offset: 3px;
        }
      `}</style>
    </div>
  );
};

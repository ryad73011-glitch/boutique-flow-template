import React, { useState, useMemo } from 'react';
import { Product, Language, Category } from '../types';
import { PRODUCTS } from '../data/products';
import { translations } from '../translations';
import { ProductCard } from './ProductCard';

interface CatalogViewProps {
  language: Language;
  onSelectProduct: (product: Product) => void;
}

export const CatalogView: React.FC<CatalogViewProps> = ({ language, onSelectProduct }) => {
  const [selectedCategory, setSelectedCategory] = useState<Category>('all');
  const [searchQuery, setSearchQuery] = useState('');
  const t = translations[language];

  const categories: { id: Category; label: string }[] = [
    { id: 'all', label: t.filterAll },
    { id: 'karakou', label: t.filterKarakou },
    { id: 'caftan', label: t.filterCaftan },
    { id: 'gandoura', label: t.filterGandoura },
    { id: 'accessoires', label: t.filterAccessoires }
  ];

  const filteredProducts = useMemo(() => {
    return PRODUCTS.filter((product) => {
      const matchCat = selectedCategory === 'all' || product.category === selectedCategory;
      const q = searchQuery.toLowerCase().trim();
      const matchQuery = !q ||
        product.nameFr.toLowerCase().includes(q) ||
        product.nameAr.includes(q) ||
        product.reference.toLowerCase().includes(q) ||
        product.subtitleFr.toLowerCase().includes(q) ||
        product.subtitleAr.includes(q);
      return matchCat && matchQuery;
    });
  }, [selectedCategory, searchQuery]);

  return (
    <section style={{ maxWidth: '1200px', margin: '0 auto', padding: '40px 24px 80px' }}>
      {/* Title */}
      <div style={{ textAlign: 'center', marginBottom: '32px' }}>
        <h2 style={{
          fontFamily: 'var(--font-serif)',
          fontSize: '32px',
          fontWeight: 'bold',
          marginBottom: '8px',
          color: 'var(--color-warm-cream)'
        }}>
          {t.navCatalog}
        </h2>
        <p style={{ color: 'var(--color-gold-accent)', fontSize: '14px' }}>
          {language === 'ar' ? 'تشكيلة حصرية من أزياء الهوت كوتور العاصمية' : 'Collections Haute Couture & Prêt-à-Porter'}
        </p>
      </div>

      {/* Search Input */}
      <div style={{ maxWidth: '600px', margin: '0 auto 24px' }}>
        <input
          type="text"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          placeholder={t.searchPlaceholder}
          style={{
            width: '100%',
            padding: '14px 18px',
            backgroundColor: 'var(--color-charcoal-800)',
            border: '1px solid var(--color-charcoal-600)',
            borderRadius: '8px',
            color: 'var(--color-warm-cream)',
            fontSize: '14px',
            outline: 'none'
          }}
        />
      </div>

      {/* Category Pills */}
      <div style={{
        display: 'flex',
        gap: '8px',
        flexWrap: 'wrap',
        justifyContent: 'center',
        marginBottom: '32px'
      }}>
        {categories.map((cat) => (
          <button
            key={cat.id}
            onClick={() => setSelectedCategory(cat.id)}
            style={{
              padding: '8px 16px',
              borderRadius: '20px',
              border: `1px solid ${selectedCategory === cat.id ? 'var(--color-gold-accent)' : 'var(--color-charcoal-700)'}`,
              backgroundColor: selectedCategory === cat.id ? 'var(--color-gold-accent)' : 'var(--color-charcoal-800)',
              color: selectedCategory === cat.id ? 'var(--color-charcoal-900)' : 'var(--color-warm-cream)',
              fontSize: '12px',
              fontWeight: selectedCategory === cat.id ? 700 : 500,
              cursor: 'pointer',
              transition: 'all 0.2s'
            }}
          >
            {cat.label}
          </button>
        ))}
      </div>

      {/* Grid or Empty */}
      {filteredProducts.length === 0 ? (
        <div style={{
          textAlign: 'center',
          padding: '60px 20px',
          backgroundColor: 'var(--color-charcoal-800)',
          borderRadius: '8px',
          maxWidth: '500px',
          margin: '0 auto'
        }}>
          <h3 style={{ fontFamily: 'var(--font-serif)', fontSize: '20px', marginBottom: '8px' }}>
            {t.noProductsFound}
          </h3>
          <p style={{ color: '#888', fontSize: '13px', marginBottom: '20px' }}>
            {t.noProductsDesc}
          </p>
          <button
            onClick={() => {
              setSelectedCategory('all');
              setSearchQuery('');
            }}
            style={{
              backgroundColor: 'var(--color-gold-accent)',
              color: 'var(--color-charcoal-900)',
              border: 'none',
              padding: '10px 20px',
              borderRadius: '4px',
              fontWeight: 'bold',
              cursor: 'pointer'
            }}
          >
            {t.resetFilters}
          </button>
        </div>
      ) : (
        <div style={{
          display: 'grid',
          gridTemplateColumns: 'repeat(auto-fill, minmax(260px, 1fr))',
          gap: '24px'
        }}>
          {filteredProducts.map((p) => (
            <ProductCard
              key={p.id}
              product={p}
              language={language}
              onSelect={onSelectProduct}
            />
          ))}
        </div>
      )}
    </section>
  );
};

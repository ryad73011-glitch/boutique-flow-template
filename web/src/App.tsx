import React, { useState } from 'react';
import { Page, Language, Product } from './types';
import { Navbar } from './components/Navbar';
import { Footer } from './components/Footer';
import { Hero } from './components/Hero';
import { CatalogView } from './components/CatalogView';
import { AboutView } from './components/AboutView';
import { ContactView } from './components/ContactView';
import { ProductCard } from './components/ProductCard';
import { ProductDetailModal } from './components/ProductDetailModal';
import { PRODUCTS } from './data/products';
import { translations } from './translations';

export const App: React.FC = () => {
  const [currentPage, setCurrentPage] = useState<Page>('home');
  const [language, setLanguage] = useState<Language>('fr');
  const [selectedProduct, setSelectedProduct] = useState<Product | null>(null);

  const t = translations[language];
  const featuredProducts = PRODUCTS.filter((p) => p.isFeatured);

  const toggleLanguage = () => {
    const nextLang = language === 'fr' ? 'ar' : 'fr';
    setLanguage(nextLang);
    document.documentElement.dir = nextLang === 'ar' ? 'rtl' : 'ltr';
    document.documentElement.lang = nextLang;
  };

  return (
    <div
      dir={language === 'ar' ? 'rtl' : 'ltr'}
      style={{
        minHeight: '100vh',
        display: 'flex',
        flexDirection: 'column',
        backgroundColor: 'var(--color-charcoal-900)',
        color: 'var(--color-warm-cream)'
      }}
    >
      <Navbar
        currentPage={currentPage}
        language={language}
        onNavigate={setCurrentPage}
        onToggleLanguage={toggleLanguage}
      />

      <main style={{ flex: 1 }}>
        {currentPage === 'home' && (
          <>
            <Hero language={language} onNavigate={setCurrentPage} />

            {/* Featured Section */}
            <section style={{ maxWidth: '1200px', margin: '0 auto', padding: '60px 24px' }}>
              <div style={{
                display: 'flex',
                justifyContent: 'space-between',
                alignItems: 'flex-end',
                marginBottom: '32px',
                flexWrap: 'wrap',
                gap: '12px'
              }}>
                <div>
                  <h2 style={{
                    fontFamily: 'var(--font-serif)',
                    fontSize: '28px',
                    fontWeight: 'bold',
                    color: 'var(--color-warm-cream)'
                  }}>
                    {t.featuredTitle}
                  </h2>
                  <p style={{ color: 'var(--color-gold-accent)', fontSize: '13px' }}>
                    {t.featuredSubtitle}
                  </p>
                </div>

                <button
                  onClick={() => setCurrentPage('catalog')}
                  style={{
                    background: 'none',
                    border: 'none',
                    color: 'var(--color-gold-accent)',
                    fontSize: '13px',
                    fontWeight: 600,
                    cursor: 'pointer'
                  }}
                >
                  {t.viewAll} →
                </button>
              </div>

              <div style={{
                display: 'grid',
                gridTemplateColumns: 'repeat(auto-fit, minmax(260px, 1fr))',
                gap: '24px'
              }}>
                {featuredProducts.map((p) => (
                  <ProductCard
                    key={p.id}
                    product={p}
                    language={language}
                    onSelect={setSelectedProduct}
                  />
                ))}
              </div>
            </section>
          </>
        )}

        {currentPage === 'catalog' && (
          <CatalogView
            language={language}
            onSelectProduct={setSelectedProduct}
          />
        )}

        {currentPage === 'about' && (
          <AboutView
            language={language}
            onNavigate={setCurrentPage}
          />
        )}

        {currentPage === 'contact' && (
          <ContactView
            language={language}
          />
        )}
      </main>

      {/* Product Detail Modal */}
      {selectedProduct && (
        <ProductDetailModal
          product={selectedProduct}
          language={language}
          onClose={() => setSelectedProduct(null)}
        />
      )}

      <Footer language={language} onNavigate={setCurrentPage} />
    </div>
  );
};

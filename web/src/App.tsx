import React, { useEffect, useState } from 'react';
import { Page, Language, Product } from './types';
import { Navbar } from './components/Navbar';
import { Footer } from './components/Footer';
import { Hero } from './components/Hero';
import { CatalogView } from './components/CatalogView';
import { AboutView } from './components/AboutView';
import { ContactView } from './components/ContactView';
import { StoreInfoView } from './components/StoreInfoView';
import { ProductCard } from './components/ProductCard';
import { ProductDetailModal } from './components/ProductDetailModal';
import { CartDrawer, CartItem } from './components/CartDrawer';
import { PRODUCTS } from './data/products';
import { translations } from './translations';

export const App: React.FC = () => {
  const [currentPage, setCurrentPage] = useState<Page>('home');
  const [language, setLanguage] = useState<Language>('fr');
  const [selectedProduct, setSelectedProduct] = useState<Product | null>(null);
  const [cartItems, setCartItems] = useState<CartItem[]>(() => {
    try {
      const saved = localStorage.getItem('boutique-flow-cart-v1');
      if (!saved) return [];
      const parsed: unknown = JSON.parse(saved);
      if (!Array.isArray(parsed)) return [];

      // Treat browser storage as untrusted. Rebuild each item from the current catalog
      // so edited/stale localStorage cannot change displayed product prices or details.
      return parsed.flatMap((entry): CartItem[] => {
        if (!entry || typeof entry !== 'object') return [];
        const candidate = entry as { product?: { id?: unknown }; size?: unknown; quantity?: unknown };
        const product = PRODUCTS.find((item) => item.id === candidate.product?.id);
        const size = typeof candidate.size === 'string' ? candidate.size : '';
        const quantity = Number(candidate.quantity);
        if (!product || !product.sizes.includes(size) || !Number.isInteger(quantity) || quantity < 1 || quantity > 99) return [];
        return [{ product, size, quantity }];
      });
    } catch {
      return [];
    }
  });
  const [cartOpen, setCartOpen] = useState(false);

  useEffect(() => {
    try {
      localStorage.setItem('boutique-flow-cart-v1', JSON.stringify(cartItems));
    } catch {
      // The cart remains usable for this session if browser storage is unavailable.
    }
  }, [cartItems]);

  useEffect(() => {
    const isArabic = language === 'ar';
    document.documentElement.lang = language;
    document.documentElement.dir = isArabic ? 'rtl' : 'ltr';
    document.title = `${isArabic ? 'متجر' : 'Boutique'} — ${isArabic ? translations.ar.brandName : translations.fr.brandName}`;

    const description = isArabic
      ? `${translations.ar.brandName} — ${translations.ar.brandTagline}. اكتشف التشكيلة وتواصل مع المتجر لطلب المنتجات.`
      : `${translations.fr.brandName} — ${translations.fr.brandTagline}. Découvrez la collection et contactez la boutique pour vos demandes.`;
    let descriptionTag = document.querySelector<HTMLMetaElement>('meta[name="description"]');
    if (!descriptionTag) {
      descriptionTag = document.createElement('meta');
      descriptionTag.name = 'description';
      document.head.appendChild(descriptionTag);
    }
    descriptionTag.content = description;

    let ogTitle = document.querySelector<HTMLMetaElement>('meta[property="og:title"]');
    if (!ogTitle) {
      ogTitle = document.createElement('meta');
      ogTitle.setAttribute('property', 'og:title');
      document.head.appendChild(ogTitle);
    }
    ogTitle.content = document.title;

    let ogDescription = document.querySelector<HTMLMetaElement>('meta[property="og:description"]');
    if (!ogDescription) {
      ogDescription = document.createElement('meta');
      ogDescription.setAttribute('property', 'og:description');
      document.head.appendChild(ogDescription);
    }
    ogDescription.content = description;
  }, [language]);

  const addToCart = (product: Product, size: string) => {
    setCartItems((items) => {
      const existing = items.find((item) => item.product.id === product.id && item.size === size);
      if (existing) {
        return items.map((item) => item.product.id === product.id && item.size === size
          ? { ...item, quantity: Math.min(item.quantity + 1, 99) }
          : item);
      }
      return [...items, { product, size, quantity: 1 }];
    });
    setSelectedProduct(null);
    setCartOpen(true);
  };

  const removeFromCart = (productId: string, size: string) => {
    setCartItems((items) => items.filter((item) => !(item.product.id === productId && item.size === size)));
  };

  const changeCartQuantity = (productId: string, size: string, quantity: number) => {
    if (quantity < 1) {
      removeFromCart(productId, size);
      return;
    }
    setCartItems((items) => items.map((item) => item.product.id === productId && item.size === size
      ? { ...item, quantity: Math.min(quantity, 99) }
      : item));
  };

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

      <div style={{ display: 'flex', justifyContent: 'center', padding: '10px 16px', background: 'var(--color-charcoal-800)', borderBottom: '1px solid var(--color-charcoal-700)' }}>
        <button type="button" onClick={() => setCartOpen(true)} style={{ display: 'inline-flex', alignItems: 'center', gap: '10px', border: '1px solid var(--color-gold-accent)', background: 'transparent', color: 'var(--color-warm-cream)', borderRadius: '24px', padding: '8px 16px', cursor: 'pointer', fontSize: '13px' }} aria-label={language === 'ar' ? `فتح السلة، ${cartItems.reduce((sum, item) => sum + item.quantity, 0)} منتجات` : `Ouvrir le panier, ${cartItems.reduce((sum, item) => sum + item.quantity, 0)} articles`}>
          <span>{language === 'ar' ? 'سلة المشتريات' : 'Panier'}</span>
          <span style={{ minWidth: '22px', height: '22px', display: 'inline-flex', alignItems: 'center', justifyContent: 'center', borderRadius: '50%', background: 'var(--color-gold-accent)', color: 'var(--color-charcoal-900)', fontWeight: 700 }}>{cartItems.reduce((sum, item) => sum + item.quantity, 0)}</span>
        </button>
      </div>

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

        {currentPage === 'info' && <StoreInfoView language={language} />}
      </main>

      {/* Product Detail Modal */}
      {selectedProduct && (
        <ProductDetailModal
          product={selectedProduct}
          language={language}
          onClose={() => setSelectedProduct(null)}
          onAddToCart={addToCart}
        />
      )}

      <Footer language={language} onNavigate={setCurrentPage} />

      {cartOpen && (
        <CartDrawer
          items={cartItems}
          language={language}
          onClose={() => setCartOpen(false)}
          onRemove={removeFromCart}
          onChangeQuantity={changeCartQuantity}
        />
      )}
    </div>
  );
};

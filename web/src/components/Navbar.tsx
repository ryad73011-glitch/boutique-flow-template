import React, { useState } from 'react';
import { Language, Page } from '../types';
import { translations } from '../translations';

interface NavbarProps {
  currentPage: Page;
  language: Language;
  onNavigate: (page: Page) => void;
  onToggleLanguage: () => void;
}

export const Navbar: React.FC<NavbarProps> = ({
  currentPage,
  language,
  onNavigate,
  onToggleLanguage
}) => {
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
  const t = translations[language];

  const navLinks: { id: Page; label: string }[] = [
    { id: 'home', label: t.navHome },
    { id: 'catalog', label: t.navCatalog },
    { id: 'about', label: t.navAbout },
    { id: 'contact', label: t.navContact },
    { id: 'info', label: t.navInfo }
  ];

  return (
    <header style={{
      backgroundColor: 'var(--color-charcoal-900)',
      borderBottom: '1px solid var(--color-charcoal-700)',
      position: 'sticky',
      top: 0,
      zIndex: 50
    }}>
      <div style={{
        maxWidth: '1200px',
        margin: '0 auto',
        padding: '16px 24px',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between'
      }}>
        {/* Brand logo & tagline */}
        <div
          onClick={() => onNavigate('home')}
          style={{ cursor: 'pointer', textAlign: language === 'ar' ? 'right' : 'left' }}
        >
          <div style={{
            fontSize: '18px',
            fontWeight: 'bold',
            letterSpacing: '2px',
            fontFamily: 'var(--font-serif)',
            color: 'var(--color-warm-cream)'
          }}>
            {t.brandName}
          </div>
          <div style={{
            fontSize: '10px',
            letterSpacing: '1.5px',
            color: 'var(--color-gold-accent)',
            fontWeight: 600
          }}>
            {t.brandTagline}
          </div>
        </div>

        {/* Desktop Navigation */}
        <nav style={{ display: 'none', gap: '32px', alignItems: 'center' }} className="desktop-nav">
          {navLinks.map((link) => (
            <button
              key={link.id}
              onClick={() => onNavigate(link.id)}
              style={{
                background: 'none',
                border: 'none',
                color: currentPage === link.id ? 'var(--color-gold-accent)' : 'var(--color-warm-cream)',
                fontSize: '14px',
                fontWeight: currentPage === link.id ? 600 : 400,
                cursor: 'pointer',
                letterSpacing: '0.5px',
                borderBottom: currentPage === link.id ? '1px solid var(--color-gold-accent)' : 'none',
                paddingBottom: '4px',
                transition: 'color 0.2s'
              }}
            >
              {link.label}
            </button>
          ))}
        </nav>

        {/* Actions: Language Switch & Mobile Toggle */}
        <div style={{ display: 'flex', alignItems: 'center', gap: '16px' }}>
          <button
            onClick={onToggleLanguage}
            style={{
              backgroundColor: 'var(--color-charcoal-800)',
              border: '1px solid var(--color-charcoal-600)',
              color: 'var(--color-gold-accent)',
              padding: '6px 14px',
              borderRadius: '20px',
              fontSize: '12px',
              fontWeight: 600,
              cursor: 'pointer'
            }}
          >
            {language === 'fr' ? 'العربية' : 'Français'}
          </button>

          {/* Mobile hamburger */}
          <button
            onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
            aria-label="Menu"
            style={{
              backgroundColor: 'transparent',
              border: 'none',
              color: 'var(--color-warm-cream)',
              fontSize: '22px',
              cursor: 'pointer',
              display: 'flex',
              padding: '4px'
            }}
            className="mobile-toggle"
          >
            {mobileMenuOpen ? '✕' : '☰'}
          </button>
        </div>
      </div>

      {/* Mobile Drawer */}
      {mobileMenuOpen && (
        <div style={{
          backgroundColor: 'var(--color-charcoal-800)',
          borderTop: '1px solid var(--color-charcoal-700)',
          padding: '16px 24px',
          display: 'flex',
          flexDirection: 'column',
          gap: '16px'
        }}>
          {navLinks.map((link) => (
            <button
              key={link.id}
              onClick={() => {
                onNavigate(link.id);
                setMobileMenuOpen(false);
              }}
              style={{
                background: 'none',
                border: 'none',
                color: currentPage === link.id ? 'var(--color-gold-accent)' : 'var(--color-warm-cream)',
                fontSize: '16px',
                textAlign: language === 'ar' ? 'right' : 'left',
                padding: '8px 0',
                cursor: 'pointer'
              }}
            >
              {link.label}
            </button>
          ))}
        </div>
      )}

      <style>{`
        @media (min-width: 768px) {
          .desktop-nav { display: flex !important; }
          .mobile-toggle { display: none !important; }
        }
      `}</style>
    </header>
  );
};

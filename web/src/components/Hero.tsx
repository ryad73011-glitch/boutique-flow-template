import React from 'react';
import { Language, Page } from '../types';
import { translations } from '../translations';

interface HeroProps {
  language: Language;
  onNavigate: (page: Page) => void;
}

export const Hero: React.FC<HeroProps> = ({ language, onNavigate }) => {
  const t = translations[language];

  return (
    <section style={{
      position: 'relative',
      minHeight: '75vh',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      backgroundImage: 'linear-gradient(rgba(16, 16, 18, 0.65), rgba(16, 16, 18, 0.95)), url("https://images.unsplash.com/photo-1566174053879-31528523f8ae?auto=format&fit=crop&w=1800&q=80")',
      backgroundSize: 'cover',
      backgroundPosition: 'center',
      padding: '48px 24px',
      textAlign: 'center'
    }}>
      <div style={{ maxWidth: '800px', display: 'flex', flexDirection: 'column', alignItems: 'center', gap: '20px' }}>
        <span style={{
          backgroundColor: 'var(--color-gold-accent)',
          color: 'var(--color-charcoal-900)',
          padding: '6px 14px',
          borderRadius: '4px',
          fontSize: '11px',
          fontWeight: 700,
          letterSpacing: '2px'
        }}>
          {t.heroTag}
        </span>

        <h1 style={{
          fontFamily: 'var(--font-serif)',
          fontSize: 'clamp(32px, 5vw, 56px)',
          fontWeight: 'bold',
          lineHeight: 1.15,
          color: 'var(--color-warm-cream)'
        }}>
          {t.heroTitle}
        </h1>

        <p style={{
          fontSize: 'clamp(15px, 2vw, 18px)',
          color: '#d5d3da',
          maxWidth: '650px',
          lineHeight: 1.6
        }}>
          {t.heroDesc}
        </p>

        <div style={{ display: 'flex', gap: '16px', flexWrap: 'wrap', justifyContent: 'center', marginTop: '12px' }}>
          <button
            onClick={() => onNavigate('catalog')}
            style={{
              backgroundColor: 'var(--color-gold-accent)',
              color: 'var(--color-charcoal-900)',
              border: 'none',
              padding: '14px 28px',
              borderRadius: '6px',
              fontSize: '14px',
              fontWeight: 700,
              cursor: 'pointer',
              letterSpacing: '0.5px'
            }}
          >
            {t.heroCta}
          </button>

          <button
            onClick={() => onNavigate('about')}
            style={{
              backgroundColor: 'transparent',
              color: 'var(--color-warm-cream)',
              border: '1px solid var(--color-gold-accent)',
              padding: '14px 28px',
              borderRadius: '6px',
              fontSize: '14px',
              fontWeight: 500,
              cursor: 'pointer'
            }}
          >
            {t.heroAbout}
          </button>
        </div>
      </div>
    </section>
  );
};

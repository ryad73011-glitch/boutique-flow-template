import React from 'react';
import { Language, Page } from '../types';
import { translations } from '../translations';

interface AboutViewProps {
  language: Language;
  onNavigate: (page: Page) => void;
}

export const AboutView: React.FC<AboutViewProps> = ({ language, onNavigate }) => {
  const t = translations[language];

  return (
    <section style={{ maxWidth: '960px', margin: '0 auto', padding: '60px 24px 100px' }}>
      <div style={{ textAlign: 'center', marginBottom: '48px' }}>
        <h2 style={{
          fontFamily: 'var(--font-serif)',
          fontSize: '36px',
          fontWeight: 'bold',
          marginBottom: '12px',
          color: 'var(--color-warm-cream)'
        }}>
          {t.aboutTitle}
        </h2>
        <p style={{ color: 'var(--color-gold-accent)', fontSize: '15px' }}>
          {t.aboutSubtitle}
        </p>
      </div>

      <div style={{
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(280px, 1fr))',
        gap: '40px',
        alignItems: 'center',
        marginBottom: '60px'
      }}>
        <div>
          <p style={{ fontSize: '15px', color: '#ccc', lineHeight: 1.8, marginBottom: '20px' }}>
            {t.aboutP1}
          </p>
          <p style={{ fontSize: '15px', color: '#ccc', lineHeight: 1.8 }}>
            {t.aboutP2}
          </p>
        </div>
        <div style={{ borderRadius: '8px', overflow: 'hidden', border: '1px solid var(--color-charcoal-700)' }}>
          <img
            src="https://images.unsplash.com/photo-1539109136881-3be0616acf4b?auto=format&fit=crop&w=800&q=80"
            alt="Maison El Djazair"
            style={{ width: '100%', height: '100%', objectFit: 'cover' }}
          />
        </div>
      </div>

      {/* Pillars */}
      <div style={{
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(240px, 1fr))',
        gap: '24px',
        marginBottom: '60px'
      }}>
        <div style={{
          padding: '24px',
          backgroundColor: 'var(--color-charcoal-800)',
          borderRadius: '8px',
          border: '1px solid var(--color-charcoal-700)'
        }}>
          <h4 style={{ color: 'var(--color-gold-accent)', fontSize: '16px', marginBottom: '8px' }}>
            💎 {t.pillar1Title}
          </h4>
          <p style={{ fontSize: '13px', color: '#aaa', lineHeight: 1.6 }}>
            {t.pillar1Desc}
          </p>
        </div>

        <div style={{
          padding: '24px',
          backgroundColor: 'var(--color-charcoal-800)',
          borderRadius: '8px',
          border: '1px solid var(--color-charcoal-700)'
        }}>
          <h4 style={{ color: 'var(--color-gold-accent)', fontSize: '16px', marginBottom: '8px' }}>
            ✨ {t.pillar2Title}
          </h4>
          <p style={{ fontSize: '13px', color: '#aaa', lineHeight: 1.6 }}>
            {t.pillar2Desc}
          </p>
        </div>

        <div style={{
          padding: '24px',
          backgroundColor: 'var(--color-charcoal-800)',
          borderRadius: '8px',
          border: '1px solid var(--color-charcoal-700)'
        }}>
          <h4 style={{ color: 'var(--color-gold-accent)', fontSize: '16px', marginBottom: '8px' }}>
            📍 {t.pillar3Title}
          </h4>
          <p style={{ fontSize: '13px', color: '#aaa', lineHeight: 1.6 }}>
            {t.pillar3Desc}
          </p>
        </div>
      </div>

      <div style={{ textAlign: 'center' }}>
        <button
          onClick={() => onNavigate('contact')}
          style={{
            backgroundColor: 'var(--color-gold-accent)',
            color: 'var(--color-charcoal-900)',
            border: 'none',
            padding: '14px 28px',
            borderRadius: '6px',
            fontSize: '14px',
            fontWeight: 'bold',
            cursor: 'pointer'
          }}
        >
          {t.contactTitle}
        </button>
      </div>
    </section>
  );
};

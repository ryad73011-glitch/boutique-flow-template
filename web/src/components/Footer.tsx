import React from 'react';
import { Language, Page } from '../types';
import { translations } from '../translations';
import { ATELIER_PHONE } from '../data/products';

interface FooterProps {
  language: Language;
  onNavigate: (page: Page) => void;
}

export const Footer: React.FC<FooterProps> = ({ language, onNavigate }) => {
  const t = translations[language];

  return (
    <footer style={{
      backgroundColor: 'var(--color-charcoal-800)',
      borderTop: '1px solid var(--color-charcoal-700)',
      padding: '48px 24px 24px',
      color: 'var(--color-warm-cream)'
    }}>
      <div style={{
        maxWidth: '1200px',
        margin: '0 auto',
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(260px, 1fr))',
        gap: '36px'
      }}>
        {/* Brand */}
        <div>
          <h3 style={{
            fontSize: '18px',
            fontFamily: 'var(--font-serif)',
            letterSpacing: '1.5px',
            marginBottom: '8px'
          }}>
            {t.brandName}
          </h3>
          <p style={{
            fontSize: '13px',
            color: 'var(--color-gold-accent)',
            marginBottom: '16px',
            fontWeight: 600
          }}>
            {t.brandTagline}
          </p>
          <p style={{
            fontSize: '13px',
            color: '#a09fa6',
            lineHeight: 1.6
          }}>
            {language === 'ar'
              ? 'دار أزياء جزائرية راقية مكرسة لإحياء فن الكاراكو، القفطان والأزياء الملكية التراثية بأنامل أمهر حرفيات العاصمة.'
              : 'Maison de haute couture algérienne dédiée à la sublimation du costume traditionnel, du Karakou impérial et du sur-mesure d'exception."}
          </p>
        </div>

        {/* Links */}
        <div>
          <h4 style={{
            fontSize: '14px',
            color: 'var(--color-gold-accent)',
            marginBottom: '16px',
            letterSpacing: '1px'
          }}>
            {language === 'ar' ? 'أقسام الموقع' : 'Navigation'}
          </h4>
          <ul style={{ listStyle: 'none', display: 'flex', flexDirection: 'column', gap: '8px', fontSize: '13px' }}>
            <li><button onClick={() => onNavigate('home')} style={{ background: 'none', border: 'none', color: '#a09fa6', cursor: 'pointer' }}>{t.navHome}</button></li>
            <li><button onClick={() => onNavigate('catalog')} style={{ background: 'none', border: 'none', color: '#a09fa6', cursor: 'pointer' }}>{t.navCatalog}</button></li>
            <li><button onClick={() => onNavigate('about')} style={{ background: 'none', border: 'none', color: '#a09fa6', cursor: 'pointer' }}>{t.navAbout}</button></li>
            <li><button onClick={() => onNavigate('contact')} style={{ background: 'none', border: 'none', color: '#a09fa6', cursor: 'pointer' }}>{t.navContact}</button></li>
          </ul>
        </div>

        {/* Atelier Info */}
        <div>
          <h4 style={{
            fontSize: '14px',
            color: 'var(--color-gold-accent)',
            marginBottom: '16px',
            letterSpacing: '1px'
          }}>
            {language === 'ar' ? 'الورشة وصالة العرض' : "L'Atelier d'Alger"}
          </h4>
          <p style={{ fontSize: '13px', color: '#a09fa6', marginBottom: '8px' }}>
            📍 {t.atelierAddress}
          </p>
          <p style={{ fontSize: '13px', color: '#a09fa6', marginBottom: '8px' }}>
            🕒 {t.atelierHours}
          </p>
          <p style={{ fontSize: '13px', color: '#a09fa6' }}>
            📞 {ATELIER_PHONE}
          </p>
        </div>
      </div>

      <div style={{
        maxWidth: '1200px',
        margin: '40px auto 0',
        paddingTop: '20px',
        borderTop: '1px solid var(--color-charcoal-700)',
        textAlign: 'center',
        fontSize: '12px',
        color: '#706e78'
      }}>
        © {new Date().getFullYear()} {t.brandName}. {t.rights}
      </div>
    </footer>
  );
};

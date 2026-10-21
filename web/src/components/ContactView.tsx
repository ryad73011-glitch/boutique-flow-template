import React, { useState } from 'react';
import { Language } from '../types';
import { translations } from '../translations';
import { WHATSAPP_PHONE, ATELIER_PHONE } from '../data/products';

interface ContactViewProps {
  language: Language;
}

export const ContactView: React.FC<ContactViewProps> = ({ language }) => {
  const [name, setName] = useState('');
  const [phone, setPhone] = useState('');
  const [message, setMessage] = useState('');
  const [submitted, setSubmitted] = useState(false);

  const t = translations[language];

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!name.trim() || !phone.trim()) return;

    const body = language === 'ar'
      ? `طلب تواصل من الموقع\nالاسم: ${name.trim()}\nالهاتف: ${phone.trim()}\nالرسالة: ${message.trim() || 'لا توجد رسالة إضافية'}`
      : `Demande de contact depuis le site\nNom : ${name.trim()}\nTéléphone : ${phone.trim()}\nMessage : ${message.trim() || 'Aucun message complémentaire'}`;

    const url = `https://wa.me/${WHATSAPP_PHONE.replace(/\\D/g, '')}?text=${encodeURIComponent(body)}`;
    window.open(url, '_blank', 'noopener,noreferrer');
    setSubmitted(true);
  };

  return (
    <section style={{ maxWidth: '800px', margin: '0 auto', padding: '60px 24px 100px' }}>
      <div style={{ textAlign: 'center', marginBottom: '40px' }}>
        <h2 style={{
          fontFamily: 'var(--font-serif)',
          fontSize: '32px',
          fontWeight: 'bold',
          marginBottom: '8px',
          color: 'var(--color-warm-cream)'
        }}>
          {t.contactTitle}
        </h2>
        <p style={{ color: 'var(--color-gold-accent)', fontSize: '14px' }}>
          {t.contactSubtitle}
        </p>
      </div>

      <div style={{
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(220px, 1fr))',
        gap: '16px',
        marginBottom: '40px'
      }}>
        <a
          href={`https://wa.me/${WHATSAPP_PHONE.replace(/\\D/g, '')}`}
          target="_blank"
          rel="noopener noreferrer"
          style={{
            padding: '20px',
            backgroundColor: 'var(--color-charcoal-800)',
            border: '1px solid var(--color-charcoal-700)',
            borderRadius: '8px',
            textAlign: 'center',
            textDecoration: 'none',
            color: 'var(--color-warm-cream)'
          }}
        >
          <div style={{ fontSize: '24px', marginBottom: '8px' }}>💬</div>
          <strong style={{ display: 'block', color: 'var(--color-gold-accent)', fontSize: '15px' }}>WhatsApp</strong>
          <span style={{ fontSize: '13px', color: '#aaa' }}>{WHATSAPP_PHONE}</span>
        </a>

        <a
          href={`tel:${ATELIER_PHONE.replace(/\\s+/g, '')}`}
          style={{
            padding: '20px',
            backgroundColor: 'var(--color-charcoal-800)',
            border: '1px solid var(--color-charcoal-700)',
            borderRadius: '8px',
            textAlign: 'center',
            textDecoration: 'none',
            color: 'var(--color-warm-cream)'
          }}
        >
          <div style={{ fontSize: '24px', marginBottom: '8px' }}>📞</div>
          <strong style={{ display: 'block', color: 'var(--color-gold-accent)', fontSize: '15px' }}>Téléphone Atelier</strong>
          <span style={{ fontSize: '13px', color: '#aaa' }}>{ATELIER_PHONE}</span>
        </a>

        <div style={{
          padding: '20px',
          backgroundColor: 'var(--color-charcoal-800)',
          border: '1px solid var(--color-charcoal-700)',
          borderRadius: '8px',
          textAlign: 'center'
        }}>
          <div style={{ fontSize: '24px', marginBottom: '8px' }}>📍</div>
          <strong style={{ display: 'block', color: 'var(--color-gold-accent)', fontSize: '15px' }}>Atelier & Showroom</strong>
          <span style={{ fontSize: '12px', color: '#aaa' }}>{t.atelierAddress}</span>
        </div>
      </div>

      <div style={{
        backgroundColor: 'var(--color-charcoal-800)',
        border: '1px solid var(--color-charcoal-700)',
        borderRadius: '8px',
        padding: '32px'
      }}>
        {submitted ? (
          <div style={{ textAlign: 'center', padding: '24px' }}>
            <div style={{ fontSize: '36px', color: 'var(--color-gold-accent)', marginBottom: '12px' }}>↗</div>
            <h3 style={{ fontSize: '18px', marginBottom: '8px' }}>
              {language === 'ar' ? 'تم فتح WhatsApp' : 'WhatsApp a été ouvert'}
            </h3>
            <p style={{ fontSize: '14px', color: '#aaa', marginBottom: '20px' }}>
              {language === 'ar'
                ? 'الرسالة محضّرة فقط. راجعها واضغط إرسال داخل WhatsApp؛ لم يتم إرسالها من الموقع.'
                : 'Le message est seulement préparé. Vérifiez-le puis appuyez sur Envoyer dans WhatsApp ; le site ne l’a pas envoyé.'}
            </p>
            <button
              onClick={() => {
                setSubmitted(false);
                setName('');
                setPhone('');
                setMessage('');
              }}
              style={{
                backgroundColor: 'transparent',
                border: '1px solid var(--color-gold-accent)',
                color: 'var(--color-gold-accent)',
                padding: '8px 16px',
                borderRadius: '4px',
                cursor: 'pointer'
              }}
            >
              {language === 'ar' ? 'رسالة جديدة' : 'Nouveau message'}
            </button>
          </div>
        ) : (
          <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '16px' }}>
            <div>
              <label style={{ display: 'block', fontSize: '13px', marginBottom: '6px', color: '#ccc' }}>
                {t.fullName} *
              </label>
              <input
                type="text"
                required
                value={name}
                onChange={(e) => setName(e.target.value)}
                style={{
                  width: '100%',
                  padding: '12px',
                  backgroundColor: 'var(--color-charcoal-900)',
                  border: '1px solid var(--color-charcoal-600)',
                  borderRadius: '6px',
                  color: '#fff',
                  fontSize: '14px'
                }}
              />
            </div>

            <div>
              <label style={{ display: 'block', fontSize: '13px', marginBottom: '6px', color: '#ccc' }}>
                {t.phone} *
              </label>
              <input
                type="tel"
                required
                value={phone}
                onChange={(e) => setPhone(e.target.value)}
                placeholder="+213..."
                style={{
                  width: '100%',
                  padding: '12px',
                  backgroundColor: 'var(--color-charcoal-900)',
                  border: '1px solid var(--color-charcoal-600)',
                  borderRadius: '6px',
                  color: '#fff',
                  fontSize: '14px'
                }}
              />
            </div>

            <div>
              <label style={{ display: 'block', fontSize: '13px', marginBottom: '6px', color: '#ccc' }}>
                {t.message}
              </label>
              <textarea
                rows={4}
                value={message}
                onChange={(e) => setMessage(e.target.value)}
                style={{
                  width: '100%',
                  padding: '12px',
                  backgroundColor: 'var(--color-charcoal-900)',
                  border: '1px solid var(--color-charcoal-600)',
                  borderRadius: '6px',
                  color: '#fff',
                  fontSize: '14px',
                  resize: 'vertical'
                }}
              />
            </div>

            <button
              type="submit"
              style={{
                backgroundColor: 'var(--color-gold-accent)',
                color: 'var(--color-charcoal-900)',
                border: 'none',
                padding: '14px',
                borderRadius: '6px',
                fontSize: '15px',
                fontWeight: 'bold',
                cursor: 'pointer',
                marginTop: '8px'
              }}
            >
              {language === 'ar' ? 'متابعة عبر WhatsApp' : 'Continuer sur WhatsApp'}
            </button>
          </form>
        )}
      </div>
    </section>
  );
};

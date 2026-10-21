/**
 * White-label configuration for each client boutique.
 *
 * Set these values with Vite environment variables when deploying a client copy.
 * Keep secrets out of VITE_* variables: they are public in the browser bundle.
 */
function envValue(value: string | undefined, fallback: string): string {
  const normalized = value?.trim();
  return normalized ? normalized : fallback;
}

function normalizePhone(value: string): string {
  return value.replace(/\D/g, '');
}

const whatsappPhone = normalizePhone(
  envValue(import.meta.env.VITE_WHATSAPP_PHONE, '')
);
const atelierPhone = envValue(import.meta.env.VITE_ATELIER_PHONE, 'À configurer');
const atelierPhoneConfigured = normalizePhone(atelierPhone).length >= 8;

export const storeConfig = {
  name: envValue(import.meta.env.VITE_STORE_NAME, 'MAISON EL DJAZAIR'),
  nameAr: envValue(import.meta.env.VITE_STORE_NAME_AR, 'دار الجزائر'),
  taglineFr: envValue(import.meta.env.VITE_STORE_TAGLINE_FR, 'HAUTE COUTURE ALGÉRIENNE'),
  taglineAr: envValue(import.meta.env.VITE_STORE_TAGLINE_AR, 'خياطة راقية جزائرية'),
  whatsappPhone,
  whatsappConfigured: whatsappPhone.length >= 10,
  whatsappDisplay: whatsappPhone ? `+${whatsappPhone}` : 'À configurer',
  atelierPhone,
  atelierPhoneConfigured,
  addressFr: envValue(import.meta.env.VITE_ATELIER_ADDRESS_FR, 'Adresse à personnaliser'),
  addressAr: envValue(import.meta.env.VITE_ATELIER_ADDRESS_AR, 'العنوان يُخصّص حسب المتجر'),
  hoursFr: envValue(import.meta.env.VITE_ATELIER_HOURS_FR, 'Horaires à confirmer'),
  hoursAr: envValue(import.meta.env.VITE_ATELIER_HOURS_AR, 'أوقات العمل تُحدّد حسب المتجر')
} as const;

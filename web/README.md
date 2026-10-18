# Maison El Djazair — Algerian Haute Couture Boutique (Web App)

A premium, responsive clothing boutique web application designed for a luxury Algerian fashion brand specializing in Haute Couture, traditional Karakou, royal caftans, and contemporary ready-to-wear.

---

## 🏛️ Brand Aesthetic & Design

- **Color Palette**: Luxury charcoal (`#101012`, `#18181C`), warm cream (`#FAF7F2`, `#F3ECE4`), and restrained antique gold / champagne accents (`#C8A97E`).
- **Typography**: Editorial serif headings (`Cinzel` / `Amiri`) paired with crisp sans-serif (`Plus Jakarta Sans`).
- **Bilingual & RTL**: Seamless toggle between **Français (LTR)** and **العربية (RTL)** with native text direction adjustments.
- **Motion & Accessibility**: Smooth transitions that respect `prefers-reduced-motion` settings, accessible touch targets (min 48px), and responsive mobile layout.

---

## 🌟 Key Features

1. **Pages & Views**:
   - **Home**: Editorial hero presentation, capsule collection banner, craftsmanship pillars, and curated featured garments.
   - **Catalog**: Complete collection with category filter pills (*Karakou Algérois*, *Caftans Royaux*, *Gandouras Modernes*, *Pochettes & Accessoires*), real-time search, and product count.
   - **Product Details Modal**: Full editorial photography, reference numbers, size picker (`36` to `Sur-mesure`), pricing in **Algerian Dinars (DZD / د.ج)**, and artisanal craftsmanship specifications.
   - **About the Brand**: Algerian heritage storytelling (Casbah of Algiers, ancestral *Fetla* and *Medjboud* gold thread needlework, modern atelier in Hydra).
   - **Atelier & Contact**: Showroom location in Sidi Yahia, Algiers, business hours, and appointment booking form.
2. **Direct WhatsApp Ordering**:
   - Every product card and detail modal features a direct WhatsApp order button.
   - Automatically drafts a pre-filled, localized message containing the product reference, selected size, and price in DZD to the atelier's WhatsApp contact (`+213 555 123 456`).
3. **No Fake Backend / Honest Architecture**:
   - Uses local mock data for product inventory, ensuring fast, dependable client-side execution with zero external vendor lock-in.

---

## 🚀 Getting Started (Run Outside This Platform)

### Prerequisites

- Node.js (version 18 or newer recommended)
- npm or pnpm or yarn

### Installation

```bash
cd web
npm install
```

### Development Server

Run the development server locally:

```bash
npm run dev
```

Open your browser at `http://localhost:3000` to interact with the boutique.

### Production Build

Create an optimized, standard static production bundle:

```bash
npm run build
```

The compiled assets will be in `web/dist/`. You can preview the production build locally:

```bash
npm run preview
```

---

## 🌐 Deployment Instructions

The static bundle in `dist/` can be deployed anywhere with zero server-side lock-in:

### Deploy to Vercel
```bash
npx vercel
```

### Deploy to Netlify
```bash
npx netlify deploy --prod --dir=dist
```

### Deploy to GitHub Pages or Cloudflare Pages
Upload or link the repository and set:
- Build command: `npm run build`
- Output directory: `dist`

---

## 🔒 Environment Variables

If you configure external APIs or customize the WhatsApp phone number via environment variables, copy `.env.example`:

```bash
cp .env.example .env
```

And set your desired variables (e.g., `VITE_WHATSAPP_PHONE=+213555123456`).
Never commit `.env` with real credentials.

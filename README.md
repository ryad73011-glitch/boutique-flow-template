# Maison El Djazair — Algerian Luxury Fashion Boutique
## دار الجزائر للموضة الراقية

A high-end, responsive boutique application built for **Maison El Djazair**, an Algerian haute couture brand celebrating traditional Algerian craftsmanship (Karakou Algérois, Caftans Royaux, modern Gandouras, and luxury accessories).

This repository provides **dual implementations**:
1. **📱 Native Android Application (Jetpack Compose + Kotlin + Material 3)**: Optimized for mobile with edge-to-edge rendering, bilingual Arabic RTL & French support, custom adaptive launcher icon, local test suite, and direct WhatsApp ordering.
2. **🌐 Portable Web Application (React + TypeScript + Vite)**: Self-contained in the `/web` directory, ready to deploy to Vercel, Netlify, or any static host.

---

## 🎨 Visual Identity & Aesthetic

- **Color Palette**: Deep editorial charcoal (`#101012`, `#18181C`), warm cream (`#FAF7F2`, `#F3ECE4`), and restrained champagne gold accents (`#C8A97E`).
- **Bilingual & RTL**: Dynamic toggle between **French (Français)** and **Arabic (العربية)** with layout direction flipping (`RTL`/`LTR`).
- **Product Photography**: High-fashion photography showcasing embroidered velvet Karakous, royal gold caftans, fluid gandouras, and artisanal jewelry.
- **Prices in DZD**: All items formatted in Algerian Dinars (e.g. `145 000 DZD` / `145 000 د.ج`).

---

## 📂 Project Structure

```
├── app/                                 # Native Android Application (Kotlin + Jetpack Compose)
│   ├── src/main/java/com/example/
│   │   ├── MainActivity.kt              # Entry point with RTL provider & screen routing
│   │   ├── data/
│   │   │   ├── model/Product.kt         # Product models, categories, bilingual helpers
│   │   │   ├── model/Language.kt        # Language enum & RTL flags
│   │   │   └── repository/              # Mock product collection & WhatsApp message generator
│   │   └── ui/
│   │       ├── MainViewModel.kt         # State management (navigation, search, filters, favorites)
│   │       ├── components/              # LuxuryTopAppBar, LuxuryBottomNavBar, ProductCard
│   │       ├── screens/                 # HomeScreen, CatalogScreen, ProductDetailScreen,
│   │       │                            # AboutScreen, ContactScreen, FavoritesScreen
│   │       └── theme/                   # Color.kt, Theme.kt, Type.kt (editorial serif + sans)
│   ├── src/main/res/                    # Custom adaptive icon (mipmap), luxury drawables
│   └── src/test/java/com/example/       # Local JVM Robolectric & Unit test suite
├── web/                                 # Portable Web Application (React 18 + TypeScript + Vite)
│   ├── src/
│   │   ├── App.tsx                      # Root component with state & navigation
│   │   ├── components/                  # Navbar, Hero, CatalogView, ProductCard,
│   │   │                                # ProductDetailModal, AboutView, ContactView, Footer
│   │   ├── data/products.ts             # Local products dataset & WhatsApp URL builder
│   │   ├── translations.ts              # French & Arabic localized strings
│   │   └── index.css                    # Charcoal, cream, gold variables & reduced-motion rules
│   ├── index.html                       # HTML template with Google Fonts
│   ├── package.json                     # Standard npm dependencies
│   ├── tsconfig.json                    # TypeScript configuration
│   ├── vite.config.ts                   # Vite build configuration
│   └── README.md                        # Web-specific setup and deployment instructions
├── metadata.json                        # Platform metadata in sync with app_name
├── settings.gradle.kts                  # Root project configuration
└── README.md                            # Comprehensive documentation
```

---

## 📱 Running the Android App

### In AI Studio:
The app builds automatically into an APK and streams in the interactive browser emulator.

### In Android Studio (Local Machine):
1. Open this repository in **Android Studio Hedgehog or Ladybug+**.
2. Let Gradle sync dependencies.
3. Select an emulator or physical Android device (API 24+) and click **Run (Shift + F10)**.

### Running Android Unit Tests:
```bash
gradle :app:testDebugUnitTest
```

---

## 🌐 Running the Web Application Outside This Platform

To run the React + TypeScript + Vite boutique website on any computer:

```bash
cd web
npm install
npm run dev
```
Open `http://localhost:3000` in your web browser.

To compile for production:
```bash
npm run build
```
The output will be in `web/dist/`, which can be served by any static web server (Nginx, Apache, Vercel, Netlify, GitHub Pages, Cloudflare Pages).

---

## 🔄 How to Export or Sync to a Private GitHub Repository

You can export this codebase from AI Studio to your own private GitHub repository in one of two ways:

### Option A: Using the AI Studio UI
1. Click on the **Project Settings / Options** menu in the top-right toolbar.
2. Select **"Push to GitHub"** (or **"Export as ZIP"** if you prefer downloading an archive).
3. If connecting to GitHub, choose **"Create Private Repository"**, name your repository (e.g., `maison-el-djazair-boutique`), and confirm.

### Option B: Using Git in the Terminal
If you exported the files or have shell access with a Personal Access Token:
```bash
# 1. Initialize git if not already present
git init

# 2. Add all files and make an initial commit
git add .
git commit -m "Initial commit: Maison El Djazair Android and Web boutique"

# 3. Rename branch to main
git branch -M main

# 4. Add your GitHub private repository as remote
git remote add origin https://github.com/YOUR_USERNAME/maison-el-djazair-boutique.git

# 5. Push to GitHub
git push -u origin main
```

---

## ⚖️ Limitations & Design Choices (Honest Reporting)

1. **Client-Side Mock Inventory**: In accordance with the prompt specification, inventory numbers, product listings, and order flows use a curated local dataset. There is no fake payment gateway or simulated database backend.
2. **Consultative WhatsApp Ordering**: In high-end Algerian haute couture, bridal and formal attire (such as hand-embroidered Karakous and Caftans) typically require private fitting, customization, and bespoke sizing. The app purposefully uses direct WhatsApp consultation with pre-filled order specs instead of an impersonal automated checkout.
3. **No External Secrets Required**: The app runs fully self-contained without requiring third-party API keys or paid backend services.

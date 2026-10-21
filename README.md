# Boutique en ligne — modèle de démonstration

Ce dépôt contient deux applications distinctes :

- `web/` : modèle de boutique React, TypeScript et Vite, adapté aux démos clients et au déploiement statique.
- `app/` : application Android native Kotlin/Jetpack Compose.

Le modèle web actuel présente un catalogue local en français et en arabe, des fiches produits, une recherche, des filtres et des messages de commande préremplis dans WhatsApp. Les données initiales sont des exemples et doivent être remplacées avant toute livraison à un commerçant.

## Démarrer la démo web

```bash
cd web
npm install
npm run dev
```

Pour vérifier la version de production :

```bash
npm run build
npm run preview
```

Consultez [web/README.md](web/README.md) pour les détails de personnalisation, de configuration et de déploiement.

## Préparer une version pour un commerçant

1. Créez `web/.env.local` à partir de `web/.env.example`.
2. Configurez le nom, le numéro WhatsApp international, le téléphone, l'adresse et les horaires.
3. Remplacez les exemples dans `web/src/data/products.ts` par les produits, prix et photos validés par le client.
4. Testez les langues française et arabe, les filtres, chaque commande WhatsApp et le formulaire de contact sur un téléphone.
5. Exécutez `npm run build` et publiez `web/dist/` sur un hébergeur statique.

**Limites à annoncer au client :** il s'agit d'un catalogue front-end, pas d'une plateforme e-commerce complète. Il ne possède pas de paiement en ligne, de gestion de stock côté serveur, de compte client ni de tableau de bord commerçant. WhatsApp sert à transmettre manuellement les demandes ; le site n'envoie pas lui-même les messages.

## Validation automatique

Le workflow GitHub Actions `.github/workflows/web-build.yml` installe les dépendances du site web, exécute le contrôle TypeScript et construit les fichiers de production pour les changements poussés sur `main` ou les branches `feat/**`, ainsi que pour les pull requests vers `main`.

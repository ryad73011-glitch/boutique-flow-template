# Boutique en ligne — modèle personnalisable

Modèle de boutique React + TypeScript + Vite pour présenter des produits et recevoir des demandes de commande via WhatsApp. Les produits actuels et les coordonnées par défaut sont des **données de démonstration** : remplacez-les avant de remettre le site à un commerçant.

## Démarrer

Prérequis : Node.js 20 ou 22 et npm.

```bash
cd web
npm install
npm run dev
```

Vite affiche l'adresse locale dans le terminal (le port configuré est généralement `3000`). Pour vérifier le site de production :

```bash
npm run build
npm run preview
```

Le résultat de production se trouve dans `web/dist/`.

## Personnaliser une boutique

1. Copiez `.env.example` vers `.env.local` dans le dossier `web/`.
2. Remplacez les valeurs `VITE_*` : nom, slogans, WhatsApp au format international avec indicatif pays, téléphone, adresse et horaires.
3. Modifiez les produits et les prix dans `src/data/products.ts`. Les textes français et arabes de l'interface se trouvent dans `src/translations.ts`.
4. Remplacez les images de démonstration par les photos du commerçant, avec son autorisation.
5. Lancez `npm run build` et testez les liens WhatsApp sur téléphone avant livraison.

Les variables `VITE_*` sont intégrées au code envoyé au navigateur : n'y placez jamais de mots de passe, clés privées ou secrets de paiement.

## Commandes et limites

- Les boutons de produit préparent un message WhatsApp contenant la référence, le prix et la taille sélectionnée.
- Le formulaire de contact prépare un message dans WhatsApp. **Le client doit lui-même appuyer sur Envoyer** ; le site n'expédie pas les messages automatiquement.
- Aucun paiement en ligne, gestion de stock réelle, compte client, tableau de bord commerçant ou stockage serveur n'est implémenté dans ce modèle.
- Le catalogue est local et les coordonnées/produits de départ sont fictifs. Vérifiez les prix, disponibilités, coordonnées, livraison, retours et conditions commerciales avec le commerçant avant publication.

## Déploiement

Le dossier `dist/` est un site statique et peut être publié sur Vercel, Netlify ou Cloudflare Pages. Configurez `web` comme dossier de projet, `npm run build` comme commande et `dist` comme répertoire de sortie. Ajoutez les variables `VITE_*` au service d'hébergement avant le build.

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
5. Relisez la page « FAQ & Informations pratiques » : les règles de livraison, paiement, retours et confidentialité sont des textes indicatifs à adapter au commerçant.
6. Lancez `npm run build` et testez les liens WhatsApp sur téléphone avant livraison.

Les variables `VITE_*` sont intégrées au code envoyé au navigateur : n'y placez jamais de mots de passe, clés privées ou secrets de paiement.

## Commandes et limites

- Le panier accepte plusieurs produits et tailles, permet de modifier les quantités, de retirer un article ou de vider le panier, et reste mémorisé dans le navigateur sur cet appareil.
- La recherche du catalogue ignore les accents français et les filtres peuvent être réinitialisés.
- Le titre de page et les métadonnées de partage sont adaptés à la langue et au nom configuré du magasin.
- Avant d’ouvrir WhatsApp, le client renseigne son nom, téléphone, wilaya et adresse ; les champs sont validés côté navigateur puis ajoutés au récapitulatif de demande.
- Par sécurité, le numéro WhatsApp est vide par défaut : la commande est désactivée dans la démo jusqu’à ce que le propriétaire configure `VITE_WHATSAPP_PHONE` avec son vrai numéro international (chiffres uniquement).
- La validation du panier prépare un récapitulatif multiligne via WhatsApp avec les quantités et un total estimatif ; le client doit vérifier puis envoyer lui-même le message.
- Les boutons de produit préparent aussi un message WhatsApp contenant la référence, le prix et la taille sélectionnée.
- Le formulaire de contact prépare un message dans WhatsApp. **Le client doit lui-même appuyer sur Envoyer** ; le site n'expédie pas les messages automatiquement.
- Aucun paiement en ligne, gestion de stock réelle, compte client, tableau de bord commerçant ou stockage serveur n'est implémenté dans ce modèle.
- Une page bilingue FAQ/informations pratiques explique le parcours de commande, la livraison, les retours et l’usage des données. Son contenu reste un modèle indicatif, à vérifier et personnaliser avant publication.
- Le téléphone de l’atelier est vide par défaut et l’appel est désactivé jusqu’à ce qu’un vrai numéro soit configuré. Le catalogue et les coordonnées de départ sont des données de démonstration. Vérifiez prix, disponibilités, coordonnées, livraison, retours et conditions commerciales avec le commerçant avant publication.

## Déploiement

Le dossier `dist/` est un site statique et peut être publié sur Vercel, Netlify ou Cloudflare Pages. Configurez `web` comme dossier de projet, `npm run build` comme commande et `dist` comme répertoire de sortie. Ajoutez les variables `VITE_*` au service d'hébergement avant le build.

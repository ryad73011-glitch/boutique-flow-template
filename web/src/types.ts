export type Language = 'fr' | 'ar';

export type Category = 'all' | 'karakou' | 'caftan' | 'gandoura' | 'accessoires';

export interface Product {
  id: string;
  reference: string;
  category: Category;
  nameFr: string;
  nameAr: string;
  subtitleFr: string;
  subtitleAr: string;
  priceDzd: number;
  imageUrl: string;
  descriptionFr: string;
  descriptionAr: string;
  craftsmanshipFr: string;
  craftsmanshipAr: string;
  fabricFr: string;
  fabricAr: string;
  sizes: string[];
  isFeatured?: boolean;
}

export type Page = 'home' | 'catalog' | 'about' | 'contact';

export interface IProduct {
  id: number;
  qrCode?: string | null;
  name?: string | null;
  imageData?: string | null;
  imageDataContentType?: string | null;
  imageUrl?: string | null;
  qT?: number | null;
}

export type NewProduct = Omit<IProduct, 'id'> & { id: null };

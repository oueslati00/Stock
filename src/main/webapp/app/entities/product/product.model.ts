export interface IProduct {
  id: number;
  qrCode?: string | null;
  name?: string | null;
  imageData?: string | null;
  imageDataContentType?: string | null;
  imageUrl?: string | null;
  qT?: number | null;
  shouldBeNotification?: boolean | null;
  notificationDeleted?: boolean | null;
  minQT?: number | null;
}

export type NewProduct = Omit<IProduct, 'id'> & { id: null };

import { IProduct, NewProduct } from './product.model';

export const sampleWithRequiredData: IProduct = {
  id: 5114,
  qrCode: 'percer broum',
};

export const sampleWithPartialData: IProduct = {
  id: 25735,
  qrCode: 'oups jusqu’à ce que',
  name: 'rudement spécialiste',
  imageData: '../fake-data/blob/hipster.png',
  imageDataContentType: 'unknown',
};

export const sampleWithFullData: IProduct = {
  id: 17194,
  qrCode: 'juriste',
  name: 'gratis',
  imageData: '../fake-data/blob/hipster.png',
  imageDataContentType: 'unknown',
  imageUrl: 'moins impromptu',
  qT: 3167,
  shouldBeNotification: true,
  notificationDeleted: false,
  minQT: 6126,
};

export const sampleWithNewData: NewProduct = {
  qrCode: 'lors vu que à peu près',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

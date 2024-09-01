import { IProduct, NewProduct } from './product.model';

export const sampleWithRequiredData: IProduct = {
  id: 16943,
  qrCode: 'cot cot euh équipe de recherche',
};

export const sampleWithPartialData: IProduct = {
  id: 15813,
  qrCode: 'adversaire dring rudement',
};

export const sampleWithFullData: IProduct = {
  id: 13438,
  qrCode: 'servir juriste trop',
  name: 'guide',
  imageData: '../fake-data/blob/hipster.png',
  imageDataContentType: 'unknown',
  imageUrl: 'impromptu',
  qT: 3167,
};

export const sampleWithNewData: NewProduct = {
  qrCode: 'svelte',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

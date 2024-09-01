import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: 'b196edc0-db51-4566-bc7e-e33f14703eca',
};

export const sampleWithPartialData: IAuthority = {
  name: 'ffee8c8d-d923-48f9-a614-75515f63cb7c',
};

export const sampleWithFullData: IAuthority = {
  name: '1841e845-0a81-4a5b-bcb1-8aa7dc0be81c',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

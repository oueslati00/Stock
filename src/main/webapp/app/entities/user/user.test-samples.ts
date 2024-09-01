import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 6676,
  login: 'XW46S@fDrRC\\oK\\CHDK\\ld\\dD05c',
};

export const sampleWithPartialData: IUser = {
  id: 8503,
  login: 't@7\\~8T\\2T',
};

export const sampleWithFullData: IUser = {
  id: 11941,
  login: 'BX',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

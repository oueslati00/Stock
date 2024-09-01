import dayjs from 'dayjs/esm';

import { IDemand, NewDemand } from './demand.model';

export const sampleWithRequiredData: IDemand = {
  id: 19916,
};

export const sampleWithPartialData: IDemand = {
  id: 10225,
  demandBy: 'placide',
  demandDate: dayjs('2024-08-11T11:11'),
  status: 'REJECTED',
  validate: false,
};

export const sampleWithFullData: IDemand = {
  id: 30035,
  qT: 11356,
  demandBy: 'ramener rectorat',
  demandDate: dayjs('2024-08-11T13:54'),
  status: 'REJECTED',
  validate: true,
};

export const sampleWithNewData: NewDemand = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

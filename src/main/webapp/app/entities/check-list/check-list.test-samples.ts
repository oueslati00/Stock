import dayjs from 'dayjs/esm';

import { ICheckList, NewCheckList } from './check-list.model';

export const sampleWithRequiredData: ICheckList = {
  id: 19827,
};

export const sampleWithPartialData: ICheckList = {
  id: 18839,
  client: 'jeune dessus',
  adress: 'concurrence',
  technicienDef: 'extra puisque',
  diDMTaskStatus: 'INSPECTED',
  diDMComment: '../fake-data/blob/hipster.txt',
  detecteursPonctuelsTaskStatus: 'PRD',
  detecteursPonctuelsComment: '../fake-data/blob/hipster.txt',
  tableMiseSecurityIncendieComment: '../fake-data/blob/hipster.txt',
  alimentationSecoursComment: '../fake-data/blob/hipster.txt',
  dasTaskStatus: 'REPAIRED',
  arretTechniqueComment: '../fake-data/blob/hipster.txt',
  baiePcsTaskStatus: 'INSPECTED',
  superviseurTaskStatus: 'PRD',
  superviseurComment: '../fake-data/blob/hipster.txt',
  validate: true,
};

export const sampleWithFullData: ICheckList = {
  id: 26200,
  client: "à l'entour de",
  contractNumber: 'raide personnel professionnel cultiver',
  adress: 'prestataire de services',
  technicienDef: 'cocorico hirsute dans la mesure où',
  date: dayjs('2024-08-16T16:24'),
  tableDetectionTaskStatus: 'REPAIRED',
  tableDetectionComment: '../fake-data/blob/hipster.txt',
  diDMTaskStatus: 'PRD',
  diDMComment: '../fake-data/blob/hipster.txt',
  detecteursPonctuelsTaskStatus: 'PRD',
  detecteursPonctuelsComment: '../fake-data/blob/hipster.txt',
  declencheurManuelsTaskStatus: 'REPAIRED',
  declencheurManuelsComment: '../fake-data/blob/hipster.txt',
  tableMiseSecurityIncendieTaskStatus: 'REPAIRED',
  tableMiseSecurityIncendieComment: '../fake-data/blob/hipster.txt',
  alimentationSecoursTaskStatus: 'INSPECTED',
  alimentationSecoursComment: '../fake-data/blob/hipster.txt',
  equipementAlarmeTaskStatus: 'REPAIRED',
  equipementAlarmeComment: '../fake-data/blob/hipster.txt',
  dasTaskStatus: 'INSPECTED',
  dasComment: '../fake-data/blob/hipster.txt',
  arretTechniqueTaskStatus: 'REPAIRED',
  arretTechniqueComment: '../fake-data/blob/hipster.txt',
  baiePcsTaskStatus: 'REPAIRED',
  baiePCScomment: '../fake-data/blob/hipster.txt',
  superviseurTaskStatus: 'REPAIRED',
  superviseurComment: '../fake-data/blob/hipster.txt',
  validate: true,
  createdBy: 'étant donné que groin groin malgré',
};

export const sampleWithNewData: NewCheckList = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

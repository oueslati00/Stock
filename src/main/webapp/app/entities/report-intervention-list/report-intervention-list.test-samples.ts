import dayjs from 'dayjs/esm';

import { IReportInterventionList, NewReportInterventionList } from './report-intervention-list.model';

export const sampleWithRequiredData: IReportInterventionList = {
  id: 20128,
};

export const sampleWithPartialData: IReportInterventionList = {
  id: 11746,
  site: 'déborder',
  codeAgence: 'lorsque cocorico',
  affaireNumber: 'glouglou davantage atchoum',
  tel: 'broum',
  interlocuteurFacturation: 'clocher crac au-dessous de',
  conditionDePayementCheque: true,
  conditionPayementAutre: false,
  maintenancePreventive: false,
  maintenanceCorrective: false,
  interventionDate: dayjs('2024-08-16T15:02'),
  endDate: dayjs('2024-08-17T01:50'),
  causeExterieurInstallation: true,
  clientApreciation: 'composer pourvu que',
  respectDelais: 'PASSABLE',
  devisComplentaire: false,
  codeTechnicien: 'correspondre consacrer dense',
  validationNameFunction: 'allonger drelin broum',
  validationClientDate: dayjs('2024-08-16'),
  bonPourCommand: false,
  validation: false,
};

export const sampleWithFullData: IReportInterventionList = {
  id: 18472,
  site: 'valoir fourbe psitt',
  codeAgence: 'perplexe au lieu de jeune',
  affaireNumber: 'loufoque corps enseignant',
  contractNumber: 'très fréquenter',
  installationAdress: 'grâce à lorsque analyser',
  interlocuteurIntervation: 'aigre terne crac',
  tel: 'taper',
  installationSousContract: false,
  installationSousGarantie: false,
  adressFacturation: 'comprendre soupçonner paf',
  interlocuteurFacturation: 'via entre-temps outre',
  conditionDePayementCheque: false,
  conditionPayementAutre: false,
  conditionPayementComment: 'clientèle',
  miseEnServiceDefinitvie: true,
  miseEnServicePartielle: false,
  maintenancePreventive: false,
  maintenanceCorrective: false,
  bT: 'vaste ailleurs',
  anomalieSignalee: 'parlementaire',
  interventionDate: dayjs('2024-08-17T09:24'),
  interventionStartDate: dayjs('2024-08-17T05:51'),
  remiseServiceDate: dayjs('2024-08-16T17:04'),
  endDate: dayjs('2024-08-17T08:57'),
  natureIntervention: '../fake-data/blob/hipster.txt',
  causeExterieurInstallation: true,
  installationFonctionnelleApresInervention: true,
  autreInterventionsAPrevoir: 'trop peu',
  clientApreciation: 'trop calmer',
  respectDelais: 'SATISFAISANT',
  qualiteIntervention: 'TRES_SATISFAISANT',
  qualiteDevoirConseil: 'SATISFAISANT',
  prestationsAchevees: true,
  devisComplentaire: false,
  technicienName: 'croâ',
  codeTechnicien: 'clientèle gai',
  validationClientName: 'd’autant que police hebdomadaire',
  validationNameFunction: 'toutefois laisser élever',
  validationClientDate: dayjs('2024-08-17'),
  bonPourCommand: false,
  createdBy: 'par rapport à marron rédiger',
  validation: false,
};

export const sampleWithNewData: NewReportInterventionList = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

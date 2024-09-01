import dayjs from 'dayjs/esm';
import { TaskStatus } from 'app/entities/enumerations/task-status.model';

export interface ICheckList {
  id: number;
  client?: string | null;
  contractNumber?: string | null;
  adress?: string | null;
  technicienDef?: string | null;
  date?: dayjs.Dayjs | null;
  tableDetectionTaskStatus?: keyof typeof TaskStatus | null;
  tableDetectionComment?: string | null;
  diDMTaskStatus?: keyof typeof TaskStatus | null;
  diDMComment?: string | null;
  detecteursPonctuelsTaskStatus?: keyof typeof TaskStatus | null;
  detecteursPonctuelsComment?: string | null;
  declencheurManuelsTaskStatus?: keyof typeof TaskStatus | null;
  declencheurManuelsComment?: string | null;
  tableMiseSecurityIncendieTaskStatus?: keyof typeof TaskStatus | null;
  tableMiseSecurityIncendieComment?: string | null;
  alimentationSecoursTaskStatus?: keyof typeof TaskStatus | null;
  alimentationSecoursComment?: string | null;
  equipementAlarmeTaskStatus?: keyof typeof TaskStatus | null;
  equipementAlarmeComment?: string | null;
  dasTaskStatus?: keyof typeof TaskStatus | null;
  dasComment?: string | null;
  arretTechniqueTaskStatus?: keyof typeof TaskStatus | null;
  arretTechniqueComment?: string | null;
  baiePcsTaskStatus?: keyof typeof TaskStatus | null;
  baiePCScomment?: string | null;
  superviseurTaskStatus?: keyof typeof TaskStatus | null;
  superviseurComment?: string | null;
  validate?: boolean | null;
  createdBy?: string | null;
}

export type NewCheckList = Omit<ICheckList, 'id'> & { id: null };

import dayjs from 'dayjs/esm';
import { IProduct } from 'app/entities/product/product.model';
import { Status } from 'app/entities/enumerations/status.model';

export interface IDemand {
  id: number;
  qT?: number | null;
  demandBy?: string | null;
  demandDate?: dayjs.Dayjs | null;
  status?: keyof typeof Status | null;
  validate?: boolean | null;
  name?: Pick<IProduct, 'id' | 'name'> | null;
}

export type NewDemand = Omit<IDemand, 'id'> & { id: null };

import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IDemand, NewDemand } from '../demand.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDemand for edit and NewDemandFormGroupInput for create.
 */
type DemandFormGroupInput = IDemand | PartialWithRequiredKeyOf<NewDemand>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IDemand | NewDemand> = Omit<T, 'demandDate'> & {
  demandDate?: string | null;
};

type DemandFormRawValue = FormValueOf<IDemand>;

type NewDemandFormRawValue = FormValueOf<NewDemand>;

type DemandFormDefaults = Pick<NewDemand, 'id' | 'demandDate' | 'validate'>;

type DemandFormGroupContent = {
  id: FormControl<DemandFormRawValue['id'] | NewDemand['id']>;
  qT: FormControl<DemandFormRawValue['qT']>;
  demandBy: FormControl<DemandFormRawValue['demandBy']>;
  demandDate: FormControl<DemandFormRawValue['demandDate']>;
  status: FormControl<DemandFormRawValue['status']>;
  validate: FormControl<DemandFormRawValue['validate']>;
  name: FormControl<DemandFormRawValue['name']>;
};

export type DemandFormGroup = FormGroup<DemandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DemandFormService {
  createDemandFormGroup(demand: DemandFormGroupInput = { id: null }): DemandFormGroup {
    const demandRawValue = this.convertDemandToDemandRawValue({
      ...this.getFormDefaults(),
      ...demand,
    });
    return new FormGroup<DemandFormGroupContent>({
      id: new FormControl(
        { value: demandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      qT: new FormControl(demandRawValue.qT),
      demandBy: new FormControl(demandRawValue.demandBy),
      demandDate: new FormControl(demandRawValue.demandDate),
      status: new FormControl(demandRawValue.status),
      validate: new FormControl(demandRawValue.validate),
      name: new FormControl(demandRawValue.name, {
        validators: [Validators.required],
      }),
    });
  }

  getDemand(form: DemandFormGroup): IDemand | NewDemand {
    return this.convertDemandRawValueToDemand(form.getRawValue() as DemandFormRawValue | NewDemandFormRawValue);
  }

  resetForm(form: DemandFormGroup, demand: DemandFormGroupInput): void {
    const demandRawValue = this.convertDemandToDemandRawValue({ ...this.getFormDefaults(), ...demand });
    form.reset(
      {
        ...demandRawValue,
        id: { value: demandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): DemandFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      demandDate: currentTime,
      validate: false,
    };
  }

  private convertDemandRawValueToDemand(rawDemand: DemandFormRawValue | NewDemandFormRawValue): IDemand | NewDemand {
    return {
      ...rawDemand,
      demandDate: dayjs(rawDemand.demandDate, DATE_TIME_FORMAT),
    };
  }

  private convertDemandToDemandRawValue(
    demand: IDemand | (Partial<NewDemand> & DemandFormDefaults),
  ): DemandFormRawValue | PartialWithRequiredKeyOf<NewDemandFormRawValue> {
    return {
      ...demand,
      demandDate: demand.demandDate ? demand.demandDate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}

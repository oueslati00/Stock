import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ICheckList, NewCheckList } from '../check-list.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICheckList for edit and NewCheckListFormGroupInput for create.
 */
type CheckListFormGroupInput = ICheckList | PartialWithRequiredKeyOf<NewCheckList>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ICheckList | NewCheckList> = Omit<T, 'date'> & {
  date?: string | null;
};

type CheckListFormRawValue = FormValueOf<ICheckList>;

type NewCheckListFormRawValue = FormValueOf<NewCheckList>;

type CheckListFormDefaults = Pick<NewCheckList, 'id' | 'date' | 'validate'>;

type CheckListFormGroupContent = {
  id: FormControl<CheckListFormRawValue['id'] | NewCheckList['id']>;
  client: FormControl<CheckListFormRawValue['client']>;
  contractNumber: FormControl<CheckListFormRawValue['contractNumber']>;
  adress: FormControl<CheckListFormRawValue['adress']>;
  technicienDef: FormControl<CheckListFormRawValue['technicienDef']>;
  date: FormControl<CheckListFormRawValue['date']>;
  tableDetectionTaskStatus: FormControl<CheckListFormRawValue['tableDetectionTaskStatus']>;
  tableDetectionComment: FormControl<CheckListFormRawValue['tableDetectionComment']>;
  diDMTaskStatus: FormControl<CheckListFormRawValue['diDMTaskStatus']>;
  diDMComment: FormControl<CheckListFormRawValue['diDMComment']>;
  detecteursPonctuelsTaskStatus: FormControl<CheckListFormRawValue['detecteursPonctuelsTaskStatus']>;
  detecteursPonctuelsComment: FormControl<CheckListFormRawValue['detecteursPonctuelsComment']>;
  declencheurManuelsTaskStatus: FormControl<CheckListFormRawValue['declencheurManuelsTaskStatus']>;
  declencheurManuelsComment: FormControl<CheckListFormRawValue['declencheurManuelsComment']>;
  tableMiseSecurityIncendieTaskStatus: FormControl<CheckListFormRawValue['tableMiseSecurityIncendieTaskStatus']>;
  tableMiseSecurityIncendieComment: FormControl<CheckListFormRawValue['tableMiseSecurityIncendieComment']>;
  alimentationSecoursTaskStatus: FormControl<CheckListFormRawValue['alimentationSecoursTaskStatus']>;
  alimentationSecoursComment: FormControl<CheckListFormRawValue['alimentationSecoursComment']>;
  equipementAlarmeTaskStatus: FormControl<CheckListFormRawValue['equipementAlarmeTaskStatus']>;
  equipementAlarmeComment: FormControl<CheckListFormRawValue['equipementAlarmeComment']>;
  dasTaskStatus: FormControl<CheckListFormRawValue['dasTaskStatus']>;
  dasComment: FormControl<CheckListFormRawValue['dasComment']>;
  arretTechniqueTaskStatus: FormControl<CheckListFormRawValue['arretTechniqueTaskStatus']>;
  arretTechniqueComment: FormControl<CheckListFormRawValue['arretTechniqueComment']>;
  baiePcsTaskStatus: FormControl<CheckListFormRawValue['baiePcsTaskStatus']>;
  baiePCScomment: FormControl<CheckListFormRawValue['baiePCScomment']>;
  superviseurTaskStatus: FormControl<CheckListFormRawValue['superviseurTaskStatus']>;
  superviseurComment: FormControl<CheckListFormRawValue['superviseurComment']>;
  validate: FormControl<CheckListFormRawValue['validate']>;
  createdBy: FormControl<CheckListFormRawValue['createdBy']>;
};

export type CheckListFormGroup = FormGroup<CheckListFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CheckListFormService {
  createCheckListFormGroup(checkList: CheckListFormGroupInput = { id: null }): CheckListFormGroup {
    const checkListRawValue = this.convertCheckListToCheckListRawValue({
      ...this.getFormDefaults(),
      ...checkList,
    });
    return new FormGroup<CheckListFormGroupContent>({
      id: new FormControl(
        { value: checkListRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      client: new FormControl(checkListRawValue.client),
      contractNumber: new FormControl(checkListRawValue.contractNumber),
      adress: new FormControl(checkListRawValue.adress),
      technicienDef: new FormControl(checkListRawValue.technicienDef),
      date: new FormControl(checkListRawValue.date),
      tableDetectionTaskStatus: new FormControl(checkListRawValue.tableDetectionTaskStatus),
      tableDetectionComment: new FormControl(checkListRawValue.tableDetectionComment),
      diDMTaskStatus: new FormControl(checkListRawValue.diDMTaskStatus),
      diDMComment: new FormControl(checkListRawValue.diDMComment),
      detecteursPonctuelsTaskStatus: new FormControl(checkListRawValue.detecteursPonctuelsTaskStatus),
      detecteursPonctuelsComment: new FormControl(checkListRawValue.detecteursPonctuelsComment),
      declencheurManuelsTaskStatus: new FormControl(checkListRawValue.declencheurManuelsTaskStatus),
      declencheurManuelsComment: new FormControl(checkListRawValue.declencheurManuelsComment),
      tableMiseSecurityIncendieTaskStatus: new FormControl(checkListRawValue.tableMiseSecurityIncendieTaskStatus),
      tableMiseSecurityIncendieComment: new FormControl(checkListRawValue.tableMiseSecurityIncendieComment),
      alimentationSecoursTaskStatus: new FormControl(checkListRawValue.alimentationSecoursTaskStatus),
      alimentationSecoursComment: new FormControl(checkListRawValue.alimentationSecoursComment),
      equipementAlarmeTaskStatus: new FormControl(checkListRawValue.equipementAlarmeTaskStatus),
      equipementAlarmeComment: new FormControl(checkListRawValue.equipementAlarmeComment),
      dasTaskStatus: new FormControl(checkListRawValue.dasTaskStatus),
      dasComment: new FormControl(checkListRawValue.dasComment),
      arretTechniqueTaskStatus: new FormControl(checkListRawValue.arretTechniqueTaskStatus),
      arretTechniqueComment: new FormControl(checkListRawValue.arretTechniqueComment),
      baiePcsTaskStatus: new FormControl(checkListRawValue.baiePcsTaskStatus),
      baiePCScomment: new FormControl(checkListRawValue.baiePCScomment),
      superviseurTaskStatus: new FormControl(checkListRawValue.superviseurTaskStatus),
      superviseurComment: new FormControl(checkListRawValue.superviseurComment),
      validate: new FormControl(checkListRawValue.validate),
      createdBy: new FormControl(checkListRawValue.createdBy),
    });
  }

  getCheckList(form: CheckListFormGroup): ICheckList | NewCheckList {
    return this.convertCheckListRawValueToCheckList(form.getRawValue() as CheckListFormRawValue | NewCheckListFormRawValue);
  }

  resetForm(form: CheckListFormGroup, checkList: CheckListFormGroupInput): void {
    const checkListRawValue = this.convertCheckListToCheckListRawValue({ ...this.getFormDefaults(), ...checkList });
    form.reset(
      {
        ...checkListRawValue,
        id: { value: checkListRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CheckListFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      date: currentTime,
      validate: false,
    };
  }

  private convertCheckListRawValueToCheckList(rawCheckList: CheckListFormRawValue | NewCheckListFormRawValue): ICheckList | NewCheckList {
    return {
      ...rawCheckList,
      date: dayjs(rawCheckList.date, DATE_TIME_FORMAT),
    };
  }

  private convertCheckListToCheckListRawValue(
    checkList: ICheckList | (Partial<NewCheckList> & CheckListFormDefaults),
  ): CheckListFormRawValue | PartialWithRequiredKeyOf<NewCheckListFormRawValue> {
    return {
      ...checkList,
      date: checkList.date ? checkList.date.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}

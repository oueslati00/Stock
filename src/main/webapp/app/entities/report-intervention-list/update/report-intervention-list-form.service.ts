import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IReportInterventionList, NewReportInterventionList } from '../report-intervention-list.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IReportInterventionList for edit and NewReportInterventionListFormGroupInput for create.
 */
type ReportInterventionListFormGroupInput = IReportInterventionList | PartialWithRequiredKeyOf<NewReportInterventionList>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IReportInterventionList | NewReportInterventionList> = Omit<
  T,
  'interventionDate' | 'interventionStartDate' | 'remiseServiceDate' | 'endDate'
> & {
  interventionDate?: string | null;
  interventionStartDate?: string | null;
  remiseServiceDate?: string | null;
  endDate?: string | null;
};

type ReportInterventionListFormRawValue = FormValueOf<IReportInterventionList>;

type NewReportInterventionListFormRawValue = FormValueOf<NewReportInterventionList>;

type ReportInterventionListFormDefaults = Pick<
  NewReportInterventionList,
  | 'id'
  | 'installationSousContract'
  | 'installationSousGarantie'
  | 'conditionDePayementCheque'
  | 'conditionPayementAutre'
  | 'miseEnServiceDefinitvie'
  | 'miseEnServicePartielle'
  | 'maintenancePreventive'
  | 'maintenanceCorrective'
  | 'interventionDate'
  | 'interventionStartDate'
  | 'remiseServiceDate'
  | 'endDate'
  | 'causeExterieurInstallation'
  | 'installationFonctionnelleApresInervention'
  | 'prestationsAchevees'
  | 'devisComplentaire'
  | 'bonPourCommand'
  | 'validation'
>;

type ReportInterventionListFormGroupContent = {
  id: FormControl<ReportInterventionListFormRawValue['id'] | NewReportInterventionList['id']>;
  site: FormControl<ReportInterventionListFormRawValue['site']>;
  codeAgence: FormControl<ReportInterventionListFormRawValue['codeAgence']>;
  affaireNumber: FormControl<ReportInterventionListFormRawValue['affaireNumber']>;
  contractNumber: FormControl<ReportInterventionListFormRawValue['contractNumber']>;
  installationAdress: FormControl<ReportInterventionListFormRawValue['installationAdress']>;
  interlocuteurIntervation: FormControl<ReportInterventionListFormRawValue['interlocuteurIntervation']>;
  tel: FormControl<ReportInterventionListFormRawValue['tel']>;
  installationSousContract: FormControl<ReportInterventionListFormRawValue['installationSousContract']>;
  installationSousGarantie: FormControl<ReportInterventionListFormRawValue['installationSousGarantie']>;
  adressFacturation: FormControl<ReportInterventionListFormRawValue['adressFacturation']>;
  interlocuteurFacturation: FormControl<ReportInterventionListFormRawValue['interlocuteurFacturation']>;
  conditionDePayementCheque: FormControl<ReportInterventionListFormRawValue['conditionDePayementCheque']>;
  conditionPayementAutre: FormControl<ReportInterventionListFormRawValue['conditionPayementAutre']>;
  conditionPayementComment: FormControl<ReportInterventionListFormRawValue['conditionPayementComment']>;
  miseEnServiceDefinitvie: FormControl<ReportInterventionListFormRawValue['miseEnServiceDefinitvie']>;
  miseEnServicePartielle: FormControl<ReportInterventionListFormRawValue['miseEnServicePartielle']>;
  maintenancePreventive: FormControl<ReportInterventionListFormRawValue['maintenancePreventive']>;
  maintenanceCorrective: FormControl<ReportInterventionListFormRawValue['maintenanceCorrective']>;
  bT: FormControl<ReportInterventionListFormRawValue['bT']>;
  anomalieSignalee: FormControl<ReportInterventionListFormRawValue['anomalieSignalee']>;
  interventionDate: FormControl<ReportInterventionListFormRawValue['interventionDate']>;
  interventionStartDate: FormControl<ReportInterventionListFormRawValue['interventionStartDate']>;
  remiseServiceDate: FormControl<ReportInterventionListFormRawValue['remiseServiceDate']>;
  endDate: FormControl<ReportInterventionListFormRawValue['endDate']>;
  natureIntervention: FormControl<ReportInterventionListFormRawValue['natureIntervention']>;
  causeExterieurInstallation: FormControl<ReportInterventionListFormRawValue['causeExterieurInstallation']>;
  installationFonctionnelleApresInervention: FormControl<ReportInterventionListFormRawValue['installationFonctionnelleApresInervention']>;
  autreInterventionsAPrevoir: FormControl<ReportInterventionListFormRawValue['autreInterventionsAPrevoir']>;
  clientApreciation: FormControl<ReportInterventionListFormRawValue['clientApreciation']>;
  respectDelais: FormControl<ReportInterventionListFormRawValue['respectDelais']>;
  qualiteIntervention: FormControl<ReportInterventionListFormRawValue['qualiteIntervention']>;
  qualiteDevoirConseil: FormControl<ReportInterventionListFormRawValue['qualiteDevoirConseil']>;
  prestationsAchevees: FormControl<ReportInterventionListFormRawValue['prestationsAchevees']>;
  devisComplentaire: FormControl<ReportInterventionListFormRawValue['devisComplentaire']>;
  technicienName: FormControl<ReportInterventionListFormRawValue['technicienName']>;
  codeTechnicien: FormControl<ReportInterventionListFormRawValue['codeTechnicien']>;
  validationClientName: FormControl<ReportInterventionListFormRawValue['validationClientName']>;
  validationNameFunction: FormControl<ReportInterventionListFormRawValue['validationNameFunction']>;
  validationClientDate: FormControl<ReportInterventionListFormRawValue['validationClientDate']>;
  bonPourCommand: FormControl<ReportInterventionListFormRawValue['bonPourCommand']>;
  createdBy: FormControl<ReportInterventionListFormRawValue['createdBy']>;
  validation: FormControl<ReportInterventionListFormRawValue['validation']>;
};

export type ReportInterventionListFormGroup = FormGroup<ReportInterventionListFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ReportInterventionListFormService {
  createReportInterventionListFormGroup(
    reportInterventionList: ReportInterventionListFormGroupInput = { id: null },
  ): ReportInterventionListFormGroup {
    const reportInterventionListRawValue = this.convertReportInterventionListToReportInterventionListRawValue({
      ...this.getFormDefaults(),
      ...reportInterventionList,
    });
    return new FormGroup<ReportInterventionListFormGroupContent>({
      id: new FormControl(
        { value: reportInterventionListRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      site: new FormControl(reportInterventionListRawValue.site),
      codeAgence: new FormControl(reportInterventionListRawValue.codeAgence),
      affaireNumber: new FormControl(reportInterventionListRawValue.affaireNumber),
      contractNumber: new FormControl(reportInterventionListRawValue.contractNumber),
      installationAdress: new FormControl(reportInterventionListRawValue.installationAdress),
      interlocuteurIntervation: new FormControl(reportInterventionListRawValue.interlocuteurIntervation),
      tel: new FormControl(reportInterventionListRawValue.tel),
      installationSousContract: new FormControl(reportInterventionListRawValue.installationSousContract),
      installationSousGarantie: new FormControl(reportInterventionListRawValue.installationSousGarantie),
      adressFacturation: new FormControl(reportInterventionListRawValue.adressFacturation),
      interlocuteurFacturation: new FormControl(reportInterventionListRawValue.interlocuteurFacturation),
      conditionDePayementCheque: new FormControl(reportInterventionListRawValue.conditionDePayementCheque),
      conditionPayementAutre: new FormControl(reportInterventionListRawValue.conditionPayementAutre),
      conditionPayementComment: new FormControl(reportInterventionListRawValue.conditionPayementComment),
      miseEnServiceDefinitvie: new FormControl(reportInterventionListRawValue.miseEnServiceDefinitvie),
      miseEnServicePartielle: new FormControl(reportInterventionListRawValue.miseEnServicePartielle),
      maintenancePreventive: new FormControl(reportInterventionListRawValue.maintenancePreventive),
      maintenanceCorrective: new FormControl(reportInterventionListRawValue.maintenanceCorrective),
      bT: new FormControl(reportInterventionListRawValue.bT),
      anomalieSignalee: new FormControl(reportInterventionListRawValue.anomalieSignalee),
      interventionDate: new FormControl(reportInterventionListRawValue.interventionDate),
      interventionStartDate: new FormControl(reportInterventionListRawValue.interventionStartDate),
      remiseServiceDate: new FormControl(reportInterventionListRawValue.remiseServiceDate),
      endDate: new FormControl(reportInterventionListRawValue.endDate),
      natureIntervention: new FormControl(reportInterventionListRawValue.natureIntervention),
      causeExterieurInstallation: new FormControl(reportInterventionListRawValue.causeExterieurInstallation),
      installationFonctionnelleApresInervention: new FormControl(reportInterventionListRawValue.installationFonctionnelleApresInervention),
      autreInterventionsAPrevoir: new FormControl(reportInterventionListRawValue.autreInterventionsAPrevoir),
      clientApreciation: new FormControl(reportInterventionListRawValue.clientApreciation),
      respectDelais: new FormControl(reportInterventionListRawValue.respectDelais),
      qualiteIntervention: new FormControl(reportInterventionListRawValue.qualiteIntervention),
      qualiteDevoirConseil: new FormControl(reportInterventionListRawValue.qualiteDevoirConseil),
      prestationsAchevees: new FormControl(reportInterventionListRawValue.prestationsAchevees),
      devisComplentaire: new FormControl(reportInterventionListRawValue.devisComplentaire),
      technicienName: new FormControl(reportInterventionListRawValue.technicienName),
      codeTechnicien: new FormControl(reportInterventionListRawValue.codeTechnicien),
      validationClientName: new FormControl(reportInterventionListRawValue.validationClientName),
      validationNameFunction: new FormControl(reportInterventionListRawValue.validationNameFunction),
      validationClientDate: new FormControl(reportInterventionListRawValue.validationClientDate),
      bonPourCommand: new FormControl(reportInterventionListRawValue.bonPourCommand),
      createdBy: new FormControl(reportInterventionListRawValue.createdBy),
      validation: new FormControl(reportInterventionListRawValue.validation),
    });
  }

  getReportInterventionList(form: ReportInterventionListFormGroup): IReportInterventionList | NewReportInterventionList {
    return this.convertReportInterventionListRawValueToReportInterventionList(
      form.getRawValue() as ReportInterventionListFormRawValue | NewReportInterventionListFormRawValue,
    );
  }

  resetForm(form: ReportInterventionListFormGroup, reportInterventionList: ReportInterventionListFormGroupInput): void {
    const reportInterventionListRawValue = this.convertReportInterventionListToReportInterventionListRawValue({
      ...this.getFormDefaults(),
      ...reportInterventionList,
    });
    form.reset(
      {
        ...reportInterventionListRawValue,
        id: { value: reportInterventionListRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ReportInterventionListFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      installationSousContract: false,
      installationSousGarantie: false,
      conditionDePayementCheque: false,
      conditionPayementAutre: false,
      miseEnServiceDefinitvie: false,
      miseEnServicePartielle: false,
      maintenancePreventive: false,
      maintenanceCorrective: false,
      interventionDate: currentTime,
      interventionStartDate: currentTime,
      remiseServiceDate: currentTime,
      endDate: currentTime,
      causeExterieurInstallation: false,
      installationFonctionnelleApresInervention: false,
      prestationsAchevees: false,
      devisComplentaire: false,
      bonPourCommand: false,
      validation: false,
    };
  }

  private convertReportInterventionListRawValueToReportInterventionList(
    rawReportInterventionList: ReportInterventionListFormRawValue | NewReportInterventionListFormRawValue,
  ): IReportInterventionList | NewReportInterventionList {
    return {
      ...rawReportInterventionList,
      interventionDate: dayjs(rawReportInterventionList.interventionDate, DATE_TIME_FORMAT),
      interventionStartDate: dayjs(rawReportInterventionList.interventionStartDate, DATE_TIME_FORMAT),
      remiseServiceDate: dayjs(rawReportInterventionList.remiseServiceDate, DATE_TIME_FORMAT),
      endDate: dayjs(rawReportInterventionList.endDate, DATE_TIME_FORMAT),
    };
  }

  private convertReportInterventionListToReportInterventionListRawValue(
    reportInterventionList: IReportInterventionList | (Partial<NewReportInterventionList> & ReportInterventionListFormDefaults),
  ): ReportInterventionListFormRawValue | PartialWithRequiredKeyOf<NewReportInterventionListFormRawValue> {
    return {
      ...reportInterventionList,
      interventionDate: reportInterventionList.interventionDate
        ? reportInterventionList.interventionDate.format(DATE_TIME_FORMAT)
        : undefined,
      interventionStartDate: reportInterventionList.interventionStartDate
        ? reportInterventionList.interventionStartDate.format(DATE_TIME_FORMAT)
        : undefined,
      remiseServiceDate: reportInterventionList.remiseServiceDate
        ? reportInterventionList.remiseServiceDate.format(DATE_TIME_FORMAT)
        : undefined,
      endDate: reportInterventionList.endDate ? reportInterventionList.endDate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}

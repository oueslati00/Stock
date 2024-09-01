import {
  ReportInterventionListFormGroup,
  ReportInterventionListFormService,
} from '../../../entities/report-intervention-list/update/report-intervention-list-form.service';
import { Injectable } from '@angular/core';
import dayjs from 'dayjs/esm';
import {
  IReportInterventionList,
  NewReportInterventionList,
} from '../../../entities/report-intervention-list/report-intervention-list.model';
import { DATE_FORMAT, DATE_TIME_FORMAT, TIME_FORMAT, validDateFormat } from '../../../config/input.constants';
@Injectable({ providedIn: 'root' })
export class ServiceReportInter extends ReportInterventionListFormService {
  resetForm(form: ReportInterventionListFormGroup, reportInterventionList: any): void {
    const reportInterventionListRawValue = this.convertReportInterventionListToReportInterventionListRawValueExt({
      ...this.getFormDefaultsExt(),
      ...reportInterventionList,
    });
    form.reset(
      {
        ...reportInterventionListRawValue,
        id: { value: reportInterventionListRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaultsExt(): any {
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
    };
  }
  private convertReportInterventionListToReportInterventionListRawValueExt(
    reportInterventionList: IReportInterventionList | Partial<NewReportInterventionList>,
  ): any {
    console.log('convertReportInterventionListToReportInterventionListRawValueExt');
    console.log({ 'interventionDAte!': reportInterventionList.interventionDate?.format(validDateFormat) });
    return {
      ...reportInterventionList,
      interventionDate: reportInterventionList.interventionDate
        ? reportInterventionList.interventionDate.format(validDateFormat)
        : undefined,
      interventionStartDate: reportInterventionList.interventionStartDate
        ? reportInterventionList.interventionStartDate.format(TIME_FORMAT)
        : undefined,
      remiseServiceDate: reportInterventionList.remiseServiceDate
        ? reportInterventionList.remiseServiceDate.format(TIME_FORMAT)
        : undefined,
      endDate: reportInterventionList.endDate ? reportInterventionList.endDate.format(TIME_FORMAT) : undefined,
    };
  }
}

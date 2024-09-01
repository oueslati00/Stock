import { ReportInterventionListUpdateComponent } from '../../../entities/report-intervention-list/update/report-intervention-list-update.component';
import { Component, inject, OnInit } from '@angular/core';
import SharedModule from '../../../shared/shared.module';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  IReportInterventionList,
  NewReportInterventionList,
} from '../../../entities/report-intervention-list/report-intervention-list.model';
import {
  ReportInterventionListFormGroup,
  ReportInterventionListFormService,
} from '../../../entities/report-intervention-list/update/report-intervention-list-form.service';
import dayjs from 'dayjs/esm';
import { DATE_FORMAT, DATE_TIME_FORMAT, TIME_FORMAT } from '../../../config/input.constants';
import { Dayjs } from 'dayjs';
import { ServiceReportInter } from '../serviceReportIntervention/serviceReportInter';
import { AccountService } from '../../../core/auth/account.service';
@Component({
  standalone: true,
  selector: 'report-intervention-list-update',
  templateUrl: './report-intervention-list-update.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ReportInterventionListUpdateComponentExt extends ReportInterventionListUpdateComponent implements OnInit {
  protected reportInterventionListFormService = inject(ReportInterventionListFormService);
  protected serviceReportInter = inject(ServiceReportInter);
  protected currentUserService = inject(AccountService);
  ngOnInit() {
    super.ngOnInit();
  }

  save(): void {
    console.log('overriding save method was executed ');
    this.isSaving = true;
    const reportInterventionList = this.getReportInterventionList(this.editForm);
    if (reportInterventionList.id !== null) {
      reportInterventionList.validation = null;
      this.subscribeToSaveResponse(this.reportInterventionListService.update(reportInterventionList));
    } else {
      reportInterventionList.createdBy = this.getCurrentUserName();
      this.subscribeToSaveResponse(this.reportInterventionListService.create(reportInterventionList));
    }
  }

  getCurrentUserName(): string {
    const currentAccount = this.currentUserService.trackCurrentAccount();
    const firsName = currentAccount()?.firstName;
    const lastName = currentAccount()?.lastName;
    return `${firsName} ${lastName}`;
  }

  getReportInterventionList(form: ReportInterventionListFormGroup): IReportInterventionList | NewReportInterventionList {
    const interventionDate = dayjs(form.getRawValue().interventionDate, DATE_FORMAT);
    const inteventionStartDateTime = dayjs(form.getRawValue().interventionStartDate, TIME_FORMAT);
    const remiseServiceDateTime = dayjs(form.getRawValue().remiseServiceDate, TIME_FORMAT);
    const endDateTime = dayjs(form.getRawValue().endDate, TIME_FORMAT);
    let intervationStartDate = dayjs(form.getRawValue().interventionDate, DATE_FORMAT)
      .hour(inteventionStartDateTime.hour())
      .minute(inteventionStartDateTime.minute());
    let remiseServiceDate = dayjs(form.getRawValue().interventionDate, DATE_FORMAT)
      .minute(remiseServiceDateTime.minute())
      .hour(remiseServiceDateTime.hour());
    let endDate = dayjs(form.getRawValue().interventionDate, DATE_FORMAT).minute(endDateTime.minute()).hour(endDateTime.hour());
    return {
      ...form.getRawValue(),
      interventionDate: interventionDate,
      interventionStartDate: intervationStartDate,
      remiseServiceDate: remiseServiceDate,
      endDate: endDate,
    };
  }
  protected updateForm(reportInterventionList: IReportInterventionList): void {
    console.log('update form method executed');
    super.updateForm(reportInterventionList);

    this.serviceReportInter.resetForm(this.editForm, reportInterventionList);
  }
}

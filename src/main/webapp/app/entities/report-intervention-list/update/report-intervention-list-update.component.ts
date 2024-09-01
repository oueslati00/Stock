import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { EvaluationStatus } from 'app/entities/enumerations/evaluation-status.model';
import { ReportInterventionListService } from '../service/report-intervention-list.service';
import { IReportInterventionList } from '../report-intervention-list.model';
import { ReportInterventionListFormService, ReportInterventionListFormGroup } from './report-intervention-list-form.service';

@Component({
  standalone: true,
  selector: 'jhi-report-intervention-list-update',
  templateUrl: './report-intervention-list-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ReportInterventionListUpdateComponent implements OnInit {
  isSaving = false;
  reportInterventionList: IReportInterventionList | null = null;
  evaluationStatusValues = Object.keys(EvaluationStatus);

  protected dataUtils = inject(DataUtils);
  protected eventManager = inject(EventManager);
  protected reportInterventionListService = inject(ReportInterventionListService);
  protected reportInterventionListFormService = inject(ReportInterventionListFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ReportInterventionListFormGroup = this.reportInterventionListFormService.createReportInterventionListFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reportInterventionList }) => {
      this.reportInterventionList = reportInterventionList;
      if (reportInterventionList) {
        this.updateForm(reportInterventionList);
      }
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('stockProjectApp.error', { ...err, key: 'error.file.' + err.key })),
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reportInterventionList = this.reportInterventionListFormService.getReportInterventionList(this.editForm);
    if (reportInterventionList.id !== null) {
      this.subscribeToSaveResponse(this.reportInterventionListService.update(reportInterventionList));
    } else {
      this.subscribeToSaveResponse(this.reportInterventionListService.create(reportInterventionList));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReportInterventionList>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(reportInterventionList: IReportInterventionList): void {
    this.reportInterventionList = reportInterventionList;
    this.reportInterventionListFormService.resetForm(this.editForm, reportInterventionList);
  }
}

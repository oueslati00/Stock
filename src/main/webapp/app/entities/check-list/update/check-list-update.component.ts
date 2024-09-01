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
import { TaskStatus } from 'app/entities/enumerations/task-status.model';
import { CheckListService } from '../service/check-list.service';
import { ICheckList } from '../check-list.model';
import { CheckListFormService, CheckListFormGroup } from './check-list-form.service';

@Component({
  standalone: true,
  selector: 'jhi-check-list-update',
  templateUrl: './check-list-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CheckListUpdateComponent implements OnInit {
  isSaving = false;
  checkList: ICheckList | null = null;
  taskStatusValues = Object.keys(TaskStatus);

  protected dataUtils = inject(DataUtils);
  protected eventManager = inject(EventManager);
  protected checkListService = inject(CheckListService);
  protected checkListFormService = inject(CheckListFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: CheckListFormGroup = this.checkListFormService.createCheckListFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ checkList }) => {
      this.checkList = checkList;
      if (checkList) {
        this.updateForm(checkList);
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
    const checkList = this.checkListFormService.getCheckList(this.editForm);
    if (checkList.id !== null) {
      this.subscribeToSaveResponse(this.checkListService.update(checkList));
    } else {
      this.subscribeToSaveResponse(this.checkListService.create(checkList));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICheckList>>): void {
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

  protected updateForm(checkList: ICheckList): void {
    this.checkList = checkList;
    this.checkListFormService.resetForm(this.editForm, checkList);
  }
}

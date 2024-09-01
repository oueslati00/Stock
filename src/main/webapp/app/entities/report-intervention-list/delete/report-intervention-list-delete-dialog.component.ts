import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IReportInterventionList } from '../report-intervention-list.model';
import { ReportInterventionListService } from '../service/report-intervention-list.service';

@Component({
  standalone: true,
  templateUrl: './report-intervention-list-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ReportInterventionListDeleteDialogComponent {
  reportInterventionList?: IReportInterventionList;

  protected reportInterventionListService = inject(ReportInterventionListService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reportInterventionListService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}

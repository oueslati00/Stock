import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IDemand } from '../demand.model';
import { DemandService } from '../service/demand.service';

@Component({
  standalone: true,
  templateUrl: './demand-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class DemandDeleteDialogComponent {
  demand?: IDemand;

  protected demandService = inject(DemandService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.demandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}

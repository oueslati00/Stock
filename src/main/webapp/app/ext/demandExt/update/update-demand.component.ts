import { Component, computed, inject, OnInit } from '@angular/core';
import SharedModule from '../../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DemandUpdateComponent } from '../../../entities/demand/update/demand-update.component';
import { AccountService } from '../../../core/auth/account.service';
import { Status } from '../../../entities/enumerations/status.model';
@Component({
  standalone: true,
  selector: 'demand-update',
  templateUrl: './demand-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DemandUpdateComponentExt extends DemandUpdateComponent implements OnInit {
  protected currentUserService = inject(AccountService);
  ngOnInit() {
    super.ngOnInit();
  }

  save(): void {
    this.isSaving = true;
    const demand = this.demandFormService.getDemand(this.editForm);
    if (demand.id !== null) {
      demand.validate = null;
      this.subscribeToSaveResponse(this.demandService.update(demand));
    } else {
      demand.demandBy = this.getCurrentUserName();
      demand.status = this.getDefaultStatus();
      this.subscribeToSaveResponse(this.demandService.create(demand));
    }
  }

  getCurrentUserName(): string {
    const currentAccount = this.currentUserService.trackCurrentAccount();
    const firsName = currentAccount()?.firstName;
    const lastName = currentAccount()?.lastName;
    return `${firsName} ${lastName}`;
  }
  getDefaultStatus(): Status {
    return Status.IN_PROGRESS;
  }

  hasRole(role: string) {
    this.currentUserService.hasAnyAuthority('');
  }
}

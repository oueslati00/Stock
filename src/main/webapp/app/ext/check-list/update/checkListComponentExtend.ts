import { Component, inject, OnInit } from '@angular/core';
import SharedModule from '../../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CheckListComponent } from '../../../entities/check-list/list/check-list.component';
import { CheckListUpdateComponent } from '../../../entities/check-list/update/check-list-update.component';
import { AccountService } from '../../../core/auth/account.service';

@Component({
  standalone: true,
  selector: 'check-list-update',
  templateUrl: './checkListUpdate.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CheckListComponentExtend extends CheckListUpdateComponent implements OnInit {
  protected currentUserService = inject(AccountService);
  ngOnInit() {
    super.ngOnInit();
  }
  getCurrentUserName(): string {
    const currentAccount = this.currentUserService.trackCurrentAccount();
    const firsName = currentAccount()?.firstName;
    const lastName = currentAccount()?.lastName;
    return `${firsName} ${lastName}`;
  }

  save() {
    this.isSaving = true;
    const checklist = this.checkListFormService.getCheckList(this.editForm);
    if (checklist.id !== null) {
      checklist.validate = null;
      this.subscribeToSaveResponse(this.checkListService.update(checklist));
    } else {
      checklist.createdBy = this.getCurrentUserName();
      this.subscribeToSaveResponse(this.checkListService.create(checklist));
    }
  }
}

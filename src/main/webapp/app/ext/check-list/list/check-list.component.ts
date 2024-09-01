import { CheckListComponent as checkList } from '../../../entities/check-list/list/check-list.component';
import { Component, inject, OnInit } from '@angular/core';
import SharedModule from '../../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { SortByDirective, SortDirective } from '../../../shared/sort';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from '../../../shared/date';
import { FilterComponent } from '../../../shared/filter';
import { ItemCountComponent } from '../../../shared/pagination';
import { ICheckList } from '../../../entities/check-list/check-list.model';
import { EntityArrayResponseType, EntityResponseType } from '../../../entities/check-list/service/check-list.service';
import { Observable, tap } from 'rxjs';
import { AccountService } from '../../../core/auth/account.service';

@Component({
  standalone: true,
  selector: 'check-list',
  templateUrl: './check-list.component.html',
  imports: [
    RouterModule,
    FormsModule,
    SharedModule,
    SortDirective,
    SortByDirective,
    DurationPipe,
    FormatMediumDatetimePipe,
    FormatMediumDatePipe,
    FilterComponent,
    ItemCountComponent,
  ],
})
export class CheckListComponent extends checkList implements OnInit {
  protected currentUserService = inject(AccountService);
  ngOnInit() {
    super.ngOnInit();
  }
  currentRoleIsTechnicien(): boolean {
    return this.currentUserService.hasAnyAuthority('ROLE_TECH');
  }
  currentUserName(): string {
    const currentAccount = this.currentUserService.trackCurrentAccount();
    const firsName = currentAccount()?.firstName;
    const lastName = currentAccount()?.lastName;
    return `${firsName} ${lastName}`;
  }

  validate(checkList: ICheckList, b: boolean) {
    checkList.validate = b;
    this.checkListService.update(checkList).subscribe({
      next: (checkL: EntityResponseType) => {
        const check = checkL.body as ICheckList;
        this.checkLists?.filter(x => x.id === checkList.id).map(x => ({ ...check }));
        location.reload();
      },
    });
  }

  protected queryBackend(): Observable<EntityArrayResponseType> {
    const { page, filters, currentSearch } = this;

    this.isLoading = true;
    const pageToLoad: number = page;
    let queryObject: any = {
      page: pageToLoad - 1,
      size: this.itemsPerPage,
      query: currentSearch,
      sort: this.sortService.buildSortParam(this.sortState()),
    };
    if (this.currentRoleIsTechnicien()) {
      queryObject = { ...queryObject, 'createdBy.equals': this.currentUserName() };
    }
    filters.filterOptions.forEach(filterOption => {
      queryObject[filterOption.name] = filterOption.values;
    });
    if (this.currentSearch && this.currentSearch !== '') {
      return this.checkListService.search(queryObject).pipe(tap(() => (this.isLoading = false)));
    } else {
      return this.checkListService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
    }
  }
}

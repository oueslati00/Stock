import { ProductComponent } from '../../../entities/product/list/product.component';
import { Component, inject, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import SharedModule from '../../../shared/shared.module';
import { SortByDirective, SortDirective } from '../../../shared/sort';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from '../../../shared/date';
import { FilterComponent } from '../../../shared/filter';
import { ItemCountComponent } from '../../../shared/pagination';
import { AccountService } from '../../../core/auth/account.service';

@Component({
  standalone: true,
  selector: 'product',
  templateUrl: './product.component.html',
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
export class ProductComponentExt extends ProductComponent implements OnInit {
  protected currentUserService = inject(AccountService);
  ngOnInit() {
    super.ngOnInit();
  }

  currentUserCanAddUpdateDeleteProduct(): boolean {
    const roles = ['ROLE_RES_MAINTENANCE', 'ROLE_ADMIN'];
    return this.currentUserService.hasAnyAuthority(roles);
  }
}

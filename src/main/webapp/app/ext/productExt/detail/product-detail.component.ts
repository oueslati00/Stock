import { ProductDetailComponent } from '../../../entities/product/detail/product-detail.component';
import { Component, inject, OnInit } from '@angular/core';
import SharedModule from '../../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from '../../../shared/date';
import { AccountService } from '../../../core/auth/account.service';
@Component({
  standalone: true,
  selector: 'product-detail',
  templateUrl: './product-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ProductDetailComponentExt extends ProductDetailComponent {
  protected currentUserService = inject(AccountService);
  currentUserCanAddUpdateDeleteProduct(): boolean {
    const roles = ['ROLE_RES_MAINTENANCE', 'ROLE_ADMIN'];
    return this.currentUserService.hasAnyAuthority(roles);
  }
}

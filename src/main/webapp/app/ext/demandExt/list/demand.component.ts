import { DemandComponent } from '../../../entities/demand/list/demand.component';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import SharedModule from '../../../shared/shared.module';
import { SortByDirective, SortDirective } from '../../../shared/sort';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from '../../../shared/date';
import { FilterComponent } from '../../../shared/filter';
import { ItemCountComponent } from '../../../shared/pagination';
import { IDemand } from '../../../entities/demand/demand.model';
import { Status } from '../../../entities/enumerations/status.model';
import { finalize } from 'rxjs/operators';
import { EntityResponseType } from '../../../entities/demand/service/demand.service';
import { Observable } from 'rxjs';
import HasAnyAuthorityDirective from '../../../shared/auth/has-any-authority.directive';
@Component({
  standalone: true,
  selector: 'demand',
  templateUrl: './demand.component.html',
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
    HasAnyAuthorityDirective,
  ],
})
export class DemandComponentExt extends DemandComponent implements OnInit {
  ngOnInit() {
    super.ngOnInit();
  }

  setAccept(demand: IDemand, b: boolean): void {
    demand.validate = b;
    this.demandService.update(demand).subscribe({
      next: (demand: EntityResponseType) => {
        const value = demand.body as IDemand;
        this.demands?.filter(x => x.id === value.id).map(() => ({ ...value }));
        location.reload();
      },
    });
  }

  protected readonly Status = Status;
}

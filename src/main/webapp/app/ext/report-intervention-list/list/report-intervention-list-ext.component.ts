import { ReportInterventionListComponent } from '../../../entities/report-intervention-list/list/report-intervention-list.component';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import SharedModule from '../../../shared/shared.module';
import { SortByDirective, SortDirective } from '../../../shared/sort';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from '../../../shared/date';
import { FilterComponent } from '../../../shared/filter';
import { ItemCountComponent } from '../../../shared/pagination';
import { IReportInterventionList } from '../../../entities/report-intervention-list/report-intervention-list.model';
import { EntityResponseType } from '../../../entities/user/service/user.service';
import HasAnyAuthorityDirective from '../../../shared/auth/has-any-authority.directive';
@Component({
  standalone: true,
  selector: 'report-intervention-list',
  templateUrl: './report-intervention-list-ext.component.html',
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
export class ReportInterventionListExtComponent extends ReportInterventionListComponent implements OnInit {
  ngOnInit() {
    super.ngOnInit();
  }

  validate(reportInterventionList: IReportInterventionList, b: boolean) {
    reportInterventionList.validation = b;
    this.reportInterventionListService.update(reportInterventionList).subscribe({
      next: (rep: EntityResponseType) => {
        const report = rep.body as IReportInterventionList;
        this.reportInterventionLists?.filter(x => x.id === reportInterventionList.id).map(() => ({ ...report }));
        location.reload();
      },
    });
  }
}

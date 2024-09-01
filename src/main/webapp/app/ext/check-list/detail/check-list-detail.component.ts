import { CheckListDetailComponent } from '../../../entities/check-list/detail/check-list-detail.component';
import { Component, OnInit } from '@angular/core';
import SharedModule from '../../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from '../../../shared/date';
@Component({
  standalone: true,
  selector: 'heck-list-detail',
  templateUrl: './check-list-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class CheckListDetailComponentExt extends CheckListDetailComponent {}

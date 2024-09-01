import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IDemand } from '../demand.model';

@Component({
  standalone: true,
  selector: 'jhi-demand-detail',
  templateUrl: './demand-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class DemandDetailComponent {
  demand = input<IDemand | null>(null);

  previousState(): void {
    window.history.back();
  }
}

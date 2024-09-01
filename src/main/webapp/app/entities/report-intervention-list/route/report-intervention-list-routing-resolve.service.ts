import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IReportInterventionList } from '../report-intervention-list.model';
import { ReportInterventionListService } from '../service/report-intervention-list.service';

const reportInterventionListResolve = (route: ActivatedRouteSnapshot): Observable<null | IReportInterventionList> => {
  const id = route.params['id'];
  if (id) {
    return inject(ReportInterventionListService)
      .find(id)
      .pipe(
        mergeMap((reportInterventionList: HttpResponse<IReportInterventionList>) => {
          if (reportInterventionList.body) {
            return of(reportInterventionList.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default reportInterventionListResolve;

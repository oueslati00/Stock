import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDemand } from '../demand.model';
import { DemandService } from '../service/demand.service';

const demandResolve = (route: ActivatedRouteSnapshot): Observable<null | IDemand> => {
  const id = route.params['id'];
  if (id) {
    return inject(DemandService)
      .find(id)
      .pipe(
        mergeMap((demand: HttpResponse<IDemand>) => {
          if (demand.body) {
            return of(demand.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default demandResolve;

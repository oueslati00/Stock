import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { DemandDetailComponent } from '../../entities/demand/detail/demand-detail.component';
import DemandResolve from './../../entities/demand/route/demand-routing-resolve.service';
import { DemandUpdateComponent } from '../../entities/demand/update/demand-update.component';
import { DemandComponentExt } from './list/demand.component';
import { DemandUpdateComponentExt } from './update/update-demand.component';

const demandRoute: Routes = [
  {
    path: '',
    component: DemandComponentExt,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DemandDetailComponent,
    resolve: {
      demand: DemandResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DemandUpdateComponentExt,
    resolve: {
      demand: DemandResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DemandUpdateComponentExt,
    resolve: {
      demand: DemandResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default demandRoute;

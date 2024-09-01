import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { DemandComponent } from './list/demand.component';
import { DemandDetailComponent } from './detail/demand-detail.component';
import { DemandUpdateComponent } from './update/demand-update.component';
import DemandResolve from './route/demand-routing-resolve.service';

const demandRoute: Routes = [
  {
    path: '',
    component: DemandComponent,
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
    component: DemandUpdateComponent,
    resolve: {
      demand: DemandResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DemandUpdateComponent,
    resolve: {
      demand: DemandResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default demandRoute;

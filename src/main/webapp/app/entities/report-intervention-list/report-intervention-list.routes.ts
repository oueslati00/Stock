import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ReportInterventionListComponent } from './list/report-intervention-list.component';
import { ReportInterventionListDetailComponent } from './detail/report-intervention-list-detail.component';
import { ReportInterventionListUpdateComponent } from './update/report-intervention-list-update.component';
import ReportInterventionListResolve from './route/report-intervention-list-routing-resolve.service';

const reportInterventionListRoute: Routes = [
  {
    path: '',
    component: ReportInterventionListComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReportInterventionListDetailComponent,
    resolve: {
      reportInterventionList: ReportInterventionListResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReportInterventionListUpdateComponent,
    resolve: {
      reportInterventionList: ReportInterventionListResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReportInterventionListUpdateComponent,
    resolve: {
      reportInterventionList: ReportInterventionListResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default reportInterventionListRoute;

import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ReportInterventionListDetailComponent } from '../../entities/report-intervention-list/detail/report-intervention-list-detail.component';
import { ReportInterventionListUpdateComponentExt } from './update/report-intervention-list-update';
import ReportInterventionListResolve from '../../entities/report-intervention-list/route/report-intervention-list-routing-resolve.service';
import { ReportInterventionListExtComponent } from './list/report-intervention-list-ext.component';

const reportInterventionListRoute: Routes = [
  {
    path: '',
    component: ReportInterventionListExtComponent,
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
    component: ReportInterventionListUpdateComponentExt,
    resolve: {
      reportInterventionList: ReportInterventionListResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReportInterventionListUpdateComponentExt,
    resolve: {
      reportInterventionList: ReportInterventionListResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default reportInterventionListRoute;

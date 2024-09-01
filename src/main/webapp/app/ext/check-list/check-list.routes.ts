import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { CheckListUpdateComponent } from '../../entities/check-list/update/check-list-update.component';
import CheckListResolve from '../../entities/check-list/route/check-list-routing-resolve.service';
import { CheckListComponentExtend } from './update/checkListComponentExtend';
import { CheckListComponent } from './list/check-list.component';
import { CheckListDetailComponentExt } from './detail/check-list-detail.component';

const checkListRoute: Routes = [
  {
    path: '',
    component: CheckListComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CheckListDetailComponentExt,
    resolve: {
      checkList: CheckListResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CheckListComponentExtend,
    resolve: {
      checkList: CheckListResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CheckListComponentExtend,
    resolve: {
      checkList: CheckListResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default checkListRoute;

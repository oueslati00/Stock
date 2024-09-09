import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import { ProductComponent } from '../../entities/product/list/product.component';
import { ProductDetailComponent } from '../../entities/product/detail/product-detail.component';
import { ProductUpdateComponentExtend } from './update/ProductUpdateComponentExtend';
import ProductResolve from '../../entities/product/route/product-routing-resolve.service';
import { ProductUpdateComponent } from '../../entities/product/update/product-update.component';
import { ProductComponentExt } from './list/product.component';
import { ProductDetailComponentExt } from './detail/product-detail.component';

const productRoute: Routes = [
  {
    path: '',
    component: ProductComponentExt,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProductDetailComponentExt,
    resolve: {
      product: ProductResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProductUpdateComponentExtend,
    resolve: {
      product: ProductResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProductUpdateComponentExtend,
    resolve: {
      product: ProductResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default productRoute;

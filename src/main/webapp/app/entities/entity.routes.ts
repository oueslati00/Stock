import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'stockProjectApp.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'product',
    data: { pageTitle: 'stockProjectApp.product.home.title' },
    loadChildren: () => import('../ext/productExt/product.routes'),
  },
  {
    path: 'demand',
    data: { pageTitle: 'stockProjectApp.demand.home.title' },
    loadChildren: () => import('./../ext/demandExt/demand.routes'),
  },
  {
    path: 'check-list',
    data: { pageTitle: 'stockProjectApp.checkList.home.title' },
    loadChildren: () => import('../ext/check-list/check-list.routes'),
  },
  {
    path: 'report-intervention-list',
    data: { pageTitle: 'stockProjectApp.reportInterventionList.home.title' },
    loadChildren: () => import('../ext/report-intervention-list/report-intervention-list.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;

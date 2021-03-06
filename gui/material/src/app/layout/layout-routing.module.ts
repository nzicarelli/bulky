import { RouterModule, Routes } from '@angular/router';

import { LayoutComponent } from './layout.component';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { AuthGuard } from '../services/auth-guard';

const routes: Routes = [
    {
        path: 'app',
        component: LayoutComponent,
        children: [
            {path: '', redirectTo: '/app/dashboard', pathMatch: 'full'},
            {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
            {path: 'chart', loadChildren: '../charts/charts.module#ChartsModule'},
            {path: 'ecommerce', loadChildren: '../ecommerce/ecommerce.module#ECommerceModule'},
            {path: 'form', loadChildren: '../forms/forms.module#MyFormsModule'},
            {path: 'page', loadChildren: '../pages/pages.module#PagesModule'},
            {path: 'pglayout', loadChildren: '../page-layouts/page-layouts.module#PageLayoutsModule'},
            {path: 'table', loadChildren: '../tables/tables.module#MyTablesModule'},
            {path: 'ui', loadChildren: '../ui/ui.module#UIModule'},
            {path: 'crm', loadChildren: '../crm/crm.module#CrmModule'},
        ]
    }
];

export const LayoutRoutingModule = RouterModule.forChild(routes);

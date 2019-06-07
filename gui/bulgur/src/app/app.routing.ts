import {ExtraOptions, RouterModule, Routes} from '@angular/router';

import {DashboardComponent} from './dashboard/dashboard.component';
import {UserComponent} from './user/user.component';
import {TableComponent} from './table/table.component';
import {TypographyComponent} from './typography/typography.component';
import {IconsComponent} from './icons/icons.component';
import {MapsComponent} from './maps/maps.component';
import {NotificationsComponent} from './notifications/notifications.component';
import {UpgradeComponent} from './upgrade/upgrade.component';
import {LoginComponent} from './user/login/login.component';
import {AuthGuard} from './services/auth-guard';
import {NgModule} from '@angular/core';


export const AppRoutes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'user',
    component: UserComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'table',
    component: TableComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'typography',
    component: TypographyComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'icons',
    component: IconsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'maps',
    component: MapsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'notifications',
    component: NotificationsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'upgrade',
    component: UpgradeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'crm',
    loadChildren: './crm/crm.module#CrmModule',
    canActivate: [AuthGuard]
  }
];
const config: ExtraOptions = {
  useHash: true,
};
@NgModule({
  imports: [RouterModule.forRoot(AppRoutes, config)],
  exports: [RouterModule]
})
export class AppRouting {
}

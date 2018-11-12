import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders, NgModule} from '@angular/core';


import {AuthGuard} from './_guard/auth.guard';

import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {UserModule} from "./user/user.module";
import {CustomerModule} from "./customer/customer.module";

export const routes: Routes = [
  {path: '', component: AppComponent},
  {path: 'user', loadChildren: () => UserModule, canActivate: [AuthGuard]},
  {path: 'customer', loadChildren: () => CustomerModule, canActivate: [AuthGuard]},
  //{path: 'user', loadChildren: './app.module#UserModule', canActivate: [AuthGuard]},
  // {path: 'customer', loadChildren: './app.module#CustomerModule', canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutes { }

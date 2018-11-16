import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserHomeComponent} from "./user-home/user-home.component";
import {AuthUserGuard} from "../_guard/authUser.guard";
import {UserLeadListComponent} from "./lead/user-lead-list/user-lead-list.component";
import {UserListComponent} from "./lead/user-list/user-list.component";
import { ProxyRouteComponent } from './proxy-route/proxy-route.component';

const routes: Routes = [
  { path: '', component: UserHomeComponent, canActivate: [AuthUserGuard]  } ,
  { path: 'list-user', component: UserListComponent, outlet: 'userOut', canActivate: [AuthUserGuard]  },
  { path: 'list-lead', component: UserLeadListComponent, outlet: 'userOut', canActivate: [AuthUserGuard]  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }

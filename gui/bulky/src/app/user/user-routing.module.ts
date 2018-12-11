import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserHomeComponent} from "./user-home/user-home.component";
import {AuthUserGuard} from "../_guard/authUser.guard";
import {UserLeadListComponent} from "./lead/user-lead-list/user-lead-list.component";
import {UserListComponent} from "./lead/user-list/user-list.component";
import { ProxyRouteComponent } from './proxy-route/proxy-route.component';
import {ElencoClientiComponent} from "./anagrafiche/elenco-clienti/elenco-clienti.component";
import {UserPlanningComponent} from "./lead/user-planning/user-planning.component";
import {UserHomeLinkComponent} from "./user-home-link/user-home-link.component";
import {UserLeadComponent} from "./lead/user-lead/user-lead.component";

const routes: Routes = [
  { path: '', component: UserHomeComponent, canActivate: [AuthUserGuard]  } ,
  { path: '', component: UserHomeLinkComponent, outlet: 'userOut', canActivate: [AuthUserGuard]  } ,
  { path: 'list-user', component: UserListComponent, outlet: 'userOut', canActivate: [AuthUserGuard]  },
  { path: 'list-lead', component: UserLeadListComponent, outlet: 'userOut', canActivate: [AuthUserGuard]  },
  { path: 'list-clienti', component: ElencoClientiComponent, outlet: 'userOut', canActivate: [AuthUserGuard], data: {breadcrumb: 'Elenco Clienti', append: false, noRoute: false  }   },
  { path: 'planning', component: UserPlanningComponent, outlet: 'userOut', canActivate: [AuthUserGuard]  },
  { path: 'lead', component: UserLeadComponent, outlet: 'userOut', canActivate: [AuthUserGuard], data: {breadcrumb: 'Dettaglio Lead', append: false, noRoute: false  }   }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }

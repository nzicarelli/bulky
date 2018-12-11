import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import {AuthUserGuard} from '../_guard/authUser.guard';
import { UserHomeComponent } from './user-home/user-home.component';

import { UserLeadListComponent } from './lead/user-lead-list/user-lead-list.component';
import { UserListComponent } from './lead/user-list/user-list.component';
import {ProxyRouteComponent} from "./proxy-route/proxy-route.component";
import {SharedModule} from "../shared/shared/shared.module";
import { UserPlanningComponent } from './lead/user-planning/user-planning.component';
import { ElencoClientiComponent } from './anagrafiche/elenco-clienti/elenco-clienti.component';
import { UserComponent } from './anagrafiche/user/user.component';
import { ClienteComponent } from './anagrafiche/cliente/cliente.component';
import {AnagraficheService} from "../_services/anagrafiche.service";
import {DataUtilService} from "../_services/data-util.service";
import { UserHomeLinkComponent } from './user-home-link/user-home-link.component';
import { UserLeadComponent } from './lead/user-lead/user-lead.component';

import {ChartModule} from 'primeng/chart';


@NgModule({
  declarations: [UserHomeComponent, UserLeadListComponent, UserListComponent, ProxyRouteComponent, UserPlanningComponent, ElencoClientiComponent, UserComponent, ClienteComponent, UserHomeLinkComponent, UserLeadComponent],
  imports: [
    CommonModule,
    UserRoutingModule,

    //PRIMENG
    ChartModule,


    SharedModule
  ],
  providers: [AuthUserGuard, AnagraficheService, DataUtilService],
})
export class UserModule { }

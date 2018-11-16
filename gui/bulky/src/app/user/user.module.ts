import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import {AuthUserGuard} from '../_guard/authUser.guard';
import { UserHomeComponent } from './user-home/user-home.component';
import {SplitButtonModule, ToolbarModule} from "primeng/primeng";
import {ButtonModule} from "primeng/button";
import { UserLeadListComponent } from './lead/user-lead-list/user-lead-list.component';
import { UserListComponent } from './lead/user-list/user-list.component';
import {ProxyRouteComponent} from "./proxy-route/proxy-route.component";
import {MenuItem} from "primeng/api";
import {MenubarModule} from "primeng/menubar";

@NgModule({
  declarations: [UserHomeComponent, UserLeadListComponent, UserListComponent, ProxyRouteComponent],
  imports: [
    CommonModule,
    UserRoutingModule,

    //PRIMENG
    ToolbarModule,
    ButtonModule,
    SplitButtonModule,
    MenubarModule
  ],
  providers: [AuthUserGuard],
})
export class UserModule { }

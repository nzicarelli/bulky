import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserHomeComponent} from "./user-home/user-home.component";
import {AuthUserGuard} from "../_guard/authUser.guard";

const routes: Routes = [
  { path: '', component: UserHomeComponent, canActivate: [AuthUserGuard]  }   //  { path: '', component: UserHomeComponent, outlet: 'user', canActivate: [AuthUserGuard]  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }

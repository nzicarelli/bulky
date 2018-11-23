import { NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CustomerHomeComponent} from "./customer-home/customer-home.component";
import {AuthCustomerGuard} from "../_guard/authCustomer.guard";
import {ActCustomerStandardComponent} from "./lead/action/act-customer-standard/act-customer-standard.component";
import {ActCustomerBulkyComponent} from "./lead/action/act-customer-bulky/act-customer-bulky.component";
import {CustomerHomeActComponent} from "./customer-home-act/customer-home-act.component";

const routes: Routes = [
  { path: '', component: CustomerHomeComponent, canActivate: [AuthCustomerGuard]  },   // { path: '', component: CustomerHomeComponent, outlet: 'customer', canActivate: [AuthCustomerGuard]  }
  { path: 'home-act', component: CustomerHomeActComponent, outlet: 'customerOut', canActivate: [AuthCustomerGuard], pathMatch: 'full'  },
  { path: 'act-standard', component: ActCustomerStandardComponent, outlet: 'customerOut', canActivate: [AuthCustomerGuard], pathMatch: 'full'  },
  { path: 'act-bulky', component: ActCustomerBulkyComponent, outlet: 'customerOut', canActivate: [AuthCustomerGuard] , pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }

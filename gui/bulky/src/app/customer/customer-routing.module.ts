import { NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CustomerHomeComponent} from "./customer-home/customer-home.component";
import {AuthCustomerGuard} from "../_guard/authCustomer.guard";

const routes: Routes = [
  { path: '', component: CustomerHomeComponent, canActivate: [AuthCustomerGuard]  }   // { path: '', component: CustomerHomeComponent, outlet: 'customer', canActivate: [AuthCustomerGuard]  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }

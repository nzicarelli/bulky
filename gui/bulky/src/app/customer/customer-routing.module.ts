import { NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CustomerHomeComponent} from "./customer-home/customer-home.component";
import {AuthCustomerGuard} from "../_guard/authCustomer.guard";
import {ActCustomerStandardComponent} from "./lead/action/act-customer-standard/act-customer-standard.component";
import {ActCustomerBulkyComponent} from "./lead/action/act-customer-bulky/act-customer-bulky.component";
import {CustomerHomeActComponent} from "./customer-home-act/customer-home-act.component";

const routes: Routes = [
  { path: '', component: CustomerHomeComponent, canActivate: [AuthCustomerGuard], data: {breadcrumb: '', append: false, noRoute: false  }  },
  { path: '', component: CustomerHomeActComponent, outlet: 'customerOut', canActivate: [AuthCustomerGuard], data: {breadcrumb: '', append: false, noRoute: false  }  },
  { path: 'home-act', component: CustomerHomeActComponent, outlet: 'customerOut', canActivate: [AuthCustomerGuard], pathMatch: 'full', data: {breadcrumb: 'Nuova Richiesta', append: false, noRoute: false  }  },
  { path: 'act-standard', component: ActCustomerStandardComponent, outlet: 'customerOut', canActivate: [AuthCustomerGuard], pathMatch: 'full', data: {breadcrumb: 'Nuova Richiesta', append: false, noRoute: false  }  },
  { path: 'act-bulky', component: ActCustomerBulkyComponent, outlet: 'customerOut', canActivate: [AuthCustomerGuard] , pathMatch: 'full', data: {breadcrumb: 'Nuova Richiesta Ritiro', append: false, noRoute: false  } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }

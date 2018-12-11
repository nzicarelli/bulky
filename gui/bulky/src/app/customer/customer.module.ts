import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import {AuthCustomerGuard} from '../_guard/authCustomer.guard';
import { CustomerHomeComponent } from './customer-home/customer-home.component';
import {SharedModule} from "../shared/shared/shared.module";
import {ActionService} from "../_services/action.service";
import { ActCustomerStandardComponent } from './lead/action/act-customer-standard/act-customer-standard.component';
import { ActCustomerBulkyComponent } from './lead/action/act-customer-bulky/act-customer-bulky.component';
import { CustomerHomeActComponent } from './customer-home-act/customer-home-act.component';
import {ParameterService} from "../_services/parameter.service";

@NgModule({
  declarations: [CustomerHomeComponent, CustomerHomeActComponent],
  imports: [
    CommonModule,
    CustomerRoutingModule,

    // PRIMENG


    SharedModule
  ],
  providers: [AuthCustomerGuard, ActionService, ParameterService],

})
export class CustomerModule { }

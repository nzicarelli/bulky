import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import {AuthCustomerGuard} from '../_guard/authCustomer.guard';
import { CustomerHomeComponent } from './customer-home/customer-home.component';
import {SharedModule} from "../shared/shared/shared.module";
import {ActionService} from "../_services/action.service";

@NgModule({
  declarations: [CustomerHomeComponent],
  imports: [
    CommonModule,
    CustomerRoutingModule,

    // PRIMENG


    SharedModule
  ],
  providers: [AuthCustomerGuard, ActionService],
})
export class CustomerModule { }

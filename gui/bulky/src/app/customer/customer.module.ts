import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import {AuthCustomerGuard} from '../_guard/authCustomer.guard';
import { CustomerHomeComponent } from './customer-home/customer-home.component';

@NgModule({
  declarations: [CustomerHomeComponent],
  imports: [
    CommonModule,
    CustomerRoutingModule
  ],
  providers: [AuthCustomerGuard],
})
export class CustomerModule { }

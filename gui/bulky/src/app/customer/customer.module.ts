import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import {AuthCustomerGuard} from '../_guard/authCustomer.guard';
import { CustomerHomeComponent } from './customer-home/customer-home.component';
import {ToolbarModule} from "primeng/primeng";

@NgModule({
  declarations: [CustomerHomeComponent],
  imports: [
    CommonModule,
    CustomerRoutingModule,

    // PRIMENG
    ToolbarModule
  ],
  providers: [AuthCustomerGuard],
})
export class CustomerModule { }

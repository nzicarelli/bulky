import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CrmRoutingModule } from './crm-routing.module';
import { ContactsComponent } from './contacts/contacts.component';

@NgModule({
  declarations: [ContactsComponent],
  imports: [
    CommonModule,
    CrmRoutingModule
  ]
})
export class CrmModule { }

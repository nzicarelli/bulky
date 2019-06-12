import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CrmRoutingModule } from './crm-routing.module';
import { ContactsComponent } from './contacts/contacts.component';
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {AngularPaginatorModule} from "angular-paginator";
import {PaginatorComponent} from "../common/paginator/paginator.component";

@NgModule({
  declarations: [ContactsComponent,PaginatorComponent],
  imports: [
    CommonModule,
    CrmRoutingModule,
    NgSelectModule,
    FormsModule,
    AngularPaginatorModule
  ]
})
export class CrmModule { }

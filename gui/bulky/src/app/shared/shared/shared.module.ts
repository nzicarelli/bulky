import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {InputTextModule} from "primeng/primeng";
import {ButtonModule} from "primeng/button";
import {CardModule} from "primeng/card";
import {SidebarModule} from 'primeng/sidebar';
import {BreadcrumbModule} from 'primeng/breadcrumb';
import {SpinnerModule} from 'primeng/spinner';
import {TableModule} from 'primeng/table';
import {DialogModule} from 'primeng/dialog';
import {RadioButtonModule} from 'primeng/radiobutton';
import {MatInputModule} from "@angular/material/input";
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import {MatStepperModule} from '@angular/material/stepper';
import {MatRadioModule} from '@angular/material/radio';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatChipsModule} from '@angular/material/chips';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatExpansionModule} from '@angular/material/expansion';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ActCustomerStandardComponent} from "../../customer/lead/action/act-customer-standard/act-customer-standard.component";
import {ActCustomerBulkyComponent} from "../../customer/lead/action/act-customer-bulky/act-customer-bulky.component";

@NgModule({
  declarations: [
    // BULKY
    ActCustomerStandardComponent,
    ActCustomerBulkyComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    // PRIMENG *********
    ButtonModule,
    InputTextModule,
    CardModule,
    SpinnerModule,
    TableModule,
    BreadcrumbModule,
    SidebarModule,
    DialogModule,
    RadioButtonModule,

    // MATERIAL *****
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatToolbarModule,
    MatSelectModule,
    MatStepperModule,
    MatRadioModule,
    MatIconModule,
    MatTooltipModule,
    MatChipsModule,
    MatButtonToggleModule,
    MatExpansionModule


  ],
  exports: [
    FormsModule,
    ReactiveFormsModule,
    // PRIMNG
    ButtonModule,
    InputTextModule,
    CardModule,
    SpinnerModule,
    TableModule,
    BreadcrumbModule,
    SidebarModule,
    DialogModule,
    RadioButtonModule,

    // MATERIAL
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatToolbarModule,
    MatSelectModule,
    MatStepperModule,
    MatRadioModule,
    MatIconModule,
    MatTooltipModule,
    MatChipsModule,
    MatButtonToggleModule,
    MatExpansionModule,


    // BULKY
    ActCustomerStandardComponent,
    ActCustomerBulkyComponent
    ]
})
export class SharedModule { }

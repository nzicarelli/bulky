import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {InputTextModule} from "primeng/primeng";
import {ButtonModule} from "primeng/button";
import {CardModule} from "primeng/card";
import {SpinnerModule} from 'primeng/spinner';
import {MatInputModule} from "@angular/material/input";
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import {MatStepperModule} from '@angular/material/stepper';
import {MatRadioModule} from '@angular/material/radio';
import {MatIconModule} from '@angular/material/icon';

import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,

    // PRIMENG *********
    ButtonModule,
    InputTextModule,
    CardModule,
    SpinnerModule,

    // MATERIAL *****
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatToolbarModule,
    MatSelectModule,
    MatStepperModule,
    MatRadioModule,
    MatIconModule
  ],
  exports: [
    FormsModule,
    ReactiveFormsModule,
    // PRIMNG
    ButtonModule,
    InputTextModule,
    CardModule,
    SpinnerModule,

    // MATERIAL
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatToolbarModule,
    MatSelectModule,
    MatStepperModule,
    MatRadioModule,
    MatIconModule
    ]
})
export class SharedModule { }

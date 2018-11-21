import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {InputTextModule} from "primeng/primeng";
import {ButtonModule} from "primeng/button";
import {CardModule} from "primeng/card";
import {MatButtonModule} from "@angular/material";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    // PRIMENG *********
    ButtonModule,
    InputTextModule,
    CardModule,

    // MATERIAL *****
    MatButtonModule
  ],
  exports: [

    // PRIMNG
    ButtonModule,
    InputTextModule,
    CardModule,

    // MATERIAL
    MatButtonModule
    ]
})
export class SharedModule { }

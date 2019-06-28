import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CrmRoutingModule } from './crm-routing.module';
import { CrmComponent } from './crm.component';
import { PlanListComponent } from './plan-list/plan-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
    MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatChipsModule,
    MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule, MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule, MatRippleModule,
    MatSelectModule,
    MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatSortModule, MatStepperModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule
} from '@angular/material';
import { CollListComponent } from './coll-list/coll-list.component';
import { PlannerComponent } from './planner/planner.component';
import { UpdateAddressZoneComponent } from './update-address-zone/update-address-zone.component';
import { DialogUpdAddrZoneComponent } from './update-address-zone/dialog-upd-addr-zone/dialog-upd-addr-zone.component';
import { UpdateZoneComponent } from './update-address-zone/update-zone/update-zone.component';


@NgModule({
    imports: [
        MatAutocompleteModule,
        MatButtonModule,
        MatButtonToggleModule,
        MatCardModule,
        MatCheckboxModule,
        MatChipsModule,
        MatDatepickerModule,
        MatDialogModule,
        MatExpansionModule,
        MatGridListModule,
        MatIconModule,
        MatInputModule,
        MatListModule,
        MatMenuModule,
        MatNativeDateModule,
        MatPaginatorModule,
        MatProgressBarModule,
        MatProgressSpinnerModule,
        MatRadioModule,
        MatRippleModule,
        MatSelectModule,
        MatSidenavModule,
        MatSliderModule,
        MatSlideToggleModule,
        MatSnackBarModule,
        MatSortModule,
        MatTableModule,
        MatTabsModule,
        MatToolbarModule,
        MatTooltipModule,
        MatStepperModule,
        FormsModule,
        CommonModule,
        CrmRoutingModule,
        ReactiveFormsModule,
    ],
    declarations: [CrmComponent, PlanListComponent, CollListComponent, PlannerComponent, UpdateAddressZoneComponent, DialogUpdAddrZoneComponent, UpdateZoneComponent],
    entryComponents: [DialogUpdAddrZoneComponent, UpdateZoneComponent]
})
export class CrmModule {
}

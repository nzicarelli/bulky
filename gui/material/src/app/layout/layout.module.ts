import { NgModule } from '@angular/core';

import { LayoutRoutingModule } from './layout-routing.module';
import { SharedModule } from '../shared/shared.module';
import { AuthGuard } from '../services/auth-guard';

@NgModule({
    imports: [
        LayoutRoutingModule,
        SharedModule,
    ],
    declarations: [],
    providers: [AuthGuard]
})

export class LayoutModule {
}

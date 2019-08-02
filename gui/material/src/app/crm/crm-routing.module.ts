import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlanListComponent } from './plan-list/plan-list.component';
import { CrmComponent } from './crm.component';
import { AuthGuard } from '../services/auth-guard';
import { CollListComponent } from './coll-list/coll-list.component';
import { LeadComponent } from './lead/lead.component';

const routes: Routes = [
    {
        path: '',
        component: CrmComponent,
        children: [
            {path: '', redirectTo: '/app/dashboard', pathMatch: 'full'},
            {path: 'plan-list', component: PlanListComponent, canActivate: [AuthGuard]},
            {path: 'coll-list', component: CollListComponent, canActivate: [AuthGuard]},
            {path: 'lead', component: LeadComponent, canActivate: [AuthGuard]}
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CrmRoutingModule {
}

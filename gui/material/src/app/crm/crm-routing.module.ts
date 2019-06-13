import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlanListComponent } from './plan-list/plan-list.component';
import { CrmComponent } from './crm.component';
import { AuthGuard } from '../services/auth-guard';

const routes: Routes = [
    {
        path: '',
        component: CrmComponent,
        children: [
            {path: '', redirectTo: '/app/dashboard', pathMatch: 'full'},
            {path: 'plan-list', component: PlanListComponent, canActivate: [AuthGuard]}
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CrmRoutingModule {
}

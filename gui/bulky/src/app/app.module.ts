import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {AuthGuard} from './_guard/auth.guard';
import {AppRoutes} from './app.routes';
import { LoginComponent } from './login/login.component';
import {ButtonModule} from 'primeng/button';
import { LandingComponent } from './landing/landing.component';


@NgModule({
  imports: [

    BrowserModule,

    // PRIMENG
    ButtonModule,

    AppRoutes
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    LandingComponent
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }

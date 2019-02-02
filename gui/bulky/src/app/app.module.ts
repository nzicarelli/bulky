import {APP_INITIALIZER, NgModule} from '@angular/core';

import { AppComponent } from './app.component';
import {AuthGuard} from './_guard/auth.guard';
import {AppRoutes} from './app.routes';
import { LoginComponent } from './login/login.component';
import { LandingComponent } from './landing/landing.component';
import {AppConfig} from "./app.config";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {JwtInterceptor} from "./_services/jwt-interceptor";
import {AuthenticationService} from "./_services/authentication.service";
import { RegistrationComponent } from './registration/registration.component';
import {FormsModule} from "@angular/forms";
import { RecoveryPasswordComponent } from './recovery-password/recovery-password.component';

import { getBaseUrl } from './shared/common-functions.util';
import {SharedModule} from "./shared/shared/shared.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

import {LocationStrategy, HashLocationStrategy} from '@angular/common';

export function initConfig(config: AppConfig) {
  return () => config.loadNew();
}

@NgModule({
  imports: [
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,

    // PRIMENG

    SharedModule,
    AppRoutes
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    LandingComponent,
    RegistrationComponent,
    RecoveryPasswordComponent
  ],
  providers: [AuthGuard, AppConfig, AuthenticationService,
    {provide: APP_INITIALIZER, useFactory: initConfig, deps: [AppConfig], multi: true},
    { provide: 'BASE_URL', useFactory: getBaseUrl },
    {provide: LocationStrategy, useClass: HashLocationStrategy},
    /*{
      provide: APP_BASE_HREF,
      useFactory: getBaseLocation
    },*/
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }

import {Injectable} from '@angular/core';
import {HttpRequest, HttpHandler, HttpEvent, HttpInterceptor} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthenticationServiceService} from './authentication-service.service';
import {APPCONFIG} from "../config";


@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  AppConfig: any;

  constructor(private authenticationService: AuthenticationServiceService) {
    this.AppConfig = APPCONFIG;
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add authorization header with jwt token if available
    let currentUser = this.authenticationService.currentUserValue;
    const cfg = APPCONFIG;
    console.log('_____________________ JwtInterceptor intercept ' + request.method + ' -- ' + request.url + '_____________________');
    if (currentUser && currentUser.token) {
      request = request.clone({
        setHeaders: {
          'X-Auth': currentUser.token,
          [cfg.tokenHeader]: currentUser.token
        }
      })
      ;
    }

    return next.handle(request);
  }
}

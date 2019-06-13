import {Injectable} from '@angular/core';
import {HttpRequest, HttpHandler, HttpEvent, HttpInterceptor} from '@angular/common/http';
import {Observable} from 'rxjs';
import {APPCONFIG} from '../config';
import { AuthenticationService } from './authentication.service';


@Injectable({
    providedIn: 'root'
})
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthenticationService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add authorization header with jwt token if available
    const currentUser = this.authenticationService.currentUserValue;
    console.log('_____________________ JwtInterceptor intercept ' + request.method + ' -- ' + request.url + '_____________________');
    if (currentUser && currentUser.token) {
      const header: string = '' + APPCONFIG.tokenHeader;
      request = request.clone({
        setHeaders: {
          'X-Auth': currentUser.token,
          [header]: currentUser.token
        }
      });
    }

    return next.handle(request);
  }
}

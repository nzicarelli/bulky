import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {AuthenticationService} from './authentication.service';
import {Router} from '@angular/router';
import {Inject, Injectable, Injector} from '@angular/core';
import {AppConfig} from '../app.config';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  private bUrl = '';
  constructor(public router: Router, private inj: Injector, private config: AppConfig,  @Inject('BASE_URL') baseUrl: string,
              private authService: AuthenticationService) {
    this.bUrl = baseUrl;
  }


  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
     console.log('JWT_INTERCEPTOR **********************************************************');
    const auth = this.inj.get(AuthenticationService);
    req = req.clone({
      setHeaders: {
        Authorization: `${auth.getToken()}`
      }
    });
    if (req.url.startsWith('/')) {
      const servUrl = this.config.getConfig('baseUrl');
      const myUrl = this.bUrl;  // prefix base url
      req = req.clone({
        url: servUrl + myUrl + req.url.substring(1, req.url.length),
      });
    }

    return next.handle(req).do((event: HttpEvent<any>) => {
      if (event instanceof HttpResponse) {
        // do stuff with response if you want
      }
    }, (err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401) {
          // redirect to the login route
          // or show a modal
          this.authService.logout();
          this.router.navigate(['/login']);
        }
      }
    });
  }
}

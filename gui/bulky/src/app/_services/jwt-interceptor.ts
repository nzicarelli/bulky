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
import {Injectable, Injector} from '@angular/core';
import {AppConfig} from '../app.config';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {


  constructor(public router: Router, private inj: Injector, private config: AppConfig) {
  }


  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // console.log('JWT_INTERCEPTOR **********************************************************');
    const auth = this.inj.get(AuthenticationService);
    req = req.clone({
      setHeaders: {
        Authorization: `${auth.getToken()}`
      }
    });
    if (req.url.startsWith('/')) {
      const myUrl = this.config.getConfig('baseUrl');     // prefix base url
      req = req.clone({
        url: myUrl + req.url
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
          this.router.navigate(['/login']);
        }
      }
    });
  }
}

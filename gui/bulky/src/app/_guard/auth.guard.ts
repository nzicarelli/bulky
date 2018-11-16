import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import {AuthenticationService} from "../_services/authentication.service";

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router, private authServ: AuthenticationService) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        console.log('AUTOGUARD GENERIC');
      if (localStorage.getItem('currentUser') || localStorage.getItem('bearer')) {
        //     // logged in so return true
        //     console.log('GUARDIA true');
        return true;
      }
      //
      //     console.log('GUARDIA false');
      //
      // // not logged in so redirect to login page with the return url
      this.authServ.logout();
      this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
      return false;
    }
}

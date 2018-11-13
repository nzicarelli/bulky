import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthCustomerGuard implements CanActivate {

    constructor(private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        console.log('AUTOGUARD CUSTOMER');
        // if (localStorage.getItem('currentUser') || localStorage.getItem('bearer')) {
        //     // logged in so return true
        //     console.log('GUARDIA true');
        //     return true;
        // }
        //
        //     console.log('GUARDIA false');
        //
        // // not logged in so redirect to login page with the return url
        // this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
        // return false;
      return true;
    }
}

import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { JwtHelperServiceService } from './jwt-helper-service.service';
import { AuthenticationService } from './authentication.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    constructor(private authenticationService: AuthenticationService, private router: Router, private jwtUtil: JwtHelperServiceService) {

    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const currentUser = this.authenticationService.currentUserValue;
        console.log('******************** AuthGuard **********************');
        console.log(currentUser);
        if (currentUser && !this.jwtUtil.isTokenExpired(currentUser.token)) {

            // logged in so return true
            return true;
        }

        // not logged in so redirect to login page with the return url
        this.router.navigate(['/extra/login'], {queryParams: {returnUrl: state.url}});
        return false;
    }
}

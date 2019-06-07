import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Rx';
import {AuthenticationServiceService} from './authentication-service.service';
import {JwtHelperServiceService} from './jwt-helper-service.service';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private authenticationService: AuthenticationServiceService, private router: Router
    , private jwtUtil: JwtHelperServiceService) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.authenticationService.currentUserValue;
    console.log('******************** AuthGuard **********************');
    console.log(currentUser);
    this.jwtUtil.isTokenExpired(currentUser.token);
    if (currentUser) {
      // logged in so return true
      return true;
    }

    // not logged in so redirect to login page with the return url
    this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
    return false;
  }
}

import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs/Rx';
import {User} from '../user/user';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {JwtHelperServiceService} from './jwt-helper-service.service';
import {tap} from 'rxjs/internal/operators';
import {APPCONFIG} from '../config';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationServiceService {

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;
  private AppConfig:any;

  constructor(private http: HttpClient, private jwtUtil: JwtHelperServiceService) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
    this.AppConfig = APPCONFIG;
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string) {
    return this.http.post<any>('/auth/login', {username, password}, {observe: 'response'})
      .pipe(tap(resp => {
        return this.elabResp(resp);
      }));
  }

  refresh() {
    // return this.http.get<any>('/api/auth/refresh-token',{observe: 'response'})
    //   .pipe(tap(resp => {
    //     return this.elabResp(resp);
    //   }));
    const hhs = new HttpHeaders({});
    if (this.currentUserValue && this.currentUserValue.token) {
      hhs.set(this.AppConfig.tokenHeader, this.currentUserValue.token);
    }

    return this.http.get('/api/auth/refresh-token',{observe: 'response', headers:hhs}).pipe(
      tap(
        resp=>{
          return this.elabResp(resp);
        }
      )
    );

    // return this.http.get<any>(`/api/auth/refresh-token`,{observe: 'response'} )
    //   .pipe(tap(resp => {
    //     return this.elabResp(resp);
    //   }));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }


  isTokenExpired(){
    return (!this.currentUserValue || !this.currentUserValue.token || this.jwtUtil.isTokenExpired(this.currentUserValue.token));
  }

  private elabResp(resp: any) {
    const token = resp.headers.get(this.AppConfig.tokenHeader);
    const user: any = resp.body;
    user.token = token;
    const tkObj = this.jwtUtil.decodeToken(token);
    console.log(tkObj);
    const date1 = new Date(tkObj.exp * 1000);
    const date2 = new Date();
    const exp = Math.abs((date1.getTime() - date2.getTime()))-10000;
    if (exp) {
      setTimeout(() => {
        console.log('refresh token');
        this.refresh().subscribe((data) => {
        }, (error) => {
        });
      }, exp);
    }
    if (user && user.token) {
      // store user details and jwt token in local storage to keep user logged in between page refreshes
      localStorage.setItem('currentUser', JSON.stringify(user));
      this.currentUserSubject.next(user);
    }

    return user;
  }


}

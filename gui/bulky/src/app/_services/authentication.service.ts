import {Injectable} from '@angular/core';

import {AppConfig} from '../app.config';
import {HttpClient} from '@angular/common/http';


@Injectable()
export class AuthenticationService {
  constructor(private http: HttpClient, private config: AppConfig) {
  }

  login(username: string, password: string) {
    const data = {
      username: username,
      password: password
    };

    const myUrl = this.config.getConfig('baseUrl');

    return this.http
      .post(myUrl + '/auth/login', data).map((response: any) => {
        console.log('New login');
        const user = response;
        const tk = user.access_token;
        const exp = user.expires_in;
        const u = user.data;

        if ((tk)) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('bearer', tk);
          localStorage.setItem('expireToken', exp);
          localStorage.setItem('imgUserPath', u.img);
          localStorage.setItem('currentUser', JSON.stringify(u));
          u.success = true;
          return u;
        } else {
          console.log('No login');
          return user;
        }
      });
  }

  public register(periodo: string) {
    const myParam: any = {
      PERIODO: periodo
    };
    return this.http.post('/services/auth/login', myParam)
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }

  public recoveryPassword(periodo: string) {
    const myParam: any = {
      PERIODO: periodo
    };
    return this.http.post('/services/auth/login', myParam)
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }

  logout() {
    // this.parameterService.cleanService();
    // this.permessi.clear();
    localStorage.removeItem('currentUser');
    localStorage.removeItem('bearer');
    localStorage.removeItem('expireToken');
  }

  getToken() {
    return localStorage.getItem('bearer');
  }

  isAdmin() {
    const u = localStorage.getItem('currentUser');
    const u2 = JSON.parse(u);
    if (u2.grpId === 4) {
      return true;
    } else {
      return false;
    }

  }

  getUserId() {
    const u = localStorage.getItem('currentUser');
    const u2 = JSON.parse(u);
    return u2.usrId;
  }

}

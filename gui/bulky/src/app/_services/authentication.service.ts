import {Injectable} from '@angular/core';

import {AppConfig} from '../app.config';
import {HttpClient} from '@angular/common/http';


@Injectable()
export class AuthenticationService {

  public TYPE_CUSTOMER = 'CUSTOMER';
  public TYPE_USER = 'USER';

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
        const tk = user.tk;
        const exp = user.expires_in;
        const u = user.user;

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

  public register(nome: string, cognome: string, username: string, password: string, cfPiva: string) {
    const myParam: any = {
      cuname: nome,
      cusurname: cognome,
      cuusername: username,
      cupassword: password,
      cupiva: cfPiva,
      cucf: cfPiva,
      cufkaccount: 1  // TODO Da gestire
    };

    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/customer/signup', myParam)
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
    if (u2.role === 'ROLE_ADMIN') {
      return true;
    } else {
      return false;
    }

  }

  getUserName() {
    const u = localStorage.getItem('currentUser');
    const u2 = JSON.parse(u);
    return u2.username;
  }

  getName() {
    const u = localStorage.getItem('currentUser');
    const u2 = JSON.parse(u);
    return u2.name;
  }

  isCustomer() {
    const u = localStorage.getItem('currentUser');
    const u2 = JSON.parse(u);
    if (u2.accountType === this.TYPE_CUSTOMER) {
      return true;
    }
    return false;
  }

  isUser() {
    const u = localStorage.getItem('currentUser');
    const u2 = JSON.parse(u);
    if (u2.accountType === this.TYPE_USER) {
      return true;
    }
    return false;
  }

}

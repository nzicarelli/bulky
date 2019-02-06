import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppConfig} from "../app.config";

@Injectable({
  providedIn: 'root'
})
export class AnagraficheService {

  constructor(private http: HttpClient, private config: AppConfig) { }


  public listClienti(comune: string) {
    const myParam: any = {
      comune: comune
    };

    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/customer/list-by-comune', myParam)
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }

  public listAddress4cli(idCli: number) {
    const myParam: any = {
      cuid: idCli
    };

    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/customer/address', myParam)
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }

  public listUser() {
    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/user/list', {})
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }

  public saveUser(user: any) {
    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/user/add-update', user)
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }

  public enableUser(idUser: any) {
    const myParam: any = {
      uid: idUser
    };

    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/user/enable', myParam)
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }

  public disableUser(idUser: any) {
    const myParam: any = {
      uid: idUser
    };

    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/user/disable', myParam)
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }

}

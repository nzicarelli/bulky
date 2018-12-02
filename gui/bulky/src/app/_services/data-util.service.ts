import { Injectable } from '@angular/core';
import {AppConfig} from "../app.config";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DataUtilService {

  constructor(private http: HttpClient, private config: AppConfig) { }

  public getPeriodi() {
    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/calendar/periodi', {})
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }

  public getComuni() {
    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/calendar/periodi', {})
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }
}

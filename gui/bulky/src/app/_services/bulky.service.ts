import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppConfig} from "../app.config";

@Injectable({
  providedIn: 'root'
})
export class BulkyService {

  constructor(private http: HttpClient, private config: AppConfig) { }

  public getCatgIngombranti() {

    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/custom/list-catg', {})
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }
}

import { Injectable } from '@angular/core';
import {AppConfig} from "../app.config";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient, private config: AppConfig) { }

  public getAddress(customerId: number) {
    const myData = {
      cuid: customerId
    }

    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/customer/address', myData)
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }
}

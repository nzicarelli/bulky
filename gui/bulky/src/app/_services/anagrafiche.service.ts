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


}

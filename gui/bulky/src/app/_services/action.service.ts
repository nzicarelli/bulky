import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppConfig} from "../app.config";

@Injectable({
  providedIn: 'root'
})
export class ActionService {

  constructor(private http: HttpClient, private config: AppConfig) { }


  public getCatgAction4customer() {
    const myParam: any = {
      // cuid: 1
    };

    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/action/list', myParam)
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }

  public saveAction(id: number, descr: string, oggetto: string, idType: number) {
    const myParam: any = {
      aid: id,
      aaccount: 1,
      afktype: idType,
      aowner: null,
      aassign: null,
      astatus: undefined,
      adescr: descr,
      atitle: undefined,
      asubject: oggetto,
      afkcustomer: undefined
    };
    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/action/save', myParam)
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }
}

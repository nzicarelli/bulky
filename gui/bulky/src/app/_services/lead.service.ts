import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppConfig} from "../app.config";

@Injectable({
  providedIn: 'root'
})
export class LeadService {

  constructor(private http: HttpClient, private config: AppConfig) { }


  public listLead(cuid: number, dal: any, al: any) {
    const myParam: any = {
      cuid: cuid,
      dal: dal,
      al: al,
      start: 0,
      limit: 5000
    };

    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/action/list-lead', myParam)
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }

  public listAction4Lead(leadId: number) {
    const myParam: any = {
      lead: leadId
    };

    const myUrl = this.config.getConfig('baseUrl');
    return this.http.post(myUrl + '/api/action/list-activity-4lead', myParam)
      .toPromise()
      .then(res => {
        return res;
      })
      .catch(error =>
        Promise.reject(error.message || error)
      );
  }
}

import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {APPCONFIG} from "../config";
import {tap} from "rxjs/internal/operators";

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private AppConfig:any;
  constructor(private http: HttpClient) {
    this.AppConfig = APPCONFIG;
  }

  public listComuni(params:any){
    if (!params){
      params = {};
    }
    return this.http.post<any>('/api/data/list-comuni', params, {observe: 'response'})
      .pipe(tap(resp => {
        return this.elabResp(resp);
      }));
  }

  public elabResp(resp: any) {
    console.log("************************ API SERVICE ");
    console.log(resp);
    const obj = this.resp2Data(resp);
    // FIXME ME SE NOT SUCCES CHIAMARE IL SERVIZIO PER MOSTRARE L'ERRORE
    if (!obj.success){
      //"message":{"message":"OK","type":"SUCCESS","args":null}
      // MOSTRARE IL MESSAGGIO DI ERRORE
    }
    return resp;

  }

  public resp2Data(resp:any):any{
    let obj:any = {};
    if (resp && resp.body){
      const data = resp.body.output;
      const success = resp.body.success;
      console.log(success);
      console.log(data);
      obj.data = data;
      obj.success = success;
      obj.message = resp.body.message;
      return obj;
    }
    return obj;
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { APPCONFIG } from "../config";
import { tap } from "rxjs/internal/operators";
import { replaceNgsp } from '@angular/compiler/src/ml_parser/html_whitespaces';

@Injectable({
    providedIn: 'root'
})
export class ApiService {
    private AppConfig: any;

    constructor(private http: HttpClient) {
        this.AppConfig = APPCONFIG;
    }

    public listComuni(params: any) {
        if (!params) {
            params = {};
        }
        return this.http.post<any>('/api/data/list-comuni', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }

    public listCustomer(params: any) {
        if (!params) {
            params = {};
        }
        return this.http.post<any>('/api/customer/list-by-comune', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }

    public listIndirizziByCustomer(params: any) {
        if (!params) {
            params = {};
        }
        return this.http.post<any>('/api/customer/address', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }

    public listCatgTipoLead(params: any) {
        if (!params) {
            params = {};
        }
        return this.http.post<any>('/api/action/list-tipo-lead', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }

    public listCatgAction(params: any) {
        if (!params) {
            params = {};
        }
        return this.http.post<any>('/api/action/list-catgactivity-4lead', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }

    public listCatg(params: any) {
        if (!params) {
            params = {};
        }
        return this.http.post<any>('/api/custom/list-catg', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }



    public elabResp(resp: any) {
        console.log('************************ API SERVICE ');
        console.log(resp);
        const obj = this.resp2Data(resp);
        // FIXME ME SE NOT SUCCES CHIAMARE IL SERVIZIO PER MOSTRARE L'ERRORE
        if (!obj.success) {
            // 'message':{'message':'OK','type':'SUCCESS','args':null}
            // MOSTRARE IL MESSAGGIO DI ERRORE
        }
        return resp;

    }

    public resp2Data(resp: any): any {
        const obj: any = {};
        if (resp && resp.body) {
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

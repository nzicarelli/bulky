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


    public listZone(params: any) {
        if (!params) {
            params = {};
        }
        return this.http.post<any>('/api/planning/list-zone', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }

    public listZoneByComune(params: any) {
        if (!params) {
            params = {};
        }
        return this.http.post<any>('/api/planning/list-zone-by-comune', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }

    public listIndirizziByZone(params: any) {
        if (!params) {
            params = {};
        }
        return this.http.post<any>('/api/planning/list-detail-zone', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }

    public listDistinctIndirizziByZone(params: any) {
        if (!params) {
            params = {};
        }
        return this.http.post<any>('/api/planning/list-disticnt-address', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }

    public saveZone(params: any) {
        if (!params) {
            params = [];
        }
        return this.http.post<any>('/api/planning/save-zona', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }

    public saveAddressZone(params: any) {
        if (!params) {
            params = [];
        }
        return this.http.post<any>('/api/planning/save-address-zona', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }


    public listPlanningByZone(params: any) {
        if (!params) {
            params = {};
        }
        return this.http.post<any>('/api/planning/list-planning', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }

    public listPlanDetail(params: any) {
        if (!params) {
            params = {};
        }
        return this.http.post<any>('/api/planning/list-planning-detail', params, {observe: 'response'})
            .pipe(tap((resp) => {
                return this.elabResp(resp);
            }));
    }

    public deletePlanDetail(params: any) {
        if (!params) {
            params = {};
        }
        return this.http.post<any>('/api/planning/delete-planning-detail', params, {observe: 'response'})
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

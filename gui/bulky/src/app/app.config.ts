import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Rx';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class AppConfig {

    private config: Object = null;
    private env: Object = null;

    public VERSIONE = 'version';

    public AUTOMOTIVE = 'AUTOMOTIVE';
    public DIY = 'DIY';

    constructor(private http: HttpClient) {

    }

    /**
     * Use to get the data found in the second file (config file)
     */
    public getConfig(key: any) {
        return this.config[key];
    }

    /**
     * Use to get the data found in the first file (env file)
     */
    public getEnv(key: any) {
        return this.env[key];
    }


    public loadNew() {
        return new Promise((resolve, reject) => {
            this.http.get('config/env.json').map((res: any) => {
              console.log('RES: ' + res);
              return res;
            }).catch((error: any): any => {
                console.log('Configuration file "env.json" could not be read');
                resolve(true);
                return Observable.throw(error.error || 'Server error');
            }).subscribe((envResponse) => {
                this.env = envResponse;
                let request: any = null;

                switch (envResponse.env) {
                    case 'production': {
                        request = this.http.get('config/config.' + envResponse.env + '.json');
                    } break;

                    case 'development': {
                        request = this.http.get('config/config.' + envResponse.env + '.json');
                    } break;

                    case 'default': {
                        console.error('Environment file is not set or invalid');
                        resolve(true);
                    } break;
                }

                if (request) {
                    request
                        .map(res => res)
                        .catch((error: any) => {
                            console.error('Error reading ' + envResponse.env + ' configuration file');
                            resolve(error);
                            return Observable.throw(error.error || 'Server error');
                        })
                        .subscribe((responseData) => {
                            this.config = responseData;
                            resolve(true);
                        });
                } else {
                    console.error('Env config file "env.json" is not valid');
                    resolve(true);
                }
            });

        });
    }
}

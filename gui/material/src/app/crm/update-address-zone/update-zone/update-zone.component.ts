import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../services/api.service';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material';

@Component({
    selector: 'app-update-zone',
    templateUrl: './update-zone.component.html',
    styleUrls: ['./update-zone.component.scss'],
    providers: [
        // The locale would typically be provided on the root module of your application. We do it at
        // the component level here, due to limitations of our example generation script.
        {provide: MAT_DATE_LOCALE, useValue: 'it-IT'},

        // `MomentDateAdapter` and `MAT_MOMENT_DATE_FORMATS` can be automatically provided by importing
        // `MatMomentDateModule` in your applications root module. We provide it at the component level
        // here, due to limitations of our example generation script.
        // {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
        // {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
    ],
})
export class UpdateZoneComponent implements OnInit {
    comune: any = {};
    zona: any = {};
    dal: any;
    al: any;
    days: any = {};

    constructor(private api: ApiService, private _adapter: DateAdapter<any>) {
    }

    ngOnInit() {
        console.log(this.zona);
        this._adapter.setLocale('it');
        this.days = {
            lun: false,
            mart: false,
            merc: false,
            giov: false,
            ven: false,
            sab: false,
            dom: false
        };
    }

    salva() {
        const payload = {
            z: this.zona,
            d: this.days,
            dal: this.dal,
            al: this.al
        };
        this.api.saveZone(payload).subscribe(
            (resp) => {
                const dataList = this.api.resp2Data(resp);
                this.zona = dataList && dataList.length > 0 ? dataList[0] : this.zona;
            }
        );
    }
}

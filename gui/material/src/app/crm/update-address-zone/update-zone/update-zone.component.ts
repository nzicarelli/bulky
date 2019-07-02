import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../services/api.service';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material';
import { DatePipe, formatDate } from '@angular/common';

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
        DatePipe
    ]
})
export class UpdateZoneComponent implements OnInit {
    comune: any = {};
    zona: any = {};
    dal: any;
    al: any;
    days: any = {};
    plannings: any;

    constructor(private api: ApiService, private _adapter: DateAdapter<any>, private datePipe: DatePipe) {
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
        // console.log(formatDate(this.dal, 'dd/MM/yyyy'));
        const payload = {
            z: this.zona,
            d: this.days,
            dal: this.dal ? this.datePipe.transform(this.dal, 'dd/MM/yyyy') : '',
            al: this.al ? this.datePipe.transform(this.al, 'dd/MM/yyyy') : ''
        };
        this.api.saveZone(payload).subscribe(
            (resp) => {
                const dataList = this.api.resp2Data(resp);
                this.zona = dataList && dataList.length > 0 ? dataList[0] : this.zona;
            }
        );
    }

    changeData(evt: any) {
        // load list planning
        const payload = {
            zone: this.zona ? this.zona.zid : '',
            dal: this.dal ? this.datePipe.transform(this.dal, 'dd/MM/yyyy') : '',
            al: this.al ? this.datePipe.transform(this.al, 'dd/MM/yyyy') : ''
        };
        this.api.listPlanningByZone(payload).subscribe(
            (resp) => {
                const plns = this.api.resp2Data(resp);
                this.plannings = plns.data;
                this.loadPlanDetail(0);

            }
        );
    }

    deleteDetail(detail: any) {
        const payload = {pldid: detail.pldid};

        this.api.deletePlanDetail(payload).subscribe(
            (resp) => {
                console.log(this.api.resp2Data(resp));
                this.changeData(undefined);
            }
        );

    }

    private loadPlanDetail(index: number) {
        if (!this.plannings) {
            return;
        }
        if (index >= 0 && index < this.plannings.length) {
            const pln = this.plannings[index];
            if (!pln) {
                return;
            }
            const payload = {
                planning: pln.plnid
            };

            this.api.listPlanDetail(payload).subscribe(
                (resp) => {
                    const data = this.api.resp2Data(resp);
                    this.plannings[index].detail = data.data;
                    this.loadPlanDetail((index + 1));
                }
            );
        }
    }
}

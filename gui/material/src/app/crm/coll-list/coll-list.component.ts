import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { APPCONFIG } from '../../config';

@Component({
    selector: 'app-coll-list',
    templateUrl: './coll-list.component.html',
    styleUrls: ['./coll-list.component.scss']
})
export class CollListComponent implements OnInit {
    id: string | null;

    indirizzi: any = [];
    indirizzo: any;
    AppConfig: any;
    catgTipoLead: any = [];
    private _index = 0;
    selectedAction: any;


    constructor(private route: ActivatedRoute, private api: ApiService) {
    }

    ngOnInit() {
        this.AppConfig = APPCONFIG;
        this.id = this.route.snapshot.paramMap.get('data');
        if (this.id) {
            this.api.listIndirizziByCustomer({cuid: this.id}).subscribe(
                (resp) => {
                    const _data = this.api.resp2Data(resp);
                    this.indirizzi = _data.data;
                }
            );
        }
        this._index = 0;
        this.api.listCatgTipoLead({}).subscribe(
            (resp) => {
                const _data = this.api.resp2Data(resp);
                this.catgTipoLead = _data.data;
                if (this._index < this.catgTipoLead.length) {
                    this.loadCatgAction(this._index);
                }
            }
        );
    }

    onSelectIndirizzo(evt: any) {

    }

    doAction(action: any) {
        this.selectedAction = action;
    }

    private loadCatgAction(index: number) {
        const _tlead = this.catgTipoLead[index].lctid;
        this.api.listCatgAction({tlead: _tlead}).subscribe(
            (resp) => {
                const _data = this.api.resp2Data(resp);
                this.catgTipoLead[index].action = _data.data;
                this._index++;
                if (this._index < this.catgTipoLead.length) {
                    this.loadCatgAction(this._index);
                }
            }
        );
    }
}

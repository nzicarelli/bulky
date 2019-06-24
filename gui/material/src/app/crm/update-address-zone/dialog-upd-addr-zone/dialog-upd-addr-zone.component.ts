import { Component, OnInit, ViewChild } from '@angular/core';
import { UpdateAddressZoneComponent } from '../update-address-zone.component';

@Component({
    selector: 'app-dialog-upd-addr-zone',
    templateUrl: './dialog-upd-addr-zone.component.html',
    styleUrls: ['./dialog-upd-addr-zone.component.scss']
})
export class DialogUpdAddrZoneComponent implements OnInit {

    comune: any;
    zona: any;
    zone: any = [];

    @ViewChild('updater') updater: UpdateAddressZoneComponent;

    constructor() {
    }

    ngOnInit() {
    }

    salva() {
        if (this.updater) {
            this.updater.save();
        }
    }

}

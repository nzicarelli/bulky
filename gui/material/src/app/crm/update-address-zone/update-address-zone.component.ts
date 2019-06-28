import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { CustomerData } from '../plan-list/plan-list.component';
import { APPCONFIG } from '../../config';

@Component({
    selector: 'app-update-address-zone',
    templateUrl: './update-address-zone.component.html',
    styleUrls: ['./update-address-zone.component.scss']
})
export class UpdateAddressZoneComponent implements OnInit {

    @Input() comnune: any;
    @Input() zona: any;
    @Input() zone: any = [];

    data: any;

    displayedColumns: string[] = ['adcap', 'adaddress', 'action'];
    dataSource: MatTableDataSource<CustomerData>;
    // {adaddress: "BORGO DEGLI ULTRAMONTANI", adcap: "87046"}

    @ViewChild(MatPaginator) paginator: MatPaginator;
    @ViewChild(MatSort) sort: MatSort;
    AppConfig: any;
    numItemInZone: number = 0;

    constructor(private  api: ApiService) {
        // Create 100 users
        const data = [];

        // Assign the data to the data source for the table to render
        this.dataSource = new MatTableDataSource(data);
    }

    ngOnInit() {
        this.AppConfig = APPCONFIG;
        const zId = this.zona ? '' + this.zona.zid : '';
        this.api.listDistinctIndirizziByZone({comune: this.comnune ? this.comnune.adcomune : '', zona: zId}).subscribe(
            (resp) => {
                this.data = this.api.resp2Data(resp).data;
                this.updateDataSource();
            }
        );
    }

    applyFilter(filterValue: string) {
        this.dataSource.filter = filterValue.trim().toLowerCase();

        if (this.dataSource.paginator) {
            this.dataSource.paginator.firstPage();
        }
    }

    addToZone(row: any) {
        console.log(row);
        const zId = this.zona ? '' + this.zona.zid : '';
        if (this.existsInZone(row)) {
            return;
        }
        if (row.adaddress && row.adaddress.trim().length > 0) {
            this.zone.push({
                azaddress: row.adaddress,
                azcap: row.adcap,
                azcomune: this.comnune ? this.comnune.adcomune : '',
                azfkzona: zId,
                zone: true,
                azsiglaprov: this.comnune ? this.comnune.adsiglaprov : ''
            });
            this.updateDataSource();

        } else if (row.adcap && row.adcap.trim().length > 0) {
            this.zone.push({
                azaddress: '',
                azcap: row.adcap,
                azcomune: this.comnune ? this.comnune.adcomune : '',
                azfkzona: zId,
                zone: true,
                azsiglaprov: this.comnune ? this.comnune.adsiglaprov : ''
                // {"adsiglaprov":"CS","adcap":"87046","adcomune":"MOLTALTO UFFUGO"}
            });
            this.updateDataSource();
        }
    }

    updateDataSource() {
        let tot = 0;
        for (const x of this.data) {
            if (this.existsInZone(x)) {
                x.zone = true;
                tot++;
            } else {
                x.zone = false;
            }
        }
        this.dataSource = new MatTableDataSource(this.data);
        // this.changeDetectorRefs.detectChanges();
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.numItemInZone = tot;
    }

    removeToZone(row: any, index: number) {
        console.log(row);
        const ix = this.indexOfZone(row);
        this.zone.splice(ix, 1);
        this.updateDataSource();
        // if (ix >= 0 && ix < this.data.length) {
        //     this.data[ix].zone = false;
        //     this.updateDataSource();
        // }
    }

    save() {
        console.log('Save');
        this.api.saveAddressZone(this.zone).subscribe(
            (resp) => {
                console.log(resp);
            }
        );
    }

    private existsInZone(row: any): boolean {
        return this.indexOfZone(row) >= 0;
    }

    private indexOfZone(row: any): number {
        if (row.adaddress && row.adaddress.trim().length > 0) {
            for (let i = 0; i < this.zone.length; i++) {
                const z = this.zone[i];
                if (z.azaddress == row.adaddress) {
                    return i;
                }
            }

        } else if (row.adcap && row.adcap.trim().length > 0) {
            for (let i = 0; i < this.zone.length; i++) {
                const z = this.zone[i];
                if (z.azcap == row.adcap && !z.azaddress) {
                    return i;

                }
            }

        }
        return -1;
    }
}

import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { APPCONFIG } from '../../config';
import { ApiService } from '../../services/api.service';

@Component({
    selector: 'app-lead',
    templateUrl: './lead.component.html',
    styleUrls: ['./lead.component.scss']
})
export class LeadComponent implements OnInit {

    displayedColumns: string[] = ['lid', 'cusurname', 'cuname', 'lctdescrizione', 'N', 'action'];
    dataSource: MatTableDataSource<LeadData>;

    @ViewChild(MatPaginator) paginator: MatPaginator;
    @ViewChild(MatSort) sort: MatSort;
    AppConfig: any;

    constructor(private api: ApiService) {

        const leads = [];
        // Assign the data to the data source for the table to render
        this.dataSource = new MatTableDataSource(leads);
    }

    ngOnInit() {
        this.AppConfig = APPCONFIG;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.loadLead();

    }

    applyFilter(filterValue: string) {
        this.dataSource.filter = filterValue.trim().toLowerCase();

        if (this.dataSource.paginator) {
            this.dataSource.paginator.firstPage();
        }
    }

    private loadLead() {
        this.api.loadLead({}).subscribe(
            (resp) => {
                const d = this.api.resp2Data(resp).data;
                this.dataSource = new MatTableDataSource(d);
                // this.changeDetectorRefs.detectChanges();
                this.dataSource.paginator = this.paginator;
                this.dataSource.sort = this.sort;
            }
        );
    }
}

//
export interface LeadData {
    lid: number;
    laccount: number;
    ltype: number;
    lowner: number;
    lassign: number;
    ldtmod: string;
    lstatus: number;
    ldescr: string;
    lfkcustomer: number;
    ledtins: string;
    lefkmediaarrivo: number;
    lctdescrizione: string;
    lcmadescrizione: string;
    lcsdescrizione: string;
    cuname: string;
    cusurname: string;
    annullabile: boolean;
    N: number;
}

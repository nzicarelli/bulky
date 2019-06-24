import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { MatDialog, MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { APPCONFIG } from '../../config';
import { Router } from '@angular/router';
import { DialogUpdAddrZoneComponent } from '../update-address-zone/dialog-upd-addr-zone/dialog-upd-addr-zone.component';

@Component({
    selector: 'app-plan-list',
    templateUrl: './plan-list.component.html',
    styleUrls: ['./plan-list.component.scss']
})
export class PlanListComponent implements OnInit {

    comune: any;
    comuni: any = [];
    zona: any;
    zone: any = [];

    indirizzi: any = [];
    indirizzo: any;

    displayedColumns: string[] = ['cuid', 'cusurname', 'cuname', 'cucf', 'action'];
    dataSource: MatTableDataSource<CustomerData>;

    @ViewChild(MatPaginator) paginator: MatPaginator;
    @ViewChild(MatSort) sort: MatSort;
    AppConfig: any;

    constructor(private api: ApiService, private changeDetectorRefs: ChangeDetectorRef, private router: Router,
                private dialog: MatDialog) {
        // Create 100 users
        const customeres = [];

        // Assign the data to the data source for the table to render
        this.dataSource = new MatTableDataSource(customeres);

    }

    ngOnInit() {
        this.AppConfig = APPCONFIG;
        this.api.listComuni({}).subscribe((resp) => {
            const result = this.api.resp2Data(resp);
            this.comuni = result.data;
            this.comune = APPCONFIG.comune;
            if (this.comune) {
                this.onSelectComune('');
            }
        }, (error) => {
            console.log(error);
        });

        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;

    }

    applyFilter(filterValue: string) {
        this.dataSource.filter = filterValue.trim().toLowerCase();

        if (this.dataSource.paginator) {
            this.dataSource.paginator.firstPage();
        }
    }

    onSelectComune(evt: any) {
        // console.log(evt);
        console.log(this.comune + ' ' + this.paginator.pageIndex + ' ' + this.paginator.pageSize);
        this.AppConfig.comune = this.comune;
        this.api.listCustomer({comune: this.comune ? this.comune.adcomune : ''}).subscribe(
            (resp) => {
                const data = this.api.resp2Data(resp);
                console.log(data);
                this.dataSource = new MatTableDataSource(data.data);
                // this.changeDetectorRefs.detectChanges();
                this.dataSource.paginator = this.paginator;
                this.dataSource.sort = this.sort;
            }, (error) => {
                console.log(error);
            }
        );

        this.api.listZoneByComune({comune: this.comune ? this.comune.adcomune : ''}).subscribe(
            (resp) => {
                this.zone = this.api.resp2Data(resp).data;
            }
        );
    }

    onSelectZona(evt: any) {
        console.log(this.zona);
        this.api.listIndirizziByZone({idzona: this.zona.zid}).subscribe(
            (resp) => {
                this.indirizzi = this.api.resp2Data(resp).data;
            }
        );
    }

    onSelectIndirizzo(evt: any) {
        console.log(this.indirizzo);

    }

    updateZone() {
        const dialogRef = this.dialog.open(DialogUpdAddrZoneComponent, {
            height: '600px',
            width: '800px'
        });
        dialogRef.componentInstance.comune = this.comune;
        dialogRef.componentInstance.zona = this.zona;
        dialogRef.componentInstance.zone = this.indirizzi;
    }

    showCollList(row: any) {
        this.AppConfig.customer = row;
        // {path : 'heroes', component : HeroDetailComponent, data : {some_data : 'some value'}
        this.router.navigate(['/app/crm/coll-list', {data: row.cuid}]);
        // this.router.navigate([{path: 'app/crm/coll-list', data: {data: row}}]);
    }
}

export interface CustomerData {
    cuid: number;
    cufkaccount: number;
    cuname: string;
    cusurname: string;
    cucf: string
    cupiva: string;
    cufktype: number;
    cudtmod: string;
    cudtins: string;
    cuusermod: number;
    cuusername: string;
    cupassword: string;
    cuenabled: boolean;
    cunote: string;
    cucode01: string;
    cucode02: string;

}


/** Constants used to fill up our data base. */
const COLORS: string[] = ['maroon', 'red', 'orange', 'yellow', 'olive', 'green', 'purple',
    'fuchsia', 'lime', 'teal', 'aqua', 'blue', 'navy', 'black', 'gray'];
const NAMES: string[] = ['Maia', 'Asher', 'Olivia', 'Atticus', 'Amelia', 'Jack',
    'Charlotte', 'Theodore', 'Isla', 'Oliver', 'Isabella', 'Jasper',
    'Cora', 'Levi', 'Violet', 'Arthur', 'Mia', 'Thomas', 'Elizabeth'];

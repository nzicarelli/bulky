import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { MatDialog, MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { APPCONFIG } from '../../config';
import { Router } from '@angular/router';
import { DialogUpdAddrZoneComponent } from '../update-address-zone/dialog-upd-addr-zone/dialog-upd-addr-zone.component';
import { UpdateZoneComponent } from '../update-address-zone/update-zone/update-zone.component';

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
    catgActions: any = [];
    catgTipoLead: any = [];
    loadIndex = 0;

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

        this.api.listCatgTipoLead({}).subscribe((resp) => {
            const result = this.api.resp2Data(resp);
            this.catgTipoLead = result.data;
            this.loadIndex = 0;
            this.loadLead();

        }, (error) => {
            console.log(error);
        });

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
        this.loadCustomers();


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
        this.loadCustomers();
    }

    onSelectIndirizzo(evt: any) {
        console.log(this.indirizzo);
        this.loadCustomers();
    }

    updateZone() {
        const dialogRef = this.dialog.open(UpdateZoneComponent, {
            height: '600px',
            width: '800px'
        });
        dialogRef.componentInstance.comune = this.comune;
        dialogRef.componentInstance.zona = this.zona;
    }

    updateZoneNew() {
        const dialogRef = this.dialog.open(UpdateZoneComponent, {
            height: '600px',
            width: '800px'
        });
        dialogRef.componentInstance.comune = this.comune;
        dialogRef.componentInstance.zona = {
            zid: undefined,
            zcomune: this.comune ? this.comune.adcomune : '',
            zdescr: ''
        };
    }

    updateAddressZone() {
        const dialogRef = this.dialog.open(DialogUpdAddrZoneComponent, {
            height: '600px',
            width: '800px'
        });
        dialogRef.componentInstance.comune = this.comune;
        dialogRef.componentInstance.zona = this.zona;
        dialogRef.componentInstance.zone = this.indirizzi;
    }

    showCollList(row: any, action: any) {
        this.AppConfig.customer = row;
        this.AppConfig.action = action;
        // {path : 'heroes', component : HeroDetailComponent, data : {some_data : 'some value'}
        this.router.navigate(['/app/crm/coll-list', {data: row.cuid, act: action.caid}]);
        // this.router.navigate([{path: 'app/crm/coll-list', data: {data: row}}]);
    }

    private loadCustomers() {
        this.api.listCustomer({
            comune: this.comune ? this.comune.adcomune : '',
            zona: this.zona ? '' + this.zona.zid : '',
            indirizzo: this.indirizzo ? this.indirizzo.azaddress : ''
        }).subscribe(
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
    }

    private loadLead() {
        if (!this.catgTipoLead) {
            return;
        }
        if (this.loadIndex < 0 || this.loadIndex >= this.catgTipoLead.length) {
            return;
        }
        this.api.listCatgAction({tlead: this.catgTipoLead[this.loadIndex].lctid}).subscribe(
            (resp) => {
                this.catgTipoLead[this.loadIndex].actions = this.api.resp2Data(resp).data;
                this.loadIndex++;
                this.loadLead();
            }, (error) => {
                console.log(error);
            }
        );
    }
}

export interface CustomerData {
    cuid: number;
    cufkaccount: number;
    cuname: string;
    cusurname: string;
    cucf: string;
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

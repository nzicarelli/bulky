import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';

@Component({
    selector: 'app-plan-list',
    templateUrl: './plan-list.component.html',
    styleUrls: ['./plan-list.component.scss']
})
export class PlanListComponent implements OnInit {

    comune: any;
    comuni: any = [];

    displayedColumns: string[] = ['cuid', 'cusurname', 'cuname', 'cucf', 'progress', 'color'];
    dataSource: MatTableDataSource<CustomerData>;

    @ViewChild(MatPaginator) paginator: MatPaginator;
    @ViewChild(MatSort) sort: MatSort;

    constructor(private api: ApiService, private changeDetectorRefs: ChangeDetectorRef) {
        // Create 100 users
        const customeres = [];

        // Assign the data to the data source for the table to render
        this.dataSource = new MatTableDataSource(customeres);
    }

    ngOnInit() {
        this.api.listComuni({}).subscribe((resp) => {
            const result = this.api.resp2Data(resp);
            this.comuni = result.data;
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
        console.log(evt);
        console.log(this.comune);

        this.api.listCustomer({comune: this.comune}).subscribe(
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

import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs/index';
import { startWith, map } from 'rxjs/operators';
import { ApiService } from '../../services/api.service';
import { StepperSelectionEvent } from '@angular/cdk/stepper';

@Component({
    selector: 'app-planner',
    templateUrl: './planner.component.html',
    styleUrls: ['./planner.component.scss']
})
export class PlannerComponent implements OnInit {
    isLinear: false;


    secondFormGroup: FormGroup;

    @Input()
    tipoAction: any;
    @Input()
    indirizzo: any;

    catgGroups: CatgGroup[];

    catgForm: FormGroup = this._formBuilder.group({
        catgGroup: '',
    });

    catgGroupOptions: Observable<CatgGroup[]>;

    basket: any = [];
    avaiablePlans: any = [];
    mySeletedDate: any;

    @ViewChild('planList')
    planList: any;

    constructor(private _formBuilder: FormBuilder, private api: ApiService) {
    }

    ngOnInit() {
        console.log('Planner');
        this.catgGroups = [];
        this.basket = [];
        this.api.listCatg({}).subscribe(
            (resp) => {
                const _data = this.api.resp2Data(resp);
                for (const x of _data.data) {
                    let catg: CatgGroup;
                    for (const c of this.catgGroups) {
                        if (c.tipo === x.crtype) {
                            catg = c;
                            break;
                        }
                    }
                    if (!catg) {
                        catg = {
                            values: [],
                            tipo: x.crtype
                        };
                        this.catgGroups.push(catg);
                    }
                    if (!catg.values) {
                        catg.values = [];
                    }
                    catg.values.push(x);
                }
                console.log(this.catgGroups);
            }
        );

        // this.catgFormGroup = this._formBuilder.group({
        //     catgGroup: '',
        // });
        this.secondFormGroup = this._formBuilder.group({
            secondCtrl: ['', Validators.required]
        });

        this.catgGroupOptions = this.catgForm.get('catgGroup')!.valueChanges
            .pipe(
                startWith(''),
                map((value) => this._filterGroup(value))
            );
    }

    onSelect(evento: any, evt: any) {
        if (!evento.source.value) {
            return;
        }
        if (!this.basket) {
            this.basket = [];
        }
        let trovato = false;
        for (const x of this.basket) {
            if (x.materiale.crid === evt.crid) {
                x.qty = x.qty + 1;
                trovato = true;
                break;
            }
        }
        if (!trovato) {
            const x: any = {
                materiale: evt,
                qty: 1
            }
            this.basket.push(x);
        }

        evento.source.value = ''


    }

    updateQty(item: any, qty: number) {
        const val = (item.qty + qty);
        if (val > item.materiale.crqtymax || val < item.materiale.crqtymin) {
            return;
        }
        item.qty = item.qty + qty;
    }

    removeItem(index: number) {
        if (index >= 0 && index < this.basket.length) {
            // this.basket =
            this.basket.splice(index, 1);
        }
    }

    private _filterGroup(value: string): CatgGroup[] {
        if (value) {
            return this.catgGroups
                .map((group) => ({tipo: group.tipo, values: _filter(group.values, value)}))
                .filter((group) => group.values.length > 0);
        }
        return this.catgGroups;
    }

    selectionChange($event?: StepperSelectionEvent): void {
        console.log(' Step ' + $event.selectedIndex);

        if ($event.selectedIndex == 1) {
            // load pianificazioni possibili
            console.log(this.indirizzo); // adaddress {adid: 12, adaddress: "CORSO ITALIA", adcap: "87046", adcomune: "MOLTALTO UFFUGO", addtins: null, …}
            this.api.listPlanDetail4Address({
                indirizzo: this.indirizzo ? this.indirizzo.adaddress : ''
            }).subscribe(
                (data) => {
                    const _data = this.api.resp2Data(data);
                    console.log(_data.data);
                    this.avaiablePlans = _data.data;
                }, (error) => {

                }
            );
        } else if ($event.selectedIndex == 2) {
            if (this.planList.selectedOptions) {
                for (const x of this.planList.selectedOptions.selected) {
                    console.log(x.value);
                }
            }
        }
    }

    onPlanSelectChange($event:any){
        this.planList.deselectAll();
        $event.option.selected = true;
    }

    // stateGroups: StateGroup[] = [{
    //     letter: 'A',
    //     names: ['Alabama', 'Alaska', 'Arizona', 'Arkansas']
    // }, {
    //     letter: 'C',
    //     names: ['California', 'Colorado', 'Connecticut']
    // }, {
    //     letter: 'D',
    //     names: ['Delaware']
    // }, {
    //     letter: 'F',
    //     names: ['Florida']
    // }, {
    //     letter: 'G',
    //     names: ['Georgia']
    // }, {
    //     letter: 'H',
    //     names: ['Hawaii']
    // }, {
    //     letter: 'I',
    //     names: ['Idaho', 'Illinois', 'Indiana', 'Iowa']
    // }, {
    //     letter: 'K',
    //     names: ['Kansas', 'Kentucky']
    // }, {
    //     letter: 'L',
    //     names: ['Louisiana']
    // }, {
    //     letter: 'M',
    //     names: ['Maine', 'Maryland', 'Massachusetts', 'Michigan',
    //         'Minnesota', 'Mississippi', 'Missouri', 'Montana']
    // }, {
    //     letter: 'N',
    //     names: ['Nebraska', 'Nevada', 'New Hampshire', 'New Jersey',
    //         'New Mexico', 'New York', 'North Carolina', 'North Dakota']
    // }, {
    //     letter: 'O',
    //     names: ['Ohio', 'Oklahoma', 'Oregon']
    // }, {
    //     letter: 'P',
    //     names: ['Pennsylvania']
    // }, {
    //     letter: 'R',
    //     names: ['Rhode Island']
    // }, {
    //     letter: 'S',
    //     names: ['South Carolina', 'South Dakota']
    // }, {
    //     letter: 'T',
    //     names: ['Tennessee', 'Texas']
    // }, {
    //     letter: 'U',
    //     names: ['Utah']
    // }, {
    //     letter: 'V',
    //     names: ['Vermont', 'Virginia']
    // }, {
    //     letter: 'W',
    //     names: ['Washington', 'West Virginia', 'Wisconsin', 'Wyoming']
    // }];

}

// export interface StateGroup {
//     letter: string;
//     names: string[];
// }

export interface CatgGroup {
    tipo: string;
    values: Catg[];
}

export interface Catg {
    crid: number;
    craccount: number;
    crname: string;
    crtype: string;
    crqtymin: number;
    crqtymax: number;
    crudm: string;
    crincombro: number;
    crnote: string;
    crdtmod: string;
    crusermod: number;

}

export const _filter = (opt: Catg[], value: string): Catg[] => {
    const filterValue = value.toLowerCase();

    return opt.filter((item) => item.crname.toLowerCase().indexOf(filterValue) === 0);
};

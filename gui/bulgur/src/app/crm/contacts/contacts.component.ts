import {Component, OnInit} from '@angular/core';
import {ApiService} from "../../services/api.service";
import {NgSelectModule, NgOption} from '@ng-select/ng-select';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.scss']
})
export class ContactsComponent implements OnInit {
  comuni: { id: string; name: string }[]=[];
  selectedComune: any;
  tableData1:any;

  constructor(private api: ApiService) {
    this.comuni.push({id:'prova',name:'prova'});
  }

  ngOnInit() {
    this.comuni = [];
    this.api.listComuni({}).subscribe(
      (data) => {
        console.log(data);
        if (data) {
          const _data = this.api.resp2Data(data);
          for (const x of _data.data) {
            // adcap: "87046"
            // adcomune: "MOLTALTO UFFUGO"
            // adsiglaprov: "CS"
            this.comuni = [...this.comuni,{id: x.adcomune, name: x.adcomune}];

          }
        }
      }, (error) => {

      });

    this.tableData1 = {
      headerRow: [ 'ID', 'Name', 'Country', 'City', 'Salary'],
      dataRows: [
        ['1', 'Dakota Rice', 'Niger', 'Oud-Turnhout', '$36,738'],
        ['2', 'Minerva Hooper', 'Curaçao', 'Sinaai-Waas', '$23,789'],
        ['3', 'Sage Rodriguez', 'Netherlands', 'Baileux', '$56,142'],
        ['4', 'Philip Chaney', 'Korea, South', 'Overland Park', '$38,735'],
        ['5', 'Doris Greene', 'Malawi', 'Feldkirchen in Kärnten', '$63,542'],
        ['6', 'Mason Porter', 'Chile', 'Gloucester', '$78,615']
      ]
    };


  }

  onComuneSelect(evt:any){
    console.log(evt);
    if (evt) {
      const comune = evt.name;
    }else{
      // clean filter
    }

  }

}

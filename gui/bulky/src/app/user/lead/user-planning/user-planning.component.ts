import { Component, OnInit } from '@angular/core';
import {BulkyService} from "../../../_services/bulky.service";

@Component({
  selector: 'app-user-planning',
  templateUrl: './user-planning.component.html',
  styleUrls: ['./user-planning.component.css']
})
export class UserPlanningComponent implements OnInit {

  listZone: any[] = [];
  listPlannong: any[] = [];
  zSel: any = {};
  pSel: any = {};
  dettPlanning: any[] = [];
  rowGroupMetadata: any;

  constructor(private bluServ: BulkyService) { }

  ngOnInit() {
    this.loadZone();
  }

  loadZone() {
    this.bluServ.getListZone().then( (res: any) => {
      this.listZone = res.output;
    });
  }

  changeZona(evt) {
    this.bluServ.getListPlanning4zona(this.zSel.zid).then((res: any) => {
      this.listPlannong = res.output;
    });
  }

  changePlanning(evt) {
    console.log('changePlanning ' + evt);
    this.bluServ.getListPlanning4zonaCli(this.pSel.plnid).then((res: any) => {
      this.dettPlanning = res.output;
      this.updateRowGroupMetaData();
    });
  }

  updateRowGroupMetaData() {
    console.log('GroupRow');
    this.rowGroupMetadata = {};
    if (this.dettPlanning) {
      for (let i = 0; i < this.dettPlanning.length; i++) {
        let rowData = this.dettPlanning[i];
        let brand = rowData.pldid;
        if (i == 0) {
          this.rowGroupMetadata[brand] = { index: 0, size: 1 };
        }
        else {
          let previousRowData = this.dettPlanning[i - 1];
          let previousRowGroup = previousRowData.pldid;
          if (brand === previousRowGroup)
            this.rowGroupMetadata[brand].size++;
          else
            this.rowGroupMetadata[brand] = { index: i, size: 1 };
        }
      }
    }
  }

}

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
  listDettPlannong: any[] = [];
  zSel: any = {};
  pSel: any = {};
  pdSel: any = {};
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
    this.pSel = {};
    this.pdSel = {};
    const tzid = this.zSel && this.zSel.zid && this.zSel.zid > 0 ? this.zSel.zid : undefined;
    if (tzid) {
      this.bluServ.getListPlanning4zona(this.zSel.zid).then((res: any) => {
        this.listPlannong = res.output;
      });
    } else {
      this.listPlannong = [];
      this.dettPlanning = [];
    }
  }

  changePlanning(evt) {
    console.log('changePlanning ' + evt);
    this.pdSel = {};
    this.loadDettPlanning();
    const tplnid = this.pSel && this.pSel.plnid && this.pSel.plnid > 0 ? this.pSel.plnid : undefined;
    if (tplnid) {
      this.bluServ.getListPlanning4zonaCli(this.pSel.plnid, undefined).then((res: any) => {
        this.dettPlanning = res.output;
        this.updateRowGroupMetaData();
      });
    } else {
      this.dettPlanning = [];
    }
  }

  loadDettPlanning() {
    const tplnid = this.pSel && this.pSel.plnid && this.pSel.plnid > 0 ? this.pSel.plnid : undefined;
    this.bluServ.getPlanningDetail(tplnid).then((res: any) => {
      this.listDettPlannong = res.output;
    });
  }

  changeDettPlanning(evt) {
    const tplnid = this.pSel && this.pSel.plnid && this.pSel.plnid > 0 ? this.pSel.plnid : undefined;
    const tpldid = this.pdSel && this.pdSel.plan && this.pdSel.plan.pldid && this.pdSel.plan.pldid > 0 ? this.pdSel.plan.pldid : undefined;
    this.bluServ.getListPlanning4zonaCli(tplnid, tpldid).then((res: any) => {
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

import {Component, OnInit} from '@angular/core';
import {ApiService} from '../../services/api.service';

@Component({
  selector: 'app-plan-list',
  templateUrl: './plan-list.component.html',
  styleUrls: ['./plan-list.component.scss']
})
export class PlanListComponent implements OnInit {

  comune: any;
  comuni:any = [];

  constructor(private api:ApiService) {
  }

  ngOnInit() {
    this.api.listComuni({}).subscribe((resp)=>{
      let result = this.api.resp2Data(resp);
      this.comuni = result.data;
    }, (error)=>{

    })
  }

  onSelectComune(evt: any) {
    console.log(evt);
    console.log(this.comune);
  }

}

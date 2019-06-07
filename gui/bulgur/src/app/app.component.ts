import {Component, OnInit} from '@angular/core';
import {APPCONFIG} from './config';

declare var $:any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  AppConfig:any

  constructor(){

  }

  ngOnInit(): void {
    this.AppConfig = APPCONFIG;
  }
}

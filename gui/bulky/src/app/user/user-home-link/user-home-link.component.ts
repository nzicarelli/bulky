import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-user-home-link',
  templateUrl: './user-home-link.component.html',
  styleUrls: ['./user-home-link.component.css']
})
export class UserHomeLinkComponent implements OnInit {

  dataChartXcomune: any;
  optionsChartXcomune: any;
  dataChartXstato: any;
  optionsChartXstato: any;
  dataChartXmese: any;
  optionsChartXmese: any;

  constructor(private router: Router, private route: ActivatedRoute) {


    this.buildChartComune();
    this.buildChartStato();
    this.buildChartMese();
  }

  ngOnInit() {
  }

  gotoAnagrafica() {
    this.router.navigate([{ outlets: { userOut: [  'list-clienti' ] }}],
      { skipLocationChange: true, relativeTo: this.route.parent });
  }

  gotoLead() {
    this.router.navigate([{ outlets: { userOut: [  'list-lead' ] }}],
      { skipLocationChange: true, relativeTo: this.route.parent });
  }

  gotoPlanning() {
    this.router.navigate([{ outlets: { userOut: [  'planning' ] }}],
      { skipLocationChange: true, relativeTo: this.route.parent });
  }

  buildChartComune() {
    this.dataChartXcomune = {
      labels: ['A','B','C'],
      datasets: [
        {
          data: [300, 50, 100],
          backgroundColor: [
            "#FF6384",
            "#36A2EB",
            "#FFCE56"
          ],
          hoverBackgroundColor: [
            "#FF6384",
            "#36A2EB",
            "#FFCE56"
          ]
        }]
    };

    this.optionsChartXcomune = {
      title: {
        display: true,
        text: 'Lead Mese per Comune',
        fontSize: 16
      },
      legend: {
        position: 'bottom'
      }
    };
  }

  buildChartStato() {
    this.dataChartXstato = {
      labels: ['A','B','C'],
      datasets: [
        {
          data: [300, 50, 100],
          backgroundColor: [
            "#FF6384",
            "#36A2EB",
            "#FFCE56"
          ],
          hoverBackgroundColor: [
            "#FF6384",
            "#36A2EB",
            "#FFCE56"
          ]
        }]
    };

    this.optionsChartXstato = {
      title: {
        display: true,
        text: 'Lead Mese per Stato',
        fontSize: 16
      },
      legend: {
        position: 'bottom'
      }
    };
  }

  buildChartMese() {
    this.dataChartXmese = {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [
        {
          label: 'First Dataset',
          data: [65, 59, 80, 81, 56, 55, 40],
          fill: false,
          borderColor: '#4bc0c0'
        },
        {
          label: 'Second Dataset',
          data: [28, 48, 40, 19, 86, 27, 90],
          fill: false,
          borderColor: '#565656'
        }
      ]
    };

    this.optionsChartXmese = {
      title: {
        display: true,
        text: 'Numero Lead per Mese',
        fontSize: 16
      },
      legend: {
        position: 'bottom'
      }
    };
  }
}

import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, NavigationEnd, Params, Router} from "@angular/router";
import {AuthenticationService} from "../../_services/authentication.service";
import {ActionService} from "../../_services/action.service";
import {MenuItem} from "primeng/api";

interface IBreadcrumb {
  label: string;
  params: {
    [key: string]: any;
  };
  // url: string;
  routerLink: any[];
  append: boolean;
  noRoute: boolean;
}

@Component({
  selector: 'app-customer-home',
  templateUrl: './customer-home.component.html',
  styleUrls: ['./customer-home.component.css']
})
export class CustomerHomeComponent implements OnInit, OnDestroy {

  public myName = '';
  breadcrumbs: MenuItem[];

  home: MenuItem;
  public listner: any;
  public listnerBread: any;

  constructor(private router: Router, private route: ActivatedRoute,
              private authService: AuthenticationService, private actService: ActionService) { }

  ngOnInit() {
  console.log('INIT CUSTOMER');
    // this.router.navigate([{ outlets: { customerOut: ['home-act'] } }],
    //   { skipLocationChange: true, relativeTo: this.route });
    this.myName = this.authService.getName();

    this.home = {icon: 'pi pi-home', routerLink: '/customer'};

    this.breadcrumbs = [
      {label:'Categories'},
      {label:'Sports'},
      {label:'Football'},
      {label:'Countries'},
      {label:'Spain'},
      {label:'F.C. Barcelona'},
      {label:'Squad'},
      {label:'Lionel Messi', url: 'https://en.wikipedia.org/wiki/Lionel_Messi', icon: 'pi pi-external-link'}
    ];

    this.listner = this.router.events.filter(event => event instanceof NavigationEnd).subscribe(event => {
      //set breadcrumbs
      let root: ActivatedRoute = this.route.root;
      this.breadcrumbs = this.getBreadcrumbs(root);
/*
      if (this.breadcrumbs && this.breadcrumbs.length > 0) {
        if (this.breadcrumbs[this.breadcrumbs.length - 1].append &&
          (this.otherMenuItems && this.otherMenuItems.length > 0 && this.otherMenuItems[0].label == this.breadcrumbs[0].label)) {
          if (!this.otherMenuItems)
            this.otherMenuItems = [];
          //this.otherMenuItems.push({ label: this.breadcrumbs[this.breadcrumbs.length - 1].label, routerLink: [this.breadcrumbs[this.breadcrumbs.length - 1].url, this.breadcrumbs[this.breadcrumbs.length - 1].params] });
          if (this.breadcrumbs[this.breadcrumbs.length - 1].label != this.otherMenuItems[this.otherMenuItems.length - 1].label) {
            if (this.otherMenuItems[this.otherMenuItems.length - 1].append) {
              this.otherMenuItems.splice(this.otherMenuItems.length - 1, 1, { label: this.breadcrumbs[this.breadcrumbs.length - 1].label, routerLink: [this.breadcrumbs[this.breadcrumbs.length - 1].url], append: true });
            } else {
              this.otherMenuItems.push({ label: this.breadcrumbs[this.breadcrumbs.length - 1].label, routerLink: [this.breadcrumbs[this.breadcrumbs.length - 1].url], append: true });
            }
          }
        } else {
          this.otherMenuItems = [];
          if (this.breadcrumbs.length == 1) {
            var tmp = this.breadcrumbs[0];
            if (tmp.url == ('/' + (this.authenticationService.getUserLogged().homePage || ''))) {
              this.otherMenuItems = null;
            } else {
              if (tmp.noRoute && tmp.noRoute == true) {
                this.otherMenuItems.push({ label: tmp.label });
              } else {
                this.otherMenuItems.push({ label: tmp.label, routerLink: [tmp.url] });
              }
            }
          } else {
            for (var elem of this.breadcrumbs) {
              if (elem.noRoute && elem.noRoute == true) {
                this.otherMenuItems.push({ label: elem.label });
              } else {
                //this.otherMenuItems.push({ label: elem.label, routerLink: [elem.url, elem.params] });
                this.otherMenuItems.push({ label: elem.label, routerLink: [elem.url] });
              }
            }
          }
        }
      } else {
        this.otherMenuItems = null;
      }
*/
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login'], {});
  }

  gotoAction(act) {
    console.log(act);
    // this.router.navigate(['(customerOut:act-standard)'], {});

    this.router.navigate([{ outlets: { customerOut: ['act-standard'] } }],
       { skipLocationChange: true, relativeTo: this.route });
  }

  ngOnDestroy() {
    if (this.listner) {
      this.listner.unsubscribe();
    }
    if (this.listnerBread) {
      this.listnerBread.unsubscribe();
    }
  }

  /**
   * Returns array of IBreadcrumb objects that represent the breadcrumb
   *
   * @class DetailComponent
   * @method getBreadcrumbs
   * @param {ActivateRoute} route
   * @param {string} url
   * @param {IBreadcrumb[]} breadcrumbs
   */
  private getBreadcrumbs(route: ActivatedRoute, url: string = "", breadcrumbs: IBreadcrumb[] = []): IBreadcrumb[] {
    const ROUTE_DATA_BREADCRUMB: string = "breadcrumb";
    const ROUTE_DATA_APPEND: string = "append";
    const ROUTE_DATA_NOROUTE: string = "noRoute";

    //get the child routes
    let children: ActivatedRoute[] = route.children;

    //return if there are no more children
    if (children.length === 0) {
      return breadcrumbs;
    }

    //iterate over each children
    for (let child of children) {
      //verify primary route
      /*if (child.outlet !== 'primary') {
        continue;
      }*/

      if ((child.outlet === 'primary') && !child.snapshot.data.hasOwnProperty(ROUTE_DATA_BREADCRUMB)) {
        return this.getBreadcrumbs(child, url, breadcrumbs);
      } else {
        if (child.outlet === 'primary') {
          continue;
        }
      }

      //verify the custom data property "breadcrumb" is specified on the route
      if (!child.snapshot.data.hasOwnProperty(ROUTE_DATA_BREADCRUMB)) {
        return this.getBreadcrumbs(child, url, breadcrumbs);
      }

      //get the route's URL segment
      let routeURL: string = child.snapshot.url.map(segment => segment.path).join("/");

      //append route URL to URL
      url += `${routeURL}`;

      //add breadcrumb
      //url: url
      let breadcrumb: IBreadcrumb = {
        label: child.snapshot.data[ROUTE_DATA_BREADCRUMB],
        params: child.snapshot.params,
        routerLink: [{ outlets: { customerOut: url} }],
        append: child.snapshot.data[ROUTE_DATA_APPEND],
        noRoute: child.snapshot.data[ROUTE_DATA_NOROUTE]
      };

      breadcrumbs.push(breadcrumb);

      //recursive
      return this.getBreadcrumbs(child, url, breadcrumbs);
    }
  }

}

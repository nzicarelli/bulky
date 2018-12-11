import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {MenuItem} from "primeng/api";
import {AuthenticationService} from "../../_services/authentication.service";


interface IBreadcrumb {
  label: string;
  params: {
    [key: string]: any;
  };
  // url: string;
  routerLink: string;
  append: boolean;
  noRoute: boolean;
}


@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {

  public myName = '';
  userMenu: MenuItem[];
  itemsMenu: MenuItem[];

  public otherMenuItems: any[];
  breadcrumbs: MenuItem[];
  home: MenuItem;
  public listner: any;
  public listnerBread: any;

  constructor(private router: Router, private route: ActivatedRoute, private authService: AuthenticationService) { }

  ngOnInit() {
    this.myName = this.authService.getName();

    this.home = {icon: 'pi pi-home', routerLink: '/user'};

    // this.listner = this.router.events.filter(event => event instanceof NavigationEnd).subscribe(event => {
    //   //set breadcrumbs
    //   console.log('BUILD BREADCRUMB');
    //   let root: ActivatedRoute = this.route.root;
    //   const a = this.getBreadcrumbs(root);
    //   if (this.breadcrumbs) {
    //     this.breadcrumbs.push(...a);
    //   } else {
    //     this.breadcrumbs = a;
    //   }
    //
    //
    // });
  }

  test() {
    // this.router.navigate([ { outlet: { user: [ 'list-lead' ]}}]);
    //this.router.navigate([{ outlets: { userOut: 'list-user'} }]);
    this.router.navigate([{ outlets: { userOut: ['list-user'] } }],
      { skipLocationChange: true, relativeTo: this.route });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login'], {});
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
  // private getBreadcrumbs(route: ActivatedRoute, url: string = "", breadcrumbs: IBreadcrumb[] = []): IBreadcrumb[] {
  //   const ROUTE_DATA_BREADCRUMB: string = "breadcrumb";
  //   const ROUTE_DATA_APPEND: string = "append";
  //   const ROUTE_DATA_NOROUTE: string = "noRoute";
  //
  //   //get the child routes
  //   let children: ActivatedRoute[] = route.children;
  //
  //   //return if there are no more children
  //   if (children.length === 0) {
  //     return breadcrumbs;
  //   }
  //
  //   //iterate over each children
  //   for (let child of children) {
  //     //verify primary route
  //     /*if (child.outlet !== 'primary') {
  //       continue;
  //     }*/
  //
  //     if ((child.outlet === 'primary') && !child.snapshot.data.hasOwnProperty(ROUTE_DATA_BREADCRUMB) && (child.children && child.children.length > 0) ) {
  //       return this.getBreadcrumbs(child, url, breadcrumbs);
  //     } else {
  //       if (child.outlet === 'primary') {
  //         continue;
  //       }
  //     }
  //
  //     //verify the custom data property "breadcrumb" is specified on the route
  //     // if (!child.snapshot.data.hasOwnProperty(ROUTE_DATA_BREADCRUMB)) {
  //     //   return this.getBreadcrumbs(child, url, breadcrumbs);
  //     // }
  //
  //     //get the route's URL segment
  //     let routeURL: string = child.snapshot.url.map(segment => segment.path).join("/");
  //
  //     //append route URL to URL
  //     url += `${routeURL}`;
  //
  //     //add breadcrumb
  //     //url: url
  //     let breadcrumb: IBreadcrumb = {
  //       label: child.snapshot.data[ROUTE_DATA_BREADCRUMB],
  //       params: child.snapshot.params,
  //       routerLink: url,
  //       append: child.snapshot.data[ROUTE_DATA_APPEND],
  //       noRoute: child.snapshot.data[ROUTE_DATA_NOROUTE]
  //       // url: ''
  //     };
  //
  //     breadcrumbs.push(breadcrumb);
  //
  //     //recursive
  //     return this.getBreadcrumbs(child, url, breadcrumbs);
  //   }
  // }

}

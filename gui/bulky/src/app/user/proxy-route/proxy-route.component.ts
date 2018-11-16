/*
*  This is a meta component just for workaround until named outlets will support lazyloaded
*  modules. Taken from https://github.com/angular/angular/issues/12842#issuecomment-270836368
*  It is also the issue for this.
*  Original error says: “A componentless route cannot have a named outlet set”
*/

import { Component } from '@angular/core';

@Component({
  template: '<router-outlet></router-outlet>'
})
export class ProxyRouteComponent {}

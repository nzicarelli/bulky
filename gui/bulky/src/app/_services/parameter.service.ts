import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ParameterService {

  private catgAction: any;

  constructor() { }

  public getCatgAction() {
    return this.catgAction;
  }

  public setCatgAction(catgAct) {
    this.catgAction = catgAct;
  }
}

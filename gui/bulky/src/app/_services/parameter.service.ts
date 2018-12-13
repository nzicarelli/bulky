import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ParameterService {

  private catgAction: any;
  private idLead: number;

  constructor() { }

  public getCatgAction() {
    return this.catgAction;
  }

  public setCatgAction(catgAct) {
    this.catgAction = catgAct;
  }

  public setIdLead(id: number) {
    this.idLead = id;
  }

  public getIdLead() {
    return this.idLead;
  }
}

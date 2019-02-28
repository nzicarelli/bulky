import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AnagraficheService} from "../../../_services/anagrafiche.service";
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  @Input() user: any;
  @Output() close: EventEmitter<any> = new EventEmitter<any>();

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new MyErrorStateMatcher();

  constructor(private aServ: AnagraficheService) { }

  ngOnInit() {

  }

  annulla() {
    this.close.emit();
  }

  conferma() {
    this.aServ.saveUser(this.user).then( (res: any) => {
      if (res.success) {
        this.close.emit();
      }
    });
  }

}

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ElencoClientiComponent } from './elenco-clienti.component';

describe('ElencoClientiComponent', () => {
  let component: ElencoClientiComponent;
  let fixture: ComponentFixture<ElencoClientiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ElencoClientiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ElencoClientiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

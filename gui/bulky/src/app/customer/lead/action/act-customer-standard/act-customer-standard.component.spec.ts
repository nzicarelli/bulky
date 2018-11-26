import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActCustomerStandardComponent } from './act-customer-standard.component';

describe('ActCustomerStandardComponent', () => {
  let component: ActCustomerStandardComponent;
  let fixture: ComponentFixture<ActCustomerStandardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActCustomerStandardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActCustomerStandardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActCustomerBulkyComponent } from './act-customer-bulky.component';

describe('ActCustomerBulkyComponent', () => {
  let component: ActCustomerBulkyComponent;
  let fixture: ComponentFixture<ActCustomerBulkyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActCustomerBulkyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActCustomerBulkyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

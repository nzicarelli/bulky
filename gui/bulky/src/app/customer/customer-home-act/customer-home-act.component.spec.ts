import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerHomeActComponent } from './customer-home-act.component';

describe('CustomerHomeActComponent', () => {
  let component: CustomerHomeActComponent;
  let fixture: ComponentFixture<CustomerHomeActComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerHomeActComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerHomeActComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

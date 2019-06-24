import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAddressZoneComponent } from './update-address-zone.component';

describe('UpdateAddressZoneComponent', () => {
  let component: UpdateAddressZoneComponent;
  let fixture: ComponentFixture<UpdateAddressZoneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateAddressZoneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateAddressZoneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

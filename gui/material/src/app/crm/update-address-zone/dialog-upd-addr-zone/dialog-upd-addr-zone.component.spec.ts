import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogUpdAddrZoneComponent } from './dialog-upd-addr-zone.component';

describe('DialogUpdAddrZoneComponent', () => {
  let component: DialogUpdAddrZoneComponent;
  let fixture: ComponentFixture<DialogUpdAddrZoneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogUpdAddrZoneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogUpdAddrZoneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

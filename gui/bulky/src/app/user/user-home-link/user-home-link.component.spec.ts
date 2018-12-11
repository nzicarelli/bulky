import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserHomeLinkComponent } from './user-home-link.component';

describe('UserHomeLinkComponent', () => {
  let component: UserHomeLinkComponent;
  let fixture: ComponentFixture<UserHomeLinkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserHomeLinkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserHomeLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

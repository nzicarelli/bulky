import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLeadComponent } from './user-lead.component';

describe('UserLeadComponent', () => {
  let component: UserLeadComponent;
  let fixture: ComponentFixture<UserLeadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserLeadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserLeadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

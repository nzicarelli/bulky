import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLeadListComponent } from './user-lead-list.component';

describe('UserLeadListComponent', () => {
  let component: UserLeadListComponent;
  let fixture: ComponentFixture<UserLeadListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserLeadListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserLeadListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CollListComponent } from './coll-list.component';

describe('CollListComponent', () => {
  let component: CollListComponent;
  let fixture: ComponentFixture<CollListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CollListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CollListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

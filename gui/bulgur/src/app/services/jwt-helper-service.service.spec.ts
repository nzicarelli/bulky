import { TestBed } from '@angular/core/testing';

import { JwtHelperServiceService } from './jwt-helper-service.service';

describe('JwtHelperServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: JwtHelperServiceService = TestBed.get(JwtHelperServiceService);
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { ComparteinfService } from './comparteinf.service';

describe('ComparteinfService', () => {
  let service: ComparteinfService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ComparteinfService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

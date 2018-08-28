import { TestBed, inject } from '@angular/core/testing';

import { TurmasService } from './turmas.service';

describe('TurmasService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TurmasService]
    });
  });

  it('should be created', inject([TurmasService], (service: TurmasService) => {
    expect(service).toBeTruthy();
  }));
});

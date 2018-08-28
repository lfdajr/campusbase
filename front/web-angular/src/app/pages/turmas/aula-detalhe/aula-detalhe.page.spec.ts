import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AulaDetalhePage } from './aula-detalhe.page';

describe('AulaDetalhePage', () => {
  let component: AulaDetalhePage;
  let fixture: ComponentFixture<AulaDetalhePage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AulaDetalhePage ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AulaDetalhePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

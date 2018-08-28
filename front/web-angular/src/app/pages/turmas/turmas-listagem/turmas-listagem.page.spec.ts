import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TurmasListagemPage } from './turmas-listagem.page';

describe('TurmasListagemPage', () => {
  let component: TurmasListagemPage;
  let fixture: ComponentFixture<TurmasListagemPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TurmasListagemPage ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TurmasListagemPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

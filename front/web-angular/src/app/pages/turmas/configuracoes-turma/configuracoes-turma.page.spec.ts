import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfiguracoesTurmaPage } from './configuracoes-turma.page';

describe('TurmaDetalhePage', () => {
  let component: ConfiguracoesTurmaPage;
  let fixture: ComponentFixture<ConfiguracoesTurmaPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfiguracoesTurmaPage ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfiguracoesTurmaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

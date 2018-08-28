import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfiguracoesTurmaComponent } from './configuracoes-turma.component';

describe('ConfiguracoesTurmaComponent', () => {
  let component: ConfiguracoesTurmaComponent;
  let fixture: ComponentFixture<ConfiguracoesTurmaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfiguracoesTurmaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfiguracoesTurmaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

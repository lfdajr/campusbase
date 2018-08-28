import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AtividadeRecenteComponent } from './atividade-recente.component';

describe('AtividadeRecenteComponent', () => {
  let component: AtividadeRecenteComponent;
  let fixture: ComponentFixture<AtividadeRecenteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AtividadeRecenteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AtividadeRecenteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

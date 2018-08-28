import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AulaDetalheComponent } from './aula-detalhe.component';

describe('AulaDetalheComponent', () => {
  let component: AulaDetalheComponent;
  let fixture: ComponentFixture<AulaDetalheComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AulaDetalheComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AulaDetalheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

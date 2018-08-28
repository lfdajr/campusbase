import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NovaTurmaPage } from './nova-turma.page';

describe('NovaTurmaComponent', () => {
  let component: NovaTurmaPage;
  let fixture: ComponentFixture<NovaTurmaPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NovaTurmaPage ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NovaTurmaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

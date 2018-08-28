import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NovaAulaPage } from './nova-aula.page';

describe('NovaAulaComponent', () => {
  let component: NovaAulaPage;
  let fixture: ComponentFixture<NovaAulaPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NovaAulaPage ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NovaAulaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

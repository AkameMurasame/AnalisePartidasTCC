import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroInvocadorComponent } from './cadastro-invocador.component';

describe('CadastroInvocadorComponent', () => {
  let component: CadastroInvocadorComponent;
  let fixture: ComponentFixture<CadastroInvocadorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastroInvocadorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroInvocadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

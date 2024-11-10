import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistromedicamentosComponent } from './registromedicamentos.component';

describe('RegistromedicamentosComponent', () => {
  let component: RegistromedicamentosComponent;
  let fixture: ComponentFixture<RegistromedicamentosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistromedicamentosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistromedicamentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

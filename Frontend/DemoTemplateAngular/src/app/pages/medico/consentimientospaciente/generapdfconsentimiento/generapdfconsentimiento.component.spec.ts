import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerapdfconsentimientoComponent } from './generapdfconsentimiento.component';

describe('GenerapdfconsentimientoComponent', () => {
  let component: GenerapdfconsentimientoComponent;
  let fixture: ComponentFixture<GenerapdfconsentimientoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenerapdfconsentimientoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GenerapdfconsentimientoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

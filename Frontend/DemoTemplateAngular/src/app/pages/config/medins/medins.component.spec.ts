import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedinsComponent } from './medins.component';

describe('MedinsComponent', () => {
  let component: MedinsComponent;
  let fixture: ComponentFixture<MedinsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedinsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

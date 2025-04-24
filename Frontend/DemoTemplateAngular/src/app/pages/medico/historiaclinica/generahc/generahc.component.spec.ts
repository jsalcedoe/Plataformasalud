import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerahcComponent } from './generahc.component';

describe('GenerahcComponent', () => {
  let component: GenerahcComponent;
  let fixture: ComponentFixture<GenerahcComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenerahcComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GenerahcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

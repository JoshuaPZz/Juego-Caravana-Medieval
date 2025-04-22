import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicioComprarComponent } from './servicio-comprar.component';

describe('ServicioComprarComponent', () => {
  let component: ServicioComprarComponent;
  let fixture: ComponentFixture<ServicioComprarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ServicioComprarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServicioComprarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

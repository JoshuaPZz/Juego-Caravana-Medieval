import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RutaDestinosComponent } from './ruta-destinos.component';

describe('RutaDestinosComponent', () => {
  let component: RutaDestinosComponent;
  let fixture: ComponentFixture<RutaDestinosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RutaDestinosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RutaDestinosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViajarCiudadComponent } from './viajar-ciudad.component';

describe('ViajarCiudadComponent', () => {
  let component: ViajarCiudadComponent;
  let fixture: ComponentFixture<ViajarCiudadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViajarCiudadComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViajarCiudadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

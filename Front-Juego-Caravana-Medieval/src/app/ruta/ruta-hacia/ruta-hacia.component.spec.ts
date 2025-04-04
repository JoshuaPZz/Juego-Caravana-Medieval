import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RutaHaciaComponent } from './ruta-hacia.component';

describe('RutaHaciaComponent', () => {
  let component: RutaHaciaComponent;
  let fixture: ComponentFixture<RutaHaciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RutaHaciaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RutaHaciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

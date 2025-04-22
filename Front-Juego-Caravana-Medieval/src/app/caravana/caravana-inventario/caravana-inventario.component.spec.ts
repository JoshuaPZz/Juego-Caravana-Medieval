import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaravanaInventarioComponent } from './caravana-inventario.component';

describe('CaravanaInventarioComponent', () => {
  let component: CaravanaInventarioComponent;
  let fixture: ComponentFixture<CaravanaInventarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CaravanaInventarioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CaravanaInventarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

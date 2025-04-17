import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaravanaProductosListComponent } from './caravana-productos-list.component';

describe('CaravanaProductosListComponent', () => {
  let component: CaravanaProductosListComponent;
  let fixture: ComponentFixture<CaravanaProductosListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CaravanaProductosListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CaravanaProductosListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

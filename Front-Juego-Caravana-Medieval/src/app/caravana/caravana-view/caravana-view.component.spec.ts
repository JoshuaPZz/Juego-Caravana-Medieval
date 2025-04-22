import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaravanaViewComponent } from './caravana-view.component';

describe('CaravanaViewComponent', () => {
  let component: CaravanaViewComponent;
  let fixture: ComponentFixture<CaravanaViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CaravanaViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CaravanaViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

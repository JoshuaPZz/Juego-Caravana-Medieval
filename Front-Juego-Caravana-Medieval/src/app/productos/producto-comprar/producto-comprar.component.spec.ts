import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductoComprarComponent } from './producto-comprar.component';

describe('ProductoComprarComponent', () => {
  let component: ProductoComprarComponent;
  let fixture: ComponentFixture<ProductoComprarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductoComprarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductoComprarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

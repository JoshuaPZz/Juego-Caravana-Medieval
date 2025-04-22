import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductosVenderComponent } from './productos-vender.component';

describe('ProductosVenderComponent', () => {
  let component: ProductosVenderComponent;
  let fixture: ComponentFixture<ProductosVenderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductosVenderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductosVenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

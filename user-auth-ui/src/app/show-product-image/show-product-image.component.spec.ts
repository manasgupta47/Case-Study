import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowProductImageComponent } from './show-product-image.component';

describe('ShowProductImageComponent', () => {
  let component: ShowProductImageComponent;
  let fixture: ComponentFixture<ShowProductImageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowProductImageComponent]
    });
    fixture = TestBed.createComponent(ShowProductImageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { IProduct } from 'app/entities/product/product.model';
import { ProductService } from 'app/entities/product/service/product.service';
import { DemandService } from '../service/demand.service';
import { IDemand } from '../demand.model';
import { DemandFormService } from './demand-form.service';

import { DemandUpdateComponent } from './demand-update.component';

describe('Demand Management Update Component', () => {
  let comp: DemandUpdateComponent;
  let fixture: ComponentFixture<DemandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let demandFormService: DemandFormService;
  let demandService: DemandService;
  let productService: ProductService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [DemandUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(DemandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DemandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    demandFormService = TestBed.inject(DemandFormService);
    demandService = TestBed.inject(DemandService);
    productService = TestBed.inject(ProductService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Product query and add missing value', () => {
      const demand: IDemand = { id: 456 };
      const name: IProduct = { id: 18524 };
      demand.name = name;

      const productCollection: IProduct[] = [{ id: 22144 }];
      jest.spyOn(productService, 'query').mockReturnValue(of(new HttpResponse({ body: productCollection })));
      const additionalProducts = [name];
      const expectedCollection: IProduct[] = [...additionalProducts, ...productCollection];
      jest.spyOn(productService, 'addProductToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ demand });
      comp.ngOnInit();

      expect(productService.query).toHaveBeenCalled();
      expect(productService.addProductToCollectionIfMissing).toHaveBeenCalledWith(
        productCollection,
        ...additionalProducts.map(expect.objectContaining),
      );
      expect(comp.productsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const demand: IDemand = { id: 456 };
      const name: IProduct = { id: 19723 };
      demand.name = name;

      activatedRoute.data = of({ demand });
      comp.ngOnInit();

      expect(comp.productsSharedCollection).toContain(name);
      expect(comp.demand).toEqual(demand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDemand>>();
      const demand = { id: 123 };
      jest.spyOn(demandFormService, 'getDemand').mockReturnValue(demand);
      jest.spyOn(demandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ demand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: demand }));
      saveSubject.complete();

      // THEN
      expect(demandFormService.getDemand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(demandService.update).toHaveBeenCalledWith(expect.objectContaining(demand));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDemand>>();
      const demand = { id: 123 };
      jest.spyOn(demandFormService, 'getDemand').mockReturnValue({ id: null });
      jest.spyOn(demandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ demand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: demand }));
      saveSubject.complete();

      // THEN
      expect(demandFormService.getDemand).toHaveBeenCalled();
      expect(demandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDemand>>();
      const demand = { id: 123 };
      jest.spyOn(demandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ demand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(demandService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareProduct', () => {
      it('Should forward to productService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(productService, 'compareProduct');
        comp.compareProduct(entity, entity2);
        expect(productService.compareProduct).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});

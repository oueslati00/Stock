import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../demand.test-samples';

import { DemandFormService } from './demand-form.service';

describe('Demand Form Service', () => {
  let service: DemandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DemandFormService);
  });

  describe('Service methods', () => {
    describe('createDemandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDemandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            qT: expect.any(Object),
            demandBy: expect.any(Object),
            demandDate: expect.any(Object),
            status: expect.any(Object),
            validate: expect.any(Object),
            name: expect.any(Object),
          }),
        );
      });

      it('passing IDemand should create a new form with FormGroup', () => {
        const formGroup = service.createDemandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            qT: expect.any(Object),
            demandBy: expect.any(Object),
            demandDate: expect.any(Object),
            status: expect.any(Object),
            validate: expect.any(Object),
            name: expect.any(Object),
          }),
        );
      });
    });

    describe('getDemand', () => {
      it('should return NewDemand for default Demand initial value', () => {
        const formGroup = service.createDemandFormGroup(sampleWithNewData);

        const demand = service.getDemand(formGroup) as any;

        expect(demand).toMatchObject(sampleWithNewData);
      });

      it('should return NewDemand for empty Demand initial value', () => {
        const formGroup = service.createDemandFormGroup();

        const demand = service.getDemand(formGroup) as any;

        expect(demand).toMatchObject({});
      });

      it('should return IDemand', () => {
        const formGroup = service.createDemandFormGroup(sampleWithRequiredData);

        const demand = service.getDemand(formGroup) as any;

        expect(demand).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDemand should not enable id FormControl', () => {
        const formGroup = service.createDemandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDemand should disable id FormControl', () => {
        const formGroup = service.createDemandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});

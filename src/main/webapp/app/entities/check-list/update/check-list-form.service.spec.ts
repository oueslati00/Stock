import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../check-list.test-samples';

import { CheckListFormService } from './check-list-form.service';

describe('CheckList Form Service', () => {
  let service: CheckListFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckListFormService);
  });

  describe('Service methods', () => {
    describe('createCheckListFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCheckListFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            client: expect.any(Object),
            contractNumber: expect.any(Object),
            adress: expect.any(Object),
            technicienDef: expect.any(Object),
            date: expect.any(Object),
            tableDetectionTaskStatus: expect.any(Object),
            tableDetectionComment: expect.any(Object),
            diDMTaskStatus: expect.any(Object),
            diDMComment: expect.any(Object),
            detecteursPonctuelsTaskStatus: expect.any(Object),
            detecteursPonctuelsComment: expect.any(Object),
            declencheurManuelsTaskStatus: expect.any(Object),
            declencheurManuelsComment: expect.any(Object),
            tableMiseSecurityIncendieTaskStatus: expect.any(Object),
            tableMiseSecurityIncendieComment: expect.any(Object),
            alimentationSecoursTaskStatus: expect.any(Object),
            alimentationSecoursComment: expect.any(Object),
            equipementAlarmeTaskStatus: expect.any(Object),
            equipementAlarmeComment: expect.any(Object),
            dasTaskStatus: expect.any(Object),
            dasComment: expect.any(Object),
            arretTechniqueTaskStatus: expect.any(Object),
            arretTechniqueComment: expect.any(Object),
            baiePcsTaskStatus: expect.any(Object),
            baiePCScomment: expect.any(Object),
            superviseurTaskStatus: expect.any(Object),
            superviseurComment: expect.any(Object),
            validate: expect.any(Object),
            createdBy: expect.any(Object),
          }),
        );
      });

      it('passing ICheckList should create a new form with FormGroup', () => {
        const formGroup = service.createCheckListFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            client: expect.any(Object),
            contractNumber: expect.any(Object),
            adress: expect.any(Object),
            technicienDef: expect.any(Object),
            date: expect.any(Object),
            tableDetectionTaskStatus: expect.any(Object),
            tableDetectionComment: expect.any(Object),
            diDMTaskStatus: expect.any(Object),
            diDMComment: expect.any(Object),
            detecteursPonctuelsTaskStatus: expect.any(Object),
            detecteursPonctuelsComment: expect.any(Object),
            declencheurManuelsTaskStatus: expect.any(Object),
            declencheurManuelsComment: expect.any(Object),
            tableMiseSecurityIncendieTaskStatus: expect.any(Object),
            tableMiseSecurityIncendieComment: expect.any(Object),
            alimentationSecoursTaskStatus: expect.any(Object),
            alimentationSecoursComment: expect.any(Object),
            equipementAlarmeTaskStatus: expect.any(Object),
            equipementAlarmeComment: expect.any(Object),
            dasTaskStatus: expect.any(Object),
            dasComment: expect.any(Object),
            arretTechniqueTaskStatus: expect.any(Object),
            arretTechniqueComment: expect.any(Object),
            baiePcsTaskStatus: expect.any(Object),
            baiePCScomment: expect.any(Object),
            superviseurTaskStatus: expect.any(Object),
            superviseurComment: expect.any(Object),
            validate: expect.any(Object),
            createdBy: expect.any(Object),
          }),
        );
      });
    });

    describe('getCheckList', () => {
      it('should return NewCheckList for default CheckList initial value', () => {
        const formGroup = service.createCheckListFormGroup(sampleWithNewData);

        const checkList = service.getCheckList(formGroup) as any;

        expect(checkList).toMatchObject(sampleWithNewData);
      });

      it('should return NewCheckList for empty CheckList initial value', () => {
        const formGroup = service.createCheckListFormGroup();

        const checkList = service.getCheckList(formGroup) as any;

        expect(checkList).toMatchObject({});
      });

      it('should return ICheckList', () => {
        const formGroup = service.createCheckListFormGroup(sampleWithRequiredData);

        const checkList = service.getCheckList(formGroup) as any;

        expect(checkList).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICheckList should not enable id FormControl', () => {
        const formGroup = service.createCheckListFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCheckList should disable id FormControl', () => {
        const formGroup = service.createCheckListFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});

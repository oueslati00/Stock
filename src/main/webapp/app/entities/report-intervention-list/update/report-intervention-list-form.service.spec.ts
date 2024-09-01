import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../report-intervention-list.test-samples';

import { ReportInterventionListFormService } from './report-intervention-list-form.service';

describe('ReportInterventionList Form Service', () => {
  let service: ReportInterventionListFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReportInterventionListFormService);
  });

  describe('Service methods', () => {
    describe('createReportInterventionListFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createReportInterventionListFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            site: expect.any(Object),
            codeAgence: expect.any(Object),
            affaireNumber: expect.any(Object),
            contractNumber: expect.any(Object),
            installationAdress: expect.any(Object),
            interlocuteurIntervation: expect.any(Object),
            tel: expect.any(Object),
            installationSousContract: expect.any(Object),
            installationSousGarantie: expect.any(Object),
            adressFacturation: expect.any(Object),
            interlocuteurFacturation: expect.any(Object),
            conditionDePayementCheque: expect.any(Object),
            conditionPayementAutre: expect.any(Object),
            conditionPayementComment: expect.any(Object),
            miseEnServiceDefinitvie: expect.any(Object),
            miseEnServicePartielle: expect.any(Object),
            maintenancePreventive: expect.any(Object),
            maintenanceCorrective: expect.any(Object),
            bT: expect.any(Object),
            anomalieSignalee: expect.any(Object),
            interventionDate: expect.any(Object),
            interventionStartDate: expect.any(Object),
            remiseServiceDate: expect.any(Object),
            endDate: expect.any(Object),
            natureIntervention: expect.any(Object),
            causeExterieurInstallation: expect.any(Object),
            installationFonctionnelleApresInervention: expect.any(Object),
            autreInterventionsAPrevoir: expect.any(Object),
            clientApreciation: expect.any(Object),
            respectDelais: expect.any(Object),
            qualiteIntervention: expect.any(Object),
            qualiteDevoirConseil: expect.any(Object),
            prestationsAchevees: expect.any(Object),
            devisComplentaire: expect.any(Object),
            technicienName: expect.any(Object),
            codeTechnicien: expect.any(Object),
            validationClientName: expect.any(Object),
            validationNameFunction: expect.any(Object),
            validationClientDate: expect.any(Object),
            bonPourCommand: expect.any(Object),
            createdBy: expect.any(Object),
            validation: expect.any(Object),
          }),
        );
      });

      it('passing IReportInterventionList should create a new form with FormGroup', () => {
        const formGroup = service.createReportInterventionListFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            site: expect.any(Object),
            codeAgence: expect.any(Object),
            affaireNumber: expect.any(Object),
            contractNumber: expect.any(Object),
            installationAdress: expect.any(Object),
            interlocuteurIntervation: expect.any(Object),
            tel: expect.any(Object),
            installationSousContract: expect.any(Object),
            installationSousGarantie: expect.any(Object),
            adressFacturation: expect.any(Object),
            interlocuteurFacturation: expect.any(Object),
            conditionDePayementCheque: expect.any(Object),
            conditionPayementAutre: expect.any(Object),
            conditionPayementComment: expect.any(Object),
            miseEnServiceDefinitvie: expect.any(Object),
            miseEnServicePartielle: expect.any(Object),
            maintenancePreventive: expect.any(Object),
            maintenanceCorrective: expect.any(Object),
            bT: expect.any(Object),
            anomalieSignalee: expect.any(Object),
            interventionDate: expect.any(Object),
            interventionStartDate: expect.any(Object),
            remiseServiceDate: expect.any(Object),
            endDate: expect.any(Object),
            natureIntervention: expect.any(Object),
            causeExterieurInstallation: expect.any(Object),
            installationFonctionnelleApresInervention: expect.any(Object),
            autreInterventionsAPrevoir: expect.any(Object),
            clientApreciation: expect.any(Object),
            respectDelais: expect.any(Object),
            qualiteIntervention: expect.any(Object),
            qualiteDevoirConseil: expect.any(Object),
            prestationsAchevees: expect.any(Object),
            devisComplentaire: expect.any(Object),
            technicienName: expect.any(Object),
            codeTechnicien: expect.any(Object),
            validationClientName: expect.any(Object),
            validationNameFunction: expect.any(Object),
            validationClientDate: expect.any(Object),
            bonPourCommand: expect.any(Object),
            createdBy: expect.any(Object),
            validation: expect.any(Object),
          }),
        );
      });
    });

    describe('getReportInterventionList', () => {
      it('should return NewReportInterventionList for default ReportInterventionList initial value', () => {
        const formGroup = service.createReportInterventionListFormGroup(sampleWithNewData);

        const reportInterventionList = service.getReportInterventionList(formGroup) as any;

        expect(reportInterventionList).toMatchObject(sampleWithNewData);
      });

      it('should return NewReportInterventionList for empty ReportInterventionList initial value', () => {
        const formGroup = service.createReportInterventionListFormGroup();

        const reportInterventionList = service.getReportInterventionList(formGroup) as any;

        expect(reportInterventionList).toMatchObject({});
      });

      it('should return IReportInterventionList', () => {
        const formGroup = service.createReportInterventionListFormGroup(sampleWithRequiredData);

        const reportInterventionList = service.getReportInterventionList(formGroup) as any;

        expect(reportInterventionList).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IReportInterventionList should not enable id FormControl', () => {
        const formGroup = service.createReportInterventionListFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewReportInterventionList should disable id FormControl', () => {
        const formGroup = service.createReportInterventionListFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});

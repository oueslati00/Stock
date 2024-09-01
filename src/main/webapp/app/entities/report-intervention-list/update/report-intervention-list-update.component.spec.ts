import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { ReportInterventionListService } from '../service/report-intervention-list.service';
import { IReportInterventionList } from '../report-intervention-list.model';
import { ReportInterventionListFormService } from './report-intervention-list-form.service';

import { ReportInterventionListUpdateComponent } from './report-intervention-list-update.component';

describe('ReportInterventionList Management Update Component', () => {
  let comp: ReportInterventionListUpdateComponent;
  let fixture: ComponentFixture<ReportInterventionListUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let reportInterventionListFormService: ReportInterventionListFormService;
  let reportInterventionListService: ReportInterventionListService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ReportInterventionListUpdateComponent],
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
      .overrideTemplate(ReportInterventionListUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ReportInterventionListUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    reportInterventionListFormService = TestBed.inject(ReportInterventionListFormService);
    reportInterventionListService = TestBed.inject(ReportInterventionListService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const reportInterventionList: IReportInterventionList = { id: 456 };

      activatedRoute.data = of({ reportInterventionList });
      comp.ngOnInit();

      expect(comp.reportInterventionList).toEqual(reportInterventionList);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportInterventionList>>();
      const reportInterventionList = { id: 123 };
      jest.spyOn(reportInterventionListFormService, 'getReportInterventionList').mockReturnValue(reportInterventionList);
      jest.spyOn(reportInterventionListService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportInterventionList });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportInterventionList }));
      saveSubject.complete();

      // THEN
      expect(reportInterventionListFormService.getReportInterventionList).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(reportInterventionListService.update).toHaveBeenCalledWith(expect.objectContaining(reportInterventionList));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportInterventionList>>();
      const reportInterventionList = { id: 123 };
      jest.spyOn(reportInterventionListFormService, 'getReportInterventionList').mockReturnValue({ id: null });
      jest.spyOn(reportInterventionListService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportInterventionList: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: reportInterventionList }));
      saveSubject.complete();

      // THEN
      expect(reportInterventionListFormService.getReportInterventionList).toHaveBeenCalled();
      expect(reportInterventionListService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReportInterventionList>>();
      const reportInterventionList = { id: 123 };
      jest.spyOn(reportInterventionListService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ reportInterventionList });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(reportInterventionListService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});

import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IReportInterventionList } from '../report-intervention-list.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../report-intervention-list.test-samples';

import { ReportInterventionListService, RestReportInterventionList } from './report-intervention-list.service';

const requireRestSample: RestReportInterventionList = {
  ...sampleWithRequiredData,
  interventionDate: sampleWithRequiredData.interventionDate?.toJSON(),
  interventionStartDate: sampleWithRequiredData.interventionStartDate?.toJSON(),
  remiseServiceDate: sampleWithRequiredData.remiseServiceDate?.toJSON(),
  endDate: sampleWithRequiredData.endDate?.toJSON(),
  validationClientDate: sampleWithRequiredData.validationClientDate?.format(DATE_FORMAT),
};

describe('ReportInterventionList Service', () => {
  let service: ReportInterventionListService;
  let httpMock: HttpTestingController;
  let expectedResult: IReportInterventionList | IReportInterventionList[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(ReportInterventionListService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a ReportInterventionList', () => {
      const reportInterventionList = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(reportInterventionList).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ReportInterventionList', () => {
      const reportInterventionList = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(reportInterventionList).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ReportInterventionList', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ReportInterventionList', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ReportInterventionList', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a ReportInterventionList', () => {
      const queryObject: any = {
        page: 0,
        size: 20,
        query: '',
        sort: [],
      };
      service.search(queryObject).subscribe(() => expectedResult);

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(null, { status: 500, statusText: 'Internal Server Error' });
      expect(expectedResult).toBe(null);
    });

    describe('addReportInterventionListToCollectionIfMissing', () => {
      it('should add a ReportInterventionList to an empty array', () => {
        const reportInterventionList: IReportInterventionList = sampleWithRequiredData;
        expectedResult = service.addReportInterventionListToCollectionIfMissing([], reportInterventionList);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportInterventionList);
      });

      it('should not add a ReportInterventionList to an array that contains it', () => {
        const reportInterventionList: IReportInterventionList = sampleWithRequiredData;
        const reportInterventionListCollection: IReportInterventionList[] = [
          {
            ...reportInterventionList,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addReportInterventionListToCollectionIfMissing(reportInterventionListCollection, reportInterventionList);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ReportInterventionList to an array that doesn't contain it", () => {
        const reportInterventionList: IReportInterventionList = sampleWithRequiredData;
        const reportInterventionListCollection: IReportInterventionList[] = [sampleWithPartialData];
        expectedResult = service.addReportInterventionListToCollectionIfMissing(reportInterventionListCollection, reportInterventionList);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportInterventionList);
      });

      it('should add only unique ReportInterventionList to an array', () => {
        const reportInterventionListArray: IReportInterventionList[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const reportInterventionListCollection: IReportInterventionList[] = [sampleWithRequiredData];
        expectedResult = service.addReportInterventionListToCollectionIfMissing(
          reportInterventionListCollection,
          ...reportInterventionListArray,
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const reportInterventionList: IReportInterventionList = sampleWithRequiredData;
        const reportInterventionList2: IReportInterventionList = sampleWithPartialData;
        expectedResult = service.addReportInterventionListToCollectionIfMissing([], reportInterventionList, reportInterventionList2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(reportInterventionList);
        expect(expectedResult).toContain(reportInterventionList2);
      });

      it('should accept null and undefined values', () => {
        const reportInterventionList: IReportInterventionList = sampleWithRequiredData;
        expectedResult = service.addReportInterventionListToCollectionIfMissing([], null, reportInterventionList, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(reportInterventionList);
      });

      it('should return initial array if no ReportInterventionList is added', () => {
        const reportInterventionListCollection: IReportInterventionList[] = [sampleWithRequiredData];
        expectedResult = service.addReportInterventionListToCollectionIfMissing(reportInterventionListCollection, undefined, null);
        expect(expectedResult).toEqual(reportInterventionListCollection);
      });
    });

    describe('compareReportInterventionList', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareReportInterventionList(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareReportInterventionList(entity1, entity2);
        const compareResult2 = service.compareReportInterventionList(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareReportInterventionList(entity1, entity2);
        const compareResult2 = service.compareReportInterventionList(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareReportInterventionList(entity1, entity2);
        const compareResult2 = service.compareReportInterventionList(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});

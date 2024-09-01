import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IDemand } from '../demand.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../demand.test-samples';

import { DemandService, RestDemand } from './demand.service';

const requireRestSample: RestDemand = {
  ...sampleWithRequiredData,
  demandDate: sampleWithRequiredData.demandDate?.toJSON(),
};

describe('Demand Service', () => {
  let service: DemandService;
  let httpMock: HttpTestingController;
  let expectedResult: IDemand | IDemand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(DemandService);
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

    it('should create a Demand', () => {
      const demand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(demand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Demand', () => {
      const demand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(demand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Demand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Demand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Demand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    it('should handle exceptions for searching a Demand', () => {
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

    describe('addDemandToCollectionIfMissing', () => {
      it('should add a Demand to an empty array', () => {
        const demand: IDemand = sampleWithRequiredData;
        expectedResult = service.addDemandToCollectionIfMissing([], demand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(demand);
      });

      it('should not add a Demand to an array that contains it', () => {
        const demand: IDemand = sampleWithRequiredData;
        const demandCollection: IDemand[] = [
          {
            ...demand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDemandToCollectionIfMissing(demandCollection, demand);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Demand to an array that doesn't contain it", () => {
        const demand: IDemand = sampleWithRequiredData;
        const demandCollection: IDemand[] = [sampleWithPartialData];
        expectedResult = service.addDemandToCollectionIfMissing(demandCollection, demand);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(demand);
      });

      it('should add only unique Demand to an array', () => {
        const demandArray: IDemand[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const demandCollection: IDemand[] = [sampleWithRequiredData];
        expectedResult = service.addDemandToCollectionIfMissing(demandCollection, ...demandArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const demand: IDemand = sampleWithRequiredData;
        const demand2: IDemand = sampleWithPartialData;
        expectedResult = service.addDemandToCollectionIfMissing([], demand, demand2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(demand);
        expect(expectedResult).toContain(demand2);
      });

      it('should accept null and undefined values', () => {
        const demand: IDemand = sampleWithRequiredData;
        expectedResult = service.addDemandToCollectionIfMissing([], null, demand, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(demand);
      });

      it('should return initial array if no Demand is added', () => {
        const demandCollection: IDemand[] = [sampleWithRequiredData];
        expectedResult = service.addDemandToCollectionIfMissing(demandCollection, undefined, null);
        expect(expectedResult).toEqual(demandCollection);
      });
    });

    describe('compareDemand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDemand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareDemand(entity1, entity2);
        const compareResult2 = service.compareDemand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareDemand(entity1, entity2);
        const compareResult2 = service.compareDemand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareDemand(entity1, entity2);
        const compareResult2 = service.compareDemand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});

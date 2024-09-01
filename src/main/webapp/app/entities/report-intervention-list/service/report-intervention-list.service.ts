import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { SearchWithPagination } from 'app/core/request/request.model';
import { IReportInterventionList, NewReportInterventionList } from '../report-intervention-list.model';

export type PartialUpdateReportInterventionList = Partial<IReportInterventionList> & Pick<IReportInterventionList, 'id'>;

type RestOf<T extends IReportInterventionList | NewReportInterventionList> = Omit<
  T,
  'interventionDate' | 'interventionStartDate' | 'remiseServiceDate' | 'endDate' | 'validationClientDate'
> & {
  interventionDate?: string | null;
  interventionStartDate?: string | null;
  remiseServiceDate?: string | null;
  endDate?: string | null;
  validationClientDate?: string | null;
};

export type RestReportInterventionList = RestOf<IReportInterventionList>;

export type NewRestReportInterventionList = RestOf<NewReportInterventionList>;

export type PartialUpdateRestReportInterventionList = RestOf<PartialUpdateReportInterventionList>;

export type EntityResponseType = HttpResponse<IReportInterventionList>;
export type EntityArrayResponseType = HttpResponse<IReportInterventionList[]>;

@Injectable({ providedIn: 'root' })
export class ReportInterventionListService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/report-intervention-lists');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/report-intervention-lists/_search');

  create(reportInterventionList: NewReportInterventionList): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reportInterventionList);
    return this.http
      .post<RestReportInterventionList>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(reportInterventionList: IReportInterventionList): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reportInterventionList);
    return this.http
      .put<RestReportInterventionList>(`${this.resourceUrl}/${this.getReportInterventionListIdentifier(reportInterventionList)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(reportInterventionList: PartialUpdateReportInterventionList): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reportInterventionList);
    return this.http
      .patch<RestReportInterventionList>(`${this.resourceUrl}/${this.getReportInterventionListIdentifier(reportInterventionList)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestReportInterventionList>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestReportInterventionList[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestReportInterventionList[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IReportInterventionList[]>()], asapScheduler)),
    );
  }

  getReportInterventionListIdentifier(reportInterventionList: Pick<IReportInterventionList, 'id'>): number {
    return reportInterventionList.id;
  }

  compareReportInterventionList(o1: Pick<IReportInterventionList, 'id'> | null, o2: Pick<IReportInterventionList, 'id'> | null): boolean {
    return o1 && o2 ? this.getReportInterventionListIdentifier(o1) === this.getReportInterventionListIdentifier(o2) : o1 === o2;
  }

  addReportInterventionListToCollectionIfMissing<Type extends Pick<IReportInterventionList, 'id'>>(
    reportInterventionListCollection: Type[],
    ...reportInterventionListsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const reportInterventionLists: Type[] = reportInterventionListsToCheck.filter(isPresent);
    if (reportInterventionLists.length > 0) {
      const reportInterventionListCollectionIdentifiers = reportInterventionListCollection.map(reportInterventionListItem =>
        this.getReportInterventionListIdentifier(reportInterventionListItem),
      );
      const reportInterventionListsToAdd = reportInterventionLists.filter(reportInterventionListItem => {
        const reportInterventionListIdentifier = this.getReportInterventionListIdentifier(reportInterventionListItem);
        if (reportInterventionListCollectionIdentifiers.includes(reportInterventionListIdentifier)) {
          return false;
        }
        reportInterventionListCollectionIdentifiers.push(reportInterventionListIdentifier);
        return true;
      });
      return [...reportInterventionListsToAdd, ...reportInterventionListCollection];
    }
    return reportInterventionListCollection;
  }

  protected convertDateFromClient<T extends IReportInterventionList | NewReportInterventionList | PartialUpdateReportInterventionList>(
    reportInterventionList: T,
  ): RestOf<T> {
    return {
      ...reportInterventionList,
      interventionDate: reportInterventionList.interventionDate?.toJSON() ?? null,
      interventionStartDate: reportInterventionList.interventionStartDate?.toJSON() ?? null,
      remiseServiceDate: reportInterventionList.remiseServiceDate?.toJSON() ?? null,
      endDate: reportInterventionList.endDate?.toJSON() ?? null,
      validationClientDate: reportInterventionList.validationClientDate?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restReportInterventionList: RestReportInterventionList): IReportInterventionList {
    return {
      ...restReportInterventionList,
      interventionDate: restReportInterventionList.interventionDate ? dayjs(restReportInterventionList.interventionDate) : undefined,
      interventionStartDate: restReportInterventionList.interventionStartDate
        ? dayjs(restReportInterventionList.interventionStartDate)
        : undefined,
      remiseServiceDate: restReportInterventionList.remiseServiceDate ? dayjs(restReportInterventionList.remiseServiceDate) : undefined,
      endDate: restReportInterventionList.endDate ? dayjs(restReportInterventionList.endDate) : undefined,
      validationClientDate: restReportInterventionList.validationClientDate
        ? dayjs(restReportInterventionList.validationClientDate)
        : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestReportInterventionList>): HttpResponse<IReportInterventionList> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestReportInterventionList[]>): HttpResponse<IReportInterventionList[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}

import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { SearchWithPagination } from 'app/core/request/request.model';
import { IDemand, NewDemand } from '../demand.model';

export type PartialUpdateDemand = Partial<IDemand> & Pick<IDemand, 'id'>;

type RestOf<T extends IDemand | NewDemand> = Omit<T, 'demandDate'> & {
  demandDate?: string | null;
};

export type RestDemand = RestOf<IDemand>;

export type NewRestDemand = RestOf<NewDemand>;

export type PartialUpdateRestDemand = RestOf<PartialUpdateDemand>;

export type EntityResponseType = HttpResponse<IDemand>;
export type EntityArrayResponseType = HttpResponse<IDemand[]>;

@Injectable({ providedIn: 'root' })
export class DemandService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/demands');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/demands/_search');

  create(demand: NewDemand): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(demand);
    return this.http
      .post<RestDemand>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(demand: IDemand): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(demand);
    return this.http
      .put<RestDemand>(`${this.resourceUrl}/${this.getDemandIdentifier(demand)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(demand: PartialUpdateDemand): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(demand);
    return this.http
      .patch<RestDemand>(`${this.resourceUrl}/${this.getDemandIdentifier(demand)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestDemand>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestDemand[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestDemand[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<IDemand[]>()], asapScheduler)),
    );
  }

  getDemandIdentifier(demand: Pick<IDemand, 'id'>): number {
    return demand.id;
  }

  compareDemand(o1: Pick<IDemand, 'id'> | null, o2: Pick<IDemand, 'id'> | null): boolean {
    return o1 && o2 ? this.getDemandIdentifier(o1) === this.getDemandIdentifier(o2) : o1 === o2;
  }

  addDemandToCollectionIfMissing<Type extends Pick<IDemand, 'id'>>(
    demandCollection: Type[],
    ...demandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const demands: Type[] = demandsToCheck.filter(isPresent);
    if (demands.length > 0) {
      const demandCollectionIdentifiers = demandCollection.map(demandItem => this.getDemandIdentifier(demandItem));
      const demandsToAdd = demands.filter(demandItem => {
        const demandIdentifier = this.getDemandIdentifier(demandItem);
        if (demandCollectionIdentifiers.includes(demandIdentifier)) {
          return false;
        }
        demandCollectionIdentifiers.push(demandIdentifier);
        return true;
      });
      return [...demandsToAdd, ...demandCollection];
    }
    return demandCollection;
  }

  protected convertDateFromClient<T extends IDemand | NewDemand | PartialUpdateDemand>(demand: T): RestOf<T> {
    return {
      ...demand,
      demandDate: demand.demandDate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restDemand: RestDemand): IDemand {
    return {
      ...restDemand,
      demandDate: restDemand.demandDate ? dayjs(restDemand.demandDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestDemand>): HttpResponse<IDemand> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestDemand[]>): HttpResponse<IDemand[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}

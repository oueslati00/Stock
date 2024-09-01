import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable, asapScheduler, scheduled } from 'rxjs';

import { catchError } from 'rxjs/operators';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { SearchWithPagination } from 'app/core/request/request.model';
import { ICheckList, NewCheckList } from '../check-list.model';

export type PartialUpdateCheckList = Partial<ICheckList> & Pick<ICheckList, 'id'>;

type RestOf<T extends ICheckList | NewCheckList> = Omit<T, 'date'> & {
  date?: string | null;
};

export type RestCheckList = RestOf<ICheckList>;

export type NewRestCheckList = RestOf<NewCheckList>;

export type PartialUpdateRestCheckList = RestOf<PartialUpdateCheckList>;

export type EntityResponseType = HttpResponse<ICheckList>;
export type EntityArrayResponseType = HttpResponse<ICheckList[]>;

@Injectable({ providedIn: 'root' })
export class CheckListService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/check-lists');
  protected resourceSearchUrl = this.applicationConfigService.getEndpointFor('api/check-lists/_search');

  create(checkList: NewCheckList): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(checkList);
    return this.http
      .post<RestCheckList>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(checkList: ICheckList): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(checkList);
    return this.http
      .put<RestCheckList>(`${this.resourceUrl}/${this.getCheckListIdentifier(checkList)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(checkList: PartialUpdateCheckList): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(checkList);
    return this.http
      .patch<RestCheckList>(`${this.resourceUrl}/${this.getCheckListIdentifier(checkList)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestCheckList>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCheckList[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<RestCheckList[]>(this.resourceSearchUrl, { params: options, observe: 'response' }).pipe(
      map(res => this.convertResponseArrayFromServer(res)),

      catchError(() => scheduled([new HttpResponse<ICheckList[]>()], asapScheduler)),
    );
  }

  getCheckListIdentifier(checkList: Pick<ICheckList, 'id'>): number {
    return checkList.id;
  }

  compareCheckList(o1: Pick<ICheckList, 'id'> | null, o2: Pick<ICheckList, 'id'> | null): boolean {
    return o1 && o2 ? this.getCheckListIdentifier(o1) === this.getCheckListIdentifier(o2) : o1 === o2;
  }

  addCheckListToCollectionIfMissing<Type extends Pick<ICheckList, 'id'>>(
    checkListCollection: Type[],
    ...checkListsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const checkLists: Type[] = checkListsToCheck.filter(isPresent);
    if (checkLists.length > 0) {
      const checkListCollectionIdentifiers = checkListCollection.map(checkListItem => this.getCheckListIdentifier(checkListItem));
      const checkListsToAdd = checkLists.filter(checkListItem => {
        const checkListIdentifier = this.getCheckListIdentifier(checkListItem);
        if (checkListCollectionIdentifiers.includes(checkListIdentifier)) {
          return false;
        }
        checkListCollectionIdentifiers.push(checkListIdentifier);
        return true;
      });
      return [...checkListsToAdd, ...checkListCollection];
    }
    return checkListCollection;
  }

  protected convertDateFromClient<T extends ICheckList | NewCheckList | PartialUpdateCheckList>(checkList: T): RestOf<T> {
    return {
      ...checkList,
      date: checkList.date?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restCheckList: RestCheckList): ICheckList {
    return {
      ...restCheckList,
      date: restCheckList.date ? dayjs(restCheckList.date) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCheckList>): HttpResponse<ICheckList> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCheckList[]>): HttpResponse<ICheckList[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}

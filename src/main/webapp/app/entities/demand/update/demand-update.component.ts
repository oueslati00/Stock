import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IProduct } from 'app/entities/product/product.model';
import { ProductService } from 'app/entities/product/service/product.service';
import { Status } from 'app/entities/enumerations/status.model';
import { DemandService } from '../service/demand.service';
import { IDemand } from '../demand.model';
import { DemandFormService, DemandFormGroup } from './demand-form.service';

@Component({
  standalone: true,
  selector: 'jhi-demand-update',
  templateUrl: './demand-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DemandUpdateComponent implements OnInit {
  isSaving = false;
  demand: IDemand | null = null;
  statusValues = Object.keys(Status);

  productsSharedCollection: IProduct[] = [];

  protected demandService = inject(DemandService);
  protected demandFormService = inject(DemandFormService);
  protected productService = inject(ProductService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: DemandFormGroup = this.demandFormService.createDemandFormGroup();

  compareProduct = (o1: IProduct | null, o2: IProduct | null): boolean => this.productService.compareProduct(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ demand }) => {
      this.demand = demand;
      if (demand) {
        this.updateForm(demand);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const demand = this.demandFormService.getDemand(this.editForm);
    if (demand.id !== null) {
      this.subscribeToSaveResponse(this.demandService.update(demand));
    } else {
      this.subscribeToSaveResponse(this.demandService.create(demand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDemand>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(demand: IDemand): void {
    this.demand = demand;
    this.demandFormService.resetForm(this.editForm, demand);

    this.productsSharedCollection = this.productService.addProductToCollectionIfMissing<IProduct>(
      this.productsSharedCollection,
      demand.name,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.productService
      .query()
      .pipe(map((res: HttpResponse<IProduct[]>) => res.body ?? []))
      .pipe(map((products: IProduct[]) => this.productService.addProductToCollectionIfMissing<IProduct>(products, this.demand?.name)))
      .subscribe((products: IProduct[]) => (this.productsSharedCollection = products));
  }
}

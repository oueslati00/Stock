import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IProduct, NewProduct } from '../product.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IProduct for edit and NewProductFormGroupInput for create.
 */
type ProductFormGroupInput = IProduct | PartialWithRequiredKeyOf<NewProduct>;

type ProductFormDefaults = Pick<NewProduct, 'id' | 'shouldBeNotification' | 'notificationDeleted'>;

type ProductFormGroupContent = {
  id: FormControl<IProduct['id'] | NewProduct['id']>;
  qrCode: FormControl<IProduct['qrCode']>;
  name: FormControl<IProduct['name']>;
  imageData: FormControl<IProduct['imageData']>;
  imageDataContentType: FormControl<IProduct['imageDataContentType']>;
  imageUrl: FormControl<IProduct['imageUrl']>;
  qT: FormControl<IProduct['qT']>;
  shouldBeNotification: FormControl<IProduct['shouldBeNotification']>;
  notificationDeleted: FormControl<IProduct['notificationDeleted']>;
  minQT: FormControl<IProduct['minQT']>;
};

export type ProductFormGroup = FormGroup<ProductFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ProductFormService {
  createProductFormGroup(product: ProductFormGroupInput = { id: null }): ProductFormGroup {
    const productRawValue = {
      ...this.getFormDefaults(),
      ...product,
    };
    return new FormGroup<ProductFormGroupContent>({
      id: new FormControl(
        { value: productRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      qrCode: new FormControl(productRawValue.qrCode, {
        validators: [Validators.required],
      }),
      name: new FormControl(productRawValue.name),
      imageData: new FormControl(productRawValue.imageData),
      imageDataContentType: new FormControl(productRawValue.imageDataContentType),
      imageUrl: new FormControl(productRawValue.imageUrl),
      qT: new FormControl(productRawValue.qT),
      shouldBeNotification: new FormControl(productRawValue.shouldBeNotification),
      notificationDeleted: new FormControl(productRawValue.notificationDeleted),
      minQT: new FormControl(productRawValue.minQT),
    });
  }

  getProduct(form: ProductFormGroup): IProduct | NewProduct {
    return form.getRawValue() as IProduct | NewProduct;
  }

  resetForm(form: ProductFormGroup, product: ProductFormGroupInput): void {
    const productRawValue = { ...this.getFormDefaults(), ...product };
    form.reset(
      {
        ...productRawValue,
        id: { value: productRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ProductFormDefaults {
    return {
      id: null,
      shouldBeNotification: false,
      notificationDeleted: false,
    };
  }
}

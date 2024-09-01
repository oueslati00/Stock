import { Component, inject } from '@angular/core';
import SharedModule from '../../../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProductUpdateComponent } from '../../../entities/product/update/product-update.component';
import { ImageScanActivityModel } from '../image-scan-activity.model';
import { Observer, Subscription } from 'rxjs';
import { RxStomp } from '@stomp/rx-stomp';
import { AccountService } from '../../../core/auth/account.service';
import { Account } from '../../../core/auth/account.model';
import { filter, map } from 'rxjs/operators';
import SockJS from 'sockjs-client';
import { Location } from '@angular/common';
import { TrackerActivity } from '../../../core/tracker/tracker-activity.model';
const IMAGE_READER = '/topic/image';
@Component({
  standalone: true,
  selector: 'product-update',
  templateUrl: './product-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ProductUpdateComponentExtend extends ProductUpdateComponent {
  image: ImageScanActivityModel[] = [];
  private subscription: Subscription | null = null;
  private rxStomp?: RxStomp;
  private accountService = inject(AccountService);
  private location = inject(Location);
  protected currentUserService = inject(AccountService);

  ngOnInit() {
    super.ngOnInit();
    this.setup();
    this.start();
  }

  save() {
    this.isSaving = true;
    const product = this.productFormService.getProduct(this.editForm);
    const minQt = this.editForm.getRawValue().minQT as number;
    const QT = this.editForm.getRawValue().qT as number;
    product.notificationDeleted = false;
    if (minQt > QT) {
      product.shouldBeNotification = true;
    } else {
      product.shouldBeNotification = false;
    }
    if (product.id !== null) {
      this.subscribeToSaveResponse(this.productService.update(product));
    } else {
      // in case of
      this.subscribeToSaveResponse(this.productService.create(product));
    }
  }

  currentUserCanAddUpdateDeleteProduct(): boolean {
    const roles = ['ROLE_RES_MAINTENANCE', 'ROLE_ADMIN'];
    return this.currentUserService.hasAnyAuthority(roles);
  }

  setup(): void {
    this.rxStomp = new RxStomp();
    this.rxStomp.configure({
      // eslint-disable-next-line no-console
      debug: (msg: string): void => console.log(new Date(), msg),
    });
    // can add condition here for url , load data only if the url is the same as this current !!
    this.accountService.getAuthenticationState().subscribe({
      next: (account: Account | null) => {
        if (account) {
          this.stomp.configure({
            webSocketFactory: () => SockJS(this.buildUrl()),
          });
          this.stomp.activate();
        } else {
          this.stomp.deactivate();
        }
      },
    });
  }
  start(): void {
    this.subscription = this.subscribe({
      next: (activity: ImageScanActivityModel) => {
        this.showActivity(activity);
      },
    });
  }

  showActivity(activity: ImageScanActivityModel): void {
    let existingActivity = false;

    for (let index = 0; index < this.image.length; index++) {
      if (this.image[index].codeQR === activity.codeQR) {
        existingActivity = true;
        this.image[index].image = activity.image;
      }
    }

    if (!existingActivity) {
      this.image.push(activity);
    }
  }

  public subscribe(observer: Partial<Observer<ImageScanActivityModel>>): Subscription {
    return (
      this.stomp
        .watch(IMAGE_READER)
        // eslint-disable-next-line @typescript-eslint/no-unsafe-return
        .pipe(map(imessage => JSON.parse(imessage.body)))
        .subscribe(observer)
    );
  }

  private buildUrl(): string {
    // building absolute path so that websocket doesn't fail when deploying with a context path
    let url = '/websocket/tracker';
    url = this.location.prepareExternalUrl(url);
    // this line used for security i think , we can disable it for the moment !!
    // const authToken = this.authServerProvider.getToken();
    // if (authToken) {
    // return `${url}?access_token=${authToken}`;
    // }
    return url;
  }

  get stomp(): RxStomp {
    if (!this.rxStomp) {
      throw new Error('Stomp connection not initialized');
    }
    return this.rxStomp;
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}

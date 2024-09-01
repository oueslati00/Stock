import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { DemandDetailComponent } from './demand-detail.component';

describe('Demand Management Detail Component', () => {
  let comp: DemandDetailComponent;
  let fixture: ComponentFixture<DemandDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DemandDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: DemandDetailComponent,
              resolve: { demand: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(DemandDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load demand on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', DemandDetailComponent);

      // THEN
      expect(instance.demand()).toEqual(expect.objectContaining({ id: 123 }));
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});

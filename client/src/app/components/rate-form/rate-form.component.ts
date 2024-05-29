import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { GetRateRequest } from 'src/app/model/get-rate-request.model';
import { CurrencyService } from 'src/app/service/currency.service';

@Component({
  selector: 'app-rate-form',
  templateUrl: './rate-form.component.html',
  styleUrls: ['./rate-form.component.scss'],
})
export class RateFormComponent {
  currencyService = inject(CurrencyService);
  appService = inject(AppService);

  form: GetRateRequest = {
    currency: '',
    name: '',
  };

  errorMessages: string[] = [];

  value: number | null = null;

  busy = {
    load: false,
  };

  submit() {
    this.errorMessages = [];
    this.value = null;
    this.busy.load = true;

    this.currencyService
      .getRate(this.form)
      .subscribe({
        next: (res) => {
          this.value = res.value;
          this.appService.onSubmitFrom();
        },
        error: (err: HttpErrorResponse) => {
          if (err.status === 400) {
            if (Array.isArray(err?.error?.errors)) {
              this.errorMessages = err?.error?.errors;
            }
          } else if (err.status === 404) {
            this.errorMessages = ['Currency not found.'];
          } else {
            this.errorMessages = ['Service is unavailable.'];
          }
        },
      })
      .add(() => (this.busy.load = false));
  }
}

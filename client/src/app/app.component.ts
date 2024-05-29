import { Component, OnInit, inject } from '@angular/core';
import { CurrencyService } from './service/currency.service';
import { GetRateRequest } from './model/get-rate-request.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  currencyService = inject(CurrencyService);

  ngOnInit(): void {
    this.currencyService.getRequestLogs().subscribe({
      next: (res) => {
        console.log({ res });
      },
    });
  }
}

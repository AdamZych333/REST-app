import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { Subject, startWith, takeUntil } from 'rxjs';
import { AppService } from 'src/app/app.service';
import { RequestLog } from 'src/app/model/request-log.model';
import { CurrencyService } from 'src/app/service/currency.service';

@Component({
  selector: 'app-request-logs',
  templateUrl: './request-logs.component.html',
  styleUrls: ['./request-logs.component.scss'],
})
export class RequestLogsComponent implements OnInit, OnDestroy {
  currencyService = inject(CurrencyService);
  appService = inject(AppService);

  busy = {
    load: false,
  };

  onDestroy$ = new Subject<void>();

  data: RequestLog[] = [];

  ngOnInit(): void {
    this.appService.submitForm$
      .pipe(startWith(''), takeUntil(this.onDestroy$))
      .subscribe({
        next: (res) => {
          this.loadRequests();
        },
      });
  }

  ngOnDestroy(): void {
    this.onDestroy$.next();
    this.onDestroy$.complete();
  }

  loadRequests() {
    this.busy.load = true;

    this.currencyService
      .getRequestLogs()
      .subscribe({
        next: (res) => {
          this.data = res.requests;
        },
      })
      .add(() => (this.busy.load = false));
  }
}

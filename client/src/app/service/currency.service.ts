import { Injectable, inject } from '@angular/core';
import { GetRateRequest } from '../model/get-rate-request.model';
import { Api } from '../core/providers/api';
import { Observable, map } from 'rxjs';
import { GetRateResponse } from '../model/get-rate-response.model';
import { GetRequestLogsResponse } from '../model/get-request-logs-response.model';

@Injectable({
  providedIn: 'root',
})
export class CurrencyService {
  private api = inject(Api);

  getRate(data: GetRateRequest) {
    return this.api
      .post('currencies/get-current-currency-value-command', data)
      .pipe(map((res: any) => res as GetRateResponse));
  }

  getRequestLogs() {
    return this.api.get(
      'currencies/requests'
    ) as Observable<GetRequestLogsResponse>;
  }
}

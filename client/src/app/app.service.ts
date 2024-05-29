import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  private submitForm = new Subject<void>();
  submitForm$ = this.submitForm.asObservable();

  onSubmitFrom() {
    this.submitForm.next();
  }
}

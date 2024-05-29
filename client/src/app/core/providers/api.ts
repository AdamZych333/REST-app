import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class Api {
  constructor(public http: HttpClient) {}

  get(endpoint: string, options?: { params?: {}; headers?: {} }) {
    return this.http.get(`${environment.apiEndpoint}/${endpoint}`, options);
  }

  post(endpoint: string, data: any, options?: any) {
    return this.http.post(`${environment.apiEndpoint}/${endpoint}`, data, {
      ...options,
    });
  }

  put(endpoint: string, data: any, options?: any) {
    return this.http.put(`${environment.apiEndpoint}/${endpoint}`, data, {
      ...options,
    });
  }

  delete(endpoint: string, options?: any) {
    return this.http.delete(`${environment.apiEndpoint}/${endpoint}`, {
      ...options,
    });
  }
}

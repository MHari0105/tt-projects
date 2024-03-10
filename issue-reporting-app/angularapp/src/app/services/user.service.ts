import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = '';
  private authUrl: string = '';
  private transactionUrl: string = ''

  constructor(private httpClient: HttpClient) {4
    this.baseUrl = `${environment.userUrl}`
    this.authUrl = `${environment.authUrl}`;
    this.transactionUrl = `${environment.paymentUrl}`
  }

  public createTransaction(amount: number) {
    return this.httpClient.get(this.transactionUrl + amount);
  }

  signUpUser(signupForm: FormGroup): Observable<any> {
    const url = `${this.authUrl}/register`;
    return this.httpClient.post(url, signupForm.value);
  }

  loginUser(loginForm: FormGroup): Observable<any> {
    const url = `${this.authUrl}/login`;
    return this.httpClient.post(url, loginForm.value);
  }

  forgotPassUser(forgotpasswordForm: FormGroup): Observable<any>{
    const url = `${this.authUrl}/passUpdate`;
    return this.httpClient.put(url, forgotpasswordForm.value);
  }

  updateUserLocation(id: string, location: string):Observable<any>{
    const url = `${this.baseUrl}/locationUpdate/${id}/${location}`;
    return this.httpClient.put(url,null);
  }

}

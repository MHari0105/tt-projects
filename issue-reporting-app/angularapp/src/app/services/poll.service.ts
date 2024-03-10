import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Poll } from '../models/poll';
import { environment } from '../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class PollService {

  private baseUrl= '';
  
  constructor(private http: HttpClient) { 
    this.baseUrl = `${environment.pollUrl}`
  }

  public addPoll(poll: Poll): Observable<Poll> {
    const url = `${this.baseUrl}`;
    return this.http.post<Poll>(url, poll);
  }

  public getPollsByUserId(userId:string): Observable<Poll[]> {
    const url = `${this.baseUrl}/userId/${userId}`;
    return this.http.get<Poll[]>(url);
  }
  
  public getPollsByIssueId(issueId: string | undefined): Observable<Poll[]> {
    const url = `${this.baseUrl}/issueId/${issueId}`;
    return this.http.get<Poll[]>(url);
  }

}

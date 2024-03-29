import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Issue } from '../models/issue';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class IssueService {

  private baseUrl: string = '';

  constructor(private http: HttpClient) {
    this.baseUrl = `${environment.issueUrl}`
  }

  public getIssues(): Observable<Issue[]> {
    const url = `${this.baseUrl}`
    return this.http.get<Issue[]>(url);
  }

  public addIssue(issue: FormData): Observable<Issue> {
    const url = `${this.baseUrl}`
    return this.http.post<Issue>(url, issue);
  }

  public updateIssue(issueId: string, issue: Issue): Observable<Issue> {
    const url = `${this.baseUrl}/update/${issueId}`
    return this.http.put<Issue>(url, issue);
  }

  public deleteIssue(issueId: string | undefined): Observable<any> {
    const url = `${this.baseUrl}/delete/${issueId}`
    return this.http.delete<any>(url);
  }
  
  public getIssueByIssueId(issueId: string | undefined): Observable<any> {
    const url = `${this.baseUrl}/${issueId}`;
    return this.http.get<any>(url);
  }

  public getIssuesByLocation(): Observable<Issue[]> {
    let location = localStorage.getItem('location');
    const url = `${this.baseUrl}/locations/${location}`;
    return this.http.get<Issue[]>(url);
  }

  public getIssuesByUserId(): Observable<Issue[]> {
    const userId = localStorage.getItem('userId');
    const url = `${this.baseUrl}/user/${userId}`;
    return this.http.get<Issue[]>(url);
  }
}

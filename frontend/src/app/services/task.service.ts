import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Task} from "../model/task.model";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private readonly API_URL = 'http://localhost:3200/api/tasks';

  constructor(private http: HttpClient) { }

  findAll(): Observable<Task[]> {
    return this.http.get<Task[]>(this.API_URL);
  }

  findById(id: number): Observable<Task> {
    return this.http.get<Task>(`${this.API_URL}/${id}`);
  }

  create(todo: Task): Observable<Task> {
    return this.http.post<Task>(this.API_URL, todo);
  }

  update(id: number, updatedTodo: Task): Observable<Task> {
    return this.http.put<Task>(`${this.API_URL}/${id}`, updatedTodo);
  }

  deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }
}

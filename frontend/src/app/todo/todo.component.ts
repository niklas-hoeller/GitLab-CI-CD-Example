import { Component, OnInit } from '@angular/core';
import { TaskService } from '../services/task.service';
import {Task} from "../model/task.model";

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.scss']
})
export class TodoComponent implements OnInit {

  tasks: Task[] = [];
  newTaskTitle: string = '';

  constructor(private todoService: TaskService) { }

  ngOnInit(): void {
    this.fetchTasks();
  }

  fetchTasks(): void {
    this.todoService.findAll().subscribe({
      next: data => this.tasks = data,
      error: error => console.error('Error fetching tasks:', error)
    });
  }

  addTask(): void {
    if (!this.newTaskTitle.trim()) {
      alert('Task title cannot be empty');
      return;
    }

    const newTask: Task = {
      title: this.newTaskTitle,
      completed: false
    };

    this.todoService.create(newTask).subscribe({
      next: data => {
        this.tasks.push(data);
        this.newTaskTitle = '';
      },
      error: error => console.error('Error creating task:', error)
    });
  }

  updateTask(task: Task): void {
    if (task.id === undefined) return;
    this.todoService.update(task.id, task).subscribe({
      next: data => console.log('Task updated:', data),
      error: error => console.error('Error updating task:', error)
    });
  }

  deleteTask(id: number | undefined): void {
    if (id === undefined) return;
    this.todoService.deleteById(id).subscribe({
      next: () => this.tasks = this.tasks.filter(task => task.id !== id),
      error: error => console.error('Error deleting task:', error)
    });
  }
}

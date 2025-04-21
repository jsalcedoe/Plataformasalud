import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KeyboardserviceService {
  private keydownEvent = new Subject<KeyboardEvent>();


  constructor() {  
    window.addEventListener('keydown', (event) => this.handleKeydown(event));
  }
  get keydown$() {
    return this.keydownEvent.asObservable();
  }
  private handleKeydown(event: KeyboardEvent): void {
    if (event.ctrlKey && event.key.toLowerCase() === 'e') {
      event.preventDefault(); // Previene el comportamiento por defecto del navegador
      this.keydownEvent.next(event);
    }
  }
}

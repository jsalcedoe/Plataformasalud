import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComparteinfService {
  /*private pacienteSeleccionadoSubject = new BehaviorSubject<any>(null);
  private eventSeleccionadoSubject= new BehaviorSubject<any>(null);
  private hcSeleccionadoSubject= new BehaviorSubject<any>(null);*/

  constructor() { }

  /*setPacienteSeleccionado(paciente: any): void {
    this.pacienteSeleccionadoSubject.next(paciente);
  }

  getPacienteSeleccionado(): BehaviorSubject<any> {
    return this.pacienteSeleccionadoSubject;
  }
  

  setEventSeleccionado(event: any): void {
    this.eventSeleccionadoSubject.next(event);
  }

  getEventSeleccionado(): BehaviorSubject<any> {
    return this.eventSeleccionadoSubject;
  }

  sethcSeleccionado(event: any): void {
    this.hcSeleccionadoSubject.next(event);
  }

  gethcSeleccionado(): BehaviorSubject<any> {
    return this.hcSeleccionadoSubject;
  }*/

  
  
}

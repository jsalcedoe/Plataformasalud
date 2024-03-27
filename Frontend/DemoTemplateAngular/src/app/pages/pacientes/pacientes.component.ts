import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { OperacionService } from 'src/app/services/operacion.service';

@Component({
  selector: 'app-pacientes',
  templateUrl: './pacientes.component.html',
  
})
export class PacientesComponent implements OnInit {

  pac : any;
  ready : boolean = false;

  constructor(private servicio:OperacionService, private router:Router) { }

  ngOnInit(): void {
    this.getPacientes();
  }

  getPacientes(){
    this.servicio.getPacientes().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('PACIENTE', res);
        this.pac = res;
        this.ready = true;
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('ERROR:', err);
        alert('Error ' + err.message)
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  irCreapac(){
    this.router.navigate(['creapaciente'])
  }



}

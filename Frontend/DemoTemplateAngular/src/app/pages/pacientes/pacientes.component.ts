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

  redirigir(destino:String){
    console.log('valor que viene del html',destino)
    switch (destino){
      case "1":
        this.router.navigate(['citas']);
        console.log('1. ir a citas',destino)
        break;
      case "2":
        this.router.navigate(['eventos',this.pac]);
        console.log('2. ir a eventos',destino)
        break;
      case "3":
        this.router.navigate(['medico']);
        console.log('3. ir a medico',destino)
        break;
      case "4":
        this.router.navigate(['historiaclinica']);
        console.log('4. ir a hc',destino)
        break;  
      case "5":
        this.router.navigate(['evolucionmedica']);
        console.log('5. ir a evolucion medica',destino)
        break;
      case "6":
        this.router.navigate(['descripcionquirurgica']);
        console.log('6. ir a descripcion',destino)
        break;
      case "7":
        this.router.navigate(['asistencial']);
        console.log('7. ir a asistencial',destino)
        break;
      case "8":
        this.router.navigate(['evolucionenfermeria']);
        console.log('8. ir a evolucion enfermeria',destino)
        break;
      case "9":
        this.router.navigate(['registrosignosvitales']);
        console.log('9. ir a signos vitales',destino) 
        break;

    }
    console.log('selecciono:',destino)
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

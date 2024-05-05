import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ComparteinfService } from 'src/app/services/comparteinf.service';
import { OperacionService } from 'src/app/services/operacion.service';

@Component({
  selector: 'app-eventos',
  templateUrl: './eventos.component.html',
  
})
export class EventosComponent implements OnInit {
  event:any
  ready:boolean=false;

  constructor(private router:Router,
              private servicio:OperacionService,
              private datoscompartidos: ComparteinfService
  ) { }

  ngOnInit(): void {
    this.consultaeventos();
  }

  /*redirigir(destino:String, event:any){
    console.log('valor que viene del html',destino)
    // Llama a la función para seleccionar el paciente y guardar sus detalles en el servicio
    this.seleccionarevento(event);
    switch (destino){
      case "1":
        this.router.navigate(['citas']);
        console.log('1. ir a citas',destino)
        break;
      case "2":
        this.router.navigate(['creahistoriaclinica'], { state: { eventInfo: event } });
        console.log('2. ir a hc',destino)
        break;  
      case "3":
        this.router.navigate(['evolucionmedica']);
        console.log('3. ir a evolucion medica',destino)
        break;
      case "4":
        this.router.navigate(['descripcionquirurgica']);
        console.log('4. ir a descripcion',destino)
        break;
      case "5":
        this.router.navigate(['evolucionenfermeria']);
        console.log('5. ir a evolucion enfermeria',destino)
        break;
      case "6":
        this.router.navigate(['registrosignosvitales']);
        console.log('6. ir a signos vitales',destino) 
        break;

    }
    console.log('selecciono:',destino)
  }*/

  consultaeventos(){
    this.servicio.getEventos().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('EVENTOS', res);
        this.event = res;
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

  /*seleccionarevento(event:any){
      this.datoscompartidos.setEventSeleccionado(event)
  }*/

  irCreaEvento(){
    this.router.navigate(['creaeventos'])
 }

}

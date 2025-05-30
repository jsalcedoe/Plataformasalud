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
              
  ) { }

  ngOnInit(): void {
    this.consultaeventos();
  }
  
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

  redirigir(destino:string, evento:any){
    switch(destino){
      case "1": 
      this.router.navigateByUrl(`/creaeventos/${evento.idevent}`)
      console.log('valor que pasa desde el componente paciente',evento)
      break;

      case "2": 
      this.router.navigateByUrl(`/creahistoriaclinica/${evento.idevent}`)
      console.log('valor que pasa desde el componente eventos',evento)
      break;

      case "3": 
      this.router.navigateByUrl(`/creaevomed/${evento.idevent}`)
      console.log('valor que pasa desde el componente eventos',evento)
      break;

      case "4": 
      this.router.navigateByUrl(`/creadesqxcompleta/${evento.idevent}`)
      console.log('valor que pasa desde el componente eventos',evento)
      break;

      case "5": 
      this.router.navigateByUrl(`/evolucionenfermeria/${evento.idevent}`)
      console.log('valor que pasa desde el componente eventos',evento)
      break;

      case "6": 
      this.router.navigateByUrl(`/registrosignosvitales/${evento.idevent}`)
      console.log('valor que pasa desde el componente eventos',evento)
      break;

      case "7": 
      this.router.navigateByUrl(`/registromedicamentos/${evento.idevent}`)
      console.log('valor que pasa desde el componente eventos',evento)
      break;

      case "8": 
      this.router.navigateByUrl(`/epicrisis/${evento.idevent}`)
      console.log('valor que pasa desde el componente eventos',evento)
      break;

      case "9": 
      this.router.navigateByUrl(`/creaordemedins/${evento.idevent}`)
      console.log('valor que pasa desde el componente eventos',evento)
      break;

      case "10": 
      this.router.navigateByUrl(`/creaordenprocexam/${evento.idevent}`)
      console.log('valor que pasa desde el componente eventos',evento)
      break;

      case "11": 
      this.router.navigateByUrl(`/ordenesmedins/${evento.idevent}`)
     
      break;

      case "12": 
      this.router.navigateByUrl(`/ordenprocexam/${evento.idevent}`)
     
      break;

      case "13": 
      this.router.navigateByUrl(`/asignaconsentimientopaciente/${evento.idevent}`)
     
      break;

      case "14": 
      this.router.navigateByUrl(`/consentimientospaciente/${evento.idevent}`)
     
      break;


    }
    
  }

  irCreaEvento(){
    this.router.navigate(['creaeventos'])
 }

}

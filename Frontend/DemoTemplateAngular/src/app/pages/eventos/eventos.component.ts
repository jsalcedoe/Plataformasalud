import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { OperacionService } from 'src/app/services/operacion.service';

interface Evento {
  idevent: number;
  conseventpac: string;
  pacevent_fk: {
    numdocpac: string;
    primernompac: string;
    segundonompac: string;
    primerapepac: string;
    segundoapepac: string;
    entidad: { nomeapb: string };
  };
  estevent_fk: { detstatus: string };
}

@Component({
  selector: 'app-eventos',
  templateUrl: './eventos.component.html',
  
})
export class EventosComponent implements OnInit {
  objectKeys = Object.keys;
  event:any
  ready:boolean=false;

  rutasDestino: { [key: string]: string } = {
    '1': 'creaeventos',
    '2': 'creahistoriaclinica',
    '3': 'creaevomed',
    '4': 'creadesqxcompleta',
    '5': 'evolucionenfermeria',
    '6': 'registrosignosvitales',
    '7': 'registromedicamentos',
    '8': 'epicrisis',
    '9': 'creaordemedins',
    '10': 'creaordenprocexam',
    '11': 'ordenesmedins',
    '12': 'ordenprocexam',
    '13': 'asignaconsentimientopaciente',
    '14': 'consentimientospaciente',
  };

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

  redirigir(destino: string, evento: Evento): void {
    const ruta = this.rutasDestino[destino];
    if (ruta) {
      this.router.navigateByUrl(`/${ruta}/${evento.idevent}`);
    }
  }

  irCreaEvento(){
    this.router.navigate(['creaeventos'])
 }

}

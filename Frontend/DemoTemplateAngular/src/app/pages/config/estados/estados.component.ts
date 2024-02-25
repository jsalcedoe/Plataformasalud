import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';



@Component({
  selector: 'app-estados',
  templateUrl: './estados.component.html'
})
export class EstadosComponent implements OnInit {

  estados:any
  ready: boolean = false
  
  constructor(private service:ConfigService, private route:Router) { }

  ngOnInit(): void {
    this.getEstados();
  }

  getEstados():void{
    this.service.getEstados().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('ESTADOS', res);
        this.estados = res;
        this.ready = true;
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
    
  }

  irCreaestados(){

    this.route.navigate(['creaestados'])

  }

}

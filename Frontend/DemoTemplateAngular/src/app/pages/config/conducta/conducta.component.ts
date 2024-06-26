import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-conducta',
  templateUrl: './conducta.component.html',
  
})
export class ConductaComponent implements OnInit {

  conducta: any
  ready:boolean=false

  constructor(private service:ConfigService, private route:Router) { }

  ngOnInit(): void {
    this.getConductas();
  }

  getConductas(){
    this.service.getcondpac().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('CIUDADES', res);
        this.conducta = res;
        this.ready = true;
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error' + err.message)
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  irCreaConductas(){
    this.route.navigate(['creacondpac'])
  }

}

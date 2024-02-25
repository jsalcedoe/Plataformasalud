import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-plantillas',
  templateUrl: './plantillas.component.html'
})
export class PlantillasComponent implements OnInit {

  plantillas : any;
  ready : boolean = false;

  constructor(private services:ConfigService,private route: Router) { }

  ngOnInit(): void {
    this.getPlantillas();
  }

  getPlantillas(){
    this.services.getPlantillas().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('PLANTILLAS', res);
        this.plantillas = res;
        this.ready = true;
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message)
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  irCreaplantillas(){
    this.route.navigate(['creaplantillas'])
  }

}

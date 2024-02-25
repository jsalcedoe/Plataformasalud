import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-representantelegal',
  templateUrl: './representantelegal.component.html',
  
})
export class RepresentantelegalComponent implements OnInit {

  representante : any;
  ready : boolean = false;

  constructor(private services:ConfigService, private router:Router) { }

  ngOnInit(): void {
    this.getRepresentante();
  }
  getRepresentante(){
    this.services.getRepresentanteLegal().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('REPRESENTANTE', res);
        this.representante = res;
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

  irCreareplegal(){
    this.router.navigate(['creareplegal'])
  }

}

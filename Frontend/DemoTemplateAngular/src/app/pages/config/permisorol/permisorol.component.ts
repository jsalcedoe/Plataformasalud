import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-permisorol',
  templateUrl: './permisorol.component.html',
  
})
export class PermisorolComponent implements OnInit {

  perol : any
  ready : boolean = false

  constructor(private service:ConfigService) { }

  ngOnInit(): void {
    this.getPermisorol();
  }

  getPermisorol(){
    this.service.getPermisorol().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('PERMISO POR ROL', res);
        this.perol = res;
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

}

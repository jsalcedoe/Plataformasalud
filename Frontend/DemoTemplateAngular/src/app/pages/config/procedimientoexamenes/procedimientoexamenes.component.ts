import { Component, OnInit } from '@angular/core';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';

@Component({
  selector: 'app-procedimientoexamenes',
  templateUrl: './procedimientoexamenes.component.html'
})
export class ProcedimientoexamenesComponent implements OnInit {

  procedimientos : any;
  ready : boolean = false;

  constructor(private services:ConfigService) { }

  ngOnInit(): void {
    this.getProcedimientosexam();
  }
  getProcedimientosexam(){
    this.services.getProcedimientoexamenes().pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('PERMISOS', res);
        this.procedimientos = res;
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

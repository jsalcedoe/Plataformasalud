import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { OperacionService } from 'src/app/services/operacion.service';

@Component({
  selector: 'app-ordenesmedins',
  templateUrl: './ordenesmedins.component.html',
  
})
export class OrdenesmedinsComponent implements OnInit {
  ordmedins:any
  ready : boolean=false
  idevent: string
  constructor(private servicio:OperacionService,
              private router:Router,
               private paramsrouter: ActivatedRoute
  ) {
    this.idevent = this.paramsrouter.snapshot.paramMap.get('idevent')
    console.log('idevent del snapshop',this.idevent)
   }

  ngOnInit(): void {
    this.getOrdenesMedIns()
  }

   getOrdenesMedIns(){
    const id = this.idevent; // Replace with the actual id value
    this.servicio.getordenmedinsXIdEvent(id)
    .pipe(
        tap((res) => {
          // Maneja la respuesta exitosa aquí
          console.log('Ordenes MedIns', res);
          this.ordmedins = res;
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
    irEditOrdenMedIns(idordmedins:any){
      this.router.navigateByUrl(`/editordenesmedins/${idordmedins}`)      
    }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { OperacionService } from 'src/app/services/operacion.service';

@Component({
  selector: 'app-consentimientospaciente',
  templateUrl: './consentimientospaciente.component.html',
 
})
export class ConsentimientospacienteComponent implements OnInit {

  consinfpac:any
  ready : boolean=false
  idevent: string

  constructor(
    private servicio:OperacionService,
    private router:Router,
    private paramsrouter: ActivatedRoute
  ) {
    this.idevent = this.paramsrouter.snapshot.paramMap.get('idevent')
  }

  ngOnInit(): void {
    this.getConsentimientoPaciente()
  }

  getConsentimientoPaciente(){

    const id = this.idevent; 
    this.servicio.getconsinfpacXIdEvent(id)
    .pipe(
            tap((res) => {
              // Maneja la respuesta exitosa aquí
              console.log('RES: ',res)
              this.consinfpac = res;
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

  irgenerapdfconsentimiento(idconsinfpac:any){
    this.router.navigateByUrl(`/generapdfconsentimiento/${idconsinfpac}`)
    console.log('id del consentimiento que pasa',idconsinfpac)
  }

}

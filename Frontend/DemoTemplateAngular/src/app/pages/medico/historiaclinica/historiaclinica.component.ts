import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ComparteinfService } from 'src/app/services/comparteinf.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-historiaclinica',
  templateUrl: './historiaclinica.component.html',
  
})
export class HistoriaclinicaComponent implements OnInit {
  hc:any
  ready: boolean = false
  
  constructor(private router:Router,
              private service:OperacionService,
              private datoscompartidos: ComparteinfService
  ) {}
  ngOnInit(): void {
    this.irCreaHC()
  }

  irCreaHC(){
    this.service.getHc()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('HC', res);
        this.hc = res;
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
  redirigir(destino:string, hc:any){
    const idevent = hc.eventpac_fk.idevent;
    console.log('valor que pasa desde el componente eventos',idevent)
    this.router.navigateByUrl(`/verifhc/${idevent}`)
  }



}

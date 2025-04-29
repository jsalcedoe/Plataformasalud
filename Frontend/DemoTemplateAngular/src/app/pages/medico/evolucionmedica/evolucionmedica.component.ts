import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-evolucionmedica',
  templateUrl: './evolucionmedica.component.html',
  
})
export class EvolucionmedicaComponent implements OnInit {
  
  evomed:any
  ready: boolean = false

  constructor(private router:Router,
              private service:OperacionService,
               ) { }

  ngOnInit(): void {
    this.getEvoMed()
  }

  getEvoMed(){
    this.service.getEvoMed()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Evoluciones Medicas', res);
        this.evomed = res;
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

  redirigir(destino:string, evomed:any){
    //const idevomed = evomed.idevol;
    console.log('valor que pasa evoluciones', evomed.idevol)
    this.router.navigateByUrl(`/verifevomed/${evomed.idevol}`);
  }

  

}



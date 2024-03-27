import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creatiponota',
  templateUrl: './creatiponota.component.html',
  
})
export class CreatiponotaComponent implements OnInit {
  formTipoNota:FormGroup  

  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) {
                this.formTipoNota = fb.group({
                  nametypnot:['',[Validators.required,Validators.maxLength(6)]],
                  dettypnot:['',[Validators.required]]
                })
               }

  ngOnInit(): void {
  }

  creaTipoNota(){
    let struckTipoNota = {
      nametypnot:this.formTipoNota.value.nametypnot,
      dettypnot:this.formTipoNota.value.dettypnot,
      estypnot_fk:{
        "idstatus":1
      }
    }
    this.service.addTipoNota(struckTipoNota)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE NOTAS', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['tiponota']);
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: err.message // Mostrar el mensaje recibido desde el backend
        });
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

}

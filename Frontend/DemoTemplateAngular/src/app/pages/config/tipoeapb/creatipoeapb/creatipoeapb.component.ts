import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creatipoeapb',
  templateUrl: './creatipoeapb.component.html',
  
})
export class CreatipoeapbComponent implements OnInit {
  formTipoEAPB: FormGroup

  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) {
                this.formTipoEAPB = fb.group({
                  nomtipeapb: ['',[Validators.required]],
                  detipeapb:['',[Validators.required]]
                })
               }

  ngOnInit(): void {
  }

  creaTipoEAPB(){
    let structTipoEAPB = {
      nomtipeapb: this.formTipoEAPB.value.nomtipeapb,
      detipeapb:this.formTipoEAPB.value.detipeapb,
      estyeapb_fk:{
        "idstatus":1
      }
    }

    this.service.addTipoEAPB(structTipoEAPB)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO EAPB', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['tipoeapb']);
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        Swal.fire({
          icon: 'error',
          title: 'Error en la operación',
          text: err.message // Mostrar el mensaje recibido desde el backend
        });
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

}

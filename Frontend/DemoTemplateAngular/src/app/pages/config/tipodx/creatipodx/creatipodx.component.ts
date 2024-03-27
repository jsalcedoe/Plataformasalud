import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creatipodx',
  templateUrl: './creatipodx.component.html',
  
})
export class CreatipodxComponent implements OnInit {
  formtipodx:FormGroup

  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) {
                this.formtipodx = fb.group({
                  namtypdx:['',[Validators.required,Validators.maxLength(6)]],
                  detypdx:['',[Validators.required]]
                })
               }

  ngOnInit(): void {
  }

  creaTipoDx(){
    let struckTipoDx= {
      namtypdx:this.formtipodx.value.namtypdx,
      detypdx:this.formtipodx.value.detypdx,
      estyodx_fx: {
        "idstatus":1
      }
    }

    this.service.addTipoDx(struckTipoDx)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE DIAGNOSTICO', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['tipodx']);
        
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

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creadx',
  templateUrl: './creadx.component.html',
  
})
export class CreadxComponent implements OnInit {
  formdx:FormGroup

  constructor(private router:Router,
              private service:ConfigService,
              private fb:FormBuilder) {
                this.formdx = fb.group({
                  iddx:['',[Validators.required,Validators.maxLength(4)]],
                  nomdx:['',[Validators.required]],
                  descdx:['',[Validators.required]],
                  sexdx:['',[Validators.required]],
                  edadmindx:['',[Validators.required]],
                  edadmaxdx:['',[Validators.required]],
                  capdx:['',[Validators.required]]

                })

                
               }

  ngOnInit(): void {
  }

  creaDx(){
    let struckDx = {

      iddx:this.formdx.value.iddx,
      nomdx:this.formdx.value.nomdx,
      descdx:this.formdx.value.descdx,
      sexdx:this.formdx.value.sexdx,
      edadmindx:this.formdx.value.edadmindx,
      edadmaxdx:this.formdx.value.edadmaxdx,
      capdx:this.formdx.value.capdx,
      estdx_fk:{
        "idstatus":1
      }

    }
    this.service.addDx(struckDx)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('DIAGNOSTICOS', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['diagnosticos']);
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

}

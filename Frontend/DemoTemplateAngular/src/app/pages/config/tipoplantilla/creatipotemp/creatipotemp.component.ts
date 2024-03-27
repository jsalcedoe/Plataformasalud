import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creatipotemp',
  templateUrl: './creatipotemp.component.html',
  
})
export class CreatipotempComponent implements OnInit {
  formtipotemp:FormGroup


  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) {
                this.formtipotemp = fb.group({
                  nomtytemp:['',[Validators.required,Validators.maxLength(6)]],
                  detytemp:['',[Validators.required]]
                })
               }

  ngOnInit(): void {
  }

  creaTipoTemp(){
    let struckTipoTemp = {
      nomtytemp:this.formtipotemp.value.nomtytemp,
      detytemp:this.formtipotemp.value.detytemp,
      esttytemp_fk:{
        "idstatus":1
      }
    }
    this.service.addTipoPlantilla(struckTipoTemp)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('TIPO DE PLANTILLAS', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['tipoplantilla']);
        
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

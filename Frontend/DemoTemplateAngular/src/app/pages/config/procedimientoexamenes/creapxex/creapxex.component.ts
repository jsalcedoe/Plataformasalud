import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creapxex',
  templateUrl: './creapxex.component.html',
  
})
export class CreapxexComponent implements OnInit {
  formpxex:FormGroup
  tippx:any

  constructor(private service:ConfigService,
              private router:Router,
              fb:FormBuilder) {
                this.formpxex = fb.group({
                  codpxex:['',[Validators.required,Validators.maxLength(6)]],
                  nompxex:['',[Validators.required]],
                  sexopxex:['',[Validators.required]],
                  tpxex:['',[Validators.required]]
                })
               }

  ngOnInit(): void {
    this.consultaTipoPx()
  }

  consultaTipoPx(){
    this.service.getTipopx()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta tipo de procedimiento y examenes', res);
        this.tippx = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  creapxex(){
    let structpxex = {
      codpxex:this.formpxex.value.codpxex,
      nompxex:this.formpxex.value.nompxex,
      sexopxex:this.formpxex.value.sexopxex,
      tpxex:{
        "idtproc":this.formpxex.value.tpxex
      },
      estadopxex_fk:{
        "idstatus":1
      }
    }
    this.service.addProcedimientoexamenes(structpxex)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('PROCEDIMIENTOS Y EXAMENES', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['procedimientoexamenes']);
        
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

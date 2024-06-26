import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creacondpac',
  templateUrl: './creacondpac.component.html',
  
})
export class CreacondpacComponent implements OnInit {
  formConductas: FormGroup
  conducta:any

  constructor(private service:ConfigService,
              private fb:FormBuilder,
              private router:Router) { 
                this.formConductas = fb.group({
                  nomcondpac:['',[Validators.required,Validators.maxLength(6)]],
                  detcondpac:['',[Validators.required]]
                })
              }

  ngOnInit(): void {
  }

  CreaCondpac(){
    let StructConductas={
      nomcondpac:this.formConductas.value.nomcondpac,
      detcondpac:this.formConductas.value.detcondpac,
      estcondpac_fk:{
        "idstatus": 1
      }
    }
    this.service.addcondpac(StructConductas)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Conductas', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['conducta']);
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        Swal.fire({
          icon: 'error',
          title: 'Error en la Operación',
          text: err.message// Mostrar el mensaje recibido desde el backend
        });
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

}

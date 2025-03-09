import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaconsentimientos',
  templateUrl: './creaconsentimientos.component.html',
  
})
export class CreaconsentimientosComponent implements OnInit {

  formConsentimiento:FormGroup;


  constructor(private service:ConfigService,
                private fb:FormBuilder,
                private router:Router) { 
                  this.formConsentimiento = fb.group({
                    codconinf:['',[Validators.required,Validators.maxLength(100)]],
                    detconinf:['',[Validators.required]],
                  })
                }

  ngOnInit(): void {
  }

  creaconsinf(){
    let structconsinf = {
      codconinf: this.formConsentimiento.value.codconinf,
      detconinf: this.formConsentimiento.value.detconinf,
      estconinf_fk: {
                    "idstatus":1
                  }
    }
    this.service.addconinf(structconsinf)
    .pipe(
          tap((res) => {
          // Maneja la respuesta exitosa aquí
          console.log('consentimientos', res);
          Swal.fire({
            icon: 'success',
            title: 'Operación exitosa',
            text: res.mensaje // Mostrar el mensaje recibido desde el backend
            });
            return this.router.navigate(['consentimientos']);
                        
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

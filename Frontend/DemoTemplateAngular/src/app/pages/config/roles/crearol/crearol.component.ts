import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-crearol',
  templateUrl: './crearol.component.html',
  
})
export class CrearolComponent implements OnInit {
  formRoles:FormGroup

  constructor(private router:Router,
              private service:ConfigService,
              fb:FormBuilder) {
                this.formRoles = fb.group({
                  nomrol:['',[Validators.required, Validators.maxLength(20)]],
                  desrol:['',[Validators.required, Validators.maxLength(50)]]
                })
                
               }

  ngOnInit(): void {
  }

  creaRol(){
    let struckRoles = {
      nomrol:this.formRoles.value.nomrol,
      desrol:this.formRoles.value.desrol,
      estrol_fk:{
        "idstatus": 1
      }
    }
    this.service.addRoles(struckRoles)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('ROLES', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['roles']);
        
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

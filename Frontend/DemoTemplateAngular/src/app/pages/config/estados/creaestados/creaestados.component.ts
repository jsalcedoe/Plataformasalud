import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, switchMap, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaestados',
  templateUrl: './creaestados.component.html',
  
})
export class CreaestadosComponent implements OnInit {

  formstatus:FormGroup;
  st:any;
  ready:boolean=false;

  constructor(private service:ConfigService,
              private fb:FormBuilder,
              private router:Router) {
                this.formstatus = fb.group({
                  nomstatus:['',[Validators.required,Validators.maxLength(4),Validators.minLength(3)]],
                  detstatus:['',[Validators.required]]
                })
               }

  ngOnInit(): void {
  }
  /* EL ORDEN DE INGRESO DE LOS ESTADOS ES:
  1. CREADO
  2. ACTIVO
  3. EDITADO
  4. INACTIVO
   */
  insertstatus(){
    
    let structstatus={
        nomstatus:this.formstatus.value.nomstatus,  
        detstatus:this.formstatus.value.detstatus
      }

      this.service.addStatus(structstatus)
      .pipe(
        tap((res) => {
          // Maneja la respuesta exitosa aquí
          console.log('ESTADOS', res);
          Swal.fire({
            icon: 'success',
            title: 'Operación exitosa',
            text: res.mensaje // Mostrar el mensaje recibido desde el backend
          });
          return this.router.navigate(['estados']);
          
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
    

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaviaadm',
  templateUrl: './creaviaadm.component.html',
  
})
export class CreaviaadmComponent implements OnInit {

  formviaadm:FormGroup
  viadm:any

  constructor(private service:ConfigService,
              private fb:FormBuilder,
               private router:Router) { 
                this.formviaadm = fb.group({
                  nomviadm:['',[Validators.required,Validators.maxLength(8)]],
                  detviadm:['',[Validators.required]]
                })
               }

  ngOnInit(): void {
  }
  creaviadm(){
    let struckviadm = {
      nomviadm:this.formviaadm.value.nomviadm,
      detviadm:this.formviaadm.value.detviadm,
      estviadm_fk:{
        "idstatus": 1
      }
    }
    this.service.addviadm(struckviadm)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('viadm', res);
        this.viadm=res
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['viaadministracion']);
        
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

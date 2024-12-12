import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creapresentacionmedins',
  templateUrl: './creapresentacionmedins.component.html'
})
export class CreapresentacionmedinsComponent implements OnInit {

  formcreapmedins : FormGroup

  constructor(private service:ConfigService,
    private router:Router,
    private fb:FormBuilder) { 
      this.formcreapmedins = fb.group({
        pmedins:['',[Validators.required,Validators.maxLength(8)]],
        detpmedins:['',Validators.required]
      })
    }

  ngOnInit(): void {
  }

  creapmedins(){
    let struckPmedins= {
      pmedins:this.formcreapmedins.value.pmedins,
      detpmedins:this.formcreapmedins.value.detpmedins,
      estpmedins_fk: {
        "idstatus":1
      }
    }

    this.service.addpmedins(struckPmedins)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('PRESENTACION DE MEDINS', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['presentacionmedins']);
        
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

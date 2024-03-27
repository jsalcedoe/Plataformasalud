import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaprestador',
  templateUrl: './creaprestador.component.html',
  
})
export class CreaprestadorComponent implements OnInit {
  formPrestador: FormGroup
  tipodoc:any
  city:any

  constructor(private service: ConfigService,
              private router: Router,
              private fb:FormBuilder) {
                this.formPrestador = fb.group({
                  nomprestserv:['',[Validators.required]],
                  docprestserv:['',[Validators.required]],
                  dirprestserv:['',[Validators.required]],
                  telprestserv:['',[Validators.required]],
                  emailprestserv:['',[Validators.required,Validators.email]],
                  ciuprestserv_fk:['',[Validators.required]],
                  tipdocprestserv_fk:['',[Validators.required]]
                })


               }

  ngOnInit(): void {
    this.consultaTD()
    this.consultaCiudad()
  }
  consultaTD(){
    this.service.getTipoDocumentos()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta tipo de documentos', res);
        this.tipodoc = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  consultaCiudad(){
    this.service.getCity()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta tipo de documentos', res);
        this.city = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  creaPrestador(){
    let struckPrestador = {
      nomprestserv:this.formPrestador.value.nomprestserv,
      docprestserv:this.formPrestador.value.docprestserv,
      dirprestserv:this.formPrestador.value.dirprestserv,
      telprestserv:this.formPrestador.value.telprestserv,
      emailprestserv:this.formPrestador.value.emailprestserv,
      ciuprestserv_fk:{
        "idciu":this.formPrestador.value.ciuprestserv_fk
      },
      tipdocprestserv_fk:{
        "idtipdoc":this.formPrestador.value.tipdocprestserv_fk
      },
      estadoprestserv_fk:{
        "idstatus":1
      }


    }

    this.service.addPrestadorServicio(struckPrestador)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('CIUDADES', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['prestadorservicio']);
        
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

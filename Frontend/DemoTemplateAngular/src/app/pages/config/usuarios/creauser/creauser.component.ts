import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creauser',
  templateUrl: './creauser.component.html',
  
})
export class CreauserComponent implements OnInit {
  formuser:FormGroup
  tipodoc:any
  cargo:any

  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) {
                this.formuser = fb.group({
                  username:['',[Validators.required]],
                  password:['',[Validators.required]],
                  docuser:['',[Validators.required]],
                  firstname:['',[Validators.required]],
                  lastname:['',[Validators.required]],
                  emailuser:['',[Validators.required, Validators.email]],
                  typeiduser_fk:['',[Validators.required]],
                  carguser_fk:['',[Validators.required]]

                })
               }

  ngOnInit(): void {
    this.consultaTD(),
    this.consultaCargo()

  }
  consultaTD(){
    this.service.getTipoDocumentos()
     .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta Tipo de documento', res);
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

  consultaCargo(){
    this.service.getCargos()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta de cargos', res);
        this.cargo = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  creaUser(){
    let structUser = {
      username:this.formuser.value.username,
      password:this.formuser.value.password,
      docuser:this.formuser.value.docuser,
      firstname:this.formuser.value.firstname,
      lastname:this.formuser.value.lastname,
      emailuser:this.formuser.value.emailuser,
      typeiduser_fk:{
        "idtipdoc":this.formuser.value.typeiduser_fk
      },
      carguser_fk:{
        "idcarguser":this.formuser.value.carguser_fk
      },
      estuser_fk:{
        "idstatus":1
      }


    }

    this.service.addUser(structUser)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('USUARIOS', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['usuarios']);
        
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

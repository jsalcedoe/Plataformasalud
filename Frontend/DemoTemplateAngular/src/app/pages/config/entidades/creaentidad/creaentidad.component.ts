import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaentidad',
  templateUrl: './creaentidad.component.html',

})
export class CreaentidadComponent implements OnInit {
  formEntidad: FormGroup
  tipodoc:any
  tipoEntidad:any

  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) {
                this.formEntidad = fb.group({
                  nomeapb:['',[Validators.required]],
                  doceapb:['',[Validators.required]],
                  direapb:['',[Validators.required]],
                  contaceapb:['',[Validators.required]],
                  emaileapb:['',[Validators.required]],
                  gerenteapb:['',[Validators.required]],
                  tipent:['',[Validators.required]],
                  tipdoceapb_fk:['',[Validators.required]],

                })
               }

  ngOnInit(): void {
    this.consultaTipoDoc(),
    this.ConsultaTipoEntidad()
  }
  consultaTipoDoc(){
    this.service.getTipoDocumentos()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta tipo de documento', res);
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

  ConsultaTipoEntidad(){
    this.service.getTipoeapb()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta tipo de entidades', res);
        this.tipoEntidad = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  creaEntidad(){
    let structEntidades = {
      nomeapb:this.formEntidad.value.nomeapb,
      doceapb:this.formEntidad.value.doceapb,
      direapb:this.formEntidad.value.direapb,
      contaceapb:this.formEntidad.value.contaceapb,
      emaileapb:this.formEntidad.value.emaileapb,
      gerenteapb:this.formEntidad.value.gerenteapb,
      tipent:{
        "idtipeapb":this.formEntidad.value.tipent
      },
      tipdoceapb_fk:{
        "idtipdoc":this.formEntidad.value.tipdoceapb_fk
      },
      esteapb_fk:{
        "idstatus":1
      }


    }
    this.service.addEntidades(structEntidades)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('ENTIDADES', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['entidades']);
        
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

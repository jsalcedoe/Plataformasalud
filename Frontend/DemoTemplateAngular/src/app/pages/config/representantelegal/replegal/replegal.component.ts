import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-replegal',
  templateUrl: './replegal.component.html',
  
})
export class ReplegalComponent implements OnInit {
  formreplegal:FormGroup
  tipdoc:any
  preserv:any
  
  constructor(private service:ConfigService,
              private router:Router,
              private fb:FormBuilder) {
                this.formreplegal = fb.group({
                  docrepleg:['',[Validators.required]],
                  pnomrepleg:['',[Validators.required]],
                  snomrepleg:['',[Validators.required]],
                  paperepleg:['',[Validators.required]],
                  saperepleg:['',[Validators.required]],
                  emailrepleg:['',[Validators.required]],
                  tipdocrepleg_fk:['',[Validators.required]],
                  idprestservrepleg_fk:['',[Validators.required]],
                })
               }

  ngOnInit(): void {
    this.consultaPrestaServicio()
    this.consultaTD()
  }

  consultaTD(){
    this.service.getTipoDocumentos()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta tipo de documento', res);
        this.tipdoc = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  consultaPrestaServicio(){
    this.service.getPrestadorservicio()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('consulta prestador', res);
        this.preserv = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  creareplegal(){
    let struckRepLegal = {
                  docrepleg:this.formreplegal.value.docrepleg,
                  pnomrepleg:this.formreplegal.value.pnomrepleg,
                  snomrepleg:this.formreplegal.value.snomrepleg,
                  paperepleg:this.formreplegal.value.paperepleg,
                  saperepleg:this.formreplegal.value.saperepleg,
                  emailrepleg:this.formreplegal.value.emailrepleg,
                  tipdocrepleg_fk:{
                    "idtipdoc":this.formreplegal.value.tipdocrepleg_fk
                  },
                  idprestservrepleg_fk:{
                    "idprestserv":this.formreplegal.value.idprestservrepleg_fk
                  },
                  statusrepleg_fk:{
                    "idstatus":1
                  }
  }

  this.service.addRepresentanteLegal(struckRepLegal)
  .pipe(
    tap((res) => {
      // Maneja la respuesta exitosa aquí
      console.log('REPRESENTANTES', res);
      Swal.fire({
        icon: 'success',
        title: 'Operación exitosa',
        text: res.mensaje // Mostrar el mensaje recibido desde el backend
      });
      return this.router.navigate(['representantelegal']);
      
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

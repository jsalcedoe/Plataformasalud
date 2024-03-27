import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import { DatePipe } from '@angular/common';
import { MatDatepickerModule} from '@angular/material/datepicker';
import { MatInputModule} from '@angular/material/input';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import {provideNativeDateAdapter} from '@angular/material/core';


@Component({
  selector: 'app-creapac',
  templateUrl: './creapac.component.html',
  providers: [provideNativeDateAdapter()],
  
  
  
})
export class CreapacComponent implements OnInit {

  formpac: FormGroup
  tipdoc:any
  ciudad:any
  tipopac:any
  entidad:any
  fechanacimiento:Date
  edad:number
  dateObj: any;

  



  
  constructor(private service:ConfigService,
              private serviceOp:OperacionService,
              private router:Router,
              private formatofecha:DatePipe,
              private fb:FormBuilder) {
                
                this.formpac = fb.group({
                  numdocpac:['',[Validators.required]],
                  primernompac:['',[Validators.required]],
                  segundonompac:['',[Validators.required]],
                  primerapepac:['',[Validators.required]],
                  segundoapepac:['',[Validators.required]],
                  sexopac:['',[Validators.required]],
                  fechanacpac:['',[Validators.required]],
                  edadpac:['',[Validators.required]],
                  estadocivilpac:['',[Validators.required]],
                  direccionpac:['',[Validators.required]],
                  emailpac:['',[Validators.required,Validators.email]],
                  contactopac:['',[Validators.required]],
                  acudientepac:['',[Validators.required]],
                  contactoacudientepac:['',[Validators.required]],
                  resppac:['',[Validators.required]],
                  contactrespac:['',[Validators.required]],
                  typdocpac:['',[Validators.required]],
                  ciudad:['',[Validators.required]],
                  tipac:['',[Validators.required]],
                  entidad:['',[Validators .required]],
                })
                
               }

  ngOnInit(): void {
    this.consutalentidad(),
    this.consultaciudad(),
    this.consultatipdoc(),
    this.consultatipopac(),

    this.formpac.get('fechanacpac').valueChanges.subscribe(() => {
      this.calculaEdad();
    });

  }

  /*calculaEdad() {
    const fechaNacimientoString = this.formpac.value.fechanacpac;
    console.log("Fecha de nacimiento obtenida del formulario:", fechaNacimientoString);

    // Convertir la fecha de string a objeto Date
    const fechaNacimiento = new Date(fechaNacimientoString);

    // Verificar en la consola que la fecha se haya convertido correctamente
    console.log("Fecha de nacimiento convertida a objeto Date:", fechaNacimiento);

    // Realizar el cálculo de la edad
    const edadMilisegundos = Date.now() - fechaNacimiento.getTime();
    const edad = new Date(edadMilisegundos);
    console.log("Edad calculada:", Math.abs(edad.getUTCFullYear() - 1970));
}*/


  

  calculaEdad() {
    const fechaNacimientoString = this.formpac.value.fechanacpac;
    console.log('fecha capturada',fechaNacimientoString)
  
    if (fechaNacimientoString) {
      let fechaNacimiento: Date;
  
      // Intenta parsear la fecha
      fechaNacimiento = new Date(fechaNacimientoString);
  
      if (fechaNacimiento instanceof Date && !isNaN(fechaNacimiento.getTime())) {
        // Calcula la edad
        const fechaActual = new Date();
        const edadMilisegundos = fechaActual.getTime() - fechaNacimiento.getTime();
        const edadAños = Math.floor(edadMilisegundos / (1000 * 60 * 60 * 24 * 365.25)); // Ajuste para años bisiestos
  
        // Asigna la edad como un número
        this.formpac.patchValue({
          edadpac: edadAños
        });
      } else {
        this.formpac.patchValue({
          edadpac: null
        });
      }
    } else {
      this.formpac.patchValue({
        edadpac: null
      });
    }
  }
  

 /* calculaEdad() {
    if (this.formpac.value.fechanacpac) {
      let fechaNacimientoString = this.formpac.value.fechanacpac; // Obtiene la fecha de nacimiento del formulario
      let fechaNacimiento = new Date(fechaNacimientoString);
  
      let fechaActual = new Date();
      let añoActual = fechaActual.getFullYear();
      let añoNacimiento = fechaNacimiento.getFullYear();
      let edad = añoActual - añoNacimiento;
  
      let mesActual = fechaActual.getMonth();
      let mesNacimiento = fechaNacimiento.getMonth();
      if (mesNacimiento > mesActual || (mesNacimiento === mesActual && fechaNacimiento.getDate() > fechaActual.getDate())) {
        edad--;
      }
  
      // Actualiza el valor del campo de la edad en el formulario
      this.formpac.patchValue({
        edadpac: edad
      });
    }
  }*/
  
  

  
  consultatipdoc(){
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
      
  consultaciudad(){
          this.service.getCity()
          .pipe(
            tap((res) => {
              // Maneja la respuesta exitosa aquí
              console.log('Consulta Ciudades', res);
              this.ciudad = res;
              
            }),
            catchError((err) => {
              // Maneja el error aquí
              console.error('Error:', err);
              alert('Error ' + err.message);
              throw err; // Re-throw para que el error se propague al suscriptor
            })
          ).subscribe();
        }
      
  consultatipopac(){
          this.service.getTipopaciente()
          .pipe(
            tap((res) => {
              // Maneja la respuesta exitosa aquí
              console.log('Consulta tipo de paciente', res);
              this.tipopac = res;
              
            }),
            catchError((err) => {
              // Maneja el error aquí
              console.error('Error:', err);
              alert('Error ' + err.message);
              throw err; // Re-throw para que el error se propague al suscriptor
            })
          ).subscribe();
        }
      
  consutalentidad(){
          this.service.getEntidades()
          .pipe(
            tap((res) => {
              // Maneja la respuesta exitosa aquí
              console.log('Consulta entidadeas', res);
              this.entidad = res;
              
            }),
            catchError((err) => {
              // Maneja el error aquí
              console.error('Error:', err);
              alert('Error ' + err.message);
              throw err; // Re-throw para que el error se propague al suscriptor
            })
          ).subscribe();
        }
      
  creaPaciente(){
          let strucPaciente = {
      
            numdocpac:this.formpac.value.numdocpac,
            primernompac:this.formpac.value.primernompac,
            segundonompac:this.formpac.value.segundonompac,
            primerapepac:this.formpac.value.primerapepac,
            segundoapepac:this.formpac.value.segundoapepac,
            sexopac:this.formpac.value.sexopac,
            fechanacpac:this.formpac.value.fechanacpac,
            edadpac:this.formpac.value.edadpac,
            estadocivilpac: this.formpac.value.estadocivilpac,
            direccionpac:this.formpac.value.direccionpac,
            emailpac:this.formpac.value.emailpac,
            contactopac:this.formpac.value.contactopac,
            acudientepac:this.formpac.value.acudientepac,
            contactoacudientepac:this.formpac.value.contactoacudientepac,
            resppac:this.formpac.value.resppac,
            contactrespac:this.formpac.value.contactrespac,
            typdocpac:{
              "idtipdoc":this.formpac.value.typdocpac
            },
            ciudad:{
              "idciu":this.formpac.value.ciudad
            },
            tipac:{
              "idtipac":this.formpac.value.tipac
            },
            entidad:{
              "ideapb":this.formpac.value.entidad
            },
            estpac_fk:{
              "idstatus":1
            }
      
          }
          this.serviceOp.addPacientes(strucPaciente)
          .pipe(
            tap((res) => {
              // Maneja la respuesta exitosa aquí
              console.log('PACIENTES', res);
              return this.router.navigate(['eventos']);
              
            }),
            catchError((err) => {
              // Maneja el error aquí
              console.error('Error:', err);
              alert('Error ' + err.message);
              throw err; // Re-throw para que el error se propague al suscriptor
            })
          ).subscribe();
      
        }
      
        ircreaevento(){
          this.router.navigate(['eventos'])
        }
      
}

  
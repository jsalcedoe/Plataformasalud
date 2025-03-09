import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import { Router } from '@angular/router';
import { catchError, EMPTY, map, Observable, of, switchMap, tap } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creapaciente',
  templateUrl: './creapaciente.component.html',
})
export class CreapacienteComponent implements OnInit {
  formpac: FormGroup
  tipdoc:any
  ciudad:any
  tipopac:any
  entidad:any
  edad=0;
  ready=false

  constructor(private service:ConfigService,
              private serviceOp:OperacionService,
              private router:Router,
              private fb:FormBuilder) {
               this.formpac = fb.group({
                  numdocpac:['',[Validators.required]],
                  primernompac:['',[Validators.required]],
                  segundonompac:['',[Validators.nullValidator]],
                  primerapepac:['',[Validators.required]],
                  segundoapepac:['',[Validators.nullValidator]],
                  sexopac:['',[Validators.required]],
                  fechanacpac:['',[Validators.required]],                
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
    this.consultatipdoc()
    this.consultaciudad()
    this.consultatipopac()
    this.consutalentidad()  
  }
  calculaedad(date:string){
    const fechaNacimiento = new Date(date);
    const ahora = new Date();
    const edadMilisegundos = ahora.getTime() - fechaNacimiento.getTime();
    const edadAños = Math.floor(edadMilisegundos / (1000 * 60 * 60 * 24 * 365.25));
    this.edad=edadAños
    console.log('Edad:', this.edad);
    
  }

  

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
        this.ready=true
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

  const tipoDocumento = this.formpac.value.typdocpac;
  const edadPaciente = this.edad;
  const numDocumento = this.formpac.value.numdocpac;
  const estadocivilpac = this.formpac.value.estadocivilpac
  console.log('cantidad de digitos capturados',numDocumento.length)
  console.log('tipo de documento',tipoDocumento)
  if ((tipoDocumento == 1 && edadPaciente < 18) ||(tipoDocumento == 1 && estadocivilpac =="MENOR DE EDAD" ) || (tipoDocumento == 3 && edadPaciente >18 || tipoDocumento ==4 && edadPaciente > 18) ){
    
    Swal.fire({
      icon: 'error',
      title: 'Error',
      text: `No se puede crear el paciente. El tipo de documento es ${tipoDocumento} y la edad es ${edadPaciente}, los cuales no corresponden.`,
    });
    return; 
  } 

  if((tipoDocumento == 1) && (numDocumento.length < 7 || numDocumento.length > 10))
    {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: `La longitud del número de documento no es válida para el tipo de documento seleccionado (${tipoDocumento}).`,
      });
      return;
    }
    
    let strucPaciente = {

      numdocpac:this.formpac.value.numdocpac,
      primernompac:this.formpac.value.primernompac,
      segundonompac:this.formpac.value.segundonompac,
      primerapepac:this.formpac.value.primerapepac,
      segundoapepac:this.formpac.value.segundoapepac,
      sexopac:this.formpac.value.sexopac,
      fechanacpac:this.formpac.value.fechanacpac,
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
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['pacientes']);
        
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

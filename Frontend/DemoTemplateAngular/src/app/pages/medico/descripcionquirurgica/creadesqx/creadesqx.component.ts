import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creadesqx',
  templateUrl: './creadesqx.component.html',
  
})
export class CreadesqxComponent implements OnInit {

  formdesqx:FormGroup
  eventSeleccionado:any
  idevent:string
  tiempoqx: any
  tipohx:any
  tipopx:any
  tipoanest: any
  conductas:any

  constructor(private router:Router,
    private service:OperacionService,
    private services:ConfigService,
    private fb:FormBuilder,
    private paramsrouter: ActivatedRoute,) { 

      this.idevent=this.paramsrouter.snapshot.paramMap.get('idevent')
      console.log('idevent del snapshop',this.idevent)

        this.formdesqx = fb.group({
        idevent:['',[Validators.required]],
        conseventpac:['',[Validators.required]],
        idpac:['',[Validators.required]],
        numdocpac:['',[Validators.required]],
        primernompac:['',[Validators.required]],
        segundonompac:['',[Validators.required]],
        primerapepac:['',[Validators.required]],
        segundoapepac:['',[Validators.required]],
        fechaprocqx:['',[Validators.required]],
        horainicioprocqx:['',[Validators.required]],
        horafinprocqx:['',[Validators.required]],
        timeqx:['',[Validators.required]],
        tipohx_fk:['',[Validators.required]],
        descqx:['',[Validators.required]],
        typqx:['',[Validators.required]],
        matprot:['',[Validators.required]],
        muespato:['',[Validators.required]],
        conducqx_fk:['',[Validators.required]],
        hallaqx:['',[Validators.required]],
        conducqx:['',[Validators.required]],
        anestesia_fk:['',[Validators.required]],


        })
    }

  ngOnInit(): void {

    if(this.idevent != null){
      
      console.log('id del evento que llega',this.idevent)
  
      this.getDataEvent();    
      this.getTipoHx();
      this.getTipopx();
      this.gettipoanestesia();
      this.getConductas();
      
      }else{
        this.clearForm();
      }
  }

  getDataEvent() {
    console.log(this.idevent);
    this.service.getEventId(this.idevent).subscribe((res:any)=>{
     console.log('evento a mostrar en el formulario',res);
     this.eventSeleccionado = res;
     this.formdesqx.patchValue({
       idevent:this.eventSeleccionado.idevent, 
       conseventpac:this.eventSeleccionado.conseventpac,
       idpac: this.eventSeleccionado.pacevent_fk.idpac,
       numdocpac:this.eventSeleccionado.pacevent_fk.numdocpac,
       primernompac:this.eventSeleccionado.pacevent_fk.primernompac,
       segundonompac:this.eventSeleccionado.pacevent_fk.segundonompac,
       primerapepac:this.eventSeleccionado.pacevent_fk.primerapepac,
       segundoapepac:this.eventSeleccionado.pacevent_fk.segundoapepac,
     })
     
    })
    
   }

   calculaTQx() {
    let horainicioprocqx = this.formdesqx.value.horainicioprocqx;
    let horafinprocqx = this.formdesqx.value.horafinprocqx;

    console.log('hora inicio', horainicioprocqx);
    console.log('hora final', horafinprocqx);

    if (horainicioprocqx && horafinprocqx) {
        let inicioParts = horainicioprocqx.split(':');
        let finParts = horafinprocqx.split(':');

        // Convertir a minutos desde la medianoche
        let inicioEnMinutos = parseInt(inicioParts[0], 10) * 60 + parseInt(inicioParts[1], 10);
        let finEnMinutos = parseInt(finParts[0], 10) * 60 + parseInt(finParts[1], 10);

        // Calcular la diferencia en minutos y convertir a horas decimales
        let diferenciaEnMinutos = finEnMinutos - inicioEnMinutos;
       
        let horas = Math.floor(diferenciaEnMinutos / 60);
        let minutos = diferenciaEnMinutos % 60;

        console.log('tiempo quirurgico', horas + ':' + (minutos < 10 ? '0' : '') + minutos);
        this.tiempoqx = horas + ':' + (minutos < 10 ? '0' : '') + minutos;

    } else {
        console.log('Valores de tiempo no válidos');
    }
}

getConductas(){
  this.services.getcondpac().pipe(
    tap((res) => {
      // Maneja la respuesta exitosa aquí
      console.log('Consulta conductas', res);
      this.conductas = res
      
    }),
    catchError((err) => {
      // Maneja el error aquí
      console.error('Error:', err);
      alert('Error ' + err.message);
      throw err; // Re-throw para que el error se propague al suscriptor
    })
  ).subscribe();
}

getTipoHx(){
  this.services.getthx().pipe(
    tap((res) => {
      // Maneja la respuesta exitosa aquí
      console.log('Consulta tipo de heridas', res);
      this.tipohx = res
      
    }),
    catchError((err) => {
      // Maneja el error aquí
      console.error('Error:', err);
      alert('Error ' + err.message);
      throw err; // Re-throw para que el error se propague al suscriptor
    })
  ).subscribe();
}

getTipopx(){
  this.services.getTipopx().pipe(
    tap((res) => {
      // Maneja la respuesta exitosa aquí
      console.log('Consulta tipo de procedimiento', res);
      this.tipopx = res
      
    }),
    catchError((err) => {
      // Maneja el error aquí
      console.error('Error:', err);
      alert('Error ' + err.message);
      throw err; // Re-throw para que el error se propague al suscriptor
    })
  ).subscribe();
}

gettipoanestesia(){
  this.services.getTipoAnestesia().pipe(
    tap((res) => {
      // Maneja la respuesta exitosa aquí
      console.log('Consulta tipo de anestesia', res);
      this.tipoanest = res
      
    }),
    catchError((err) => {
      // Maneja el error aquí
      console.error('Error:', err);
      alert('Error ' + err.message);
      throw err; // Re-throw para que el error se propague al suscriptor
    })
  ).subscribe();
}

creaDesQx(){
  let struckDesQx={
    fechaprocqx:this.formdesqx.value.fechaprocqx,
    horainicioprocqx:this.formdesqx.value.horainicioprocqx,
    horafinprocqx:this.formdesqx.value.horafinprocqx,
    timeqx:this.formdesqx.value.timeqx,
    descqx:this.formdesqx.value.descqx,
    typqx: this.formdesqx.value.typqx,
    matprot: this.formdesqx.value.matprot,
    muespato: this.formdesqx.value.matprot,
    complicqx: this.formdesqx.value.complicqx,
    hallaqx:this.formdesqx.value.hallaqx,
    conducqx_fk:{
      "idcondpac":this.formdesqx.value.conducqx_fk
     },
    eventpxqx_fk:{
      "idevent":this.eventSeleccionado.value.idevent
     },
    anestesia_fk:{
      "idtipanest":this.formdesqx.value.anestesia_fk
    },
    tipohx_fk:{
      "idthx":this.formdesqx.value.tipohx_fk
    } 
  }
  this.service.adddesqx(struckDesQx).pipe(
    tap((res) => {
      // Maneja la respuesta exitosa aquí
      console.log('Descripcion Quirurgica', res);
      Swal.fire({
        icon: 'success',
        title: 'Operación exitosa',
        text: res.mensaje // Mostrar el mensaje recibido desde el backend
      }).then (() =>{
        this.router.navigateByUrl(`/creaequipoqx/${this.idevent}`)
      });
      
      
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





   clearForm(){}

}

import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';

@Component({
  selector: 'app-editordmedins',
  templateUrl: './editordmedins.component.html',
  
})
export class EditordmedinsComponent implements OnInit {

  formEditOrdmedins: FormGroup
  idordmedins:string
  ordmedins: any
  medins: any
  pmedins: any
  unimedins: any
 
  camposPaciente = [
    {label:'Documento Paciente',nombre:'numdocpac',type:'text'},
    {label:'Consecutivo del paciente',nombre:'conseventpac',type:'text'},
    {label:'Evento',nombre:'idevent',type:'text'},
    {label:'Id Paciente',nombre:'idpac',type:'text'},
    {label:'Primer Nombre',nombre:'primernompac',type:'text'},
    {label:'Segundo Nombre',nombre:'segundonompac',type:'text'},
    {label:'Primer Apellido',nombre:'primerapepac',type:'text'},
    {label:'Segundo Apellido',nombre:'segundoapepac',type:'text'},
    
    
  ]

  camposOrdenMedins = [
    {label:'Orden Medica',nombre:'idordmedins',type:'text'},
    {label:'Medicamento',nombre:'ordmedins_fk',type:'text'},
    {label:'Cantidad',nombre:'cantmedins',type:'text'},
    {label:'Dosis',nombre:'dosismedins',type:'text'},
    {label:'Presentacion',nombre:'pordmedins_fk',type:'text'},
    {label:'Unidades',nombre:'uniordmedins_fk',type:'text'},
    {label:'Observacion',nombre:'obsordmedins',type:'text'},
    

  ]


  constructor(  private router:Router,
                private service:OperacionService,
                private services:ConfigService,
                private fb:FormBuilder,
                private paramsrouter: ActivatedRoute,) {
                  this.idordmedins = this.paramsrouter.snapshot.paramMap.get('idordmedins')
                  console.log('idordmedins del snapshop',this.idordmedins)
                  this.formEditOrdmedins = this.fb.group({
                    numdocpac:[''],
                    conseventpac:[''],
                    idpac:[''],
                    idevent:[''],
                    primernompac:[''],
                    segundonompac:[''],
                    primerapepac:[''],
                    segundoapepac:[''],
                    idordmedins:[''],
                    dosismedins:[''],
                    obsordmedins:[''],
                    cantmedins:[''],
                    pordmedins_fk:[''],
                    uniordmedins_fk:[''],
                    ordmedins_fk:['']                                   
                  })
                   
                 }

  ngOnInit(): void {

    // metodos que se necesitan
    this.uploaddataordmedins()
    this.getMedins()
    this.getPresentacionmedins()
    this.getUnimedins()
    

  }

  getMedins(){
       this.services.getmedins()
       .pipe(
             tap((res) => {
               // Maneja la respuesta exitosa aquí
               console.log('Medicamentos', res);
               this.medins = res;
               
             }),
             catchError((err) => {
               // Maneja el error aquí
               console.error('Error:', err);
               alert('Error ' + err.message);
               throw err; // Re-throw para que el error se propague al suscriptor
             })
           ).subscribe();
       
      }
   
      getPresentacionmedins(){
       this.services.getpmedins()
       .pipe(
         tap((res) => {
           // Maneja la respuesta exitosa aquí
           console.log('Presentacion', res);
           this.pmedins = res;
           
         }),
         catchError((err) => {
           // Maneja el error aquí
           console.error('Error:', err);
           alert('Error ' + err.message);
           throw err; // Re-throw para que el error se propague al suscriptor
         })
       ).subscribe();
      }
   
      getUnimedins(){
       this.services.getunidadmedida()
       .pipe(
         tap((res) => {
           // Maneja la respuesta exitosa aquí
           console.log('Unidades de medida', res);
           this.unimedins = res;
           
         }),
         catchError((err) => {
           // Maneja el error aquí
           console.error('Error:', err);
           alert('Error ' + err.message);
           throw err; // Re-throw para que el error se propague al suscriptor
         })
       ).subscribe();
      }
      // construccion del formulario dinamico para las ordenes de medicamentos o insumos

      uploaddataordmedins(){
        this.service.getordenmedinsXId(this.idordmedins)
        .pipe(
          tap((res) => {
            // Maneja la respuesta exitosa aquí
            console.log('Orden medica por ID', res);
            this.ordmedins = res;
            this.ActualizaOrdenMedins();
            
          }),
          catchError((err) => {
            // Maneja el error aquí
            console.error('Error:', err);
            alert('Error ' + err.message);
            throw err; // Re-throw para que el error se propague al suscriptor
          })
        ).subscribe();

        
      }    
      ActualizaOrdenMedins(){

        console.log('Datos del patchValue',this.ordmedins)
        this.formEditOrdmedins.patchValue({
          idordmedins: this.ordmedins.idordmedins,
          idevent: this.ordmedins.eventordmedins_fk.idevent,
          conseventpac: this.ordmedins.eventordmedins_fk.conseventpac,
          idpac: this.ordmedins.eventordmedins_fk.pacevent_fk.idpac,
          numdocpac: this.ordmedins.eventordmedins_fk.pacevent_fk.numdocpac,
          primernompac: this.ordmedins.eventordmedins_fk.pacevent_fk.primernompac,
          segundonompac: this.ordmedins.eventordmedins_fk.pacevent_fk.segundonompac,
          primerapepac: this.ordmedins.eventordmedins_fk.pacevent_fk.primerapepac,
          segundoapepac: this.ordmedins.eventordmedins_fk.pacevent_fk.segundoapepac,
          cantmedins: this.ordmedins.cantmedins,
          dosismedins: this.ordmedins.dosismedins,
          obsordmedins: this.ordmedins.obsordmedins,
          pordmedins_fk: this.ordmedins.pordmedins_fk.pmedins,
          uniordmedins_fk: this.ordmedins.uniordmedins_fk.unimedi,
          ordmedins_fk: this.ordmedins.ordmedins_fk.medins
          
        })
        


      }

}

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
  eventSeleccionado: any
  idordmedins:string
  idevent: string
  medins: any
  pmedins: any
  unimedins: any
  ordenesmedinsfiltrado: any[] = []; // Medicamentos filtrados según la consulta incremental
  ordenmedicamentoinsumo: any[] = []; // Lista completa de medicamentos

  camposOrdenesMedIns = [
    {label:'Documento Paciente',nombre:'numdocpac',type:'text'},
    {label:'Consecutivo del paciente',nombre:'conseventpac',type:'text'},
    {label:'Evento',nombre:'idevent',type:'text'},
    {label:'Id Paciente',nombre:'idpac',type:'text'},
    {label:'Primer Nombre',nombre:'primernompac',type:'text'},
    {label:'Segundo Nombre',nombre:'segundonompac',type:'text'},
    {label:'Primer Apellido',nombre:'primerapepac',type:'text'},
    {label:'Segundo Apellido',nombre:'segundoapepac',type:'text'}
    
  ]

  constructor(  private router:Router,
                private service:OperacionService,
                private services:ConfigService,
                private fb:FormBuilder,
                private paramsrouter: ActivatedRoute,) {
                  this.idordmedins = this.paramsrouter.snapshot.paramMap.get('idordmedins')

                  this.formEditOrdmedins = this.fb.group({
                    idevent:[''],
                    conseventpac:[''],
                    idpac:[''],
                    numdocpac:[''],
                    primernompac:[''],
                    segundonompac:[''],
                    primerapepac:[''],
                    segundoapepac:[''],
                    cantmedins:[''],
                    dosismedins:[''],
                    obsordmedins:[''],
                    pordmedins_fk:[''],
                    uniordmedins_fk:[''],
                    ordmedins_fk:[''],
                    medins:[''],
                    ordenmedicamentoinsumo: this.fb.array([]) // FormArray para las ordenes médicas dinámicas
                                               
                  })
                   
                 }

  ngOnInit(): void {

    // metodos que se necesitan
    this.uploaddataordmedins()
    this.getMedins()
    this.getPresentacionmedins()
    this.getUnimedins()
    

  }

  uploaddataordmedins(){
    this.service.getordenmedinsXId(this.idordmedins)
    .subscribe((res:any)=>{
      console.log('idordmedins a mostrar en el formulario',res);
      this.eventSeleccionado = res;
      this.formEditOrdmedins.patchValue({
        idevent:this.eventSeleccionado.idordmedins, 
        conseventpac:this.eventSeleccionado.eventordmedins_fk.conseventpac,
        idpac: this.eventSeleccionado.eventordmedins_fk.pacevent_fk.idpac,
        numdocpac:this.eventSeleccionado.eventordmedins_fk.pacevent_fk.numdocpac,
        primernompac:this.eventSeleccionado.eventordmedins_fk.pacevent_fk.primernompac,
        segundonompac:this.eventSeleccionado.eventordmedins_fk.pacevent_fk.segundonompac,
        primerapepac:this.eventSeleccionado.eventordmedins_fk.pacevent_fk.primerapepac,
        segundoapepac:this.eventSeleccionado.eventordmedins_fk.pacevent_fk.segundoapepac,
        cantmedins:this.eventSeleccionado.cantmedins,
        dosismedins:this.eventSeleccionado.dosismedins,
        obsordmedins:this.eventSeleccionado.obsordmedins,
        pordmedins_fk:this.eventSeleccionado.pordmedins_fk,
        uniordmedins_fk :this.eventSeleccionado.uniordmedins_fk,
        ordmedins_fk:this.eventSeleccionado.ordmedins_fk,
        medins:this.eventSeleccionado.medins,
        
      })
      this.addMedicamentosInsumos()
     })
  }

  

   getMedins(){
       this.services.getmedins()
       .pipe(
             tap((res) => {
               // Maneja la respuesta exitosa aquí
               console.log('Medicamentos', res);
               this.ordenmedicamentoinsumo = res;
               
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
   
     get OrdenesMedInsArray(): FormArray {
       return this.formEditOrdmedins.get('ordenmedicamentoinsumo') as FormArray;
     }
   
     addMedicamentosInsumos() {
       const formularioOrdenMedIns = this.formEditOrdmedins.get('ordenmedicamentoinsumo') as FormArray;
       formularioOrdenMedIns.push(this.fb.group({
         
         cantmedins: [this.eventSeleccionado?.cantmedins || '', Validators.required],
         dosismedins: [this.eventSeleccionado?.dosismedins || '', Validators.required],
         obsordmedins: [this.eventSeleccionado?.obsordmedins || '', Validators.required],
         ordmedins_fk: [this.eventSeleccionado?.ordmedins_fk || '', Validators.required],
         pordmedins_fk: [this.eventSeleccionado?.pordmedins_fk ||'', Validators.required],
         uniordmedins_fk:[this.eventSeleccionado?.uniordmedins_fk ||'',Validators.required],
         medins:[this.eventSeleccionado?.medins ||'',Validators.required],
       }));
     }
   
     removeOrdenMedicamentosInsumos(index: number) {
       const formularioOrdenMedIns = this.formEditOrdmedins.get('ordenmedicamentoinsumo') as FormArray;
       formularioOrdenMedIns.removeAt(index);
       delete this.ordenesmedinsfiltrado[index];
     }
     // filtrado de crearodenmedins
     filtrarOrdenMedicamentosInsumos(consulta: string, index:number) {
       if (consulta.trim() !== '') {
         this.ordenesmedinsfiltrado[index] = this.ordenmedicamentoinsumo.filter(medicamentosinsumos =>
           medicamentosinsumos.medins.toLowerCase().includes(consulta.toLowerCase())
         );
       } else {
         this.ordenesmedinsfiltrado[index] = []; // Borra la lista de diagnósticos filtrados si la consulta está vacía
       }
     }
     // seleccionar de creaordenmedins
     seleccionarOrdenMedicamentosInsumos(medicamentoinsumosel: any, index: number) {
       const varmedins = this.formEditOrdmedins.get('ordenmedicamentoinsumo') as FormArray;
       varmedins.at(index).patchValue({
         ordmedins_fk: medicamentoinsumosel.idmedins,
         medins: medicamentoinsumosel.medins // Actualiza el nombre del diagnóstico
         
       });
       this.ordenesmedinsfiltrado[index] = [];
       
     }

  ActualizaOrdenMedins(){

  }

}

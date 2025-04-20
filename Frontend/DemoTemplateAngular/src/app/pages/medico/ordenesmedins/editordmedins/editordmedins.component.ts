import { style } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, of, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editordmedins',
  templateUrl: './editordmedins.component.html',
  
})
export class EditordmedinsComponent implements OnInit {

  formEditOrdmedins: FormGroup
  idordmedins:string
  ordmedins: any
  medins: any[] = []
  pmedins: any[] = []
  unimedins: any[] = []
  camposHabilitados: boolean[] = []
 
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
    
    {label:'Medicamento',nombre:'medins',type:'text',editable:false},    
    {label:'Cantidad',nombre:'cantmedins',type:'text',editable:false},
    {label:'Dosis',nombre:'dosismedins',type:'text',editable:false},
    {label:'Presentacion',nombre:'detpmedins',type:'text',editable:false},   
    {label:'Unidades',nombre:'detunimedi',type:'text',editable:false},    
    {label:'Observacion',nombre:'obsordmedins',type:'text',editable:false},
    
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
                    dosismedins:[{ value: '', disabled: true }, Validators.required],
                    obsordmedins:[{ value: '', disabled: true }, Validators.required],
                    cantmedins:[{ value: '', disabled: true }, Validators.required],
                    pordmedins_fk:[{ value: '', disabled: true }, Validators.required],
                    detpmedins:[''],
                    uniordmedins_fk:[{ value: '', disabled: true }, Validators.required],
                    detunimedi:[''],
                    ordmedins_fk:[{ value: '', disabled: true }, Validators.required],
                    medins:[''],                        
                  })                  
 
                 }

  ngOnInit(): void {

   
    this.getdataordmedins()
    this.camposHabilitados = this.camposOrdenMedins.map(() => false);
    document.addEventListener('keydown', this.handleKeydown.bind(this));
  }

  getdataordmedins(){
    this.service.getordenmedinsXId(this.idordmedins)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Orden medica por ID', res);
        this.ordmedins = res;
        this.cargaOrdenMedins();
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
    
  }

  cargaOrdenMedins(){

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
      pordmedins_fk: this.ordmedins.pordmedins_fk.idpmedins,
      uniordmedins_fk: this.ordmedins.uniordmedins_fk.idunimed,
      ordmedins_fk: this.ordmedins.ordmedins_fk.idmedins,
      detpmedins:this.ordmedins.pordmedins_fk.detpmedins,
      detunimedi:this.ordmedins.uniordmedins_fk.detunimedi,
      medins:this.ordmedins.ordmedins_fk.medins,
      
    })
   }

  handleKeydown(event: KeyboardEvent): void {
    if (event.ctrlKey && event.key.toLowerCase() === 'e') {
      event.preventDefault(); // Previene el comportamiento por defecto del navegador
  
      this.camposOrdenMedins.forEach((campo, index) => {
        const control = this.formEditOrdmedins.get(campo.nombre);
        if (control) {
          control.enable();
          this.camposHabilitados[index] = true;
        }
      });
  
      console.log('Campos de orden médica habilitados por Ctrl + E');
    }
  }
  getMedins(termino: string): void{
    console.log('Buscando medicamentos o insumos:', termino);
    if (termino.length > 2){
      this.services.getmedinsByMedins(termino)
       .pipe(
              tap((res: any[]) => {
                this.medins = res.filter(med =>
                  med.medins.toLowerCase().includes(termino.toLowerCase())
                );
                
              }),
             
             catchError((err) => {
               // Maneja el error aquí
               console.error('Error al buscar medicamento o insumo:', err);
               alert('Error ' + err.message);
               return of([]);
             })
           ).subscribe();
    }else{
      this.medins = []
    }
  }

  seleccionarMedicamento(medicamento: any): void {
    console.log('Medicamento seleccionado:', medicamento);
    this.formEditOrdmedins.get('ordmedins_fk')?.setValue(medicamento.idmedins);
    this.formEditOrdmedins.get('medins')?.setValue(medicamento.medins);
    this.medins = []; // Limpia los resultados después de seleccionar

  }
   
  getPresentacionmedins(termino: string): void{
    console.log('Buscando presentacion:', termino);
        if (termino.length > 2){
          this.services.getmedinsBypmedins(termino)
           .pipe(
                  tap((res: any[]) => {
                    this.pmedins = res.filter(pmed =>
                      pmed.detpmedins.toLowerCase().includes(termino.toLowerCase())
                    );
                  }),                
                  catchError((err) => {
                    // Maneja el error aquí
                    console.error('Error al buscar la presentacion:', err);
                    alert('Error ' + err.message);
                    return of([]);
                  })
               ).subscribe();
        }else{
          this.pmedins = []
        }  
      }

  seleccionarpresentacion(pmedicamento: any): void {
        console.log('Presentacion seleccionada:', pmedicamento.idpmedins);
        this.formEditOrdmedins.get('pordmedins_fk')?.setValue(pmedicamento.idpmedins);
        this.formEditOrdmedins.get('detpmedins')?.setValue(pmedicamento.detpmedins);
        this.pmedins = []; // Limpia los resultados después de seleccionar
    
      }

  getUnimedins(termino: string): void{
        console.log('Buscando unidades:', termino);
            if (termino.length > 2){
              this.services.getmedinsByUmedins(termino)
               .pipe(
                    tap((res: any[]) => {
                      this.unimedins = res.filter(umed =>
                        umed.detunimedi.toLowerCase().includes(termino.toLowerCase())
                      );
                    }),  
               
                
                catchError((err) => {
                  // Maneja el error aquí
                  console.error('Error al buscar las unidades:', err);
                  alert('Error ' + err.message);
                  return of([]);
                })
                   ).subscribe();
            }else{
              this.unimedins = []
            }
               
               
          }   
      seleccionarunidades(unimedins: any, index: number){
        console.log('Unidades seleccionada seleccionada:', unimedins);
        this.formEditOrdmedins.get('uniordmedins_fk')?.setValue(unimedins.idunimed);
        this.formEditOrdmedins.get('detunimedi')?.setValue(unimedins.detunimedi);
        this.unimedins = []; // Limpia los resultados después de seleccionar
    
      }
       
      updateordmedins(){
        
      const rawValues = this.formEditOrdmedins.getRawValue(); // Incluye deshabilitados

      const Structeditordmedins = {
        cantmedins: rawValues.cantmedins,
        dosismedins: rawValues.dosismedins,
        obsordmedins: rawValues.obsordmedins,
        ordmedins_fk: {
          idmedins: rawValues.ordmedins_fk
        },
        pordmedins_fk: {
          idpmedins: rawValues.pordmedins_fk
        },
        uniordmedins_fk: {
          idunimed: rawValues.uniordmedins_fk
        },
        eventordmedins_fk: {
          idevent: rawValues.idevent
        },
        estordmedins_fk: {
          idstatus: 2
        }
      };
      console.log('Estructura para editar orden medica',Structeditordmedins)
      let id = parseInt(this.paramsrouter.snapshot.paramMap.get('idordmedins'))
      console.log('ID de la orden medica',id)
      this.service.editordmedins(id,Structeditordmedins)
      .pipe(
            tap((res) => {
              // Maneja la respuesta exitosa aquí
              console.log('edicion de orden medica', res);
              Swal.fire({
                icon: 'success',
                title: 'Operación exitosa',
                text: res.mensaje // Mostrar el mensaje recibido desde el backend
              }).then (() =>{
                this.router.navigateByUrl(`/eventos`)
               
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
}

import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaordenmedins',
  templateUrl: './creaordenmedins.component.html',
  
})
export class CreaordenmedinsComponent implements OnInit {

  formOrdmedins: FormGroup
  eventSeleccionado: any
  idevent: string
  medins: any
  pmedins: any
  unimedins: any
  ordenesmedinsfiltrado: any[] = []; // Medicamentos filtrados según la consulta incremental
  ordenmedicamentoinsumo: any[] = []; // Lista completa de medicamentos

  // construccion de campos para datos personales del paciente

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
  


  constructor(private router:Router,
              private service:OperacionService,
              private services:ConfigService,
              private fb:FormBuilder,
              private paramsrouter: ActivatedRoute,) { 

        this.idevent=this.paramsrouter.snapshot.paramMap.get('idevent')

        this.formOrdmedins = fb.group({
          idevent:[''],
          conseventpac:[''],
          idpac:[''],
          numdocpac:[''],
          primernompac:[''],
          segundonompac:[''],
          primerapepac:[''],
          segundoapepac:[''],
          cantmedins:['',Validators.required],
          dosismedins:['',Validators.required],
          obsordmedins:['',Validators.required],
          pordmedins_fk:['',Validators.required],
          uniordmedins_fk :['',Validators.required],
          medins:['',],
          ordenmedicamentoinsumo: this.fb.array([]) // FormArray para las ordenes médicas dinámicas
          
         
        })
        this.addMedicamentosInsumos()
      }

  ngOnInit(): void {
    if(this.idevent != null){
      this.getDataEvent(),
      console.log('id del evento que llega',this.idevent)
      this.getMedins();
      this.getPresentacionmedins();
      this.getUnimedins();
      console.log('valores del formulario reactivo',this.formOrdmedins.value);
    
            
      }else{
        this.clearForm();
      }
      
  }

  getDataEvent() {
    console.log(this.idevent,'tipo de dato en getdataevent en creaordenmedins',typeof this.idevent);
    this.service.getEventId(this.idevent).subscribe((res:any)=>{
     console.log('evento a mostrar en el formulario getdataevent de creaordenmedins',res);
     this.eventSeleccionado = res;
     this.formOrdmedins.patchValue({
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
    return this.formOrdmedins.get('ordenmedicamentoinsumo') as FormArray;
  }

  addMedicamentosInsumos() {
    const formularioOrdenMedIns = this.formOrdmedins.get('ordenmedicamentoinsumo') as FormArray;
    formularioOrdenMedIns.push(this.fb.group({
      
      cantmedins: ['', Validators.required],
      dosismedins: ['', Validators.required],
      obsordmedins: ['', Validators.required],
      ordmedins_fk: ['', Validators.required],
      pordmedins_fk: ['', Validators.required],
      uniordmedins_fk:['',Validators.required],
      medins:[''],
    }));
  }

  removeOrdenMedicamentosInsumos(index: number) {
    const formularioOrdenMedIns = this.formOrdmedins.get('ordenmedicamentoinsumo') as FormArray;
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
    const varmedins = this.formOrdmedins.get('ordenmedicamentoinsumo') as FormArray;
    varmedins.at(index).patchValue({
      ordmedins_fk: medicamentoinsumosel.idmedins,
      medins: medicamentoinsumosel.medins // Actualiza el nombre del diagnóstico
      
    });
    this.ordenesmedinsfiltrado[index] = [];
    
  }
  
  creaordenmedins(){
    const OrdenesMedInsArray = this.formOrdmedins.get('ordenmedicamentoinsumo') as FormArray;
        const OrdenmedinsValues = OrdenesMedInsArray.value;
        console.log('El valor de OrdenmedinsValues es: ',OrdenmedinsValues)
        console.log('el idevent del struck:',this.idevent)
        const StructOrdenMedins = OrdenmedinsValues.map((OrdenmedinsValues) => {
          return {
            cantmedins: OrdenmedinsValues.cantmedins,
            dosismedins: OrdenmedinsValues.dosismedins,
            obsordmedins: OrdenmedinsValues.obsordmedins,
            ordmedins_fk:{
              "idmedins": OrdenmedinsValues.ordmedins_fk
            },
            pordmedins_fk:{
              "idpmedins": OrdenmedinsValues.pordmedins_fk
            },
            uniordmedins_fk:{
              "idunimed":OrdenmedinsValues.uniordmedins_fk
            },
            eventordmedins_fk:{
              "idevent": this.idevent
            },
            estordmedins_fk: {
              "idstatus": 1
            }
          };
          
        });
        
    
        StructOrdenMedins.forEach((rmi, index) => {
            
            this.service.addordenmedins(rmi)
                .pipe(
                    tap((res) => {
                        // Maneja la respuesta exitosa aquí
                        console.log('Orden medica', res);
                        Swal.fire({
                            icon: 'success',
                            title: 'Operación exitosa',
                            text: res.mensaje // Mostrar el mensaje recibido desde el backend
                        });
                        if (index === StructOrdenMedins.length - 1) {
                          this.router.navigateByUrl(`/eventos`)
                            console.log('evento',this.idevent)
                        }
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
        });
        console.log('Datos enviados:', this.formOrdmedins.value);
        console.log('estructura enviada',StructOrdenMedins)
  }


 
   // metodo que realiza la limpieza del formulario
   clearForm(){
    this.formOrdmedins.reset();
  }

}

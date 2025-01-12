import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaordenprocexam',
  templateUrl: './creaordenprocexam.component.html',

})
export class CreaordenprocexamComponent implements OnInit {

    FormOrdprocexam: FormGroup
    eventSeleccionado: any
    idevent: string
    tordprocexam_fk:any
    pordprocexam_fk:any
    nompxex:any
    ordenesprocedimientosfiltrado: any[] = []; // Medicamentos filtrados según la consulta incremental
    ordenesprocedimientos: any[] = []; // Lista completa de medicamentos
  
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

                this.FormOrdprocexam = fb.group({
                          idevent:[''],
                          conseventpac:[''],
                          idpac:[''],
                          numdocpac:[''],
                          primernompac:[''],
                          segundonompac:[''],
                          primerapepac:[''],
                          segundoapepac:[''],
                          nompxex:[''],
                          canordprocexam:['',Validators.required],
                          obsordprocexam:['',Validators.required],
                          tordprocexam_fk:['',Validators.required],
                         
                          ordenesprocedimientos: this.fb.array([]) // FormArray para las ordenes médicas dinámicas
                          
                         
                        })
                        this.addOrdenesProcedimientos()
              }

  ngOnInit(): void {
    if(this.idevent != null){

      this.getTipoProcedimiento()
      this.getProcedimientos()
      this.getDataEvent()


    }else{
      this.clearForm();
    }
  }

  getDataEvent() {
    console.log(this.idevent,'tipo de dato en getdataevent en creaordenesprocedimientos',typeof this.idevent);
    this.service.getEventId(this.idevent).subscribe((res:any)=>{
     console.log('evento a mostrar en el formulario getdataevent de creaordenesprocedimientos',res);
     this.eventSeleccionado = res;
     this.FormOrdprocexam.patchValue({
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

  getProcedimientos(){
    this.services.getProcedimientoexamenes()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Tipos de procedimientos', res);
        this.ordenesprocedimientos = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  getTipoProcedimiento(){
    this.services.getTipopx()
    .pipe(
              tap((res) => {
                // Maneja la respuesta exitosa aquí
                console.log('Tipos de procedimientos', res);
                this.tordprocexam_fk = res;
                
              }),
              catchError((err) => {
                // Maneja el error aquí
                console.error('Error:', err);
                alert('Error ' + err.message);
                throw err; // Re-throw para que el error se propague al suscriptor
              })
            ).subscribe();
  }

  get OrdenesProcedimientoArray(): FormArray {
      return this.FormOrdprocexam.get('ordenesprocedimientos') as FormArray;
    }

    addOrdenesProcedimientos() {
      const formularioOrdenProcedimiento = this.FormOrdprocexam.get('ordenesprocedimientos') as FormArray;
      formularioOrdenProcedimiento.push(this.fb.group({
        
        nompxex:[''],
        canordprocexam:['',Validators.required],
        obsordprocexam:['',Validators.required],
        tordprocexam_fk:['',Validators.required],
        pordprocexam_fk:['',Validators.required],
      }));
      
    }  

    removeOrdenProcedimiento(index: number) {
      const formularioOrdenProcedimiento = this.FormOrdprocexam.get('ordenesprocedimientos') as FormArray;
      formularioOrdenProcedimiento.removeAt(index);
      delete this.ordenesprocedimientosfiltrado[index];
    }

    // filtrado de crearodenmedins
  filtrarOrdenProcedimiento(consulta: string, index:number) {
    if (consulta.trim() !== '') {
      this.ordenesprocedimientosfiltrado[index] = this.ordenesprocedimientos.filter(procedimiento =>
        procedimiento.nompxex.toLowerCase().includes(consulta.toLowerCase())
      );
    } else {
      this.ordenesprocedimientosfiltrado[index] = []; // Borra la lista de diagnósticos filtrados si la consulta está vacía
    }
  }

   // seleccionar de creaordenmedins
   seleccionarOrdenProcedimiento(procedimientosel: any, index: number) {
    const varpxex = this.FormOrdprocexam.get('ordenesprocedimientos') as FormArray;
    varpxex.at(index).patchValue({
      pordprocexam_fk: procedimientosel.idpxex,
      nompxex: procedimientosel.nompxex // Actualiza el nombre del diagnóstico
      
    });
    this.ordenesprocedimientosfiltrado[index] = [];
    
  }

  creaordenprocedimiento(){
      const OrdenesProcedimientoArray = this.FormOrdprocexam.get('ordenesprocedimientos') as FormArray;
          const OrdenProcedimientoValues = OrdenesProcedimientoArray.value;
          console.log('El valor de OrdenmedinsValues es: ',OrdenProcedimientoValues)
          console.log('el idevent del struck:',this.idevent)
          const StructOrdenProcedimiento = OrdenProcedimientoValues.map((OrdenProcedimientoValues) => {
            return {
              canordprocexam: OrdenProcedimientoValues.canordprocexam,
              obsordprocexam: OrdenProcedimientoValues.obsordprocexam,
              tordprocexam_fk:{
                "idtproc": OrdenProcedimientoValues.tordprocexam_fk
              },
              pordprocexam_fk:{
                "idpxex": OrdenProcedimientoValues.pordprocexam_fk
              },
              eventordprocexam_fk:{
                "idevent": this.idevent
              },
              estordprocexam_fk: {
                "idstatus": 1
              }
            };
            
          });
          
      
          StructOrdenProcedimiento.forEach((rmi, index) => {
              
              this.service.addordprocexam(rmi)
                  .pipe(
                      tap((res) => {
                          // Maneja la respuesta exitosa aquí
                          console.log('Orden medica', res);
                          Swal.fire({
                              icon: 'success',
                              title: 'Operación exitosa',
                              text: res.mensaje // Mostrar el mensaje recibido desde el backend
                          });
                          if (index === StructOrdenProcedimiento.length - 1) {
                              //this.router.navigate(['eventos']);
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
          
          console.log('Datos enviados:', this.FormOrdprocexam.value);
          console.log('estructura enviada',StructOrdenProcedimiento)
    }

  clearForm(){}

}

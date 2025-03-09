import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-asignaconsentimientopaciente',
  templateUrl: './asignaconsentimientopaciente.component.html',

})
export class AsignaconsentimientopacienteComponent implements OnInit {

  formConsentimiento: FormGroup
  eventSeleccionado: any
  idevent: string
  medicos: any[] = []
  consentimiento:any
  codconinf: any
  username: any
  consinfpacfiltrado: any[] = []
  consinfpac: any[] = []
  consentimientos: any[] = []
  medicosFiltrados: any[]= [];


  camposConsentimientoPaciente = [
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
                  this.formConsentimiento = fb.group({
                    idevent:[''],
                    conseventpac:[''],
                    idpac:[''],
                    numdocpac:[''],
                    primernompac:[''],
                    segundonompac:[''],
                    primerapepac:[''],
                    segundoapepac:[''],
                    medpxpac_fk:[''],
                    codconinf:[''],
                    username:[''],
                    consinfpac: this.fb.array([])  
                  
                  })
                  this.addconsinfpac();
                }  

  ngOnInit(): void {
    this.getDataEvent();
    this.getMedicos();
    this.getConsentimientos();
  }

  getDataEvent() {
    this.service.getEventId(this.idevent).subscribe((res:any)=>{
     
     this.eventSeleccionado = res;
     this.formConsentimiento.patchValue({
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

   getMedicos(){
    this.services.getUsuarios()
    .pipe(
              tap((res) => {
                // Maneja la respuesta exitosa aquí
                
                this.medicos = res;
                
              }),
              catchError((err) => {
                // Maneja el error aquí
                
                alert('Error ' + err.message);
                throw err; // Re-throw para que el error se propague al suscriptor
              })
            ).subscribe();
   }

    getConsentimientos(){
      this.services.getconinf()
      .pipe(
                tap((res) => {
                  // Maneja la respuesta exitosa aquí
                  
                  this.consentimientos = res;
                }),
                catchError((err) => {
                  // Maneja el error aquí
                  
                  alert('Error ' + err.message);
                  throw err; // Re-throw para que el error se propague al suscriptor
                })
              ).subscribe();
    }

    get consinfpacArray(): FormArray {
      return this.formConsentimiento.get('consinfpac') as FormArray;
    }

    addconsinfpac(): void {
      const varconsentimiento = this.formConsentimiento.get('consinfpac') as FormArray;
      varconsentimiento.push(this.fb.group({
        conspxpac_fk: ['', Validators.required],
        medpxpac_fk: ['', Validators.required],
        codconinf: [''],
        username: [''],
      }))
    }
  
    removeconsinfpac(index: number): void {
      const varconsentimiento = this.formConsentimiento.get('consinfpac') as FormArray;
      varconsentimiento.removeAt(index);
    }

    filtrarConsentimientos(consulta: string, index:number) {
      if (consulta.trim() !== '') {
        this.consinfpacfiltrado[index] = this.consentimientos.filter(consentimientosinf =>
          consentimientosinf.codconinf.toLowerCase().includes(consulta.toLowerCase())
        );
      } else {
        this.consinfpacfiltrado[index] = []; // Borra la lista de diagnósticos filtrados si la consulta está vacía
      }
    }

    seleccionarConsentimientos(Consentimientossel: any, index: number) {
      const varconsentimientos= this.formConsentimiento.get('consinfpac') as FormArray;
      varconsentimientos.at(index).patchValue({
        conspxpac_fk: Consentimientossel.idconsinf,
        codconinf: Consentimientossel.codconinf // Actualiza el nombre del diagnóstico
      });
      this.consinfpacfiltrado[index] = [];
    }

    filtrarMedicos(consulta: string, index:number) {
      if (consulta.trim() !== '') {
        this.medicosFiltrados[index] = this.medicos.filter(medicoscx =>
          medicoscx.username?.toLowerCase().includes(consulta.toLowerCase())
          
        );
        
      } else {
        this.medicosFiltrados[index] = []; // Borra la lista de diagnósticos filtrados si la consulta está vacía
      }
    }

    seleccionarMedicos(Medicosel: any, index: number) {
      
      const varMedicos= this.formConsentimiento.get('consinfpac') as FormArray;
      varMedicos.at(index).patchValue({
        medpxpac_fk: Medicosel.iduser,
        username: Medicosel.username 
      });
     
      this.medicosFiltrados[index] = [];
    }


    asignarconsentimientos(){
    const consinfpacArray = this.formConsentimiento.get('consinfpac') as FormArray;
    const ConsinfpacValues = consinfpacArray.value;
    const StructConsinfpac = ConsinfpacValues.map((ConsinfpacValues) => {
          return {
            medpxpac_fk:{
              "iduser": ConsinfpacValues.medpxpac_fk
            },
            conspxpac_fk:{
              "idconsinf": ConsinfpacValues.conspxpac_fk
            },       
            eventconsinfpac_fk:{
              "idevent": this.idevent
            },
            statuconsinfpac_fk: {
              "idstatus": 1
            },
              };
              
            });
            
        
            StructConsinfpac.forEach((rmi, index) => {
                
                this.service.addconsinfpac(rmi)
                    .pipe(
                        tap((res) => {
                            // Maneja la respuesta exitosa aquí
                           
                            Swal.fire({
                                icon: 'success',
                                title: 'Operación exitosa',
                                text: res.mensaje // Mostrar el mensaje recibido desde el backend
                            });
                            if (index === StructConsinfpac.length - 1) {
                              this.router.navigateByUrl(`/eventos`)
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
            
  }

  
}



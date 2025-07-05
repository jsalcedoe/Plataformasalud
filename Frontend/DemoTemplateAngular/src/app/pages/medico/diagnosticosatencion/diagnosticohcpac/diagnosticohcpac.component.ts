import { Component, OnInit,Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Config } from 'protractor';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import { tap, catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-diagnosticohcpac',
  templateUrl: './diagnosticohcpac.component.html',

})
export class DiagnosticohcpacComponent implements OnInit {
   @Input() idevent: any;

    ready:boolean=false
    dxate:any;
    formDxHcpac:FormGroup
    ideventseleccionado:any
    tipdx:any
    idhcpac:number
    diagnosticos: any[] = []; // Lista completa de diagnósticos
    diagnosticosFiltrados: any[] = []; // Diagnósticos filtrados según la consulta incremental

    camposPersonales = [
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
              private fb:FormBuilder,
              private serviciodx:ConfigService,
              private paramsrouter:ActivatedRoute
            ) { this.idevent=this.paramsrouter.snapshot.paramMap.get('idevent')
                this.formDxHcpac=fb.group({
                      idevent:['',[Validators.required]],
                      conseventpac:['',[Validators.required]],
                      idpac:['',[Validators.required]],
                      numdocpac:['',[Validators.required]],
                      primernompac:['',[Validators.required]],
                      segundonompac:['',[Validators.required]],
                      primerapepac:['',[Validators.required]],
                      segundoapepac:['',[Validators.required]],
                      
                      diagnosticos: this.fb.array([]) // FormArray para los diagnósticos dinámicos
                    })
                    this.addDiagnostico();
            }

  ngOnInit(): void {
    
    if(this.idevent != null){
      this.getDataEvent(),
      console.log('id del evento que llega al diagnostico',this.idevent)
      
      }else{
        this.clearForm();
      }
      this.consultaTipoDx();
      this.consultaDx();
  }
  setIdHcPac(id: number) {
  this.idhcpac = id;
}
  getDataEvent(){
    this.service.getEventId(this.idevent).subscribe((res:any)=>{
     console.log('evento a mostrar en el formulario getdataevent de creadiagnosticosatencion',res);
     this.ideventseleccionado = res;   
     
    })
  }
  consultaTipoDx(){
      this.serviciodx.getTipoDx()
      .pipe(
        tap((res) => {
          // Maneja la respuesta exitosa aquí
          console.log('Consulta tipo de diagnostico', res);
          this.tipdx = res;
          
        }),
        catchError((err) => {
          // Maneja el error aquí
          console.error('Error:', err);
          alert('Error ' + err.message);
          throw err; // Re-throw para que el error se propague al suscriptor
        })
      ).subscribe();
  
    }
  consultaDx(){
    this.serviciodx.getDx()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta Diagnosticos', res);
        this.diagnosticos = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; 
      })
    ).subscribe();
  }

  get diagnosticosArray(): FormArray {
      return this.formDxHcpac.get('diagnosticos') as FormArray;
    }
  
    filtrarDiagnosticos(consulta: string, index:number) {
      if (consulta.trim() !== '') {
        this.diagnosticosFiltrados[index] = this.diagnosticos.filter(diagnostico =>
          diagnostico.nomdx.toLowerCase().includes(consulta.toLowerCase())
        );
      } else {
        this.diagnosticosFiltrados[index] = [];
      }
    }
  
    addDiagnostico() {
      const diagnosticos = this.formDxHcpac.get('diagnosticos') as FormArray;
      diagnosticos.push(this.fb.group({
        typdxhcpac_fk: ['', Validators.required],
        dxhcpac_fk: ['', Validators.required],
        nomdx:['']
      }));
    }
  
    removeDiagnostico(index: number) {
      const diagnosticos = this.formDxHcpac.get('diagnosticos') as FormArray;
      diagnosticos.removeAt(index);
      delete this.diagnosticosFiltrados[index]; 
    }
  
    seleccionarDiagnostico(diagnostico: any, index: number) {
      const diagnosticos = this.formDxHcpac.get('diagnosticos') as FormArray;
      diagnosticos.at(index).patchValue({
        dxhcpac_fk: diagnostico.clavedx,
        nomdx: diagnostico.nomdx 
      });
      this.diagnosticosFiltrados[index] = [];
    }

    public creaDxHcpac(){
      const hcpac_fk = this.ideventseleccionado.idhcpac;
      const diagnosticosArray = this.formDxHcpac.get('diagnosticos') as FormArray;
      const diagnosticosValues = diagnosticosArray.value;

      const diagnosticosStruct = diagnosticosValues.map((diagnostico: any) => {
            const dxhcpac_fk = Number(diagnostico.dxhcpac_fk); // Asegúrate de que este campo es un ID y no un nombre
            const typdxhcpac_fk = Number(diagnostico.typdxhcpac_fk);
            console.log('Valores convertidos:', { dxhcpac_fk, typdxhcpac_fk });
            return {
            dxhcpac_fk: {
                "clavedx": dxhcpac_fk
            },
            hcpac_fk:{
                idhcpac: this.idhcpac
            },
            typdxhcpac_fk: {
                "idtypdx": typdxhcpac_fk
            },
            estdxhcpac: {
                "idstatus": 1
            },
            
            
            
        };
    });
    diagnosticosStruct.forEach((dx, index) => {
            console.log(`Diagnóstico ${index + 1}:`, dx);
            this.service.addDxHcPac(dx)
                .pipe(
                    tap((res) => {
                        // Maneja la respuesta exitosa aquí
                        console.log('DiagnosticoAtención', res);
                        Swal.fire({
                            icon: 'success',
                            title: 'Operación exitosa',
                            text: res.mensaje // Mostrar el mensaje recibido desde el backend
                        });
                        if (index === diagnosticosStruct.length - 1) {
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
    }

  clearForm(){}

}

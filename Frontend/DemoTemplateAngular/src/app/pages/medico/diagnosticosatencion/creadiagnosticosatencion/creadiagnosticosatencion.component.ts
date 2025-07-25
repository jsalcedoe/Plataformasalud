import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creadiagnosticosatencion',
  templateUrl: './creadiagnosticosatencion.component.html',
})
export class CreadiagnosticosatencionComponent implements OnInit {

//@Input() idevent: any;
 

  ready:boolean=false
  dxate:any;
  idevent:string
  formDxAtencion:FormGroup
  ideventseleccionado:any
  tipdx:any
  tipnot:any
  diagnosticos: any[] = []; // Lista completa de diagnósticos
  diagnosticosFiltrados: any[] = []; // Diagnósticos filtrados según la consulta incremental
  

  constructor(
    private router:Router,
    private service:OperacionService,
    private fb:FormBuilder,
    private servicioDx:ConfigService,
    private paramsrouter: ActivatedRoute
    ) {
     this.idevent=this.paramsrouter.snapshot.paramMap.get('idevent')
      
      console.log('el idevent que llega en el snapshop de creadiagnosticosatencion',this.idevent,'tipo de dato en snapshop creadxate', typeof this.idevent)
      this.formDxAtencion=fb.group({
        numdocpac:['',[Validators.required]],
        conseventpac:['',[Validators.required]],    
        primernompac:['',[Validators.required]],
        segundonompac:['',[Validators.required]],
        primerapepac:['',[Validators.required]],
        segundoapepac:['',[Validators.required]],
        idevent:['',[Validators.required]],
        diagnosticos: this.fb.array([]) // FormArray para los diagnósticos dinámicos
      })
      this.addDiagnostico(); // Asegúrate de agregar esto en el constructor para inicializar un diagnóstico vacío.
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

  getDataEvent() {
    console.log(this.idevent,'tipo de dato en getdataevent en creadiagnosticosatencion',typeof this.idevent);
    this.service.getEventId(this.idevent).subscribe((res:any)=>{
     console.log('evento a mostrar en el formulario getdataevent de creadiagnosticosatencion',res);
     this.ideventseleccionado = res;
     /*this.formHC.patchValue({
       idevent:this.eventSeleccionado.idevent, 
       conseventpac:this.eventSeleccionado.conseventpac,
       idpac: this.eventSeleccionado.pacevent_fk.idpac,
       numdocpac:this.eventSeleccionado.pacevent_fk.numdocpac,
       primernompac:this.eventSeleccionado.pacevent_fk.primernompac,
       segundonompac:this.eventSeleccionado.pacevent_fk.segundonompac,
       primerapepac:this.eventSeleccionado.pacevent_fk.primerapepac,
       segundoapepac:this.eventSeleccionado.pacevent_fk.segundoapepac,
     })*/
     
    })
    
   }

  get diagnosticosArray(): FormArray {
    return this.formDxAtencion.get('diagnosticos') as FormArray;
  }

  filtrarDiagnosticos(consulta: string, index:number) {
    if (consulta.trim() !== '') {
      this.diagnosticosFiltrados[index] = this.diagnosticos.filter(diagnostico =>
        diagnostico.nomdx.toLowerCase().includes(consulta.toLowerCase())
      );
    } else {
      this.diagnosticosFiltrados[index] = []; // Borra la lista de diagnósticos filtrados si la consulta está vacía
    }
  }

  addDiagnostico() {
    const diagnosticos = this.formDxAtencion.get('diagnosticos') as FormArray;
    diagnosticos.push(this.fb.group({
      typdxatehcpac_fk: ['', Validators.required],
      origdx_fk: ['', Validators.required],
      dxatehcpac_fk: ['', Validators.required],
      nomdx:['']
    }));
  }

  removeDiagnostico(index: number) {
    const diagnosticos = this.formDxAtencion.get('diagnosticos') as FormArray;
    diagnosticos.removeAt(index);
    delete this.diagnosticosFiltrados[index]; // Remove filtered diagnostics for this index
  }

  seleccionarDiagnostico(diagnostico: any, index: number) {
    const diagnosticos = this.formDxAtencion.get('diagnosticos') as FormArray;
    diagnosticos.at(index).patchValue({
      dxatehcpac_fk: diagnostico.clavedx,
      nomdx: diagnostico.nomdx // Actualiza el nombre del diagnóstico
    });
    this.diagnosticosFiltrados[index] = [];
  }
public creaDxAtencion() {
    console.log('Valores del formulario de diagnostico:', this.ideventseleccionado);

    // Extrayendo los valores del formulario principalx
    const eventdxate_fk = Number(this.ideventseleccionado.idevent); // Asegúrate de que 'idevent' exista en el JSON
    console.log('Evento:', eventdxate_fk);

    
    /*const eventdxate_fk = this.ideventseleccionado.get('idevent')?.value;
    console.log('evento', eventdxate_fk);*/

    // Extrayendo y construyendo los valores de los diagnósticos
    const diagnosticosArray = this.formDxAtencion.get('diagnosticos') as FormArray;
    const diagnosticosValues = diagnosticosArray.value;

    const diagnosticosStruct = diagnosticosValues.map((diagnostico: any) => {
    const origdx_fk = Number(diagnostico.origdx_fk);
    const dxatehcpac_fk = Number(diagnostico.dxatehcpac_fk); // Asegúrate de que este campo es un ID y no un nombre
    const typdxatehcpac_fk = Number(diagnostico.typdxatehcpac_fk);

    console.log('Valores convertidos:', { origdx_fk, dxatehcpac_fk, typdxatehcpac_fk });


        return {
            origdx_fk: {
                "idtypnot": origdx_fk
            },
            estdxatehcpac: {
                "idstatus": 1
            },
            dxatehcpac_fk: {
                "clavedx": dxatehcpac_fk
            },
            typdxatehcpac_fk: {
                "idtypdx": typdxatehcpac_fk
            },
            eventdxate_fk: {
                "idevent": eventdxate_fk
            }
        };
    });

    diagnosticosStruct.forEach((dx, index) => {
        console.log(`Diagnóstico ${index + 1}:`, dx);
        this.service.addDxAtencion(dx)
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

    // Elimina la llamada duplicada a addDxAtencion aquí
}
  


  consultaTipoDx(){
    this.servicioDx.getTipoDx()
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

  consultaTipoNota(){
    this.servicioDx.getTiponota()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta tipo de nota', res);
        this.tipnot = res;
        
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
    this.servicioDx.getDx()
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
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  

  clearForm(){
    // Método para limpiar el formulario si es necesario
  }

}

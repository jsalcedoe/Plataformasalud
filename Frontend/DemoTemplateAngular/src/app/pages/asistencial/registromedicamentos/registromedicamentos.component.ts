import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registromedicamentos',
  templateUrl: './registromedicamentos.component.html',
})
export class RegistromedicamentosComponent implements OnInit {

  formregmedins:FormGroup
  idevent:string
  ideventseleccionado:any
  eventSeleccionado:any
  viadm:any
  medicamentoinsumo:any[] = []
  medinsfiltrado:any[] = []

  constructor(private router:Router,
    private service:ConfigService,
    private servicioop:OperacionService,
    private paramsrouter: ActivatedRoute,
    private fb:FormBuilder
  ) { 
    this.idevent=this.paramsrouter.snapshot.paramMap.get('idevent')

    this.formregmedins = fb.group({

      numdocpac:['',[Validators.required]],
      idevent:['',[Validators.required]],
      conseventpac:['',[Validators.required]],    
      primernompac:['',[Validators.required]],
      segundonompac:['',[Validators.required]],
      primerapepac:['',[Validators.required]],
      segundoapepac:['',[Validators.required]],
      regdosismedins:['',[Validators.required]],
      obsregmedins:['',[Validators.required]],
      dateregpmedins:['',[Validators.required]],
      horaaplmedins:['',[Validators.required]],
      viadm_fk:['',[]],
      medicamentoinsumo:this.fb.array([]) //form array para los medicamentos
    })
    this.addMedicamentosInsumos()
  }

  ngOnInit(): void {
    this.getDataEvent(),
    this.getmedins(),
    this.getviadm()
  }

  getDataEvent() {
    console.log(this.idevent,'tipo de dato en getdataevent',typeof this.idevent);
    this.servicioop.getEventId(this.idevent).subscribe((res:any)=>{
     console.log('evento a mostrar en el formulario getdataevent de registromedicamentos',res);
     this.ideventseleccionado = res;
     console.log('el valor ideventSeleccionado es:',this.ideventseleccionado)

     this.formregmedins.patchValue({
       
       idevent:this.ideventseleccionado.idevent, 
       conseventpac:this.ideventseleccionado.conseventpac,
       numdocpac:this.ideventseleccionado.pacevent_fk.numdocpac,
       primernompac:this.ideventseleccionado.pacevent_fk.primernompac,
       segundonompac:this.ideventseleccionado.pacevent_fk.segundonompac,
       primerapepac:this.ideventseleccionado.pacevent_fk.primerapepac,
       segundoapepac:this.ideventseleccionado.pacevent_fk.segundoapepac,
     })
     
    })
  }

  getmedins(){
    this.service.getmedins()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta medins', res);
        this.medicamentoinsumo = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  getviadm(){
    this.service.getviadm()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta viadm', res);
        this.viadm = res;
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  get medinsArray(): FormArray {
    return this.formregmedins.get('medicamentoinsumo') as FormArray;
  }

  filtrarMedicamentosInsumos(consulta: string, index:number) {
    if (consulta.trim() !== '') {
      this.medinsfiltrado[index] = this.medicamentoinsumo.filter(medicamentosinsumos =>
        medicamentosinsumos.medins.toLowerCase().includes(consulta.toLowerCase())
      );
    } else {
      this.medinsfiltrado[index] = []; // Borra la lista de diagnósticos filtrados si la consulta está vacía
    }
  }

  addMedicamentosInsumos() {
    const varmedins = this.formregmedins.get('medicamentoinsumo') as FormArray;
    varmedins.push(this.fb.group({
      
      regdosismedins: ['', Validators.required],
      obsregmedins: ['', Validators.required],
      dateregpmedins: ['', Validators.required],
      horaaplmedins: ['', Validators.required],
      medinsreg_fk: ['', Validators.required],
      medins:[''],
      viadm_fk: ['', Validators.required],

    }));
  }

  removeMedicamentosInsumos(index: number) {
    const varmedins = this.formregmedins.get('medicamentoinsumo') as FormArray;
    varmedins.removeAt(index);
    delete this.medinsfiltrado[index]; // Remove filtered diagnostics for this index
  }

  seleccionarMedicamentosInsumos(medicamentoinsumosel: any, index: number) {
    const varmedins = this.formregmedins.get('medicamentoinsumo') as FormArray;
    varmedins.at(index).patchValue({
      medinsreg_fk: medicamentoinsumosel.idmedins,
      medins: medicamentoinsumosel.medins // Actualiza el nombre del diagnóstico
    });
    this.medinsfiltrado[index] = [];
  }

  crearegistromedins(){
    
    // Extrayendo los valores del formulario principalx
    //const eventoregistromedins_fk = Number(this.ideventseleccionado.idevent); // Asegúrate de que 'idevent' exista en el JSON
    //console.log('Evento:', eventoregistromedins_fk);

    // Extrayendo y construyendo los valores de los diagnósticos
    const medinsArray = this.formregmedins.get('medicamentoinsumo') as FormArray;
    const medinsValues = medinsArray.value;
    console.log('el idevent del struck:',this.idevent)
    const Structregistromedins = medinsValues.map((medinsValue) => {
      return {
        regdosismedins: medinsValue.regdosismedins,
        obsregmedins: medinsValue.obsregmedins,
        dateregpmedins: medinsValue.dateregpmedins,
        horaaplmedins: medinsValue.horaaplmedins,
        medinsreg_fk:{
          "idmedins": medinsValue.medinsreg_fk
        },
        viadm_fk:{
          "idviadm": medinsValue.viadm_fk
        },
        eventoregistromedins_fk:{
          "idevent": this.idevent
        },
        estregmedins_fk: {
          "idstatus": 1
        }
      };
      
    });
    

    Structregistromedins.forEach((rmi, index) => {
        
        this.servicioop.addregistromedicamentos(rmi)
            .pipe(
                tap((res) => {
                    // Maneja la respuesta exitosa aquí
                    console.log('registro medins', res);
                    Swal.fire({
                        icon: 'success',
                        title: 'Operación exitosa',
                        text: res.mensaje // Mostrar el mensaje recibido desde el backend
                    });
                    if (index === Structregistromedins.length - 1) {
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

}  



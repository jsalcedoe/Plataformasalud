import { Component, OnInit, ViewChild  } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';
import { CreadiagnosticosatencionComponent } from '../../diagnosticosatencion/creadiagnosticosatencion/creadiagnosticosatencion.component';


@Component({
  selector: 'app-creadesqxcompleta',
  templateUrl: './creadesqxcompleta.component.html',
})
export class CreadesqxcompletaComponent implements OnInit {

  @ViewChild('diagnosticosComp') diagnosticosComponent: CreadiagnosticosatencionComponent;
  procedimientoForm: FormGroup;
  tiempoqx: any
  conductas: any
  tipohx: any
  tipopx: any
  tipoanest:any
  idevent:string
  sexopac:string
  eventSeleccionado: any
  user:any []=[];
  filterUser: any [][]=[];
  procedimiento:any[] = [];
  filteredProcedimientos: any[][] = []; // Array de arrays para almacenar los resultados filtrados por cada input

  constructor(private fb:FormBuilder,
              private router:Router,
              private service:OperacionService,
              private services:ConfigService,
              private paramsrouter: ActivatedRoute,
            ) { 
              this.idevent=this.paramsrouter.snapshot.paramMap.get('idevent')
              console.log('idevent del snapshop',this.idevent)

            this.procedimientoForm = this.fb.group({

                eventpxqx_fk:['',[Validators.required]],
                conseventpac:['',[Validators.required]],
                idpac:['',[Validators.required]],
                numdocpac:['',[Validators.required]],
                primernompac:['',[Validators.required]],
                segundonompac:['',[Validators.required]],
                primerapepac:['',[Validators.required]],
                segundoapepac:['',[Validators.required]],
              //-------------------------------------
                fechaprocqx: ['', Validators.required],
                horainicioprocqx: ['', Validators.required],
                horafinprocqx: ['', Validators.required],
                descqx: ['', Validators.required],
                matprot: [''],
                muespato: [''],
                complicqx: [''],
                hallaqx: [''],
                conducqx_fk: [''],
                anestesia_fk:[''],
              //-----------------------------------------
              procedimientos: this.fb.array([]),
              equipoQx: this.fb.array([])
        });
        this.addProcedimiento();
        this.addUser();
  }

  ngOnInit(): void {
    if(this.idevent != null){
      this.getConductas(),
      this.gettipoanestesia(),
      this.getDataEvent(),
      this.consultapxexam(),
      this.consultauser()
    }else{
      this.clearForm();
    }
    
  }
  getDataEvent() {
    console.log(this.idevent);
    this.service.getEventId(this.idevent).subscribe((res:any)=>{
     console.log('evento a mostrar en el formulario',res);
     this.eventSeleccionado = res;
     this.procedimientoForm.patchValue({
       eventpxqx_fk:this.eventSeleccionado.idevent, 
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
    let horainicioprocqx = this.procedimientoForm.value.horainicioprocqx;
    let horafinprocqx = this.procedimientoForm.value.horafinprocqx;

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

consultapxexam() {
  this.services.getProcedimientoexamenes()
    .pipe(
      tap((res: any[]) => {
        this.procedimiento = res;
        this.filteredProcedimientos = this.procqxArray.controls.map(() => []);
        console.log('Procedimientos cargados:', this.procedimiento); // Verifica los datos aquí
      }),
      catchError((err) => {
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err;
      })
    ).subscribe();
}

  // Obtener FormArray de procedimientos
  get procqxArray() {
    return this.procedimientoForm.get('procedimientos') as FormArray;
  }

  // Agregar un nuevo procedimiento
  addProcedimiento() {
    const procFormGroup = this.fb.group({
      nombreProcedimiento: ['', Validators.required],
      idpxex: ['', Validators.required]
    });
    this.procqxArray.push(procFormGroup);
  }
   // Eliminar un procedimiento
   removeProcedimiento(index: number) {
    this.procqxArray.removeAt(index);
  }
    // filtrar los procedimientos
    filterProcedimientos(event: any, index: number) {
      const query = event.target.value.toLowerCase();
      console.log('Query:', query); // Verifica que el valor del input es correcto
      this.filteredProcedimientos[index] = this.procedimiento.filter(proc => 
        proc.nompxex.toLowerCase().includes(query)
      );
      console.log('Resultados filtrados:', this.filteredProcedimientos[index]); // Verifica los resultados filtrados
    }

    selectProcedimiento(item: any, index: number) {

      if ((item.sexopxex === 'MASCULINO' && this.sexopac === 'FEMENINO') ||
          (item.sexopxex === 'FEMENINO' && this.sexopac === 'MASCULINO')) {
        alert('Error: El procedimiento no aplica al sexo del paciente.');
        return; // Detiene la selección si la validación falla
      }
      const formGroup = this.procqxArray.at(index);
      formGroup.patchValue({
        nombreProcedimiento: item.nompxex,
        idpxex: item.idpxex
      });
      // Limpia la lista de filtrados una vez seleccionado el procedimiento
      this.filteredProcedimientos[index] = [];
    }


    consultauser(){
      this.services.getUsuarios()
      .pipe(
        tap((res: any[]) => {
          this.user = res;
          this.filterUser = this.userArray.controls.map(() => []);
          console.log('Usuarios cargados:', this.user); // Verifica los datos aquí
        }),
        catchError((err) => {
          console.error('Error:', err);
          alert('Error ' + err.message);
          throw err;
        })
      ).subscribe();
    }

  // Obtener FormArray de equipoQx
  get userArray() {
    return this.procedimientoForm.get('equipoQx') as FormArray;
  }

  // Agregar un nuevo miembro al equipo quirúrgico
  addUser() {
    const userFormGroup = this.fb.group({
      iduser: ['', Validators.required],
      firstname:['',Validators.required],
      lastname:['',Validators.required],
    });
    this.userArray.push(userFormGroup);
  }

  // Eliminar un miembro del equipo quirúrgico
  removeUser(index: number) {
    this.userArray.removeAt(index);
  }

  // Filtra los miembros del equipo quirurgico
  filterUsuarios(idqx: any, index: number) {
    const query = idqx.target.value.toLowerCase();
    console.log('Query:', query); // Verifica que el valor del input es correcto
    this.filterUser[index] = this.user.filter(use => 
      use.firstname.toLowerCase().includes(query)
    );
    console.log('Resultados filtrados:', this.filterUser[index]); // Verifica los resultados filtrados
  }
  
  
  selectUsuarios(item: any, index: number) {
    const formGroup = this.userArray.at(index);
  
    // Pone todos los valores en sus campos correspondientes
      formGroup.patchValue({
      iduser: item.iduser,
      firstname: item.firstname,
      lastname: item.lastname,
      carguser_fk: item.carguser_fk.detcarguser
    });
  
    // Actualiza el campo de búsqueda con firstname, lastname y detcarguser
    formGroup.patchValue({
      firstname: `${item.firstname} ${item.lastname} - ${item.carguser_fk.detcarguser}`
    });
  
    // Limpia la lista de resultados filtrados
    this.filterUser[index] = [];
  }

  almacenarpxuser() {
    // Convertir el array de procedimientos y equipo quirúrgico a la estructura esperada
    const procedimientosArray = this.procedimientoForm.value.procedimientos.map(proc => ({
        procqx_fk: { idpxex: proc.idpxex }
    }));
    const equipoQxArray = this.procedimientoForm.value.equipoQx.map(user => ({
        inteqqx: { iduser: user.iduser }
    }));

    // Construir el objeto DescripcionQuirurgica para el DTO
    const descripcionQuirurgica = {
        fechaprocqx: this.procedimientoForm.value.fechaprocqx,
        horainicioprocqx: this.procedimientoForm.value.horainicioprocqx,
        horafinprocqx: this.procedimientoForm.value.horafinprocqx,
        descqx: this.procedimientoForm.value.descqx,
        matprot: this.procedimientoForm.value.matprot,
        muespato: this.procedimientoForm.value.muespato,
        complicqx: this.procedimientoForm.value.complicqx,
        hallaqx: this.procedimientoForm.value.hallaqx,
        conducqx_fk: {
            idcondpac: this.procedimientoForm.value.conducqx_fk
        },
        eventpxqx_fk: {
            idevent: this.procedimientoForm.value.eventpxqx_fk
        },
        anestesia_fk: {
            idtipanest: this.procedimientoForm.value.anestesia_fk
        },
        estpxqx_fk: {
            idstatus: 1
        }
    };

    // Construir el DTO completo que cumpla con la estructura requerida
    const structjsonDto = {
        descripcionQuirurgica: descripcionQuirurgica,
        procedimientos: procedimientosArray,
        equipoQx: equipoQxArray
    };

    // Enviar el DTO al servicio
    this.service.adddesqxcompleta([structjsonDto])
        .pipe(
            tap((res) => {
                console.log('Descripción quirúrgica almacenada', res);
                Swal.fire({
                    icon: 'success',
                    title: 'Operación exitosa',
                    text: res.mensaje
                })
            }),
            catchError((err) => {
                console.error('Error:', err);
                Swal.fire({
                    icon: 'error',
                    title: 'Error en la operación',
                    text: err.message
                })
                throw err;
            })
        ).subscribe();
}

guardarTodo(){
  this.almacenarpxuser(),
  this.diagnosticosComponent.creaDxAtencion()
  this.router.navigateByUrl(`/eventos`)
}




  clearForm(){}


}

import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-creapxqx',
  templateUrl: './creapxqx.component.html',
  
})
export class CreapxqxComponent implements OnInit {

  constructor() {} 

  ngOnInit():void {
    
  }
    /*getDataDesQx(){
    console.log('idqx que viene del snapshop',this.idqx)
    this.service.getdesqxId(this.idqx).subscribe((res:any)=>{
      console.log('descripcion  a mostrar en el formulario',res);
      this.idqxSeleccionado = res;
      this.sexopac = this.idqxSeleccionado.eventpxqx_fk.pacevent_fk.sexopac; // Obtén el sexo del paciente
      this.formpxqx.patchValue({
        numdocpac:this.idqxSeleccionado.eventpxqx_fk.pacevent_fk.numdocpac,
        idevent:this.idqxSeleccionado.eventpxqx_fk.idevent,
        conseventpac:this.idqxSeleccionado.eventpxqx_fk.conseventpac,
        idqx: this.idqxSeleccionado.idqx,
        primernompac:this.idqxSeleccionado.eventpxqx_fk.pacevent_fk.primernompac,
        segundonompac:this.idqxSeleccionado.eventpxqx_fk.pacevent_fk.segundonompac,
        primerapepac:this.idqxSeleccionado.eventpxqx_fk.pacevent_fk.primerapepac,
        segundoapepac:this.idqxSeleccionado.eventpxqx_fk.pacevent_fk.segundoapepac,
        
      })
      
     })

  }
  consultapxexam() {
    this.services.getProcedimientoexamenes()
      .pipe(
        tap((res: any[]) => {
          this.procedimientos = res;
          this.filteredProcedimientos = this.procqxArray.controls.map(() => []);
          console.log('Procedimientos cargados:', this.procedimientos); // Verifica los datos aquí
        }),
        catchError((err) => {
          console.error('Error:', err);
          alert('Error ' + err.message);
          throw err;
        })
      ).subscribe();
  }
  

  addProcedimiento() {
    const procFormGroup = this.fb.group({
      nombreProcedimiento: ['', Validators.required],
      idpxex: ['', Validators.required]
    });
    this.procqxArray.push(procFormGroup);
  }
  
  removeProcedimiento(index: number) {
    this.procqxArray.removeAt(index);
  }
  
  get procqxArray() {
    return this.formpxqx.get('procqx_fk') as FormArray;
  }

  filterProcedimientos(event: any, index: number) {
    const query = event.target.value.toLowerCase();
    console.log('Query:', query); // Verifica que el valor del input es correcto
    this.filteredProcedimientos[index] = this.procedimientos.filter(proc => 
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

  creapxqx() {
    const idqx = this.formpxqx.value.idqx;
    const procedimientos = this.formpxqx.value.procqx_fk;
    
    procedimientos.forEach(proc => {
      let structpxqx = {
        descqx_fk: {
          idqx: idqx
        },
        procqx_fk: {
          idpxex: proc.idpxex
        },
        estadopxdesqx_fk:{
          idstatus:1
        }
      };
  
      this.service.addprocedimientosdescripcion(structpxqx)
      .pipe(
        tap((res) => {
          console.log('Procedimiento guardado:', res);
          // Puedes manejar la respuesta de cada procedimiento aquí si es necesario
        }),
        catchError((err) => {
          console.error('Error al guardar procedimiento:', err);
          Swal.fire({
            icon: 'error',
            title: 'Error en la operación',
            text: err.message
          });
          throw err;
        })
      ).subscribe();
    });
  
    // Al final de todas las solicitudes, puedes mostrar un mensaje o redirigir si todo salió bien.
    Swal.fire({
      icon: 'success',
      title: 'Operación exitosa',
      text: 'Todos los procedimientos han sido guardados correctamente.'
    }).then(() => {
      //this.router.navigateByUrl(`/creaequipoqx/${this.formpxqx.value.idqx}`);
      console.log('El idqx que pasa a creacion de equipo es:',this.formpxqx.value.idqx)
    });
  }
  
  

 
 

 */


}

  
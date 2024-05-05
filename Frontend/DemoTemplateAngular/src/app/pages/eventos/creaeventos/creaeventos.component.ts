import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, catchError, map, tap } from 'rxjs';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaeventos',
  templateUrl: './creaeventos.component.html',
  
})
export class CreaeventosComponent implements OnInit {
  formEvent:FormGroup
  pacienteSeleccionado: any
  paciente:any
  ultimoEvento:number
  idpaciente: string
  //ready:boolean=false
 

  constructor(private service:OperacionService,
              private router:Router,
              private fb:FormBuilder,
              private paramsrouter: ActivatedRoute,
  ) { 
    this.idpaciente=this.paramsrouter.snapshot.paramMap.get('idpac')
    
    this.formEvent = fb.group({
    conseventpac:['',[Validators.required]],
    detevent:['',[Validators.required]],
    idpac:['',[Validators.required]],
    numdocpac: ['', [Validators.required]],
    primernompac: ['', [Validators.required]],
    segundonompac: ['', [Validators.nullValidator]],
    primerapepac: ['', [Validators.required]],
    segundoapepac: ['', [Validators.nullValidator]]
  })}

  ngOnInit(): void {

    // Limpia el formulario al inicializar el componente
    this.clearForm();

    // Obtener el ID del paciente de los parámetros de la ruta
    this.idpaciente = this.paramsrouter.snapshot.paramMap.get('idpac');
    console.log('id del paciente que llega',this.idpaciente)

    this.getDataPaciente();
    
    this.obtenerUltimoEvento()
  }
  getDataPaciente() {
   console.log(this.idpaciente);
   this.service.getPacientesId(this.idpaciente).subscribe((res:any)=>{
    console.log('paciente a mostrar en el formulario',res);
    this.pacienteSeleccionado = res;
    this.formEvent.patchValue({
      idpac: this.pacienteSeleccionado.idpac,
      numdocpac:this.pacienteSeleccionado.numdocpac,
      primernompac:this.pacienteSeleccionado.primernompac,
      segundonompac:this.pacienteSeleccionado.segundonompac,
      primerapepac:this.pacienteSeleccionado.primerapepac,
      segundoapepac:this.pacienteSeleccionado.segundoapepac,
    })
    
   })
   
  }
    
  obtenerUltimoEvento() {
    console.log('entramos en el metodo obtenerUltimoEvento');

    const numdopac = this.idpaciente
    console.log('id del paciente que se selecciono',numdopac)
  
    // Obtenemos los eventos del servicio
    this.service.getEventos().subscribe((response) => {
      console.log('valor response', response);
  
      // Filtra los eventos que corresponden al paciente seleccionado
      const eventosPaciente = response.filter((evento) => evento.pacevent_fk.idpac == numdopac);
      console.log('Eventos del paciente:', eventosPaciente);
  
      // Verificar si hay eventos en el array filtrado
      if (eventosPaciente && eventosPaciente.length > 0) {
        // Obtener el último evento del array filtrado
        const ultimoEvento = eventosPaciente[eventosPaciente.length - 1];
  
        // Verificar si el último evento tiene la propiedad conseventpac y es un número
        if (ultimoEvento && typeof ultimoEvento.conseventpac === 'number') {
          this.ultimoEvento = ultimoEvento.conseventpac;
          console.log('Último evento:', this.ultimoEvento);
  
          // Lógica para asignar el siguiente número de evento
          this.formEvent.patchValue({
            conseventpac: this.ultimoEvento + 1
          });
        } else {
          console.error('Formato de evento inválido:', ultimoEvento);
        }
      } else {
        // No hay eventos en el array, asignar 1 como primer evento
        this.formEvent.patchValue({
          conseventpac: 1
        });
      }
    });
  }
  creaEvento() {
    // Obtén el estado del paciente seleccionado
    const estadoPaciente = this.pacienteSeleccionado.estpac_fk.idstatus;
      
    // Validación de estado del paciente
    if (estadoPaciente !== 1 && estadoPaciente !== 2) {
      Swal.fire({
        icon: 'error',
        title: 'Operación fallida',
        text: 'El paciente no está en un estado válido para crear un evento.'
      });
      return;
    }

    // Verificar si el paciente ya tiene un evento activo
    this.verificarEventoActivo().subscribe(tieneEventoActivo => {
      if (tieneEventoActivo) {
        Swal.fire({
          icon: 'error',
          title: 'Operación fallida',
          text: 'El paciente ya tiene un evento activo asignado.'
        });
        return;
      }

      // Si el estado del paciente es válido y no tiene un evento activo, procede a crear el nuevo evento
      let structEvento = {
        conseventpac: this.formEvent.value.conseventpac,
        detevent: this.formEvent.value.detevent,
        pacevent_fk: {
          "idpac": this.pacienteSeleccionado.idpac
        },
        estevent_fk: {
          "idstatus": 1 // Estado activo para el evento
        }
      };

      console.log('el paciente a guardar es: ', structEvento);
      this.service.addEventos(structEvento)
        .pipe(
          tap((res) => {
            console.log('Eventos', res);
            Swal.fire({
              icon: 'success',
              title: 'Operación exitosa',
              text: res.mensaje
            });
            return this.router.navigate(['eventos']);
          }),
          catchError((err) => {
            console.error('Error:', err);
            alert('Error ' + err.message);
            throw err;
          })
        ).subscribe();
    });
}

/*verificarEventoActivo(): Observable<boolean> {
  return this.service.getEventos().pipe(
    map(eventos => {
      const eventoActivo = eventos.find(evento => evento.estevent_fk.idstatus === 1);
      return eventoActivo !== undefined;
    })
  );
}*/

verificarEventoActivo(): Observable<boolean> {
  return this.service.getEventos().pipe(
    map(eventos => {
      const eventoActivo = eventos.find(evento => {
        return evento.pacevent_fk.idpac === this.pacienteSeleccionado.idpac && evento.estevent_fk.idstatus === 1;
      });
      return eventoActivo !== undefined;
    })
  );
}

// Método para limpiar el formulario
clearForm() {
  this.formEvent.reset();
}

}


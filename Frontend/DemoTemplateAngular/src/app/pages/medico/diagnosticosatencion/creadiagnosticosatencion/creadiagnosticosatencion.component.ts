import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Config } from 'protractor';
import { catchError, map, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creadiagnosticosatencion',
  templateUrl: './creadiagnosticosatencion.component.html',
})
export class CreadiagnosticosatencionComponent implements OnInit {

  ready:boolean=false
  dxate:any;
  formDxAtencion:FormGroup
  idhcpaciente:string
  hcpacseleccionado:any
  tipdx:any
  tipnot:any
  diagnosticoAutocompletado: string; // Variable para almacenar el diagnóstico autocompletado
  diagnosticoOptions: any[] = []; // Lista de opciones de diagnóstico para el autocompletado
  diagnosticos: any[] = []; // Lista completa de diagnósticos
  diagnosticosFiltrados: any[] = []; // Diagnósticos filtrados según la consulta incremental

  


  constructor(
    private router:Router,
    private service:OperacionService,
    private fb:FormBuilder,
    private servicioDx:ConfigService,
    private paramsrouter: ActivatedRoute
    ) {
      this.idhcpaciente=this.paramsrouter.snapshot.paramMap.get('idhcpac')
      this.formDxAtencion=fb.group({
        idhcpac:['',[Validators.required]],
        numdocpac:['',[Validators.required]],
        primernompac:['',[Validators.required]],
        conseventpac:['',[Validators.required]],    
        segundonompac:['',[Validators.required]],
        primerapepac:['',[Validators.required]],
        segundoapepac:['',[Validators.required]],
        origdx_fk:['',[Validators.required]],
        dxatehcpac_fk:['',[Validators.required]],
        typdxatehcpac_fk:['',[Validators.required]],
        idevent:['',[Validators.required]],
      })
     }

  ngOnInit(): void {
    if(this.idhcpaciente != null){
      
      console.log('id del evento que llega',this.idhcpaciente)
  
      this.getDataHcpac();    
      
      }else{
        this.clearForm();
      }
      this.consultaTipoDx();
      this.consultaTipoNota();
      this.consultaDx();
      
  }

  getDataHcpac(){
    console.log('hcpac que viene del snapshop',this.idhcpaciente)
    this.service.getHCtId(this.idhcpaciente).subscribe((res:any)=>{
      console.log('historia  a mostrar en el formulario',res);
      this.hcpacseleccionado = res;
      this.formDxAtencion.patchValue({
        idhcpac:this.hcpacseleccionado.idhcpac, 
        idevent:this.hcpacseleccionado.eventpac_fk.idevent,
        conseventpac:this.hcpacseleccionado.eventpac_fk.conseventpac,
        numdocpac:this.hcpacseleccionado.eventpac_fk.pacevent_fk.numdocpac,
        primernompac:this.hcpacseleccionado.eventpac_fk.pacevent_fk.primernompac,
        segundonompac:this.hcpacseleccionado.eventpac_fk.pacevent_fk.segundonompac,
        primerapepac:this.hcpacseleccionado.eventpac_fk.pacevent_fk.primerapepac,
        segundoapepac:this.hcpacseleccionado.eventpac_fk.pacevent_fk.segundoapepac,
      })
      
     })

  }

  creaDxAtencion(){
    let structDxAtencion = {
      origdx_fk:{
        "idtypnot":this.formDxAtencion.value.origdx
      },
      estdxatehcpac:{
        "idstatus":1
      },
      dxatehcpac_fk:{
        "clavedx":this.formDxAtencion.value.dxatehcpac_fk
      },
      typdxatehcpac_fk:{
        "idtypdx":this.formDxAtencion.value.typdxatehcpac_fk
      },
      eventdxate_fk:{
        "idevent":this.formDxAtencion.value.eventdxate_fk
      }
    }
    this.service.addDxAtencion(structDxAtencion)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('DiagnosticoAtención', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['diagnosticosatencion']);
        
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

  filtrarDiagnosticos(consulta: string) {
    if (consulta.trim() !== '') {
      this.diagnosticosFiltrados = this.diagnosticos.filter(diagnostico =>
        diagnostico.nomdx.toLowerCase().includes(consulta.toLowerCase())
      );
    } else {
      this.diagnosticosFiltrados = []; // Borra la lista de diagnósticos filtrados si la consulta está vacía
    }
  }
/*
  // Método para manejar la búsqueda incremental
  handleSearch(term: string): void {
    if (term.length >= 3) { // Realiza la búsqueda solo si el término tiene al menos 3 caracteres
      this.servicioDx.getDxfindByNomdx(term)
        .pipe(
          tap((res) => {
            // Maneja la respuesta exitosa aquí
            console.log('Resultado de la búsqueda:', res);
            this.diagnosticoOptions = res; // Actualiza la lista de opciones de diagnóstico
          }),
          catchError((err) => {
            // Maneja el error aquí
            console.error('Error:', err);
            // Muestra una alerta de error, por ejemplo:
            Swal.fire({
              icon: 'error',
              title: 'Error en la búsqueda',
              text: 'Hubo un problema al buscar diagnósticos. Por favor, inténtalo de nuevo más tarde.'
            });
            throw err; // Re-throw para que el error se propague al suscriptor
          })
        ).subscribe();
    } else {
      // Si el término es muy corto, vacía la lista de opciones de diagnóstico
      this.diagnosticoOptions = [];
    }
  }*/
  clearForm(){

  }

  }

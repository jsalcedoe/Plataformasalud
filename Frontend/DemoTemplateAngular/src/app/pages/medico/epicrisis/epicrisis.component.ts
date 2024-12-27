import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { content } from 'html2canvas/dist/types/css/property-descriptors/content';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import * as pdfMake from 'pdfmake/build/pdfmake';
import * as pdfFonts from 'pdfmake/build/vfs_fonts';

//(pdfMake as any).vfs = pdfFonts.pdfMake.vfs;




@Component({
  selector: 'app-epicrisis',
  templateUrl: './epicrisis.component.html',
})
export class EpicrisisComponent implements OnInit {

  formEpicrisis:FormGroup
  epicrisis:any  
  idevent:string
  eventSeleccionado:any


  constructor(private epiService:OperacionService,
              private router:Router,
              private fb:FormBuilder,
              private paramsrouter: ActivatedRoute
  ) {
    this.idevent = this.paramsrouter.snapshot.paramMap.get('idevent')
    console.log('idevent del snapshop',this.idevent)

    this.formEpicrisis = this.fb.group({
      idevent:['',[Validators.required]],
      conseventpac:['',[Validators.required]],
      numdocpac:['',[Validators.required]],
      primernompac:['',[Validators.required]],
      segundonompac:['',[Validators.required]],
      primerapepac:['',[Validators.required]],
      segundoapepac:['',[Validators.required]],
      hcpacepi:[''],
      evoeventepi:[''],
      desqxepi:[''],
      regmedinsepi:[''],
      sigvitepi:[''],
      dxateepi:[''],  
    })
  }
    ngOnInit(): void {
      this.getDataEvent(),
      this.getEpicrisis()
  }
  getDataEvent() {
    console.log(this.idevent,'tipo de dato en getdataevent en Epicrisis',typeof this.idevent);
    this.epiService.getEventId(this.idevent).subscribe((res:any)=>{
     console.log('evento a mostrar en el formulario getdataevent de historia clinica',res);
     this.eventSeleccionado = res;
     console.log('eventSeleccionado',this.eventSeleccionado);
     this.formEpicrisis.patchValue({
      idevent:this.eventSeleccionado.idevent,
      conseventpac:this.eventSeleccionado.conseventpac,
      numdocpac:this.eventSeleccionado.pacevent_fk.numdocpac,
      primernompac:this.eventSeleccionado.pacevent_fk.primernompac,
      segundonompac:this.eventSeleccionado.pacevent_fk.segundonompac,
      primerapepac:this.eventSeleccionado.pacevent_fk.primerapepac,
      segundoapepac:this.eventSeleccionado.pacevent_fk.segundoapepac,
      })
     
    })
    
   }

   getEpicrisis(){
    this.epiService.getEpicrisis(this.idevent).pipe(
          tap((res) => {
            // Maneja la respuesta exitosa aquí
            console.log('Epicrisis', res);
            this.epicrisis = res;

            //construyo hcpacepi

            const hcpacepiText = `
                Motivo de consulta: ${this.epicrisis.hcpacepi?.motconshcpac || ''}
                Enfermedad Actual: ${this.epicrisis.hcpacepi?.enfacthcpac || ''}
                Analisis: ${this.epicrisis.hcpacepi?.analisishcpac || ''}
                Objeto: ${this.epicrisis.hcpacepi?.objhcpac || ''}
                Plan de manejo: ${this.epicrisis.hcpacepi?.planmanejhcpac || ''}
                Alergicos: ${this.epicrisis.hcpacepi?.antalerhcpac || ''}
                Familiares: ${this.epicrisis.hcpacepi?.antfamyhcpac || ''}
                Farmacologicos: ${this.epicrisis.hcpacepi?.antfarmhcpac || ''}
                Patologicos: ${this.epicrisis.hcpacepi?.antpathcpac || ''}
                Quirurgicos: ${this.epicrisis.hcpacepi?.antqxhcpac || ''}
                Traumaticos: ${this.epicrisis.hcpacepi?.anttxhcpac || ''}
                Estatura: ${this.epicrisis.hcpacepi?.estaturahcpac || ''}
                Frecuencia Cardiaca: ${this.epicrisis.hcpacepi?.fchcpac || ''}
                Frecuencia Respiratoria: ${this.epicrisis.hcpacepi?.frhcpac || ''}
                Peso: ${this.epicrisis.hcpacepi?.pesohcpac || ''}
                TA: ${this.epicrisis.hcpacepi?.tahcpac || ''}
                Temperatura: ${this.epicrisis.hcpacepi?.temphcpac || ''}
            `;

            // Función para formatear la fecha

              const formatDate = (dateString) => {
              const date = new Date(dateString);
              const year = date.getFullYear();
              const month = String(date.getMonth() + 1).padStart(2, '0'); // Añade un 0 si es necesario
              const day = String(date.getDate()).padStart(2, '0'); // Añade un 0 si es necesario
              return `${year}-${month}-${day}`;
          };

            // construyo el campo para evoluciones

            const evoeventepiText = this.epicrisis.evoeventepi.map(evo => `
                      Fecha Evolución: ${formatDate(evo.datecreatevol)}
                      Detalle: ${evo.detevol}
                      Origen de la nota: ${evo.notaevol_fk.dettypnot} (${evo.notaevol_fk.nametypnot})
                  `).join('\n');

            // construyo el campo para descripcion quirurgica
            
            const desqxepiText = this.epicrisis.desqxepi.map(desqx => ` 
              Fecha Procedimiento: ${desqx.fechaprocqx} 
              Descripción: ${desqx.descqx} 
              Procedimiento: ${desqx.procedimientos.map(proc => ` 
                  ID Procedimiento: ${proc.idprocqx} 
                  Nombre Procedimiento: ${proc.procqx_fk ? proc.procqx_fk.nompxex : ''} 
                `).join('')} 
              `).join('\n');

            // construyo campo de registro de medicamentos

            const regmedinsepiText = this.epicrisis.regmedinsepi.map(med => `
              Fecha Aplicacion: ${med.dateregpmedins}
              Medicamento: ${med.medinsreg_fk?.medins} 
              Dosis Aplicada: (${med.regdosismedins})
            `).join('\n');

            const sigvitepiText = this.epicrisis.sigvitepi.map(sig => `
              Fecha: ${sig.dateregsigvit}
              TA: ${sig.ta}
              FC: ${sig.fc}
              FR: ${sig.fr}
            `).join('\n');

            const dxateepiText = this.epicrisis.dxateepi.map(dx => `
              Fecha: ${dx.creatdxathcpac} 
              CIE10: ${dx.dxatehcpac_fk?.capdx} 
              Diagnóstico: ${dx.dxatehcpac_fk?.descdx}
              Clasificacion Dx: ${dx.typdxatehcpac_fk?.detypdx}
              Origen: ${dx.origdx_fk?.dettypnot}

              `).join('\n');

            this.formEpicrisis.patchValue({
              hcpacepi: hcpacepiText.trim(),
              evoeventepi: evoeventepiText.trim(),
              desqxepi: desqxepiText.trim(),
              regmedinsepi: regmedinsepiText.trim(),
              sigvitepi: sigvitepiText.trim(), 
              dxateepi: dxateepiText.trim(),
            });
            
          }),
          catchError((err) => {
            // Maneja el error aquí
            console.error('Error:', err);
            alert('Error ' + err.message);
            throw err; // Re-throw para que el error se propague al suscriptor
          })
        ).subscribe();
   }

   generaPDF(){

    
           
    }

}

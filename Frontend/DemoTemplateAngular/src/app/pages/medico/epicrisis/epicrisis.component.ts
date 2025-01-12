import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { content } from 'html2canvas/dist/types/css/property-descriptors/content';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import * as pdfMake from "pdfmake/build/pdfmake";
import * as pdfFonts from 'pdfmake/build/vfs_fonts';
import { createHeader } from '../../config/encabezado';
import { style } from '@angular/animations';
import { ImageServiceService } from 'src/app/services/ImageService.service';
import { __values } from 'tslib';
import { stringify } from 'querystring';

(<any>pdfMake).addVirtualFileSystem(pdfFonts);

@Component({
  selector: 'app-epicrisis',
  templateUrl: './epicrisis.component.html',
})
export class EpicrisisComponent implements OnInit {

  formEpicrisis:FormGroup
  epicrisis:any  
  idevent:string
  eventSeleccionado:any
  logoBase64: string = '';
  camposEpicrisis = [
    {label:'Documento Paciente',nombre:'numdocpac',type:'number'},
    {label:'Consecutivo Evento',nombre:'conseventpac',type:'text'},
    {label:'Evento',nombre:'idevent',type:'text'},
    {label:'Id Paciente',nombre:'idpac',type:'text'},
    {label:'Primer Nombre',nombre:'primernompac',tipo:'text'},
    {label:'Segundo Nombre',nombre:'segundonompac',tipo:'text'},
    {label:'Primer Apellido',nombre:'primerapepac',tipo:'text'},
    {label:'Segundo Apellido',nombre:'segundoapepac',tipo:'text'}
    
  ]


  constructor(private epiService:OperacionService,
              private router:Router,
              private fb:FormBuilder,
              private imageService: ImageServiceService,
              private paramsrouter: ActivatedRoute
  ) {
    this.idevent = this.paramsrouter.snapshot.paramMap.get('idevent')
    console.log('idevent del snapshop',this.idevent)

    this.formEpicrisis = this.fb.group({
      idevent:['',[Validators.required]],
      conseventpac:['',[Validators.required]],
      numdocpac:['',[Validators.required]],
      idpac:['',[Validators.required]],
      primernompac:['',[Validators.required]],
      segundonompac:['',[Validators.required]],
      primerapepac:['',[Validators.required]],
      segundoapepac:['',[Validators.required]],
      hcpacepi:['',[Validators.required]],
      evoeventepi:['',[Validators.required]],
      desqxepi:['',[Validators.required]],
      regmedinsepi:['',[Validators.required]],
      sigvitepi:['',[Validators.required]],
      dxateepi:['',[Validators.required]],
     
    })
  }
    ngOnInit(): void {
      this.imageService.convertToBase64('assets/LogoUMQ.jpeg')
      .then((base64) => (this.logoBase64 = base64))
      .catch((err) => console.error('Error loading image', err));

      this.getDataEvent()
      this.getEpicrisis()
  }
  getDataEvent() {

    if(this.idevent == null){
      console.log('Evento null',this.idevent);
    }else{
          console.log(this.idevent,'tipo de dato en getdataevent en Epicrisis',typeof this.idevent);
          this.epiService.getEventId(this.idevent).subscribe((res:any)=>{
          console.log('evento a mostrar en el formulario getdataevent de historia clinica',res);
          this.eventSeleccionado = res;
          console.log('eventSeleccionado',this.eventSeleccionado);
          this.formEpicrisis.patchValue({
            idevent:this.eventSeleccionado.idevent,
            idpac:this.eventSeleccionado.pacevent_fk.idpac,
            conseventpac:this.eventSeleccionado.conseventpac,
            numdocpac:this.eventSeleccionado.pacevent_fk.numdocpac,
            primernompac:this.eventSeleccionado.pacevent_fk.primernompac,
            segundonompac:this.eventSeleccionado.pacevent_fk.segundonompac,
            primerapepac:this.eventSeleccionado.pacevent_fk.primerapepac,
            segundoapepac:this.eventSeleccionado.pacevent_fk.segundoapepac,
            })
          
          })
    
   }
  }
  getEpicrisis(){
    this.epiService.getEpicrisis(this.idevent).pipe(
      tap((res:any)=>{
      console.log('epicrisis',res);
      this.epicrisis = res   

      // Función para formatear la fecha

        const formatDate = (dateString) => {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // Añade un 0 si es necesario
        const day = String(date.getDate()).padStart(2, '0'); // Añade un 0 si es necesario
        return `${year}-${month}-${day}`;
    };

      //construyo el campo  hcpacepi
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
    // construyo el campo para evoluciones

    const evoeventepiText = this.epicrisis.evoeventepi.map(evo => `
      Fecha Evolución: ${formatDate(evo.datecreatevol)}
      Detalle: ${evo.detevol}
      Origen de la nota: ${evo.notaevol_fk.dettypnot} (${evo.notaevol_fk.nametypnot})
  `).join('\n');

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
          hcpacepi:hcpacepiText.trim(),
          evoeventepi: evoeventepiText.trim(),
          desqxepi: desqxepiText.trim(),
          regmedinsepi:regmedinsepiText.trim(),
          sigvitepi:sigvitepiText.trim(),
          dxateepi:dxateepiText.trim(),
          
        })   
        this.epicrisis={
          hcpacepi:hcpacepiText.trim(),
          evoeventepi: evoeventepiText.trim(),
          desqxepi: desqxepiText.trim(),
          regmedinsepi:regmedinsepiText.trim(),
          sigvitepi:sigvitepiText.trim(),
          dxateepi:dxateepiText.trim(),
        }
        
      
  }), catchError((err) => {
    // Maneja el error aquí
    console.error('Error:', err);
    alert('Error ' + err.message);
    throw err; // Re-throw para que el error se propague al suscriptor
  })
    ).subscribe();

}
generaPDF() {
  console.log('Mostrar Evento en pdf', this.eventSeleccionado);
  if (!this.logoBase64) {
    console.error('La imagen no se ha cargado. Inténtalo de nuevo.');
    return;
  }
  
  const documentDefinition: any = {
    pageSize: 'A4',
    pageMargins: [40, 80, 40, 60], // Ajusta los márgenes según sea necesario
    header:{
      table: {
        widths: ['auto', 'auto', '*'], // Ajusta los anchos de las columnas según sea necesario
      body: [
        [
          {
            image: this.logoBase64,
            width: 100, // Ajusta el ancho según sea necesario
            colSpan: 2,
            alignment: 'center',
          },
          {},
          {
            text: [
              'UNIDAD MEDICO QUIRURGICA DE CIRUGIA PLASTICA AMBULATORIA S.A.S.\n',
              'AV. FERROCARRIL # 41-76 BARRIO MACARENA\n',
              'NIT: 900.192.013-3\n',
            ],
            alignment: 'center'
          },              
         
        ],
        
      ]
    },
    margin: [35, 10, 35, 10], // Ajusta el margen del encabezado según sea necesario
    },

    footer: (currentPage:number, pageCount:number) => {
      return {
        text: `Página ${currentPage} de ${pageCount}`,
        alignment: 'center',
        margin: [0, 10, 0, 0], // Ajusta el margen del pie de página
        fontSize: 10,
      };
    },

    content: [
           {
        text: 'EPICRISIS',
        style: 'header',
        alignment: 'center',
        //margin: [0, 20, 0, 10], // Margen para separar del resto
      },
      {
        table: {
          widths: ['25%', '25%', '25%', '25%'], // Ajusta los anchos según necesidad
          body: [
            [
              { text: 'IDENTIFICACIÓN DEL PACIENTE', bold: true, colSpan: 4, alignment: 'center' },
              {}, {}, {},
            ],
            [
              { text: 'Evento:', bold: true },
              { text: `${this.eventSeleccionado.idevent}` },
              { text: 'Documento:', bold: true },
              { text: `${this.eventSeleccionado.pacevent_fk.numdocpac}` },
            ],
            [
              { text: 'Nombres:', bold: true },
              { text: `${this.eventSeleccionado.pacevent_fk.primernompac} ${this.eventSeleccionado.pacevent_fk.segundonompac}` },
              { text: 'Apellidos:', bold: true },
              { text: `${this.eventSeleccionado.pacevent_fk.primerapepac} ${this.eventSeleccionado.pacevent_fk.segundoapepac}` },
            ],
          ]
        },
        margin: [0, 20, 0, 10],
      },

      
      {
        table:{
          widths: ['25%', '75%'],
          body: [
            [
              { text: 'Historia Clinica:', 
                bold: true,
                style: 'centeredLeft',
                margin: [0, 160, 0, 10],
               },
              { text: this.epicrisis.hcpacepi },
            ],
            
          ]
        }
      },

      {
        table:{
          widths: ['25%', '75%'],
          body: [
            [
              { text: 'Evoluciones Medicas:', 
                bold: true,
                style: 'centeredLeft',
                margin: [0, 10, 0, 10],
               },
              { text: this.epicrisis.evoeventepi },
            ],
            
          ]
        },
        margin: [0, 20, 0, 10],
      },

      {
        table:{
          widths: ['25%', '75%'],
          body: [
            [
              { text: 'Descripciones Quirurgicas:', 
                bold: true,
                style: 'centeredLeft',
                margin: [0, 10, 0, 10],
               },
              { text: this.epicrisis.desqxepi },
            ],
            
          ]
        },
        margin: [0, 20, 0, 10],
      },

      {
        table:{
          widths: ['25%', '75%'],
          body: [
            [
              { text: 'Medicamentos Aplicados:', 
                bold: true,
                style: 'centeredLeft',
                margin: [0, 10, 0, 10],
               },
              { text: this.epicrisis.regmedinsepi },
            ],
            
          ]
        },
        margin: [0, 20, 0, 10],
      },

      {
        table:{
          widths: ['25%', '75%'],
          body: [
            [
              { text: 'Signos Vitales:', 
                bold: true,
                style: 'centeredLeft',
                margin: [0, 10, 0, 10],
               },
              { text: this.epicrisis.sigvitepi },
            ],
            
          ]
        },
        margin: [0, 20, 0, 10],
      },

      {
        table:{
          widths: ['25%', '75%'],
          body: [
            [
              { text: 'Diagnosticos:', 
                bold: true,
                style: 'centeredLeft',
                margin: [0, 10, 0, 10],
               },
              { text: this.epicrisis.dxateepi },
            ],
            
          ]
        },
        margin: [0, 20, 0, 10],
      },
    ],
    styles: {
      header: {
        fontSize: 18,
        bold: true,
      },
      centeredLeft: {
        alignment: 'left',
        valign: 'middle',
      },
    },
  };

  pdfMake.createPdf(documentDefinition).open();
  console.log('Información definida para PDF', documentDefinition);
}






    
}
  
  



  


import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import * as pdfMake from "pdfmake/build/pdfmake";
import * as pdfFonts from 'pdfmake/build/vfs_fonts';
import { ImageServiceService } from 'src/app/services/ImageService.service';
import { OperacionService } from 'src/app/services/operacion.service';

(<any>pdfMake).addVirtualFileSystem(pdfFonts);

@Component({
  selector: 'app-generapdfconsentimiento',
  templateUrl: './generapdfconsentimiento.component.html'
  
})
export class GenerapdfconsentimientoComponent implements OnInit {

  formConsentimientosPDF: FormGroup
  idconsinfpac:string
  consentimientoSeleccionado:any
  logoBase64: string = '';
  camposPaciente = [
    {label:'Documento Paciente',nombre:'numdocpac',type:'number'},
    {label:'Consecutivo Evento',nombre:'conseventpac',type:'text'},
    {label:'Evento',nombre:'idevent',type:'text'},
    {label:'Id Paciente',nombre:'idpac',type:'text'},
    {label:'Primer Nombre',nombre:'primernompac',tipo:'text'},
    {label:'Segundo Nombre',nombre:'segundonompac',tipo:'text'},
    {label:'Primer Apellido',nombre:'primerapepac',tipo:'text'},
    {label:'Segundo Apellido',nombre:'segundoapepac',tipo:'text'}
    
  ]

  constructor(private conspdf:OperacionService,
              private router:Router,
              private imageService: ImageServiceService,
              private paramsrouter: ActivatedRoute) { 
                this.idconsinfpac = this.paramsrouter.snapshot.paramMap.get('idconsinfpac')
                console.log('id consentimiento que llega',this.idconsinfpac)
                
              }

  ngOnInit(): void {

    this.imageService.convertToBase64('assets/LogoUMQ.jpeg')
    .then((base64) => {
      this.logoBase64 = base64;
      this.getConsentimientos();  // Llamar a getConsentimientos() después de cargar la imagen
    })
    .catch((err) => console.error('Error loading image', err));
      
      
  }

  getConsentimientos(){
    this.conspdf.getconsinfpacXId(this.idconsinfpac)
    .subscribe((res:any)=>{
      if (res){
          console.log('idconsinf a mostrar en el formulario',res);
          this.consentimientoSeleccionado = res;
          console.log('Evento seleccionado',this.consentimientoSeleccionado);
          this.generaPDFConsentimientos();
      }else{
        console.error('No se encontraron datos para el consentimiento');
      }
     })
  }
  

  generaPDFConsentimientos() {
    console.log('Mostrar Consentimiento  en pdf', this.consentimientoSeleccionado);
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
          table: {
            widths: ['25%', '25%', '25%', '25%'], // Ajusta los anchos según necesidad
            body: [
              [
                { text: 'IDENTIFICACIÓN DEL PACIENTE', bold: true, colSpan: 4, alignment: 'center' },
                {}, {}, {},
              ],
              [
                { text: 'Evento:', bold: true },
                { text: `${this.consentimientoSeleccionado.eventconsinfpac_fk.idevent}` },
                { text: 'Documento:', bold: true },
                { text: `${this.consentimientoSeleccionado.eventconsinfpac_fk.pacevent_fk.numdocpac}` },
              ],
              [
                { text: 'Nombres:', bold: true },
                { text: `${this.consentimientoSeleccionado.eventconsinfpac_fk.pacevent_fk.primernompac} ${this.consentimientoSeleccionado.eventconsinfpac_fk.pacevent_fk.segundonompac}` },
                { text: 'Apellidos:', bold: true },
                { text: `${this.consentimientoSeleccionado.eventconsinfpac_fk.pacevent_fk.primerapepac} ${this.consentimientoSeleccionado.eventconsinfpac_fk.pacevent_fk.segundoapepac}` },
              ],
            ]
          },
          margin: [0, 20, 0, 10],
        },

        {
          text: `${this.consentimientoSeleccionado.conspxpac_fk.codconinf}`,
          style: 'header',
          alignment: 'center',
          margin: [0, 20, 0, 40], // Margen para separar del resto
        },

        {
          text: `${this.consentimientoSeleccionado.conspxpac_fk.detconinf}`,
          //style: 'header',
          fontSize: 10,
          alignment: 'justify',
          //margin: [0, 20, 0, 10], // Margen para separar del resto
        },

        /*{
          
          text: `${this.consentimientoSeleccionado.medpxpac_fk.firstname + ' ' + this.consentimientoSeleccionado.medpxpac_fk.lastname}`,
          style: 'header',
          alignment: 'justify',
          margin: [0, 60, 0, 5], // Margen para separar del resto
        },
        {
          
          text: 'Cirujano',
          style: 'header',
          alignment: 'justify',
          //margin: [0, 20, 0, 10], // Margen para separar del resto
        },*/

        {
          table: {
            widths: ['50%', '50%'], // Ajusta los anchos de las columnas
            body: [
              [
                { text: 'Firma del cirujano', style: 'header', alignment: 'center' },
                { text: 'Firma del paciente', style: 'header', alignment: 'center' }
              ],
              [
                {
                  text: [
                    `${this.consentimientoSeleccionado.medpxpac_fk.firstname} ${this.consentimientoSeleccionado.medpxpac_fk.lastname}\n`, // Primera línea
                    { text: 'Cirujano', fontSize: 12, bold: true } // Segunda línea, con estilo opcional
                  ],
                  style: 'header',
                  alignment: 'center', // Asegúrate de que el texto esté centrado
                },
                {
                  text: [
                    `${this.consentimientoSeleccionado.medpxpac_fk.firstname} ${this.consentimientoSeleccionado.medpxpac_fk.lastname}\n`, // Primera línea
                    { text: 'Cirujano', fontSize: 12, bold: true } // Segunda línea, con estilo opcional
                  ],
                  style: 'header',
                  alignment: 'center', // Asegúrate de que el texto esté centrado
                },
              ]
            ]
          },
          margin: [0, 20, 0, 10],
        }
        
  
        
       
      ],
      styles: {
        header: {
          fontSize: 12,
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
    this.router.navigateByUrl(`/eventos`);
  }

}

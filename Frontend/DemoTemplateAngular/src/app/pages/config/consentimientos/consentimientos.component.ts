import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import * as pdfMake from "pdfmake/build/pdfmake";
import * as pdfFonts from 'pdfmake/build/vfs_fonts';
import { ImageServiceService } from 'src/app/services/ImageService.service';

(<any>pdfMake).addVirtualFileSystem(pdfFonts);

@Component({
  selector: 'app-consentimientos',
  templateUrl: './consentimientos.component.html',

})
export class ConsentimientosComponent implements OnInit {

  consentimientos:any
  ready:boolean=false
  logoBase64: string = '';

  constructor(private service:ConfigService, 
              private imageService: ImageServiceService,
              private route:Router) { }

  ngOnInit(): void {
    this.imageService.convertToBase64('assets/LogoUMQ.jpeg')
    .then((base64) => (this.logoBase64 = base64))
    .catch((err) => console.error('Error loading image', err));


    this.getconsentimientos();
  }

  getconsentimientos(){
    this.service.getconinf().pipe(
          tap((res) => {
            // Maneja la respuesta exitosa aquí
            console.log('Consentimientos', res);
            this.consentimientos = res;
            this.ready = true;
          }),
          catchError((err) => {
            // Maneja el error aquí
            console.error('Error:', err);
            alert('Error' + err.message)
            throw err; // Re-throw para que el error se propague al suscriptor
          })
        ).subscribe();

  }
  irCreaConsentimiento(){
    this.route.navigate(['creaconsentimientos'])

  }

  generaPDF(consentimiento:any) {

    console.log('Generando consentimiento para : ', consentimiento);

    if (!this.consentimientos || Object.keys(this.consentimientos).length === 0) {
      console.error('No hay datos disponibles para generar el PDF.');
      alert('No hay datos disponibles para generar el PDF.');
      return;
    }
   
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
              text: 'CONSENTIMIENTO INFORMADO',
              style: 'header',
              alignment: 'center',
              //margin: [0, 20, 0, 10], // Margen para separar del resto del contenido
        },

        {
          table: {
            body:[
              [
                { text: `${consentimiento.codconinf || 'No disponible'}` },
              ], 
              [
                { text: `${consentimiento.detconinf || 'No disponible'}` },
              ], 
            ]
          }
        }
        
                
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

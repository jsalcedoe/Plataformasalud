import * as pdfMake from "pdfmake/build/pdfmake";
import * as pdfFonts from 'pdfmake/build/vfs_fonts';

(<any>pdfMake).addVirtualFileSystem(pdfFonts);

export const createHeader = () => {
    return{
        columns:[
            /*{
                image: 'src/assets/img/LogoUMQ.jpeg',
                width: 50,
            },*/
            {
                text: 'UNIDAD MEDICO QUIRURGICA DE CIRUGIA PLASTICA',
                fontSize: 18,
                bold: true,
                margin: [10, 0, 0, 0]
            },
            { text: 'Fecha: ' + new Date().toLocaleDateString(), 
              alignment: 'right', 
              fontSize: 10
            }
        ],
        margin: [0, 0, 0, 10]
    }
}
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creacamas',
  templateUrl: './creacamas.component.html',
  
})
export class CreacamasComponent implements OnInit {
  formcamas:FormGroup
  buscaubica:any
  mensajes: string
  res:any
  camaID:any
  


  constructor(private router:Router,
              private service:ConfigService,
              private activerouted:ActivatedRoute,
              private fb:FormBuilder
              ) {
                this.formcamas = fb.group({
                  nomhab:['',[Validators.required,Validators.minLength(3)]],
                  dethab:['',[Validators.required, Validators.minLength(10)]],
                  ubicahab:['',[Validators.required]]
                })
               }

  ngOnInit(): void {

    this.activerouted.params.subscribe(params => {
      console.log('params', params)
      this.camaID = params['idhab'];
      console.log('camaID',this.camaID)
      // Llamar a un método para cargar la información de la cama usando this.camaId
      this.getCamaInfo(this.camaID);
    });
    
    this.consultaubica();
  }

  consultaubica(){
    this.service.getUbicaciones()
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('Consulta Ubicaciones', res);
        this.buscaubica = res
        
      }),
      catchError((err) => {
        // Maneja el error aquí
        console.error('Error:', err);
        alert('Error ' + err.message);
        throw err; // Re-throw para que el error se propague al suscriptor
      })
    ).subscribe();
  }

  creaCamas(){
    let struckCamas = {
      nomhab:this.formcamas.value.nomhab,
      dethab:this.formcamas.value.dethab,
      ubicahab:{
        "idubica": this.formcamas.value.ubicahab
      },
      esthab_fk: {
        "idstatus": 1
    }

    }
    this.service.addCamas(struckCamas)
    .pipe(
      tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('CAMAS', res);
        Swal.fire({
          icon: 'success',
          title: 'Operación exitosa',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['camas']);
        
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

  getCamaInfo(idhab: number) {
    
    this.service.getCamasByIdHab(idhab).subscribe(res =>{
      console.log('muestra info',res)
      this.camaID = res
      this.formcamas.patchValue({
        nomhab:this.camaID.nomhab,
        dethab:this.camaID.dethab,
        ubicahab:{
        "idubica": this.camaID.ubicahab.idubica
      },
        esthab_fk: {
          "idstatus": 2
    }
      })
     
    })
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
    
    
  }

  guardarCambios() {

  // Obtener los valores del formulario
  const valoresFormulario = this.formcamas.value;
  console.log('valoresFormulario',valoresFormulario)

  // Crear el objeto para enviar al servicio
  let datosActualizados = {
    nomhab: valoresFormulario.nomhab,
    dethab: valoresFormulario.dethab,
    ubicahab: {
      "idubica": valoresFormulario.ubicahab.idubica
    },
    esthab_fk: {
      "idstatus": 2
    }
  };
    this.service.editCamas(this.camaID.idhab, datosActualizados)
      .pipe(
        tap((res) => {
        // Maneja la respuesta exitosa aquí
        console.log('CAMAS', res);
        Swal.fire({
          icon: 'success',
          title: 'Cama Actualizada Exitosamente',
          text: res.mensaje // Mostrar el mensaje recibido desde el backend
        });
        return this.router.navigate(['camas']);
        
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
    console.log('formcamas',this.formcamas.value)
    console.log('camaID FINAL',this.camaID)
  }

  compareUbicaciones(ubicacion1: any, ubicacion2: any): boolean {
    console.log('ubicacion1',ubicacion1)
    console.log('ubicacion2',ubicacion2)
    return ubicacion1 && ubicacion2 ? ubicacion1.idubica === ubicacion2.idubica : ubicacion1 === ubicacion2;
    
  }

  

}

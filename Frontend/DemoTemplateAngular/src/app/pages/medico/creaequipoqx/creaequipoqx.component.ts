import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, forkJoin, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-creaequipoqx',
  templateUrl: './creaequipoqx.component.html',
  
})
export class CreaequipoqxComponent implements OnInit {
  equipo:any;
  ready:boolean=false;
  formequipoqx:FormGroup;
  //idqx:string;
  user:any []=[];
  filterUser: any [][]=[];
  idqxSeleccionado: any

  constructor(private router:Router,
              private service:OperacionService,
              private fb:FormBuilder,
              private servicio:ConfigService,
              private paramsrouter: ActivatedRoute
              ) { 
                /*this.idqx=this.paramsrouter.snapshot.paramMap.get('idqx')
                //console.log('idqx del snapshop',this.idqx)
                this.formequipoqx = fb.group({
                  idevent:['',[Validators.required]],
                  idqx:['',[Validators.required]],
                  conseventpac:['',[Validators.required]],
                  numdocpac:['',[Validators.required]],
                  primernompac:['',[Validators.required]],
                  segundonompac:['',[Validators.required]],
                  primerapepac:['',[Validators.required]],
                  segundoapepac:['',[Validators.required]],
                  integrante:this.fb.array([]) // FormArray para los procedimientos

                })*/
                this.addUser();
              }

  ngOnInit(): void {
    this.consultauser();
    this.getDataEqQx();
  }
  get userArray() {
    return this.formequipoqx.get('integrante') as FormArray;
  }

  getDataEqQx(){
   

  }

  addUser() {
    const userFormGroup = this.fb.group({
      iduser: ['', Validators.required],
      firstname:['',Validators.required],
      lastname:['',Validators.required],
    });
    this.userArray.push(userFormGroup);
  }
  
  removeUser(index: number) {
    this.userArray.removeAt(index);
  }

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

  consultauser(){
    this.servicio.getUsuarios()
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

  creaEquipoQx() {
    console.log('Valores del formulario:', this.formequipoqx.value);
    // Extrayendo y construyendo los valores de los integrantes
    const userArray = this.formequipoqx.get('integrante') as FormArray;
    const userValues = userArray.value;
  
    const structequipoqx = userValues.map((integrante: any) => {
      // Extraemos los valores y verificamos que existan
      const pxqx_fk = this.formequipoqx.get('idqx')?.value; // Obtén el valor del formulario
      const inteqqx = integrante.iduser; // Extrae el ID del usuario
  
      // Valida que los valores sean correctos
      if (!pxqx_fk || !inteqqx) {
        console.error('Faltan valores para pxqx_fk o inteqqx', { pxqx_fk, inteqqx });
        return null; // O manejarlo de otra forma si es necesario
      }
  
      console.log('Valores convertidos:', { pxqx_fk, inteqqx });
  
      return {
        pxqx_fk: {
          "idqx": Number(pxqx_fk) // Convertimos pxqx_fk a número
        },
        esteqqx_fk: {
          "idstatus": 1
        },
        inteqqx: {
          "iduser": Number(inteqqx) // Convertimos inteqqx a número
        }
      };
    }).filter(item => item !== null); // Filtramos los nulos si faltan datos
  
    // Iteramos sobre el equipo para hacer las peticiones
    structequipoqx.forEach((eq, index) => {
      console.log(`Integrante:  ${index + 1}:`, eq);
      this.service.addequipoqx(eq)
        .pipe(
          tap((res) => {
            console.log('Usuarios del equipo', res);
            Swal.fire({
              icon: 'success',
              title: 'Operación exitosa',
              text: res.mensaje
            });
            if (index === structequipoqx.length - 1) {
               //this.router.navigateByUrl(`/creadiagnosticosatencion${res.idqx}`);
               console.log('id que se va a pasar',res.idqx)
            }
          }),
          catchError((err) => {
            console.error('Error:', err);
            Swal.fire({
              icon: 'error',
              title: 'Error en la operación',
              text: err.message
            });
            throw err;
          })
        ).subscribe();
    });
  }
  
}

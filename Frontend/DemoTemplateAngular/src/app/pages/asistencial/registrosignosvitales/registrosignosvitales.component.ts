import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { ConfigService } from 'src/app/services/config.service';
import { OperacionService } from 'src/app/services/operacion.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registrosignosvitales',
  templateUrl: './registrosignosvitales.component.html',
})
export class RegistrosignosvitalesComponent implements OnInit {
  formsigvit: FormGroup;
  idevent: string;
  signosvitales: any[] = [];
  ideventseleccionado: any;
  signosFiltrados: any[] = [];
  user: any[] = [];
  filterUser: any[][] = [];

  constructor(
    private router: Router,
    private service: OperacionService,
    private servicio: ConfigService,
    private fb: FormBuilder,
    private paramsrouter: ActivatedRoute
  ) {
    this.idevent = this.paramsrouter.snapshot.paramMap.get('idevent')!;
    this.formsigvit = fb.group({
      numdocpac: ['', [Validators.required]],
      idevent: ['', [Validators.required]],
      conseventpac: ['', [Validators.required]],
      primernompac: ['', [Validators.required]],
      segundonompac: ['', [Validators.required]],
      primerapepac: ['', [Validators.required]],
      segundoapepac: ['', [Validators.required]],
      signosvitales: this.fb.array([]), // Inicializa como un array vacío
    });
    this.addSignosVitales()
  }

  ngOnInit(): void {
    this.consultauser();
    this.getDataEvent();
  }

  getDataEvent() {
    this.service.getEventId(this.idevent).subscribe((res: any) => {
      this.ideventseleccionado = res;
      this.formsigvit.patchValue({
        idevent: this.ideventseleccionado.idevent,
        conseventpac: this.ideventseleccionado.conseventpac,
        numdocpac: this.ideventseleccionado.pacevent_fk.numdocpac,
        primernompac: this.ideventseleccionado.pacevent_fk.primernompac,
        segundonompac: this.ideventseleccionado.pacevent_fk.segundonompac,
        primerapepac: this.ideventseleccionado.pacevent_fk.primerapepac,
        segundoapepac: this.ideventseleccionado.pacevent_fk.segundoapepac,
      });
    });
  }

  get sigvitArray(): FormArray {
    return this.formsigvit.get('signosvitales') as FormArray;
  }

  addSignosVitales() {
    const sigvit = this.formsigvit.get('signosvitales') as FormArray;

    const newSigVital = this.fb.group({
      ta: ['', Validators.required],
      fc: ['', Validators.required],
      fr: ['', Validators.required],
      sat: [''],
      tem: [''],
      dateregsigvit: [''],
      houregsigvit: [''],
      userregsigvit: this.fb.array([
        this.fb.group({
          iduser: ['', Validators.required],
          firstname: [''],
          lastname: [''],
        }),
      ]),
    });

    sigvit.push(newSigVital);

    // Inicializa el filtro para el nuevo registro
    this.filterUser.push([]);
  }

  removeSignosVitales(index: number) {
    this.sigvitArray.removeAt(index);
    this.filterUser.splice(index, 1);
  }

  consultauser() {
    this.servicio.getUsuarios()
      .pipe(
        tap((res: any[]) => {
          this.user = res;
          this.filterUser = []; // Inicializa la lista vacía
        }),
        catchError((err) => {
          console.error('Error:', err);
          Swal.fire('Error', 'Ocurrió un error al cargar los usuarios', 'error');
          throw err;
        })
      )
      .subscribe();
  }

  getUserArray(index: number): FormArray {
    return this.sigvitArray.at(index).get('userregsigvit') as FormArray;
  }

  addUser(index: number) {
    const userFormGroup = this.fb.group({
      iduser: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
    });
    this.getUserArray(index).push(userFormGroup);
  }

  removeUser(sigvitIndex: number, userIndex: number) {
    this.getUserArray(sigvitIndex).removeAt(userIndex);
  }

  filterUsuarios(event: any, index: number) {
    const query = event.target.value.toLowerCase();
    this.filterUser[index] = this.user.filter((user) =>
      `${user.firstname} ${user.lastname}`.toLowerCase().includes(query)
    );
  }

  selectUsuarios(user: any, sigvitIndex: number, userIndex: number) {
    const formGroup = this.getUserArray(sigvitIndex).at(userIndex) as FormGroup;
    formGroup.patchValue({
      iduser: user.iduser,
      firstname: `${user.firstname} ${user.lastname} - ${user.carguser_fk?.detcarguser}`,
      lastname: user.lastname,
    });
    this.filterUser[sigvitIndex] = [];
  }

    createsigvit() {
      const sigvitArray = this.formsigvit.value.signosvitales;
      console.log('informacion del formulario ',sigvitArray)

      sigvitArray.forEach((sigvital: any, index: number) => {
        const strucksigvit = {
          ta: sigvital.ta,
          fc: sigvital.fc,
          fr: sigvital.fr,
          sat: sigvital.sat,
          tem: sigvital.tem,
          dateregsigvit: sigvital.dateregsigvit,
          houregsigvit: sigvital.houregsigvit,
          reguser_fk: { iduser: sigvital.userregsigvit[0]?.iduser },
          estsigvit_fk: { idstatus: 1 },
          eventsigvit_fk:{idevent:this.formsigvit.value.idevent }
        };
        console.log('la estructura',strucksigvit)

        this.service.addsignosvitales(strucksigvit)
          .pipe(
            tap((res) => {
              Swal.fire('Éxito', 'Registro guardado correctamente', 'success');
              this.router.navigateByUrl(`/eventos`)
            }),
            catchError((err) => {
              Swal.fire('Error', 'Ocurrió un problema al guardar', 'error');
              throw err;
            })
          )
          .subscribe();
      });
    }
}


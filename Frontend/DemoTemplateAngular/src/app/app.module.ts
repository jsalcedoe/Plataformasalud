import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from "@angular/core";

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";
import { RouterModule } from "@angular/router";
import { ToastrModule } from 'ngx-toastr';

import { AppComponent } from "./app.component";
import { AdminLayoutComponent } from "./layouts/admin-layout/admin-layout.component";
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';

import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

import { AppRoutingModule } from "./app-routing.module";
import { ComponentsModule } from "./components/components.module";
import { TestComponent } from './page/test/test.component';
import { ConfigComponent } from './pages/config/config.component';
import { EstadosComponent } from './pages/config/estados/estados.component';
import { CargosComponent } from './pages/config/cargos/cargos.component';
import { PermisosComponent } from './pages/config/permisos/permisos.component';
import { DepartamentosComponent } from './pages/config/departamentos/departamentos.component';
import { CiudadesComponent } from './pages/config/ciudades/ciudades.component';
import { DiagnosticosComponent } from './pages/config/diagnosticos/diagnosticos.component';
import { RolesComponent } from './pages/config/roles/roles.component';
import { PermisorolComponent } from './pages/config/permisorol/permisorol.component';
import { UsuariosComponent } from './pages/config/usuarios/usuarios.component';
import { TipopacienteComponent } from './pages/config/tipopaciente/tipopaciente.component';
import { EntidadesComponent } from './pages/config/entidades/entidades.component';
import { OrigendestinoComponent } from './pages/config/origendestino/origendestino.component';
import { TipodxComponent } from './pages/config/tipodx/tipodx.component';
import { TipoanestesiaComponent } from './pages/config/tipoanestesia/tipoanestesia.component';
import { TipopxComponent } from './pages/config/tipopx/tipopx.component';
import { ProcedimientoexamenesComponent } from './pages/config/procedimientoexamenes/procedimientoexamenes.component';
import { UbicacionesComponent } from './pages/config/ubicaciones/ubicaciones.component';
import { CamasComponent } from './pages/config/camas/camas.component';
import { TiponotaComponent } from './pages/config/tiponota/tiponota.component';
import { PlantillasComponent } from './pages/config/plantillas/plantillas.component';
import { PrestadorservicioComponent } from './pages/config/prestadorservicio/prestadorservicio.component';
import { RepresentantelegalComponent } from './pages/config/representantelegal/representantelegal.component';
import { TipodocumentosComponent } from './pages/config/tipodocumentos/tipodocumentos.component';
import { TipoeapbComponent } from './pages/config/tipoeapb/tipoeapb.component';
import { TipoplantillaComponent } from './pages/config/tipoplantilla/tipoplantilla.component';
import { CreadepComponent } from './pages/config/departamentos/creadep/creadep.component';
import { CreacamasComponent } from './pages/config/camas/creacamas/creacamas.component';
import { CreacargosComponent } from './pages/config/cargos/creacargos/creacargos.component';
import { CreaciudadComponent } from './pages/config/ciudades/creaciudad/creaciudad.component';
import { CreadxComponent } from './pages/config/diagnosticos/creadx/creadx.component';
import { CreaentidadComponent } from './pages/config/entidades/creaentidad/creaentidad.component';
import { CreaestadosComponent } from './pages/config/estados/creaestados/creaestados.component';
import { CreaorigdesComponent } from './pages/config/origendestino/creaorigdes/creaorigdes.component';
import { CreapermisorolComponent } from './pages/config/permisorol/creapermisorol/creapermisorol.component';
import { CreapermisosComponent } from './pages/config/permisos/creapermisos/creapermisos.component';
import { CreaplantillasComponent } from './pages/config/plantillas/creaplantillas/creaplantillas.component';
import { CreaprestadorComponent } from './pages/config/prestadorservicio/creaprestador/creaprestador.component';
import { CreapxexComponent } from './pages/config/procedimientoexamenes/creapxex/creapxex.component';
import { ReplegalComponent } from './pages/config/representantelegal/replegal/replegal.component';
import { CrearolComponent } from './pages/config/roles/crearol/crearol.component';
import { CreatipoanestComponent } from './pages/config/tipoanestesia/creatipoanest/creatipoanest.component';
import { CreatipodocComponent } from './pages/config/tipodocumentos/creatipodoc/creatipodoc.component';
import { CreatipodxComponent } from './pages/config/tipodx/creatipodx/creatipodx.component';
import { CreatipoeapbComponent } from './pages/config/tipoeapb/creatipoeapb/creatipoeapb.component';
import { CreatiponotaComponent } from './pages/config/tiponota/creatiponota/creatiponota.component';
import { CreatipacComponent } from './pages/config/tipopaciente/creatipac/creatipac.component';
import { CreatipotempComponent } from './pages/config/tipoplantilla/creatipotemp/creatipotemp.component';
import { CreatipopxComponent } from './pages/config/tipopx/creatipopx/creatipopx.component';
import { CreaubicaComponent } from './pages/config/ubicaciones/creaubica/creaubica.component';
import { CreauserComponent } from './pages/config/usuarios/creauser/creauser.component';
import { AdministrativoComponent } from './pages/administrativo/administrativo.component';
import { AsistencialComponent } from './pages/asistencial/asistencial.component';
import { PacientesComponent } from './pages/pacientes/pacientes.component';
import { CreapacComponent } from './pages/pacientes/creapac/creapac.component';
import { MedicoComponent } from './pages/medico/medico.component';
import { EvolucionenfermeriaComponent } from './pages/asistencial/evolucionenfermeria/evolucionenfermeria.component';
import { RegistrosignosvitalesComponent } from './pages/asistencial/registrosignosvitales/registrosignosvitales.component';
import { HistoriaclinicaComponent } from './pages/medico/historiaclinica/historiaclinica.component';
import { EvolucionmedicaComponent } from './pages/medico/evolucionmedica/evolucionmedica.component';
import { DescipcionquirurgicaComponent } from './pages/medico/descipcionquirurgica/descipcionquirurgica.component';



@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ComponentsModule,
    NgbModule,
    RouterModule,
    AppRoutingModule,
    ToastrModule.forRoot()
  ],
  declarations: [
    AppComponent, 
    AdminLayoutComponent, 
    AuthLayoutComponent, 
    TestComponent, 
    ConfigComponent,
    EstadosComponent, 
    CargosComponent, 
    PermisosComponent, 
    DepartamentosComponent, 
    CiudadesComponent, 
    DiagnosticosComponent, 
    RolesComponent, 
    PermisorolComponent, 
    UsuariosComponent, 
    TipopacienteComponent, 
    EntidadesComponent, 
    OrigendestinoComponent, 
    TipodxComponent, 
    TipoanestesiaComponent, 
    TipopxComponent, 
    ProcedimientoexamenesComponent, 
    UbicacionesComponent, 
    CamasComponent, 
    TiponotaComponent, 
    PlantillasComponent, 
    PrestadorservicioComponent, 
    RepresentantelegalComponent, 
    TipodocumentosComponent, 
    TipoeapbComponent, 
    TipoplantillaComponent, 
    CreadepComponent, 
    CreacamasComponent, 
    CreacargosComponent, 
    CreaciudadComponent,
    CreadxComponent, 
    CreaentidadComponent, 
    CreaestadosComponent, 
    CreaorigdesComponent, 
    CreapermisorolComponent, 
    CreapermisosComponent, 
    CreaplantillasComponent, 
    CreaprestadorComponent, 
    CreapxexComponent, 
    ReplegalComponent, 
    CrearolComponent, 
    CreatipoanestComponent, 
    CreatipodocComponent, 
    CreatipodxComponent, 
    CreatipoeapbComponent, 
    CreatiponotaComponent, 
    CreatipacComponent, 
    CreatipotempComponent, 
    CreatipopxComponent, 
    CreaubicaComponent, 
    CreauserComponent, 
    AdministrativoComponent, 
    AsistencialComponent, 
    PacientesComponent, 
    CreapacComponent, 
    MedicoComponent, 
    EvolucionenfermeriaComponent, 
    RegistrosignosvitalesComponent, 
    HistoriaclinicaComponent, 
    EvolucionmedicaComponent, 
    DescipcionquirurgicaComponent
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}

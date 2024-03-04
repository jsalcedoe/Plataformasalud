import { Routes } from "@angular/router";

import { DashboardComponent } from "../../pages/dashboard/dashboard.component";
import { IconsComponent } from "../../pages/icons/icons.component";
import { MapComponent } from "../../pages/map/map.component";
import { NotificationsComponent } from "../../pages/notifications/notifications.component";
import { UserComponent } from "../../pages/user/user.component";
import { TablesComponent } from "../../pages/tables/tables.component";
import { TypographyComponent } from "../../pages/typography/typography.component";
import { ConfigComponent } from "src/app/pages/config/config.component";
import { CiudadesComponent } from "src/app/pages/config/ciudades/ciudades.component";
import { DepartamentosComponent } from "src/app/pages/config/departamentos/departamentos.component";
import { CamasComponent } from "src/app/pages/config/camas/camas.component";
import { CargosComponent } from "src/app/pages/config/cargos/cargos.component";
import { DiagnosticosComponent } from "src/app/pages/config/diagnosticos/diagnosticos.component";
import { EntidadesComponent } from "src/app/pages/config/entidades/entidades.component";
import { EstadosComponent } from "src/app/pages/config/estados/estados.component";
import { OrigendestinoComponent } from "src/app/pages/config/origendestino/origendestino.component";
import { PermisorolComponent } from "src/app/pages/config/permisorol/permisorol.component";
import { PermisosComponent } from "src/app/pages/config/permisos/permisos.component";
import { PlantillasComponent } from "src/app/pages/config/plantillas/plantillas.component";
import { PrestadorservicioComponent } from "src/app/pages/config/prestadorservicio/prestadorservicio.component";
import { ProcedimientoexamenesComponent } from "src/app/pages/config/procedimientoexamenes/procedimientoexamenes.component";
import { RepresentantelegalComponent } from "src/app/pages/config/representantelegal/representantelegal.component";
import { RolesComponent } from "src/app/pages/config/roles/roles.component";
import { TipoanestesiaComponent } from "src/app/pages/config/tipoanestesia/tipoanestesia.component";
import { TipodocumentosComponent } from "src/app/pages/config/tipodocumentos/tipodocumentos.component";
import { TipodxComponent } from "src/app/pages/config/tipodx/tipodx.component";
import { TipoeapbComponent } from "src/app/pages/config/tipoeapb/tipoeapb.component";
import { TiponotaComponent } from "src/app/pages/config/tiponota/tiponota.component";
import { TipopacienteComponent } from "src/app/pages/config/tipopaciente/tipopaciente.component";
import { TipoplantillaComponent } from "src/app/pages/config/tipoplantilla/tipoplantilla.component";
import { TipopxComponent } from "src/app/pages/config/tipopx/tipopx.component";
import { UbicacionesComponent } from "src/app/pages/config/ubicaciones/ubicaciones.component";
import { UsuariosComponent } from "src/app/pages/config/usuarios/usuarios.component";
import { CreadepComponent } from "src/app/pages/config/departamentos/creadep/creadep.component";
import { CreacamasComponent } from "src/app/pages/config/camas/creacamas/creacamas.component";
import { CreacargosComponent } from "src/app/pages/config/cargos/creacargos/creacargos.component";
import { CreaciudadComponent } from "src/app/pages/config/ciudades/creaciudad/creaciudad.component";
import { CreadxComponent } from "src/app/pages/config/diagnosticos/creadx/creadx.component";
import { Component } from "@angular/core";
import { CreaentidadComponent } from "src/app/pages/config/entidades/creaentidad/creaentidad.component";
import { CreaestadosComponent } from "src/app/pages/config/estados/creaestados/creaestados.component";
import { CreaorigdesComponent } from "src/app/pages/config/origendestino/creaorigdes/creaorigdes.component";
import { CreapermisorolComponent } from "src/app/pages/config/permisorol/creapermisorol/creapermisorol.component";
import { CreapermisosComponent } from "src/app/pages/config/permisos/creapermisos/creapermisos.component";
import { CreaplantillasComponent } from "src/app/pages/config/plantillas/creaplantillas/creaplantillas.component";
import { CreaprestadorComponent } from "src/app/pages/config/prestadorservicio/creaprestador/creaprestador.component";
import { CreapxexComponent } from "src/app/pages/config/procedimientoexamenes/creapxex/creapxex.component";
import { ReplegalComponent } from "src/app/pages/config/representantelegal/replegal/replegal.component";
import { CrearolComponent } from "src/app/pages/config/roles/crearol/crearol.component";
import { CreatipoanestComponent } from "src/app/pages/config/tipoanestesia/creatipoanest/creatipoanest.component";
import { CreatipodocComponent } from "src/app/pages/config/tipodocumentos/creatipodoc/creatipodoc.component";
import { CreatipodxComponent } from "src/app/pages/config/tipodx/creatipodx/creatipodx.component";
import { CreatipoeapbComponent } from "src/app/pages/config/tipoeapb/creatipoeapb/creatipoeapb.component";
import { CreatiponotaComponent } from "src/app/pages/config/tiponota/creatiponota/creatiponota.component";
import { CreatipacComponent } from "src/app/pages/config/tipopaciente/creatipac/creatipac.component";
import { CreatipotempComponent } from "src/app/pages/config/tipoplantilla/creatipotemp/creatipotemp.component";
import { CreatipopxComponent } from "src/app/pages/config/tipopx/creatipopx/creatipopx.component";
import { CreaubicaComponent } from "src/app/pages/config/ubicaciones/creaubica/creaubica.component";
import { CreauserComponent } from "src/app/pages/config/usuarios/creauser/creauser.component";
import { AdministrativoComponent } from "src/app/pages/administrativo/administrativo.component";
import { AsistencialComponent } from "src/app/pages/asistencial/asistencial.component";
import { PacientesComponent } from "src/app/pages/pacientes/pacientes.component";
import { CreapacComponent } from "src/app/pages/pacientes/creapac/creapac.component";
import { MedicoComponent } from "src/app/pages/medico/medico.component";
import { EvolucionenfermeriaComponent } from "src/app/pages/asistencial/evolucionenfermeria/evolucionenfermeria.component";
import { RegistrosignosvitalesComponent } from "src/app/pages/asistencial/registrosignosvitales/registrosignosvitales.component";
import { HistoriaclinicaComponent } from "src/app/pages/medico/historiaclinica/historiaclinica.component";
import { EventosComponent } from "src/app/pages/eventos/eventos.component";
import { EvolucionmedicaComponent } from "src/app/pages/medico/evolucionmedica/evolucionmedica.component";
import { DescripcionquirurgicaComponent } from "src/app/pages/medico/descripcionquirurgica/descripcionquirurgica.component";
import { DiagnosticosatencionComponent } from "src/app/pages/medico/diagnosticosatencion/diagnosticosatencion.component";
import { ProcedimientosComponent } from "src/app/pages/medico/procedimientos/procedimientos.component";

// import { RtlComponent } from "../../pages/rtl/rtl.component";

export const AdminLayoutRoutes: Routes = [
  { path: "dashboard", component: DashboardComponent },
  { path:'administrativo',component:AdministrativoComponent},
  { path:'asistencial',component:AsistencialComponent },
  { path:'pacientes',component:PacientesComponent},
  { path:'medico',component:MedicoComponent},
  { path:"icons", component: IconsComponent },
  { path:"maps", component: MapComponent },
  { path:"notifications", component: NotificationsComponent },
  { path:"user", component: UserComponent },
  { path:"tables", component: TablesComponent },
  { path:"typography", component: TypographyComponent },
  { path:'historia_clinica',component:IconsComponent},
  { path:'config',component:ConfigComponent},
  { path:'ciudades',component:CiudadesComponent},
  { path:'departamentos',component:DepartamentosComponent},
  { path:'camas',component:CamasComponent},
  { path:'cargos',component:CargosComponent},
  { path:'diagnosticos',component:DiagnosticosComponent},
  { path:'entidades',component:EntidadesComponent},
  { path:'estados',component:EstadosComponent},
  { path:'origendestino',component:OrigendestinoComponent},
  { path:'permisorol',component:PermisorolComponent},
  { path:'permisos',component:PermisosComponent},
  { path:'plantillas',component:PlantillasComponent},
  { path:'prestadorservicio',component:PrestadorservicioComponent},
  { path:'procedimientoexamenes',component:ProcedimientoexamenesComponent},
  { path:'representantelegal',component:RepresentantelegalComponent},
  { path:'roles',component:RolesComponent},
  { path:'tipoanestesia',component:TipoanestesiaComponent},
  { path:'tipodocumentos',component:TipodocumentosComponent},
  { path:'tipodx',component:TipodxComponent},
  { path:'tipoeapb',component:TipoeapbComponent},
  { path:'tiponota',component:TiponotaComponent},
  { path:'tipopaciente',component:TipopacienteComponent},
  { path:'tipoplantilla',component:TipoplantillaComponent},
  { path:'tipopx',component:TipopxComponent},
  { path:'ubicaciones',component:UbicacionesComponent},
  { path:'usuarios',component:UsuariosComponent},
  { path:'creadep',component:CreadepComponent},
  { path:'creacamas',component:CreacamasComponent},
  { path:'creacargos',component:CreacargosComponent},
  { path:'creaciudad',component:CreaciudadComponent},
  { path:'creadx',component:CreadxComponent},
  { path:'creaentidad',component:CreaentidadComponent},
  { path:'creaestados',component:CreaestadosComponent},
  { path:'creaorigdes',component:CreaorigdesComponent},
  { path:'creapermisorol',component:CreapermisorolComponent},
  { path:'creapermisos',component:CreapermisosComponent},
  { path:'creaplantillas',component:CreaplantillasComponent},
  { path:'creaprestador',component:CreaprestadorComponent},
  { path:'creapxex',component:CreapxexComponent},
  { path:'creareplegal',component:ReplegalComponent},
  { path:'crearol',component:CrearolComponent},
  { path:'creatipoanest',component:CreatipoanestComponent},
  { path:'creatipodoc',component:CreatipodocComponent},
  { path:'creatipodx',component:CreatipodxComponent},
  { path:'creatipoeapb',component:CreatipoeapbComponent},
  { path:'creatiponota',component:CreatiponotaComponent},
  { path:'creatipac',component:CreatipacComponent},
  { path:'creatipotemp',component:CreatipotempComponent},
  { path:'creatipopx',component:CreatipopxComponent},
  { path:'creaubica',component:CreaubicaComponent},
  { path:'creauser',component:CreauserComponent},
  { path:'creapac',component:CreapacComponent},
  { path:'evolucionenfermeria',component:EvolucionenfermeriaComponent},
  { path:'registrosignosvitales',component:RegistrosignosvitalesComponent},
  { path:'historiaclinica',component:HistoriaclinicaComponent},
  { path:'eventos',component:EventosComponent},
  { path:'evolucionmedica',component:EvolucionmedicaComponent},
  { path:'descripcionquirurgica',component:DescripcionquirurgicaComponent},
  { path:'diagnosticosatencion',component:DiagnosticosatencionComponent},
  { path:'procedimientos',component:ProcedimientosComponent}


  // { path: "rtl", component: RtlComponent }
];

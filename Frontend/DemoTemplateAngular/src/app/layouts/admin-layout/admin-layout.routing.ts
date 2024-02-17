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
// import { RtlComponent } from "../../pages/rtl/rtl.component";

export const AdminLayoutRoutes: Routes = [
  { path: "dashboard", component: DashboardComponent },
  { path: "icons", component: IconsComponent },
  { path: "maps", component: MapComponent },
  { path: "notifications", component: NotificationsComponent },
  { path: "user", component: UserComponent },
  { path: "tables", component: TablesComponent },
  { path: "typography", component: TypographyComponent },
  {path:'historia_clinica',component:IconsComponent},
  {path: 'config',component:ConfigComponent},
  {path:'ciudades',component:CiudadesComponent},
  {path:'departamentos',component:DepartamentosComponent},
  {path: 'camas',component:CamasComponent},
  {path:'cargos',component:CargosComponent},
  {path:'diagnosticos',component:DiagnosticosComponent},
  {path:'entidades',component:EntidadesComponent},
  {path:'estados',component:EstadosComponent},
  {path:'origendestino',component:OrigendestinoComponent},
  {path:'permisorol',component:PermisorolComponent},
  {path:'permisos',component:PermisosComponent},
  {path:'plantillas',component:PlantillasComponent},
  {path:'prestadorservicio',component:PrestadorservicioComponent},
  {path:'procedimientoexamenes',component:ProcedimientoexamenesComponent},
  {path:'representantelegal',component:RepresentantelegalComponent},
  {path:'roles',component:RolesComponent},
  {path:'tipoanestesia',component:TipoanestesiaComponent},
  {path:'tipodocumentos',component:TipodocumentosComponent},
  {path:'tipodx',component:TipodxComponent},
  {path:'tipoeapb',component:TipoeapbComponent},
  {path:'tiponota',component:TiponotaComponent},
  {path:'tipopaciente',component:TipopacienteComponent},
  {path:'tipoplantilla',component:TipoplantillaComponent},
  {path:'tipopx',component:TipopxComponent},
  {path:'ubicaciones',component:UbicacionesComponent},
  {path:'usuarios',component:UsuariosComponent}

  // { path: "rtl", component: RtlComponent }
];

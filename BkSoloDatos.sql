CREATE DATABASE  IF NOT EXISTS `plataformasalud` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `plataformasalud`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: plataformasalud
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `agmed`
--

LOCK TABLES `agmed` WRITE;
/*!40000 ALTER TABLE `agmed` DISABLE KEYS */;
/*!40000 ALTER TABLE `agmed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ascit`
--

LOCK TABLES `ascit` WRITE;
/*!40000 ALTER TABLE `ascit` DISABLE KEYS */;
/*!40000 ALTER TABLE `ascit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cargo_user`
--

LOCK TABLES `cargo_user` WRITE;
/*!40000 ALTER TABLE `cargo_user` DISABLE KEYS */;
INSERT INTO `cargo_user` VALUES (1,'2024-09-04','AUXILIAR DE ARCHIVO','AUXARCH',1,1),(2,'2024-09-04','AUXILIAR DE ENFERMERIA DE RECUPERACION','AUXQX',1,2),(3,'2024-09-04','AUXILIAR DE ENFERMERIA HOSPITALIZACION','AUXHX',1,2),(4,'2024-09-04','MEDICO HOSPITALARIO','MEDHX',1,3),(5,'2024-09-04','MEDICO CIRUJANO PLASTICO','MEDCX',1,3),(6,'2024-09-04','INSTRUMENTADORA QUIRURGICA','ENFINST',1,2);
/*!40000 ALTER TABLE `cargo_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ciudades`
--

LOCK TABLES `ciudades` WRITE;
/*!40000 ALTER TABLE `ciudades` DISABLE KEYS */;
INSERT INTO `ciudades` VALUES (1,73001,'2024-08-25','IBAGUE',1,1),(2,73002,'2024-08-25','ESPINAL',1,1),(3,11001,'2024-08-25','BOGOTA',2,1),(4,1103,'2024-08-25','CHIA',2,1),(5,11004,'2024-08-25','GIRARDOT',2,1),(6,76001,'2024-12-06','CALI',5,1);
/*!40000 ALTER TABLE `ciudades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `condpac`
--

LOCK TABLES `condpac` WRITE;
/*!40000 ALTER TABLE `condpac` DISABLE KEYS */;
INSERT INTO `condpac` VALUES (1,'2024-08-25','OBSERVACION',NULL,'OBS',1),(2,'2024-08-25','HOSPITALIZAR',NULL,'HX',1),(3,'2024-08-25','SALIDA',NULL,'EG',1);
/*!40000 ALTER TABLE `condpac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `coninfpx`
--

LOCK TABLES `coninfpx` WRITE;
/*!40000 ALTER TABLE `coninfpx` DISABLE KEYS */;
INSERT INTO `coninfpx` VALUES (1,'LIFTING CERVICO - FACIAL (RITIDECTOMIA)','2025-02-01','INSTRUCCIONES\n Es importante que lea esta información de forma cuidadosa y completa. Por favor, ponga su firma en cada página, \nindicando así que ha leído la página, y firme el consentimiento para la cirugía propuesta por su cirujano.\n INTRODUCCIÓN\n El lifting o estiramiento facial o ritidectomía es un procedimiento quirúrgico para mejorar los signos visibles de \nenvejecimiento de la cara y el cuello. Conforme el individuo envejece, la piel y los músculos de la región de la cara \nempiezan a perder tono. El lifting facial no puede detener el proceso de envejecimiento, pero puede mejorar los signos \nmás visibles del envejecimiento mediante el tensado de las estructuras más profundas, la readaptación de la piel de la \ncara y el cuello, y la extirpación de áreas seleccionadas de grasa. Un lifting facial puede realizarse aisladamente, o en \nconjunto con otros procedimientos, como un lifting de cejas (frontal), liposucción, cirugía de los párpados o cirugía \nnasal. \nEl lifting facial se individualiza para cada paciente. El mejor candidato para un lifting facial es el que tiene una cara y \ncuello que han empezado a relajarse, pero cuya piel tiene elasticidad y cuya estructura ósea está bien definida.\n TRATAMIENTO ALTERNATIVO\n Las formas alternativas de manejo consisten en no tratar la laxitud de la cara y el cuello con un lifting facial \n(ritidectomía). Puede intentarse mejorar la laxitud cutánea, arrugas y depósitos grasos mediante otros tratamientos o \ncirugía, como los peeling químicos faciales o la liposucción. Existen riesgos y complicaciones potenciales asociados \ncon las formas alternativas de tratamiento.\n RIESGOS DEL LIFTING FACIAL (RITIDECTOMIA)\n Cualquier procedimiento quirúrgico entraña un cierto grado de riesgo y es importante que usted comprenda los \nriesgos asociados al lifting facial. La decisión individual de someterse a una intervención quirúrgica se basa en la \ncomparación del riesgo con el beneficio potencial. Aunque la mayoría de los pacientes no experimentan las siguientes \ncomplicaciones, usted debería discutir cada una de ellas con su cirujano plástico para asegurarse de que comprende los \nriesgos, complicaciones potenciales y consecuencias del lifting facial (ritidectomía).\n 1. Sangrado. Es posible, aunque raro, que se presente un episodio de hemorragia durante o después de la cirugía. Si se \ndesarrolla una hemorragia postoperatoria, puede requerir tratamiento de urgencia para extraer la sangre acumulada, o \ntransfusión de sangre. No debe tomar aspirina o antiinflamatorios desde 10 días antes de la cirugía, puesto que pueden \naumentar el riesgo de problemas de sangrado.\n La hipertensión (aumento de la presión sanguínea) que no está bien controlada médicamente puede ser causa de \nsangrado durante o después de la cirugía. Los cúmulos de sangre bajo la piel pueden retrasar la curación y causar \ncicatrización excesiva.\n 2. Infección. La infección después de la cirugía es muy rara. Si ocurre una infección, puede ser necesario tratamiento \nadicional, incluyendo antibióticos.\n 3. Cicatrización. Aunque se espera una buena curación de la herida después del procedimiento quirúrgico, pueden \ndarse cicatrices anormales tanto en la piel como en los tejidos profundos. Las cicatrices pueden ser inestéticas o de \ndiferente color al de la piel circundante. Existe la posibilidad demarcas visibles por las suturas. Pueden necesitarse \ntratamientos adicionales para tratar la cicatrización anormal.\n 4. Retraso en la cicatrización: Existe la posibilidad de una apertura de la herida o de una cicatrización retrasada. \nAlgunas zonas de la cara pueden no curar normalmente y tardar un tiempo largo en cicatrizar.\nsegún el tipo de procedimiento de lifting facial empleado. La lesión de estructuras profundas puede ser temporal o \npermanente.\n 2. Dolor crónico. Un dolor crónico puede ocurrir muy infrecuentemente tras una ritidectomía. Es una complicación \nmuy rara, causada por la cicatrización dolorosa de un nervio, y que casi siempre se soluciona por sí sola.\n 3. Asimetría.  La cara humana es normalmente asimétrica. Puede haber pequeñas variaciones de simetría, entre un \nlado y otro en el resultado de un lifting facial, más frecuentemente cuando las estructuras óseas ya son asimétricas en el \npreoperatorio.\n 4.   \n5.  \n6.  \n7.   \n8.   \n9.   \nAnestesia. Tanto la anestesia local como la general implican un riesgo. Existe la posibilidad de complicaciones, \nlesiones   e incluso muerte, por cualquier forma de anestesia o sedación quirúrgica.\n Alteraciones o cáncer de piel. El lifting facial es un procedimiento para recolocar la piel y las estructuras \nprofundas. Las alteraciones de la piel y el cáncer de piel pueden ocurrir independientemente del lifting facial.\n Resultado insatisfactorio. Existe la posibilidad de un resultado insatisfactorio en el lifting facial. \nInfrecuentemente se necesita realizar cirugía adicional para mejorar los resultados.\n Reacciones alérgicas. En casos raros se han descrito alergias locales al esparadrapo, material de sutura o \npreparados tópicos. Pueden ocurrir reacciones sistémicas, que son más graves, frente a medicaciones usadas \ndurante la cirugía o prescritas después las reacciones alérgicas pueden requerir tratamiento adicional.\n Pérdida de pelo. Puede ocurrir pérdida del pelo en áreas de la cara donde la piel se levanta durante la cirugía. La \nocurrencia de este hecho no es predecible y generalmente se soluciona al cabo de unos meses.\n Efectos a largo plazo. Pueden ocurrir alteraciones subsecuentes en el aspecto de la cara como consecuencia del \nenvejecimiento, pérdida o ganancia de peso, exposición al sol, u otras circunstancias no relacionadas con la \nritidectomía.  El lifting facial no detiene el proceso de envejecimiento ni produce recolocación permanente de los \ntejidos de la cara y del cuello. Puede necesitarse en un futuro cirugía u otros tratamientos para mantener los \nresultados de una ritidectomía.\n PÓLIZA PARA CIRUGÍAS PLÁSTICAS.\n Las Sociedades Médicas y Científicas así como la Sociedad Colombiana de Cirugía Plástica y la Sociedad Colombiana \nde Anestesiología sugieren a todos los pacientes adquirir una póliza para su cirugía plástica, la cual tiene una cobertura \ny condiciones especiales para atender el 99% de las complicaciones post-quirúrgicas que puedan presentarse en \nCirugías Estéticas. Por favor pregunte en la Unidad Médico Quirúrgica de Cirugía Plástica por la póliza \nrecomendada.\n NECESIDAD DE CIRUGÍA ADICIONAL\n Existen muchas condiciones variables además de los riesgos y complicaciones quirúrgicas potenciales que pueden \ninfluenciar los resultados a largo plazo de un lifting facial. Aunque los riesgos y complicaciones son raros, los riesgos \ncitados están particularmente asociados con la ritidectomía. Pueden ocurrir otros riesgos y complicaciones, pero son \ntodavía más infrecuentes. Si ocurren complicaciones, puede ser necesaria la cirugía adicional u otros tratamientos. La \npráctica de la Medicina y la Cirugía no es una ciencia exacta, y aunque se esperan buenos resultados, no hay garantía \nexplicita o implícita sobre los resultados que pueden obtenerse\n RESPONSABILIDADES ECONÓMICAS\n Fecha de Diligenciamiento \nde este Documento:\n Diligenciado por: \nEl coste de la cirugía resulta de diversos cargos por servicios prestados. El total incluye los honorarios del cirujano, el \ncoste del material quirúrgico, anestesia, dependiendo de dónde se realice la cirugía. Puede haber costes adicionales si \nse dan complicaciones derivadas de la cirugía. Los cargos por cirugía secundaria o cirugía hospitalaria de día \nrelacionadas con revisión quirúrgica podrían también correr a su cargo.\n ES IMPORTANTE QUE LEA CUIDADOSAMENTE LA INFORMACIÓN ANTERIOR Y HAYAN SIDO \nRESPONDIDAS TODAS SUS PREGUNTAS ANTES DE QUE FIRME EL CONSENTIMIENTO DE LA \nPÁGINA SIGUIENTE.\n CONSENTIMIENTO PARA CIRUGÍA / PROCEDIMIENTO O TRATAMIENTO\n 1.  P o r \nl a \np r e s e n t e \na u t o r i z o \na l \nD r . \n___________________________________________________________________\n __________________________________________________ y a los ayudantes que sean seleccionados para \nrealizar el siguiente procedimiento o tratamiento:  LIFTING CERVICO - FACIAL (RITIDECTOMIA)\n 2.  \n3.  \n4.   \n5.  \n6.  \n7.  \n8.  \nHe leído, comprendido y firmado las páginas del folleto informativo adjunto: “Consentimiento informado para \nliftingfacial (ritidectomía)”.\n Doy fe de no haber omitido o alterado datos al exponer mi historial y antecedentes clínico-quirúrgicos, \nespecialmente los referidos a alergias y enfermedades o riesgos personales.\n Soy consciente de que durante el curso de la operación y el tratamiento médico o anestesia, pueden darse \ncondiciones imprevistas que necesiten procedimientos diferentes a los propuestos. Por la presente autorizo al \ncirujano citado y a sus ayudantes a realizar estos otros procedimientos en el ejercicio de su juicio profesional \nnecesario y deseable. La autorización que otorga este párrafo incluirá cualquier condición que requiera \ntratamiento y que no fuera conocida por el cirujano en el momento de iniciar el procedimiento.\n Declaro no utilizar ningún fármaco adictivo ó psicofármacos.\n He tenido la oportunidad de ver fotografías de casos similares al mio.\n Doy el consentimiento para la administración de los anestésicos que se consideren necesarios o aconsejables. \nComprendo que cualquier forma de anestesia entraña un riesgo y la posibilidad de complicaciones, lesiones y a \nveces muerte.\n Estoy de acuerdo en que no se me ha dado garantía por parte de nadie en cuanto al resultado que puede ser \nobtenido.\n 9.  \nFecha de Diligenciamiento \nde este Documento:\n Diligenciado por: \nDoy el consentimiento para el fotografiado o la filmación de la operación que se va a realizar, incluyendo cualquier \nparte de mi cuerpo, con fines médicos, científicos o educativos y reconozco que este material es propiedad de la \nUnidad Médico Quirúrgica de Cirugía Plástica.\n 10. Con fines de avances en la educación médica, doy el consentimiento para la entrada de observadores en el \nquirófano.\n 11. Entiendo que si llegara a presentar eventualmente una complicación como por ejemplo hemorragia arritmia \ncardíaca paro cardíorespiratorio coágulos en los pulmones reacciones alérgicas que ameriten cuidados especiales \nasumiré los costos de cuidado intensivo y si se presentase un segundo tiempo quirúrgico por ser este un acto \nderivado de muchos factores como la mala cicatrización cicatrización retrasada queloide infección falta de \nretracción de piel, entre otros asumiré costos básicos de anestesia, derechos de sala y estancia hospitalaria \nprolongada\n 12. ME HA SIDO EXPLICADO DE FORMA COMPRENSIBLE:\n Fecha: \nNombre del (a) paciente: \nC.C. No. \nNombre(s) y apellido(s)\n Firma: \nTestigo acompañante \nC.C. No. \nNombre(s) y apellido(s)\n Firma Cirujano \nFirma Anestesiólogo\n Consentimiento Informado para\n LIFTING CERVICO - FACIAL (RITIDECTOMIA)\n Firma del paciente \nNo. Documento\n Huella\n',NULL,1),(2,'BLEFAROPLASTIA','2025-02-01',' INSTRUCCIONES: Es importante que lea esta información de forma cuidadosa y completa. Por favor, ponga su \nfirma en cada página, indicando así que ha leído la página, y el consentimiento para la cirugía propuesta por su \ncirujano.\n INTRODUCCIÓN \nLa blefaroplastia es un procedimiento quirúrgico cuyo fin es eliminar el exceso de piel y músculo de los párpados, \ntanto superiores como inferiores, así como el tejido graso subyacente. La blefaroplastia puede mejorar la piel flácida y \nlas bolsas, y puede ayudar a mejorar la visión en personas mayores que presentan un exceso importante de párpado \nsuperior que cae sobre la pupila. Aunque puede crear un pliegue en el párpado superior de un ojo de tipo asiático, no \nborrará la evidencia de los rasgos raciales o étnicos. La blefaroplastia no elimina las patas de gallo u otras arrugas, ni las \nojeras, oscuras, así como tampoco levanta las cejas caídas. La blefaroplastia es diseñada individualmente para cada \npaciente, dependiendo de sus necesidades particulares. Puede realizarse de forma aislada para los párpados superiores, \ninferiores o en combinación con otros procedimientos quirúrgicos sobre ojos, cara, cejas o nariz. La cirugía de los \npárpados no puede detener el proceso de envejecimiento, pero puede, sin embargo, disminuir el aspecto de piel flácida \ny bolsas en la región de los párpados.\n TRATAMIENTOS ALTERNATIVOS\n 1.   \n2.   \nFormas alternativas de tratamiento incluyen la laxitud cutánea y las bolsas de los párpados mediante cirugía. La \nmejora de la laxitud de la piel, depósitos grasos y arrugas cutáneas puede conseguirse mediante otros tratamientos o \ncirugías, como un estiramiento de la zona frontal o de la zona temporal, cuando esté indicado. Otras formas de cirugía \nde los párpados pueden ser necesarias si existen alteraciones que afecten la función de los párpados, como la caída de \nlos párpados por problemas musculares (ptosis palpebral), o laxitud entre el párpado y el globo ocular (ectropión). Las \narrugas cutáneas menores pueden\n mejorarse mediante peeling químico, láser u otros tratamientos de la piel. Existen riesgos y complicaciones \npotenciales asociadas a las formas alternativas de tratamiento.\n Cualquier procedimiento quirúrgico entraña un cierto grado de riesgo y es importante que usted comprenda los \nriesgos asociados a la blefaroplastia. La decisión individual de someterse a una intervención quirúrgica se basa en la \ncomparación del riesgo con el beneficio potencial. Aunque la mayoría de los pacientes no experimentan las siguientes \ncomplicaciones, usted deberá discutir cada una de ellas con su Cirujano Plástico para asegurarse de que comprende los \nriesgos, complicaciones potenciales y consecuencias de la blefaroplastia.\n Sangrado. Es posible, aunque raro, que se presente un episodio de hemorragia durante o después de la cirugía. El \nsangrado puede ocurrir debajo de la piel o internamente alrededor del globo ocular y el hematoma puede producir \nun estiramiento del nervio óptico y perdida de visión. Si se desarrolla una hemorragia durante la operación o post\noperatoria, puede requerir tratamiento o cirugía de urgencia. No debe tomar aspirina o antiinflamatorios desde 10 \ndías antes de la cirugía, puesto que pueden aumentar el riesgo de problemas de sangrado. La hipertensión \n(aumento de la presión sanguínea) que no está bien controlada medicadamente puede ser causa de sangrado \ndurante o después de la cirugía. Los cúmulos de sangre bajo los párpados pueden retrasar la curación y causar \ncicatrización excesiva. Su cirujano puede recomendarle extraer una unidad de su propia sangre o reserva de sangre \nque podría ser utilizada si fuera necesaria una transfusión sanguínea después de la cirugía.\n Infección. La infección después de la cirugía es muy rara. Si ocurre una infección, puede ser necesario tratamiento adicional, incluyendo antibióticos. \n3.  \n4.   \n5.   \n6.   \n7.   \nCicatrización. Aunque se espera una buena curación de la herida después del procedimiento quirúrgico, pueden \ndarse cicatrices anormales tanto en los párpados como en los tejidos profundos.  En casos raros pueden resultar \ncicatrices anormales, que pueden ser inestéticas o de diferente color al de la piel circundante. Existe la posibilidad \nde marcas visibles en el párpado o pequeños quistes cutáneos causados por las suturas. Pueden necesitarse \ntratamientos adicionales para tratar la cicatrización anormal.\n Problemas de sequedad ocular. Después de una blefaroplastia pueden quedar alteraciones permanentes en la \nproducción de lágrimas. Es raro que ocurra este hecho, y no es enteramente predecible. Los individuos que tienen \nhabitualmente sequedad ocular deben tener precaución especial a la hora de considerar someterse a una \nblefaroplastia, con antecedentes de enfermedades del colágeno.\n Asimetría. La cara humana y la región de los párpados es asimétrica normalmente. Puede existir variación entre \nlos dos lados después de una blefaroplastia.\n Alteraciones de la piel. La blefaroplastia es un procedimiento quirúrgico que tensa la piel flácida y las estructuras \nprofundas del párpado. Las enfermedades y el cáncer de piel pueden desarrollarse de que se haya realizado cirugía \nen el párpado independientemente de que se haya realizado cirugía en el párpado.\n Ectropion. La separación entre el párpado inferior y el globo ocular es una complicación rara. Puede necesitarse \ncirugía adicional para corregir esta alteración.\n 8.  Problemas por exposición corneal. Algunos pacientes experimentan dificultad en cerrar los párpados después de \nla cirugía, y pueden desarrollar problemas en la córnea por desecación. Si ocurre esta rara complicación, pueden \nser necesarios tratamientos o cirugía adicional. Una úlcera corneal puede necesitar de trasplante de córnea con \ncosto asumido por el(la) paciente.\n 9.   \nReacciones alérgicas. En casos raros se han descrito alergias locales al esparadrapo, material de sutura o \nproductos tópicos. Las reacciones sistémicas, que son más serias, pueden ocurrir por medicamentos utilizados \ndurante la cirugía o prescritas posteriormente. Las reacciones alérgicas pueden requerir tratamiento adicional.\n 10.  Pérdida de pestañas.  La pérdida de las pestañas puede ocurrir en el párpado inferior, donde se eleva la piel \ndurante la cirugía. La ocurrencia de este hecho no es predecible. La pérdida puede ser temporal o permanente.\n 11.  Infección de conductos lagrimales y daños de los mismos requieren cirugías posteriores.\n 12.  Cicatrización retardada. Existe la posibilidad de apertura de la herida o retraso en la cicatrización.\n 13. Caída descenso del párpado superior llamado ptosis palpebral, el cual se soluciona espontáneamente o con un \nprocedimiento quirúrgico sencillo.\n 14. Efectos a Largo plazo. Pueden ocurrir alteraciones subsecuentes en el aspecto del párpado como resultado del \nenvejecimiento, pérdida o ganancia de peso, exposición al sol, u otras circunstancias no relacionadas con la \ncirugía. La blefaroplastia no detiene el proceso de envejecimiento ni produce estiramiento permanente de la \nregión de los párpados. Puede necesitarse cirugía en un futuro u otros tratamientos para mantener los resultados \nde una blefaroplastia.\n 15.  Anestesia.  Tanto la anestesia local como la general implican un riesgo. Existe la posibilidad de complicaciones, \nlesiones e incluso muerte, por cualquier forma de anestesia o sedación quirúrgica.\n 16.  Los pacientes portadores de patologías tiroideas no son candidatos para realizarse esta cirugía excepto que siendo \nvalorados por el endocrinólogo la autorice.\n 17.  LOS FUMADORES TIENEN UN MAYOR RIESGO DE PÉRDIDA CUTÁNEA Y COMPLICACIÓN EN \nLA CICATRIZACIÓN.\n PÓLIZA PARA CIRUGÍAS PLÁSTICAS.\n Las Sociedades Médicas y Científicas así como la Sociedad Colombiana de Cirugía Plástica y la Sociedad Colombiana \nde Anestesiología sugieren a todos los pacientes adquirir una póliza para su Cirugía Plástica, la cual tiene una \ncobertura y condiciones especiales para atender el 99% de las complicaciones post-quirúrgicas que puedan presentarse \nen Cirugías Estéticas. Por favor pregunte en la Unidad Médico Quirúrgica de Cirugía Plástica Ambulatoria por la \npóliza recomendada.\n POSIBILIDAD DE CIRUGÍA ADICIONAL\n Existen diversidad de condiciones además de los riesgos y complicaciones quirúrgicas potenciales que pueden influir \nen el resultado a largo plazo de la cirugía de los párpados. Aunque los riesgos y complicaciones son raros, los riesgos \ncitados están particularmente asociados con la blefaroplastia. Pueden ocurrir otros riesgos y complicaciones, pero son \ntodavía más infrecuentes. La práctica de la Medicina y la Cirugía no es una ciencia exacta, y aunque se esperan buenos \nresultados, no hay garantía explícita o implícita sobre los resultados que pueden obtenerse.\n RESPONSABILIDADES ECONÓMICAS\n El costo de la cirugía resulta de diversos cargos por servicios prestados. El total incluye los honorarios del cirujano, el \ncosto del material quirúrgico, anestesia, servicio de enfermería e instrumentación, entre otros y cargos de hospital o \nclínica, dependiendo de dónde se realice la cirugía. Usted es responsable de pagos adicionales, deducciones y cargos no \ncubiertos. Puede haber costos adicionales si se dan complicaciones derivadas de la cirugía. Los pagos por cirugía \nsecundaria relacionadas con revisión quirúrgica también corren a su cargo.\n ES IMPORTANTE QUE LEA CUIDADOSAMENTE LA INFORMACIÓN ANTERIOR Y HAYAN\n SIDO RESUELTAS TODAS SUS PREGUNTAS ANTES DE FIRMAR EL PRESENTE \nCONSENTIMIENTO\n CONSENTIMIENTO PARA CIRUGÍA/PROCEDIMIENTO O TRATAMIENTO\n 1. Por la presente autorizo al Dr.___________________________________________________________________\n .   \n______________________________________________________ y a los colaboradores que sean seleccionados \npara realizar el siguiente procedimiento o tratamiento: BLEFAROPLASTIA.\n 2.  He leído, comprendido y firmado las páginas adjuntas del presente Consentimiento Informado para \nBlefaroplastia.\n 10. Entiendo que si llegara a presentar eventualmente una complicación como por ejemplo: hemorragia, arritmia \ncardíaca, paro cardiorespiratorio, coágulos en los pulmones, reacciones alérgicas, que ameriten cuidados \nespeciales, asumiré los costos de cuidados intensivos y si se presentase un segundo tiempo quirúrgico por ser este \nun acto derivado de muchos factores, como la mala cicatrización (cicatrización retrasada, queloide, infección, falta \nde retracción de piel, entre otros) asumiré costos básicos de anestesia, derechos de sala y estancia hospitalaria \nprolongada.\n 11. Toda infección será manejada intrahospitalariamente con costos asumidos por el(la) paciente.\n 12. Declaro que no consumo sustancias alucinógenas, adictivas ó psicofármacos.\n 13. He tenido la oportunidad de ver fotografías de casos similares al mio 15. Declaro que en este momento no me encuentro en estado de embarazo.\n 16. Puedo cambiar de opinión en cualquier momento, incluyendo en la sala de operaciones y decidir que no se me \nefectúe el procedimiento que solicito, sin que por ello pierda el derecho a recibir los servicios y la atención medica \nen el futuro. También se me explicó la posibilidad de que la operación pueda ser suspendida en algún momento o \nque no pueda culminar con éxito por algún motivo que así lo implica.\n 17. He tenido la oportunidad de aclarar todas mis dudas y han sido resueltas totalmente.\n 18. Doy fe que estoy de acuerdo con el contenido, lo entiendo claramente y me responsabilizó por el mismo, debido a \nque esta determinación ha sido tomada libre y voluntariamente sin coacción, ni aliciente y para constancia firmo \nesta solicitud.\n 19. Dejo constancia que se me explicó que existen otros métodos temporales que puedo usar para mejorar, accesibles \npara mí como dietas y ejercicios, entre otros.\n 20. Doy fe de no haber omitido ni alterado datos al exponer mi historial y antecedentes clínicos quirúrgicos \nespecialmente los referidos a alergias y enfermedades o riesgos personales.\n 21. Certifico que el presente documento ha sido leído y entendido en su integridad y que he tenido la oportunidad de \nrecibir explicaciones satisfactorias departe de mi médico con respecto a los riesgos por él advertidos y el contenido \nde este consentimiento.\n 22. Cuando el paciente no tenga capacidad legal para otorgar el consentimiento, las manifestaciones de éste \ncontenidas en el presente documento se entienden autorizadas por la persona responsable que lo represente y en \nrelación con el paciente correspondiente, para cuyo efecto lo suscribe.\n DOY EL CONSENTIMIENTO PARA EL TRATAMIENTO O PROCEDIMIENTO, Y LOS PUNTOS \nCITADOS ANTERIORMENTE.\n SE ME HA PREGUNTADO SI QUIERO UNA INFORMACIÓN MÁS DETALLADA, ESTOY \nSATISFECHA(O) CON LA EXPLICACIÓN Y NO NECESITO MÁS INFORMACIÓN.\n FIRME EL SIGUIENTE CONSENTIMIENTO',NULL,1);
/*!40000 ALTER TABLE `coninfpx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contratos`
--

LOCK TABLES `contratos` WRITE;
/*!40000 ALTER TABLE `contratos` DISABLE KEYS */;
/*!40000 ALTER TABLE `contratos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (1,73,'2024-08-25','TOLIMA',1),(2,11,'2024-08-25','CUNDINAMARCA',1),(3,5,'2024-08-25','ANTIOQUIA',1),(4,7,'2024-08-25','ATLANTICO',1),(5,76,'2024-12-06','VALLE',1);
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `desqx`
--

LOCK TABLES `desqx` WRITE;
/*!40000 ALTER TABLE `desqx` DISABLE KEYS */;
INSERT INTO `desqx` VALUES (1,'complicaciones','Descripcion del procedimiento','2024-12-01','hallazgos','09:45:00','06:00:00','material si aplica','muestra de patologia',3,2,1,1),(2,'COMPLICACIONES QUIRURGICAS JULIO','DESCRIPCION DEL PROCEDIMIENTO JULIO','2024-12-19','HALLAZGOS JULIO','07:45:00','06:00:00','MATERIAL JULIO','MUESTRA PATOLOGIA JULIO',3,2,1,2),(3,'COMPILCACIONES QUIRURGICAS SANDRA','DESCRIPCION PROCEDIMIENTO SANDRA','2024-12-19','HALLAZGOS SANDRA','08:00:00','07:00:00','MATERIAL SANDRA','MUESTRA PATOLOGIA SANDRA',3,2,1,3);
/*!40000 ALTER TABLE `desqx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dx`
--

LOCK TABLES `dx` WRITE;
/*!40000 ALTER TABLE `dx` DISABLE KEYS */;
INSERT INTO `dx` VALUES (1,'A09','2024-08-25','DIARREA Y GASTROENTERITIS DE PRESUNTO ORIGEN INFECCIOSO',99,0,'A09X','DIARREA Y GASTROENTERITIS DE PRESUNTO ORIGEN INFECCIOSO','AMBOS',1),(2,'K58','2024-08-25','SINDROME DE  COLON IRRITABLE  CON PREDOMINIO DE DIARREA [IBS-D]',99,0,'K581','SINDROME DEL COLON IRRITABLE','AMBOS',1),(3,'K59','2024-08-25','DIARREA FUNCIONAL',999,0,'K591','OTROS TRASTORNOS FUNCIONALES DEL INTESTINO','AMBOS',1),(4,'D39','2024-08-25','TUMOR DE COMPORTAMIENTO INCIERTO O DESCONOCIDO DE ORGANO GENITAL FEMENINO NO ESPECIFICADO',999,0,'D399','TUMOR DE COMPORTAMIENTO INCIERTO O DESCONOCIDO DE LOS ORGANOS GENITALES FEMENINOS','FEMENINO',1),(5,'N71','2024-08-25','ENFERMEDAD INFLAMATORIA CRONICA DEL UTERO',99,10,'N711','ENFERMEDAD INFLAMATORIA DEL UTERO, EXCEPTO DEL CUELLO UTERINO','FEMENINO',1),(6,'N75','2024-08-25','ABSCESO DE LA GLANDULA DE BARTHOLIN',999,0,'N751','ENFERMEDADES DE LA GLANDULA DE BARTHOLIN','FEMENINO',1),(7,'N42','2024-08-25','CALCULO DE LA PROSTATA',120,20,'N420','OTROS TRASTORNOS DE LA PROSTATA','MASCULINO',1),(8,'D29','2024-08-25','TUMOR BENIGNO DE LOS TESTICULOS',999,0,'D292','TUMOR BENIGNO DE LOS ORGANOS GENITALES MASCULINOS','MASCULINO',1);
/*!40000 ALTER TABLE `dx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dxatehcpac`
--

LOCK TABLES `dxatehcpac` WRITE;
/*!40000 ALTER TABLE `dxatehcpac` DISABLE KEYS */;
INSERT INTO `dxatehcpac` VALUES (1,'2024-08-26',NULL,1,1,1,2,3),(2,'2024-12-17',NULL,3,1,1,2,1),(3,'2024-12-17',NULL,1,1,1,1,1),(4,'2024-12-17',NULL,1,1,1,6,1),(5,'2024-12-19',NULL,1,1,2,2,1),(6,'2024-12-19',NULL,1,1,2,1,1),(7,'2024-12-19',NULL,1,1,2,6,5),(8,'2024-12-19',NULL,1,1,3,2,1),(9,'2024-12-19',NULL,1,1,3,1,1),(10,'2024-12-19',NULL,1,1,3,6,5),(11,'2024-12-22',NULL,1,1,3,1,1),(12,'2024-12-22',NULL,1,1,3,4,1);
/*!40000 ALTER TABLE `dxatehcpac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `eapb`
--

LOCK TABLES `eapb` WRITE;
/*!40000 ALTER TABLE `eapb` DISABLE KEYS */;
INSERT INTO `eapb` VALUES (1,'JULIO','2024-08-25','PAPAYO',999888777,'SANITAS@SANITAS.COM','JULIO','EPS SANITAS',1,2,1),(2,'JULIO','2024-08-25','CENTRO',111222333,'CAFESALUD@CAFESALUD.COM','JULIO','CAFESALUD EPSS',1,2,2),(3,'JULIO','2024-08-25','INTERLAKEN',3335444666,'MAGISTERIO@MAGISTERIO','JULIO','MAGISTERIO',1,2,3);
/*!40000 ALTER TABLE `eapb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `eqqx`
--

LOCK TABLES `eqqx` WRITE;
/*!40000 ALTER TABLE `eqqx` DISABLE KEYS */;
INSERT INTO `eqqx` VALUES (1,'2024-12-17',NULL,1,NULL,1),(2,'2024-12-19',NULL,2,NULL,1),(3,'2024-12-19',NULL,3,NULL,1);
/*!40000 ALTER TABLE `eqqx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `estpac`
--

LOCK TABLES `estpac` WRITE;
/*!40000 ALTER TABLE `estpac` DISABLE KEYS */;
/*!40000 ALTER TABLE `estpac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `eventhcpac`
--

LOCK TABLES `eventhcpac` WRITE;
/*!40000 ALTER TABLE `eventhcpac` DISABLE KEYS */;
INSERT INTO `eventhcpac` VALUES (1,1,'CONSULTA',NULL,'2024-08-26',1,1),(2,1,'PROCEDIMIENTO',NULL,'2024-12-19',1,2),(3,1,'PROCEDIMIENTO',NULL,'2024-12-19',1,3);
/*!40000 ALTER TABLE `eventhcpac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `evoenf`
--

LOCK TABLES `evoenf` WRITE;
/*!40000 ALTER TABLE `evoenf` DISABLE KEYS */;
INSERT INTO `evoenf` VALUES (1,'2024-12-19 07:49:54.773000',NULL,'EVOLUCION DE ENFERMERIA PARA  JULIO',1,2),(2,'2024-12-19 08:04:36.105000',NULL,'EVOLUCION DE ENFERMERIA SANDRA',1,3);
/*!40000 ALTER TABLE `evoenf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `evoevent`
--

LOCK TABLES `evoevent` WRITE;
/*!40000 ALTER TABLE `evoevent` DISABLE KEYS */;
INSERT INTO `evoevent` VALUES (1,'2024-12-17 15:46:45.222000',NULL,'Nota adicional de evolucion',1,1,1),(2,'2024-12-19 07:45:32.957000',NULL,'ESTO ES UNA NOTA ADICIONAL PARA JULIO',1,2,1),(3,'2024-12-19 07:55:12.180000',NULL,'EVOLUCION MEDICA SANDRA',1,3,1),(4,'2024-12-22 08:45:28.958000',NULL,'nota adicional para probar epicrisis',1,3,1),(5,'2024-12-22 08:46:05.451000',NULL,'Esta es una nota aclaratoria para la epicrisis',1,3,4);
/*!40000 ALTER TABLE `evoevent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fabmedins`
--

LOCK TABLES `fabmedins` WRITE;
/*!40000 ALTER TABLE `fabmedins` DISABLE KEYS */;
INSERT INTO `fabmedins` VALUES (1,'2024-12-06',NULL,'Calle 42N #4N-103','Advance Scientific de Colombia',6,1),(2,'2024-12-06',NULL,'Av. de las Américas 57-52','Bayer S.A.',3,1),(3,'2024-12-06',NULL,'Carrera 22 #166-66','Biochem Farmacéutica de Colombia',3,1),(4,'2024-12-06',NULL,'Cra. 7 #156-10, Oficina 3004','INNOFAR S.A.',3,1);
/*!40000 ALTER TABLE `fabmedins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `habcami`
--

LOCK TABLES `habcami` WRITE;
/*!40000 ALTER TABLE `habcami` DISABLE KEYS */;
INSERT INTO `habcami` VALUES (1,'CAMILLA RECUPERACION','2024-08-25','REC1',1,2),(2,'CAMILLA OBSERVACION','2024-08-25','OB1',1,1),(3,'SALA DE QUIROFANO 1','2024-08-25','SQX1',1,3),(4,'HOSPITALIZACION PISO 1 CAMA 1','2024-08-25','HXP1C1',1,4);
/*!40000 ALTER TABLE `habcami` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hcpac`
--

LOCK TABLES `hcpac` WRITE;
/*!40000 ALTER TABLE `hcpac` DISABLE KEYS */;
INSERT INTO `hcpac` VALUES (1,'prueba de historia clinica','na','na','na','na','na','na','2024-08-26',NULL,'prueba de historia clinica',178,'80','60','prueba de historia clinica','prueba de historia clinica',80,'prueba de historia clinica','110/80','37',1,1,NULL),(2,'analisis','alergicos','familiares','farmacologicos','patologicos','quirurgicos','traumaticos','2024-12-17',NULL,'enfermedad actual',179,'60','60','motivo de consulta','objetivo',80,'plan de manejo','110/80','37',1,1,NULL),(3,'ANALISIS JULIO','ALERGICOS JULIO','FAMILIARES JULIO','FARMACOLOGICOS JULIO','PATOLOGICOS JULIO','QUIRURGICOS JULIO','TRAUMATICOS JULIO','2024-12-19',NULL,'ENFERMEDAD ACTUAL JULIO',179,'70','20','MOTIVO DE CONSULTA JULIO','OBJETIVO JULIO',79,'PLAN DE MANEJO JULIO','110/80','37',1,2,NULL),(4,'ANALISIS SANDRA','ALERGICOS SANDRA','FAMILIARES SANDRA','FARMACOLOGICOS SANDRA','PATOLOGICOS SANDRA','QUIRURGICOS SANDRA','TRAUMATICOS SANDRA','2024-12-19',NULL,'ENFERMEDAD ACTUAL SANDRA',160,'60','16','MOTIVO DE CONSULTA SANDRA','OBJETO SANDRA',60,'PLAN DE MANEJO SANDRA','110/80','37',1,3,NULL);
/*!40000 ALTER TABLE `hcpac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `medins`
--

LOCK TABLES `medins` WRITE;
/*!40000 ALTER TABLE `medins` DISABLE KEYS */;
INSERT INTO `medins` VALUES (1,'A04AA01','8MG/4ML','20095751-03','2024-12-07',NULL,'ONDASETRON','2016M-0017422',1,1,2,2,1),(2,'B05XA03','500 ml','29523','2024-12-07',NULL,'CLORURO DE SODIO AL 0.9%','2020M-001117-R4',1,2,4,5,1),(3,'B05BA03','100 ml','20043643','2025-01-15',NULL,'DEXTROSAAL 5% EN AGUA INYECTABLE BAXTER','2020M-0013360-R1',1,2,4,5,1);
/*!40000 ALTER TABLE `medins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ordmedins`
--

LOCK TABLES `ordmedins` WRITE;
/*!40000 ALTER TABLE `ordmedins` DISABLE KEYS */;
INSERT INTO `ordmedins` VALUES (1,8,'2025-01-15 13:39:41.388000',NULL,'100 cc','cada 12 horas',1,1,2,4,1),(2,2,'2025-01-15 13:39:41.388000',NULL,'4 mg','cada 12 horas',1,1,1,2,4),(3,8,'2025-01-15 13:40:14.702000',NULL,'2 mg','cada 6 horas',1,2,1,2,1),(4,8,'2025-01-15 13:42:07.213000',NULL,'2 mg','cada 12 horas',1,3,1,2,1),(5,6,'2025-01-15 13:42:07.213000',NULL,'150 cc','cada 6 horas',1,3,3,4,1),(6,4,'2025-01-15 13:42:07.213000',NULL,'150 cc','cada 4 horas',1,3,2,4,1);
/*!40000 ALTER TABLE `ordmedins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ordprocexam`
--

LOCK TABLES `ordprocexam` WRITE;
/*!40000 ALTER TABLE `ordprocexam` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordprocexam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pac`
--

LOCK TABLES `pac` WRITE;
/*!40000 ALTER TABLE `pac` DISABLE KEYS */;
INSERT INTO `pac` VALUES (1,'PEPITO','3163720541','3163720541','3163720541','CLINICA','SINDATOS@SINDATOS.COM','CASADO','2024-08-26',NULL,'1973-09-12','93394474','NARANJO ','MIGUEL','PEPITO','GUTIERREZ','ALBIN','MASCULINO',1,1,1,1,1),(2,'SANDRA','3133692213','3108610210','3133692213','Mz D Casa 10 Brisas del pedregal','julio_salcedo2@hotmail.com','CASADO','2024-12-19',NULL,'1979-11-03','93414046','SALCEDO','JULIO','SANDRA','PELAEZ','ERNESTO','MASCULINO',1,1,1,1,1),(3,'SANDRA','3133692213','3108610210','3133692213','Mz D Casa 10 Brisas del pedregal','julio_salcedo2@hotmail.com','CASADO','2024-12-19',NULL,'1970-03-29','65748200','DIAZ','SANDRA','SANDRA','GUTIERREZ','PATRICIA','FEMENINO',1,1,1,1,1);
/*!40000 ALTER TABLE `pac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'2024-09-03','CREACION DE CAMAS','CRECAM',1),(2,'2024-09-03','EDICION DE CAMAS','EDICAM',1),(3,'2024-09-03','ELIMINACION DE CAMAS','DROCAM',1),(4,'2024-09-03','CREACION DE DESCRIPCION QUIRURGICA','CREDEXQX',1),(5,'2024-09-03','EDICION DESCRIPCION QUIRURGICA','EDIDESQX',1),(6,'2024-09-03','ELIMINACION DESCRIPCION QUIRURGICA','DRODESQX',1),(7,'2024-09-03','CREACION DE DIAGNOSTICOS ATENCION','CREDXATE',1),(8,'2024-09-03','EDICION DIAGNOSTICOS ATENCION','EDIDXATE',1),(9,'2024-09-03','ELIMINACION DIAGNOSTICOS ATENCION','DRODXATE',1),(10,'2024-09-03','CREACION DE HISTORIA CLINICA','CREHCPAC',1),(11,'2024-09-03','EDICION HISTORIA CLINICA','EDIHCPAC',1),(12,'2024-09-03','ELIMINACION DE HISTORIA CLINICA','DROHCPAC',1),(13,'2024-09-04','CREACION DE PACIENTES','CREPAC',1),(14,'2024-09-04','EDICION DE PACIENTES','EDIPAC',1),(15,'2024-09-04','ELIMINAR PACIENTES','DROPAC',1),(16,'2024-09-04','CREACION DE EVOLUCION DE ENFERMERIA','CREEVOENF',1),(17,'2024-09-04','EDICION DE EVOLUCIONES DE ENFERMERIA','EDIEVOENF',1),(18,'2024-09-04','ELIMINACION DE EVOLUCIONES DE ENFERMERIA','DROEVOENF',1),(19,'2024-09-04','CREACION DE REGISTRO DE SIGNOS VITALES','CREREGSIGVIT',1),(20,'2024-09-04','EDICION DE REGISTRO DE SIGNOS VITALES','EDIREGSIGVIT',1),(21,'2024-09-04','ELIMINACION DE REGISTRO DE SIGNOS VITALES','DROREGSIGVIT',1);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `permrol`
--

LOCK TABLES `permrol` WRITE;
/*!40000 ALTER TABLE `permrol` DISABLE KEYS */;
INSERT INTO `permrol` VALUES (1,'2024-09-03',1,1,1),(2,'2024-09-04',1,2,1),(3,'2024-09-04',1,13,1),(4,'2024-09-04',1,14,1),(5,'2024-09-04',1,19,2),(6,'2024-09-04',1,20,2);
/*!40000 ALTER TABLE `permrol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pmedins`
--

LOCK TABLES `pmedins` WRITE;
/*!40000 ALTER TABLE `pmedins` DISABLE KEYS */;
INSERT INTO `pmedins` VALUES (1,'2024-12-04',NULL,'CAPSULAS','CAP',1),(2,'2024-12-04',NULL,'AMPOLLAS','AMP',1),(3,'2024-12-04',NULL,'TABLETAS','TAB',1),(4,'2024-12-07',NULL,'SOLUCION INYECTABLE','SOL',1);
/*!40000 ALTER TABLE `pmedins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `prestserv`
--

LOCK TABLES `prestserv` WRITE;
/*!40000 ALTER TABLE `prestserv` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestserv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `procexam`
--

LOCK TABLES `procexam` WRITE;
/*!40000 ALTER TABLE `procexam` DISABLE KEYS */;
INSERT INTO `procexam` VALUES (1,'868701','2024-08-25',NULL,'PLASTIA DE PECTORALES DE AUMENTO CON DISPOSITIVO','AMBOS',1,1),(2,'868702','2024-08-25',NULL,'PLASTIA DE PECTORALES DE AUMENTO CON TEJIDO AUTÓLOGO','AMBOS',1,1),(3,'868703','2024-08-25',NULL,'GLUTEOPLASTIA DE AUMENTO CON DISPOSITIVO','AMBOS',1,1),(4,'868704','2024-08-25',NULL,'GLUTEOPLASTIA DE AUMENTO CON TEJIDO AUTÓLOGO','AMBOS',1,1),(5,'218403','2024-08-25',NULL,'SEPTORRINOPLASTIA PRIMARIA VÍA TRANSNASAL','AMBOS',1,1),(6,'218404','2024-08-25',NULL,'SEPTORRINOPLASTIA PRIMARIA VÍA ABIERTA','AMBOS',1,1),(7,'735301','2024-08-25',NULL,'ASISTENCIA DEL PARTO CON O SIN EPISIORRAFIA O PERINEORRAFIA','FEMENINO',1,1),(8,'740001','2024-08-25',NULL,'CESÁREA SEGMENTARIA TRANSPERITONEAL','AMBOS',1,1),(9,'577307','2024-08-25',NULL,'ESCISIÓN O REMOCIÓN DE VEJIGA, PRÓSTATA, VESÍCULAS SEMINALES Y TEJIDO GRASO [CISTOPROSTATECTOMÍA] VÍA ABIERTA','MASCULINO',1,1),(10,'637300','2024-08-25',NULL,'VASECTOMÍA SOD','MASCULINO',1,1);
/*!40000 ALTER TABLE `procexam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `procqx`
--

LOCK TABLES `procqx` WRITE;
/*!40000 ALTER TABLE `procqx` DISABLE KEYS */;
INSERT INTO `procqx` VALUES (1,1,NULL,5),(2,2,NULL,5),(3,3,NULL,5);
/*!40000 ALTER TABLE `procqx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `regest`
--

LOCK TABLES `regest` WRITE;
/*!40000 ALTER TABLE `regest` DISABLE KEYS */;
/*!40000 ALTER TABLE `regest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `regmedins`
--

LOCK TABLES `regmedins` WRITE;
/*!40000 ALTER TABLE `regmedins` DISABLE KEYS */;
INSERT INTO `regmedins` VALUES (1,'2024-12-01','06:30:00','aplica si hay vomito','4mg',1,1,1,2),(2,'2024-12-19','09:00:00','SI PRESENTA EMESIS','4 MG',1,2,1,2),(3,'2024-12-19','08:00:00','','4 MG',1,3,1,2),(4,'2024-12-19','08:00:00','COLOCAR BOLO DE 500 A CHORRO','500 ML',1,3,2,2);
/*!40000 ALTER TABLE `regmedins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `repleg`
--

LOCK TABLES `repleg` WRITE;
/*!40000 ALTER TABLE `repleg` DISABLE KEYS */;
/*!40000 ALTER TABLE `repleg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'2024-09-03','AUXILIAR ADMINISTRATIVO','AUXADM',1),(2,'2024-09-03','AUXILIAR DE ENFERMERIA','AUXENF',1),(3,'2024-09-03','MEDICO ESPECIALISTA','MEDESP',1),(4,'2024-09-03','ENFERMERA JEFE','ENFSUP',1),(5,'2024-09-03','AUDITORA DE CALIDAD','AUDCAL',1);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sigvit`
--

LOCK TABLES `sigvit` WRITE;
/*!40000 ALTER TABLE `sigvit` DISABLE KEYS */;
INSERT INTO `sigvit` VALUES (1,NULL,'2024-12-01',90,90,NULL,'07:15:00','98','150/100','37',1,1,2),(2,NULL,'2024-12-01',80,60,NULL,'07:00:00','98','110/80','37',1,1,2),(3,NULL,'2024-12-19',85,22,NULL,'06:15:00','95','120/100','37',1,2,1),(4,NULL,'2024-12-19',78,20,NULL,'06:00:00','98','110/80','37',1,2,2),(5,NULL,'2024-12-19',50,20,NULL,'08:00:00','98','110/80','37',1,3,2),(6,NULL,'2024-12-19',55,20,NULL,'08:20:00','98','110/90','37',1,3,2);
/*!40000 ALTER TABLE `sigvit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'2024-08-25','CREADO','CRE'),(2,'2024-08-25','ACTIVO','ACT'),(3,'2024-08-25','EDITADO','EDI'),(4,'2024-08-25','INACTIVO','INA');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `template`
--

LOCK TABLES `template` WRITE;
/*!40000 ALTER TABLE `template` DISABLE KEYS */;
/*!40000 ALTER TABLE `template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `thx`
--

LOCK TABLES `thx` WRITE;
/*!40000 ALTER TABLE `thx` DISABLE KEYS */;
INSERT INTO `thx` VALUES (1,'2024-08-27',NULL,'HERIDA LIMPIA','HL',1),(2,'2024-08-27',NULL,'HERIDA LIMPIA CONTAMINADA','HLC',1),(3,'2024-08-27',NULL,'HERIDA CONTAMINADA','HC',1),(4,'2024-08-27',NULL,'HERIDA SUCIA','HS',1);
/*!40000 ALTER TABLE `thx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipac`
--

LOCK TABLES `tipac` WRITE;
/*!40000 ALTER TABLE `tipac` DISABLE KEYS */;
INSERT INTO `tipac` VALUES (1,'2024-08-25','COTIZANTE','COT',1),(2,'2024-08-25','BENEFICIARIO','BEN',1),(3,'2024-08-25','NO AFILIADO','NA',1);
/*!40000 ALTER TABLE `tipac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipanest`
--

LOCK TABLES `tipanest` WRITE;
/*!40000 ALTER TABLE `tipanest` DISABLE KEYS */;
INSERT INTO `tipanest` VALUES (1,'21:28:59',NULL,'ANESTESIA LOCAL','AL',1),(2,'21:29:15',NULL,'ANESTESIA REGIONAL','AR',1),(3,'21:29:24',NULL,'ANESTESIA GENERAL','AG',1);
/*!40000 ALTER TABLE `tipanest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipdoc`
--

LOCK TABLES `tipdoc` WRITE;
/*!40000 ALTER TABLE `tipdoc` DISABLE KEYS */;
INSERT INTO `tipdoc` VALUES (1,'2024-08-25','CEDULA DE CIUDADANIA','CC'),(2,'2024-08-25','NUMERO IDENTIFICACION TRIBUTARIO','NIT'),(3,'2024-08-25','REGISTRO CIVIL','RC'),(4,'2024-08-25','TARJETA DE IDENTIDAD','TI'),(5,'2024-08-25','PERMISO DE PERMANENCIA TEMPORAL','PPT'),(6,'2024-08-25','CEDULA DE EXTRANJERIA','CE'),(7,'2024-08-25','PASAPORTE','PA'),(8,'2024-08-25','PERMISO ESPECIAL DE PERMANENCIA','PEP');
/*!40000 ALTER TABLE `tipdoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipeapb`
--

LOCK TABLES `tipeapb` WRITE;
/*!40000 ALTER TABLE `tipeapb` DISABLE KEYS */;
INSERT INTO `tipeapb` VALUES (1,'2024-08-25','CONTRIBUTIVO','C',1),(2,'2024-08-25','SUBSIDIADOS','S',1),(3,'2024-08-25','REGIMEN ESPECIAL','RE',1),(4,'2024-08-25','PARTICULAR','P',1);
/*!40000 ALTER TABLE `tipeapb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tproc`
--

LOCK TABLES `tproc` WRITE;
/*!40000 ALTER TABLE `tproc` DISABLE KEYS */;
INSERT INTO `tproc` VALUES (1,'2024-08-25',NULL,'QUIRURGICOS','QX',1),(2,'2024-08-25',NULL,'LABORATORIOS','LAB',1),(3,'2024-08-25',NULL,'ECOGRAFIAS','ECO',1),(4,'2024-08-25',NULL,'RAYOS X','RX',1),(5,'2024-08-25',NULL,'PROCEDIMIENTOS NO INVASIVOS','PXNI',1);
/*!40000 ALTER TABLE `tproc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tymedins`
--

LOCK TABLES `tymedins` WRITE;
/*!40000 ALTER TABLE `tymedins` DISABLE KEYS */;
INSERT INTO `tymedins` VALUES (1,'2024-12-04',NULL,'ANTIBIOTICO','MEDANT',1),(2,'2024-12-04',NULL,'ANALGESICO','MEDANAL',1),(3,'2024-12-04',NULL,'PROTESIS','INSPROT',1),(4,'2024-12-04',NULL,'ORTESIS','INSORT',1),(5,'2024-12-07',NULL,'SOLUCION ELECTROLITICA','SOLH',1);
/*!40000 ALTER TABLE `tymedins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tynote`
--

LOCK TABLES `tynote` WRITE;
/*!40000 ALTER TABLE `tynote` DISABLE KEYS */;
INSERT INTO `tynote` VALUES (1,'2024-08-25','NOTA ADICIONAL','NA',1),(2,'2024-08-25','EVOLUCION MEDICA','EM',1),(4,'2024-08-25','NOTA ACLARATORIA','NAC',1),(5,'2024-08-25','NOTA DE ENFERMERIA','NE',1),(6,'2024-10-13','DESCRIPCION QUIRURGICA','NDXQX',1);
/*!40000 ALTER TABLE `tynote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `typdx`
--

LOCK TABLES `typdx` WRITE;
/*!40000 ALTER TABLE `typdx` DISABLE KEYS */;
INSERT INTO `typdx` VALUES (1,NULL,'DIAGNOSTICO PRINCIPAL','DXP',1),(2,NULL,'DIAGNOSTICO SECUNDARIO','DXS',1),(3,NULL,'DIAGNOSTICO EN ESTUDIO','DXE',1),(4,NULL,'DIAGNOSTICO CONFIRMADO','DXC',1),(5,NULL,'DIAGNOSTICO PREQUIRURGICO','DXPRQX',1),(6,NULL,'DIAGNOSTICO POSTQUIRURGICO','DXPQX',1);
/*!40000 ALTER TABLE `typdx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tytemp`
--

LOCK TABLES `tytemp` WRITE;
/*!40000 ALTER TABLE `tytemp` DISABLE KEYS */;
/*!40000 ALTER TABLE `tytemp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ubicaciones`
--

LOCK TABLES `ubicaciones` WRITE;
/*!40000 ALTER TABLE `ubicaciones` DISABLE KEYS */;
INSERT INTO `ubicaciones` VALUES (1,'2024-08-25','OBSERVACION','OBS',1),(2,'2024-08-25','RECUPERACION','REC',1),(3,'2024-08-25','SALA QUIROFANO','SQX',1),(4,'2024-08-25','HOSPITALIZACION PISO 1','HXP1',1);
/*!40000 ALTER TABLE `ubicaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `unimed`
--

LOCK TABLES `unimed` WRITE;
/*!40000 ALTER TABLE `unimed` DISABLE KEYS */;
INSERT INTO `unimed` VALUES (1,'2024-12-05',NULL,'MILITROS','ML',1),(2,'2024-12-05',NULL,'GOTAS','GOT',1),(3,'2024-12-05',NULL,'CENTIMETROS CUBICOS','CC',1),(4,'2024-12-05',NULL,'KILOGRAMOS','KG',1);
/*!40000 ALTER TABLE `unimed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2024-09-05',93414046,'julio_salcedo2@hotmail.com',NULL,'JULIO ERNESTO','SALCEDO PELAEZ','1234','jsalcedo',5,1,1),(2,'2024-09-05',65748200,'ssandrapatridiaz@hotmail.com',NULL,'SANDRA PATRICIA','DIAZ GUTIERREZ','1234','sdiaz',6,1,1),(3,'2024-09-05',123123123,'SINDATOS@SINDATOS.COM',NULL,'pepito','perez','1234','pperez',4,1,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `viadm`
--

LOCK TABLES `viadm` WRITE;
/*!40000 ALTER TABLE `viadm` DISABLE KEYS */;
INSERT INTO `viadm` VALUES (1,'2024-12-08',NULL,'VIA ORAL','VO',1),(2,'2024-12-08',NULL,'VIA INTRAVENOSA','VIV',1),(3,'2024-12-08',NULL,'VIA SUBLINGUAL','VS',1),(4,'2024-12-08',NULL,'VIA SUBCUTANEA','VSC',1),(5,'2024-12-08',NULL,'VIA TRANSDERMICA','VTD',1),(6,'2024-12-08',NULL,'VIA INTRAMUSCULAR','VIM',1);
/*!40000 ALTER TABLE `viadm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'plataformasalud'
--

--
-- Dumping routines for database 'plataformasalud'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-01 17:03:48

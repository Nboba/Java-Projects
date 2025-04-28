package util;

public enum ConsoleText {
    ESPACIO("""
                    ********************************************************************************************************************************************
                                             UTILIZAR GUION BAJO PARA REPRESENTAR LA SEPARACION ENTRE PALABRAS Al INGRESAR SU ENTRADA
                    ********************************************************************************************************************************************
               """),
    INITINSTRUCTION(
                """
                    ------------------------------------------------------------------------------------------------------------------------------------------
                        Las instrucciones se componen en una palabra clave compuesta de puras mayusculas, luego se agrega las entradas requeridas en un  orden pre-
                        establecido, pueden haber desde cero a 3 campos requeridos, estas pueden ser una palabra o oracion, para separar entre campo de ingreso 
                        utilizar el caracter slash /.
                    ------------------------------------------------------------------------------------------------------------------------------------------
                """),
    INSTRUCCIONES("""
                         Existen 5 instrucciones, debe mantener el orden de los campos ingresados:
                            -ADD /(nombre de la tarea)/(descripcion): Agrega una tarea.
                            -UPDATE /(identificador)/(name-status-desc)/(descripcion): Modifica una tarea.
                            -DELETE /(identificador): Elimina una tarea.
                            -SHOWALL: Muestra todas las tareas creadas.
                            -SHOW /(estado de la tarea): Muestra las tareas de manera condicional dependiendo del estado.
                            -IDS: Muestra los identificadores de las tareas con su nombre y descripcion.
                            -IDFIL: Muestra lo mismo que IDS, pero solo de las tareas que se filtraron al haber aplicado SHOW antes.
            """),
    ADD(
        """
                        ADD name desc   :  Instruccion para agregar una tarea con un TEMA y DESCRIPCION; Se debe agregar ADD luego un espacio y escribir 
                                            el nombre y por ultimo la DESCRIPCION.
           """),
    UPDATE(
        """                        
                        UPDATE id tipo text :  Instruccion para MODIFICAR el TEMA, DESCRIPCION o estado; Se debe agregar UPDATE luego un espacio, la ID de la tarea, 
                                               elegir que es lo que se va a cambiar teniendo las siguientes opciones -> [name-status-desc], habiendo el tipo, escribir 
                                               el texto tras agregar un espacio.
                                               En caso de elegir status, ocupar una letra T:TODO, D:DONE , A:ABANDONED y I:IN:PROGRESS.
           """),
    DELETE(
        """
                        DELETE id         : Instruccion para ELIMINAR una TAREA; Se debe agregar DELETE LUEGO un espacio y escribir la id de la tarea a eliminar.
           """),
    SHOWALL(
        """
                        SHOW              : Instruccion para MOSTRAR todas las TAREAS creadas;Se debe agregar SHOWALL.
           """),
    SHOW("""
                        SHOW status       : Instruccion para MOSTRAR todas las tareas que tengan el ESTADO escogido 
            """),
    INCORRECTA("""
                        ERROR AL INGRESAR INSTRUCCION.
            """)
    ;

    String value;

    ConsoleText(String s) {
        this.value=s;
    }

    public String getValue() {
        return value;
    }
    public void printValue(){
        System.out.println(value);
    }
}

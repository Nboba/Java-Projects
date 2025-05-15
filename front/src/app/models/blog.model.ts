export interface Blog extends Common {
    title:String, 
}

export interface Common{
    id:number,
    fechaCreacion:Date,
    fechaEdicion:Date
}